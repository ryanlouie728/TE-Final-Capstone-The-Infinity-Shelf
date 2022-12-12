<template>
  <div class="comic-list">
    <div v-on:click.prevent="addComicEvent()" class="comic comic-list-action-card" id="add-comic-card" v-if="showAdd">
        <h5>+</h5>
        <p>Add New Comic</p>
    </div> 
    <div v-on:click.prevent="toggleRemove()" class="comic comic-list-action-card" id="remove-comic-card" v-if="showRemove" :class="{ selected: removing}">
        <h5>-</h5>
        <p>Remove Comic</p>
    </div>
    <div v-on:click.prevent="submitSelect()" class="comic comic-list-action-card" id="remove-comic-card" v-if="(showRemove && removing)">
        <p>Submit</p>
    </div>
    <div
      class="comic"
      v-for="comic in this.comics"
      v-bind:key="(comic.id + '-' + comic.collectionId)"
      v-on:click.prevent="clicked(comic)"
      v-on:mousedown="mouseDown(comic)"
      :class="{ selected: selected.includes(comic), 'only-thumbnail': onlyThumbnail}"
    >
      <img
        class="thumbnail"
        v-if="comic.thumbnailUrl"
        v-bind:src="comic.thumbnailUrl"
      />
      <div v-if="!onlyThumbnail" class="comic-text">
        <h3 class="comic-title">{{ comic.title }}</h3>
        <!-- <p class="comic-description">{{comic.description}}</p> -->
      </div>
    </div>
    
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService';
export default {
    props: ['comics', 'drag', 'showAdd', 'showRemove', 'base', 'onlyThumbnail'],
    name: 'comic-list',
    data() {
        return {
            selected: [],
            removing: false,
            clickedId: ''
        }
    },
    methods: {
        clicked(clicked) {
            if (!this.removing) {
                this.clickedId = clicked.id;
                this.$emit('clicked')
            } else {
                let comic = this.comics.find(comic => {
                    return (comic.id == clicked.id) && (comic.collectionId == clicked.collectionId);
                })
                this.selected.push(comic)
            }
            
        },
        mouseDown(clicked) {
            if (this.drag) {
                window.event.preventDefault();
            }
            this.clickedId = clicked.id;
            this.$emit('down');
        },
        addDragEvents() {
            if (this.drag) {
                let comics = document.querySelectorAll(".comic");
                for (let comic of comics) {
                    dragElement(comic, this.resetComicsEvent);
                }
            }
        },
        toggleRemove() {
            if (this.removing) {
                this.removing = false;
                this.selected = [];
            } else {
                this.removing = true;
            }
        },
        submitSelect() {
            if (this.removing) {
                this.removing = false;
                for (let comic of this.selected) {
                    CollectionService.removeComicFromCollection(this.base.collectionId, comic.id)
                    .then(() => {
                      this.resetComicsEvent();
                    })
                }
            }
        },
        addComicEvent() {
            this.$emit('addComic');
        },
        resetComicsEvent() {
            this.$emit('resetComics')
        }
    },
    created() {
        
    }
}

function dragElement(elmnt, reset) {
    
  var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
  elmnt.onmousedown = dragMouseDown;

  function makeElementFixed() {
    
    let left = elmnt.offsetLeft;
    let top = elmnt.offsetTop;
    elmnt.style.position = 'absolute';
    elmnt.style.zIndex = -1;
    elmnt.style.top = top + 'px';
    elmnt.style.left = left + 'px';
  }



  function dragMouseDown(e) {
    if (!window.event.ctrlKey) {
        return;
    }
    makeElementFixed();
    e = e || window.event;
    e.preventDefault();
    // get the mouse cursor position at startup:
    pos3 = e.clientX;
    pos4 = e.clientY;
    document.onmouseup = closeDragElement;
    // call a function whenever the cursor moves:
    document.onmousemove = elementDrag;
  }

  function elementDrag(e) {
    e = e || window.event;
    e.preventDefault();
    // calculate the new cursor position:
    pos1 = pos3 - e.clientX;
    pos2 = pos4 - e.clientY;
    pos3 = e.clientX;
    pos4 = e.clientY;
    // set the element's new position:
    elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
    elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
  }

  function closeDragElement() {
    elmnt.style.display = 'none';
    document.onmouseup = null;
    document.onmousemove = null;
    reset();
  }
}
</script>

<style>

.comic-list {
  display: flex;
  flex-direction: row;
  width: 100%;
  flex-wrap: wrap;
  align-content: flex-start;
  user-select: none;
}

.comic {
  cursor: pointer;
  box-sizing: border-box;
  width: 120px;
  height: 215px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  margin-right: 2px;
  margin-bottom: 2px;
  padding: 5px 0px;
  border-radius: 9px;
}

.comic:hover {
  background-color: var(--medium-accent);
  color: var(--white);
}

.comic-title {
  font-size: 0.5rem;
}

.comic-text {
  text-align: center;
}

.thumbnail {
  height: 80%;
  width: 92%;
}

.comic-list-holder {
  overflow: auto;
  width: 100%;
  
}

.comic-list-action-card {
  box-sizing: border-box;
  border: solid 2px var(--dark-accent);
  justify-content: center;
  border-radius: 9px;
  background-color: var(--white);
  
}
.comic-list-action-card:hover {
  background-color: var(--medium-accent);
}

.comic-list-action-card > h5 {
    margin: 0px;
    font-size: 5rem;
    line-height: 50%;
}

.comic-list-action-card > p {
    font-size: 1.5rem;
    margin: 0px;
    text-align: center;
}

.only-thumbnail {
  background-color: transparent;
  border: none;
  height: fit-content;
}

.selected {
    background-color: var(--medium-accent);
}

.selected:hover {
    background-color: var(--medium-accent);
}




</style>