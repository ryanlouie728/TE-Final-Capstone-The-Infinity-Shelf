<template>
  <div class="comic-details">
    <div id="main-panel">
      <div id="cover-holder">
        <img id="cover" v-bind:src="this.comic.thumbnailUrl"/>
        <h1 id="comic-title">{{ this.comic.title }}</h1>
      </div>
      
      <div id="comic-text">
        <p> {{ this.comic.description }} </p>
      </div>
    </div>
    <div id="sidebar">
      <div id="counts">
        <div class="count-list">
          <h5 class="count-title">Characters</h5>
          <div class="count-row" v-for="character in this.comic.characters" v-bind:key="character.name">
            <h6 class="count-name">{{character.name}}</h6>
          </div>
        </div>
        <div class="count-list">
          <h5 class="count-title">Creators</h5>
          <div class="count-row" v-for="creator in this.comic.creators" v-bind:key="creator.name">
            <h6 class="count-role">{{creator.role.toUpperCase()}}</h6>
            <h6 class="count-name">{{creator.name}}</h6>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ComicService from '../services/ComicService';

export default {
    name: 'comic-details',
    data() {
      return {
        comic: {
          creators: [],
          characters: []
        }
      }
    },
    methods: {
      getComic() {
        ComicService.getComicById(this.$route.params.id)
        .then(response => {
          if (response.status == 200) {
            this.comic = response.data;
            
          }
        })
      }
    },
    created() {
      this.getComic();
    }
}
</script>

<style>
body {
  max-height: 100vh;
}

#app {
  max-height: 100vh;
}


.comic-details {
  display: flex;
  flex-direction: row;
}
#main-panel {
  width: 100%;
  display: flex;
}

#cover-holder {
  width: 50%;
  max-width: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}

#cover {
  width: 75%;
  height: auto;
}

#comic-title {
  margin: 10px;
  font-size: 1.75rem;
}

#comic-text {
  flex-grow: 1;
}

.count-role {
  margin: 0px;
  margin-top: 10px;
}

</style>