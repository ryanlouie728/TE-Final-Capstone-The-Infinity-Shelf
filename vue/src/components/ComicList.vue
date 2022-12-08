<template>
  <div class="comic-list">
    <div
      class="comic"
      v-for="comic in this.comics"
      v-bind:key="comic.id"
      v-on:click.prevent="clicked(comic.id)"
      v-on:mousedown="mouseDown(comic.id)"
    >
      <img
        class="thumbnail"
        v-if="comic.thumbnailUrl"
        v-bind:src="comic.thumbnailUrl"
      />
      <div class="comic-text">
        <h3 class="comic-title">{{ comic.title }}</h3>
        <!-- <p class="comic-description">{{comic.description}}</p> -->
      </div>
    </div>
    <!-- <div class="comic" v-if="showAdd">Add New Comic</div> 
    work on this to get blank card to add new-->
  </div>
</template>

<script>
export default {
    props: ['comics', 'drag', 'showAdd'],
    name: 'comic-list',
    data() {
        return {
            clickedId: '',
            return: true
        }
    },
    methods: {
        clicked(id) {
            this.clickedId = id;
            this.$emit('clicked')
        },
        mouseDown(id) {
            if (this.drag) {
                window.event.preventDefault();
            }
            this.clickedId = id;
            this.$emit('down');
        },
        addDragEvents() {
            if (this.drag) {
                let comics = document.querySelectorAll(".comic");
                for (let comic of comics) {
                    dragElement(comic, this.return);
                }
            }
        }
    },
    created() {
        
    }
}

function dragElement(elmnt, shouldReturn) {
    
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
    if (shouldReturn) {
        elmnt.style.position = 'static';
        elmnt.style.zIndex = '';
        elmnt.style.top = '';
        elmnt.style.left = '';
    } else {
        elmnt.style.display = 'none';
    }
    /* stop moving when mouse button is released:*/
    document.onmouseup = null;
    document.onmousemove = null;
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
}

.comic {
  cursor: pointer;
  box-sizing: border-box;
  border: solid 2px var(--dark-accent);
  width: 120px;
  height: 215px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  margin-right: 2px;
  margin-bottom: 2px;
  padding: 5px 0px;
}

.comic:hover {
  background-color: var(--medium-accent);
}

.comic-title {
  font-size: 0.5rem;
}

.comic-text {
  text-align: center;
}

.thumbnail {
  height: auto;
  width: 92%;
}

.comic-list-holder {
  overflow: auto;
  width: 100%;
}



</style>