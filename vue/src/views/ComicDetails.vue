<template>
  <div class="comic-details">
    <div id="main-panel">
      <div id="cover-holder">
        <img id="cover" v-bind:src="comic.thumbnailUrl" />
        <h1 id="comic-title">{{ comic.title }}</h1>
      </div>
      <div class="information">
        <div id="comic-detail">
          <h2 class="desc-title">Comic Description</h2>
          <h4>{{comic.description  == null || comic.description == "" ? 'No Description Available' : comic.description }}</h4>
        </div>
        <div class="char-creator">
          <div id="character-text">
            <h2 class="character-title">Characters</h2>
            <div v-if="charactersEmpty" id="message">
              <h4>No Characters Listed</h4>
            </div>
            <div
              class="count-row"
              v-for="character in comic.characters.slice(0, 5)"
              v-bind:key="character.name"
            > 
              <h4 class="count-name">{{ character.name }}</h4>
            </div>
          </div>
          <div id="creator-text">
            <h2 class="creator-title">Creators</h2>
            <div v-if="creatorsEmpty" id="message">
              <h4>No Creators Listed</h4>
            </div>
            <div
              class="count-row"
              v-for="creator in comic.creators.slice(0, 5)"
              v-bind:key="creator.name"
            >
              <h3 class="count-role">{{ creator.role.toUpperCase() }}:</h3>
              <h4 class="count-name">{{ creator.name }}</h4>
            </div>
          </div>
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
      },     
      charactersEmpty: false,
      creatorsEmpty: false,
    };
  },
  methods: {
    getComic() {
      ComicService.getComicById(this.$route.params.id).then((response) => {
        if (response.status == 200) {
          this.comic = response.data;
          if (this.comic.characters.length == 0 && this.comic.creators.length == 0) {
            this.charactersEmpty = true;
            this.creatorsEmpty = true;
          } else if (this.comic.characters.length == 0) {
            this.charactersEmpty = true;
          } else if (this.comic.creators.length == 0) {
            this.creatorsEmpty = true;
          } else {
            this.charactersEmpty = false;
            this.creatorsEmpty = false;
          }
        }
      });
    },

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
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
#main-panel {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-content: flex-start;
}

#cover-holder {
  width: 33%;
  max-width: 400px;
  margin-right: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}
.information {
  width: 66%;
  display: flex;
  flex-grow: 1;
  flex-direction: column;
}
.char-creator {
  display: flex;
  flex-grow: 1;
  flex-direction: row;
}

#cover {
  width: 100%;
  height: auto;
}

#comic-title {
  font-size: 1.75rem;
}

#creator-text {
  width: 50%;
  max-height: fit-content;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

#character-text {
  width: 50%;
  max-height: fit-content;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}
.count-row {
  display: flex;
  max-height: 200px;
  flex-wrap: wrap;
}

.count-role {
  margin-right: 5px;
}

#comic-detail {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

#message {
  text-align: start;
}

</style>