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
          v-for="character in this.comic.characters.slice(0, 6)"
          v-bind:key="character.name"
        >
          <h4 class="count-name">{{ character.name }}</h4>
        </div>
      </div>
      <div id="creator-text">
        <h2 class="creator-title">Creators</h2>
        <div
          class="count-row"
          v-for="creator in this.comic.creators.slice(0, 6)"
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
      },
    };
  },
  methods: {
    getComic() {
      ComicService.getComicById(this.$route.params.id).then((response) => {
        if (response.status == 200) {
          this.comic = response.data;
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
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
#main-panel {
  width: 66%;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-content: flex-start;
}

#main-panel div {
  margin-right: 20px;
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
  width: 100%;
  height: auto;
}

#comic-title {
  margin: 10px;
  font-size: 1.75rem;
}

#creator-text {
  width: 50%;

  max-height: 500px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

#character-text {
  width: 50%;

  max-height: 500px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

#comic-detail {
  width: 50%;

  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.count-list {
  font-size: 1rem;
}
.count-role {
  margin-top: 10px;
}
</style>