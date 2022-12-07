<template>
  <div class="add-comic" id="add-comic">
    <div id="drag-handle">Add Comic to Collection</div>
    <div id="search-bar">
      <input id="search-bar-input" v-model="title" type="text" @keyup.enter="search()" ref="refText">
      <button id="search" v-on:click.prevent="search()">Search</button>
      <button v-on:click.prevent="cancel()">Cancel</button>
    </div>
    <div id="comic-list-holder">
      <comic-list ref="comics" @clicked="comicClicked()" v-bind:comics="this.comics" />
    </div>
  </div>
</template>

<script>
import ComicList from './ComicList.vue'
import ComicService from '../services/ComicService';
import CollectionService from '../services/CollectionService';

export default {
  components: { ComicList },
  data() {
    return {
      title: '',
      comics: []
    }
  },
  methods: {
    search() {
      ComicService.listSimpleByTitle(this.title)
      .then(response => {
        if (response.status == 200) {
          this.comics = response.data;
        }
      })
    },
    comicClicked() {
      CollectionService.addComicToCollection(this.$route.params.id, this.$refs.comics.clickedId)
      .then(response => {
        if (response.status == 200) {
          this.title = '';
          this.$emit('added');
        }
      })
    },
    cancel() {
      this.$emit('added')
    },
    focusSearch() {
      document.getElementById("search-bar-input").focus();
    }
    
  },
  mounted() {
    dragElement(document.getElementById("add-comic"));
    this.focusSearch();
  },
}
  
function dragElement(elmnt) {
  var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
  if (document.getElementById("drag-handle")) {
    /* if present, the header is where you move the DIV from:*/
    document.getElementById("drag-handle").onmousedown = dragMouseDown;
  } else {
    /* otherwise, move the DIV from anywhere inside the DIV:*/
    elmnt.onmousedown = dragMouseDown;
  }

  function dragMouseDown(e) {
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
    /* stop moving when mouse button is released:*/
    document.onmouseup = null;
    document.onmousemove = null;
  }
}

</script>

<style>
.add-comic {
  width: 505px;
  height: 500px;
  background-color: var(--light-accent);
  position: fixed;
  left: calc(50vw - 100px);
  top: calc(50vh - 100px);
  display: flex;
  flex-direction: column;
  align-items: left;
  border: solid 2px black;
}
#search-bar {
  width: 100%;
  display: flex;
  padding: 2px;
  box-sizing: border-box;
}

#search-bar > input {
  flex-grow: 1;
}

#drag-handle {
  background-color: var(--dark-accent);
  color: var(--white);
  width: 100%;
  height: 20px;
  cursor: move;
  text-align: center;
}

#comic-list-holder {
  overflow: auto;
}

.comic:hover {
  background-color: var(--main-background);
}
</style>