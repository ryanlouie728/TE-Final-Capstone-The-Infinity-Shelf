<template>
  <div id="collection-details">
    <div id="left-pane">
      <div id="img">
        <img
          v-bind:src="this.collection.collectionCoverUrl"
          alt=""
          id="cover-image"
        />
      </div>
      <div id="coll-desc">
        <h1>{{ this.collection.collectionName }} Collection</h1>
        <h2>Description</h2>
        <p>{{ this.collection.collectionDescription }}</p>
      </div>
      <div id="privacy">
        <h3>Privacy</h3>
        <p>This collection is {{ collectionPrivacy() }}</p>
      </div>
      <div class="collection-button-holder">
        <app-button v-on:click="updatingCollection = true" buttonText="Update Collection"/>
        <update-collection 
          v-if="updatingCollection" 
          v-bind:collection="this.collection" 
          @cancelled="updatingCollection = false; getCollection()"
          @updated="updatingCollection = false; getCollection()"
        />
        <remove-collection v-bind:collection="this.collection" />
        <app-button v-on:click.prevent="exportAsCsv" buttonText="Export Collection" />
      </div>
      <div id="add-comic">
        <h2 banner>Comics in the {{ this.collection.collectionName }} Collection</h2>
        <comic-list v-bind:showAdd="true" ref="comics" @clicked="comicClicked()" v-bind:comics="this.collection.comics" @addComic="addingComic = true" />
        <add-comic v-if="addingComic" @added="comicAdded()" v-bind:collection="this.collection" />
      </div>
      <div class="character-list">
        <h3 class="count-title">Top Character Appearances</h3>
        <div
          class="count-row"
          v-for="character in this.collection.characterCounts.slice(0, 5)"
          v-bind:key="character.name"
        >
          <h5 class="count-name">{{ character.name }}</h5>
        </div>
      </div>
      <div class="creator-list">
        <h3 class="count-title">Top Creator Appearances</h3>
        <div
          class="count-row"
          v-for="creator in this.collection.creatorCounts.slice(0, 5)"
          v-bind:key="creator.name"
        >
          <h5 class="count-name">{{ creator.name }}</h5>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ComicList from "../components/ComicList.vue";
import AddComic from "../components/AddComic.vue";
import CollectionService from "../services/CollectionService";
import UpdateCollection from "../components/UpdateCollection.vue";
import RemoveCollection from "../components/RemoveCollection.vue";
import AppButton from "../components/Button.vue";

export default {
  components: { ComicList, AddComic, UpdateCollection, RemoveCollection, AppButton },
  props: ["collection-details"],
  name: "collection-details",
  data() {
    return {
      collection: {
        characterCounts: [],
        creatorCounts: [],
      },
      addingComic: false,
      updatingCollection: false,
      removingComic: false,
      selectedComics: [],
    };
  },
  methods: {
    collectionPrivacy() {
      return this.collection.collectionPublic ? "Public" : "Private";
    },
    getCollection() {
      CollectionService.getCollectionById(this.$route.params.id).then(
        (response) => {
          if (response.status == 200) {
            this.collection = response.data;
          }
        }
      );
    },
    comicClicked() {
      if (this.removingComic) {
        this.selectedComics.push(this.$refs.comics.clickedId);
        console.log(this.selectedComics);

        CollectionService.removeComicFromCollection(
          this.collection.collectionId,
          this.$refs.comics.clickedId
        ).then((response) => {
          if (response.status == 200) {
            this.getCollection();
          }
        });
      } else {
        this.$router.push({
          name: "comic-details",
          params: {
            id: this.$refs.comics.clickedId,
          },
        });
      }
    },
    comicAdded() {
      this.addingComic = false;
      this.getCollection();
    },
    exportAsCsv() {
      let fileText = '';
      for (let comic of this.collection.comics) {
        fileText += comic.id + ','
      }
      fileText = fileText.slice(0, fileText.lastIndexOf(','));
      this.downloadStringAsFile(fileText)
    },
    downloadStringAsFile(text) {
      const link = document.createElement("a");
      const file = new Blob([text], { type: 'text/plain' });
      link.href = URL.createObjectURL(file);
      link.download = this.collection.collectionName + "-" + this.collection.collectionId + '.csv';
      link.click();
      URL.revokeObjectURL(link.href);
    }
  },
  created() {
    this.getCollection();
    
  },
};
</script>

<style scoped>

#left-pane {
  display: grid;
  width: 100%;
  grid-template-columns: 1fr 1fr 1fr;
  row-gap: 5px;;
  grid-template-areas:
    "img add-comic add-comic"
    "coll-desc  character-list creator-list"
    "privacy update-coll remove-coll";
}
#add-comic{
  grid-area: add-comic;
}

.collection-button-holder {
  display: flex;

}

.collection-button-holder button {
  height: 30px;
}

#img {
  grid-area: img;
  display: block;
  text-align: center;
}
#cover-image {
  height: 500px;
}
#coll-desc {
  grid-area: coll-desc;
}
#privacy {
  grid-area: privacy;
}
#remove-collection {
  grid-area: remove-coll;
  display: flex;
  align-items: center;
}
#character-list {
  grid-area: character-list;
}
#creator-list{
  grid-area: creator-list;
}
</style>