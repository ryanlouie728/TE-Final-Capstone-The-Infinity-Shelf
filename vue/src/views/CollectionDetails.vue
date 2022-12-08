<template>
  <div id="collection-details">
    <comic-list ref="comics" @clicked="comicClicked()" v-bind:comics="this.collection.comics" />
    <div id="sidebar">
      <button v-on:click.prevent="addingComic = true">Add Comic</button>
      
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
      </div>
    </div>
    <add-comic v-if="addingComic" @added="comicAdded()" />
  </div>

 
</template>

<script>
import ComicList from '../components/ComicList.vue';
import AddComic from '../components/AddComic.vue'
import CollectionService from '../services/CollectionService';


export default {
  components: { ComicList, AddComic },
  name: 'collection-details',
  data() {
    return {
      collection: {
        characterCounts: [],
        creatorCounts: []
      },
      addingComic: false,
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

        // CollectionService.removeComicFromCollection(this.collection.collectionId, this.$refs.comics.clickedId)
        // .then(response => {
        //   if (response.status == 200) {
        //     this.getCollection();
        //   }
        // })
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
  border: solid 2px gray;
  text-align: center;
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

</style>