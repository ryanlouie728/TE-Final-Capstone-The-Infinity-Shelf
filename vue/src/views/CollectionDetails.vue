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
      <div id="desc">
        <h1>{{ this.collection.collectionName }}</h1>
        <h2>Description</h2>
        <p>{{ this.collection.collectionDescription }}</p>
      </div>
      <div id="privacy">
        <h3>Privacy</h3>
        <p>This collection is {{ collectionPrivacy() }}</p>
      </div>
      <div id="update-collection">
        <app-button v-on:click="updatingCollection =!updatingCollection" buttonText="Update Collection"/>
        <update-collection v-if="updatingCollection" v-bind:collection="this.collection" />
      </div>
      <div id="remove-collection">
        <remove-collection v-bind:collection="this.collection" />
      </div>
      <div id="add-comic">
        <h2 banner>Comics in the {{ this.collection.collectionName }} Collection</h2>
        <comic-list v-bind:showAdd="true" ref="comics" @clicked="comicClicked()" v-bind:comics="this.collection.comics" @addComic="addingComic = true" />
        <add-comic v-if="addingComic" @added="comicAdded()" v-bind:collection="this.collection" />
      </div>
    </div>
    <div id="sidebar">
      <div id="counts">
        <div class="count-list">
          <h5 class="count-title">Top Character Appearances</h5>
          <div
            class="count-row"
            v-for="character in this.collection.characterCounts.slice(0, 5)"
            v-bind:key="character.name"
          >
            <h6 class="count-name">{{ character.name }}</h6>
          </div>
        </div>
        <div class="count-list">
          <h5 class="count-title">Top Creator Appearances</h5>
          <div
            class="count-row"
            v-for="creator in this.collection.creatorCounts.slice(0, 5)"
            v-bind:key="creator.name"
          >
            <h6 class="count-name">{{ creator.name }}</h6>
          </div>
        </div>
        <!-- <div class="count-list">
          <h5 class="count-title">Collection Description</h5>
          <div class="description-text">
            <p>{{ collection.collectionDescription }}</p>
          </div>
        </div> -->
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
  },
  created() {
    this.getCollection();
  },
};
</script>

<style scoped>
#left-pane {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas:
    "img desc desc"
    "img desc desc"
    "privacy update remove-collection"
    "add-comic add-comic add-comic";
}
#add-comic{
  grid-area: add-comic;
}
#update-collection {
  grid-area: update;
}
#img {
  grid-area: img;
}
#desc {
  grid-area: desc;
}
#privacy {
  grid-area: privacy;
}
#remove-collection {
  grid-area: remove-collection;
}

#router-view {
  display: flex;
}

.count-list {
  margin: 5px;
  border: solid 2px var(--main-background);
  text-align: center;
  padding: 5px;
  border-radius: 9px;
}

.count-title {
  margin: 5px 5px;
  font-weight: bolder;
  font-size: 1rem;
}

.count-name {
  margin: 0px;
  margin-bottom: 2px;
  margin-left: auto;
  margin-right: auto;
}

#counts {
  width: 200px;
}
#cover-image {
  height: 316px;
  width: 200px;
}
</style>