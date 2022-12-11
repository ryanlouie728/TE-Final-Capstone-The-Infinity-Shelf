<template>
  <div class="comic-details">
    <div id="main-panel">
      <div id="cover-holder">
        <img id="cover" v-bind:src="this.comic.thumbnailUrl" />
        <h1 id="comic-title">{{ this.comic.title }}</h1>
      </div>
      <div id="comic-detail">
        <h2 class="desc-title">Comic Description</h2>
        <p>{{ this.comic.description }}</p>
      </div>
      <div id="character-text">
        <h2 class="character-title">Characters</h2>
        <div
          class="count-row"
          v-for="character in this.comic.characters.slice(0, 5)"
          v-bind:key="character.name"
        >
          <h4 class="count-name">{{ character.name }}</h4>
          <div v-if="fieldEmpty" id="empty-message">
            <p>No Information Found</p>
          </div>
        </div>
      </div>
      <div id="creator-text">
        <h2 class="creator-title">Creators</h2>
        <div
          class="count-row"
          v-for="creator in this.comic.creators.slice(0, 5)"
          v-bind:key="creator.name"
        >
          <h3 class="count-role">{{ creator.role.toUpperCase() }}:</h3>
          <h4 class="count-name">{{ creator.name }}</h4>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ComicService from "../services/ComicService";

export default {
  name: "comic-details",
  data() {
    return {
      comic: {
        creators: [],
        characters: [],
        fieldEmpty: false,
      },
    };
  },
  methods: {
    getComic() {
      ComicService.getComicById(this.$route.params.id).then((response) => {
        if (response.status == 200) {
          this.comic = response.data;
          if (this.comic.characters.length == 0) {
            this.comicsEmpty = true;
          } else {
            this.comicsEmpty = false;
          }
        }
      });
    },
    getCreators() {},
    getCharacters() {},
  },
  created() {
    this.getComic();
  },
};
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
  justify-content: space-evenly;
  align-items: center;
}

#main-panel div {
  padding: 5px;
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

#creator-text {
  width: 50%;
  max-width: 200px;
  /* max-height: 500px; */
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-right: 10px;
}

/* .count-row {
  display: flex;
  flex-wrap: wrap;
}
/* 
.count-row h3{
  margin: 2px;
}

.count-row h4{
  margin: 4px 2px 2px 2px;

} */
*/ #character-text {
  flex-grow: 1;
  width: 50%;
  max-width: 350px;
  max-height: 500px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  justify-content: center;
  margin-right: 10px;
}

/* .character-title {
  display: block;
} */
#comic-detail {
  width: 50%;
  max-width: 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-right: 10px;
}

.count-list {
  font-size: 1rem;
}
.count-role {
  margin: 0px;
  margin-top: 10px;
}
</style>