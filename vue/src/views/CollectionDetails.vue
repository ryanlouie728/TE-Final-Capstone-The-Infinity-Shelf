<template>
  <div id="collection-details">
    <div id="left-pane">
      <h2 banner>Comics in the {{this.collection.collectionName}} Collection</h2>
      <comic-list v-bind:showAdd="true" ref="comics" @clicked="comicClicked()" v-bind:comics="this.collection.comics" @addComic="addingComic = true"/>
      <div id="update-collection">
        <button v-on:click="updatingCollection = !updatingCollection">Update Collection</button>
      </div>
      <update-collection v-if="updatingCollection" v-bind:collection="this.collection" />
      <remove-collection v-bind:collection="this.collection" />
    </div>
    <div id="sidebar">
      <div id="counts">
        <div class="count-list">
          <h5 class="count-title">Top Character Appearances</h5>
          <div class="count-row" v-for="character in this.collection.characterCounts.slice(0,5)" v-bind:key="character.name">
            <h6 class="count-name">{{character.name}}</h6>
          </div>
        </div>
        <div class="count-list">
          <h5 class="count-title">Top Creator Appearances</h5>
          <div class="count-row" v-for="creator in this.collection.creatorCounts.slice(0,5)" v-bind:key="creator.name">
            <h6 class="count-name">{{creator.name}}</h6>
          </div>
        </div>
        <div class="count-list">
          <h5 class="count-title">Collection Description</h5>
          <div class="description-text">
            <p> {{ collection.collectionDescription }} </p>
          </div>
        </div>
      </div>
    </div>
    <add-comic v-if="addingComic" @added="comicAdded()" v-bind:collection="this.collection" />
    
  </div>

 
</template>

<script>
import ComicList from '../components/ComicList.vue';
import AddComic from '../components/AddComic.vue';
import CollectionService from '../services/CollectionService';
import UpdateCollection from '../components/UpdateCollection.vue';
import RemoveCollection from '../components/RemoveCollection.vue';


export default {
  components: { ComicList, AddComic, UpdateCollection, RemoveCollection },
  props: ['collection-details'],
  name: 'collection-details',
  data() {
    return {
      collection: {
        characterCounts: [],
        creatorCounts: []
      },
      addingComic: false,
      updatingCollection: false,
      removingComic: false,
      selectedComics: [],
    }
  },
  methods: {
    getCollection() {
      CollectionService.getCollectionById(this.$route.params.id)
      .then(response => {
        if (response.status == 200) {
          this.collection = response.data
        }
      })
    },
    comicClicked() {
      if (this.removingComic) {
        this.selectedComics.push(this.$refs.comics.clickedId);
        console.log(this.selectedComics);

        CollectionService.removeComicFromCollection(this.collection.collectionId, this.$refs.comics.clickedId)
        .then(response => {
          if (response.status == 200) {
            this.getCollection();
          }
        })
      } else {
        this.$router.push({
          name: 'comic-details',
          params: {
            id: this.$refs.comics.clickedId
          }
        })
      }
    },
    comicAdded() {
      this.addingComic = false;
      this.getCollection();
    },
  },
  created() {
    this.getCollection();
  }
}
</script>

<style>
#router-view {
  display: flex;
}

.count-list {
  margin: 5px;
  border: solid 2px var(--main-background);
  text-align: center;
  padding: 5px;
}

.count-title {
  margin: 5px 5px;
  font-weight: bolder;
  font-size: 1rem;
}

.count-name {
  margin: 0px;
  margin-bottom: 2px;
}

#counts {
  width: 200px;
}

.description-text {
  text-align: center;
  font-size: .75rem;
}
</style>