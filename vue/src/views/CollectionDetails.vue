<template>
   <div id="collection-details">
    <comic-list v-bind:comics="this.comics" />
    <div id="sidebar">
      <button v-on:click.prevent="addingComic = true">Add Comic</button>
    </div>
    <add-comic v-if="addingComic" @added="comicAdded()" />
  </div>

 
</template>

<script>
import ComicList from '../components/ComicList.vue'
import ComicService from '../services/ComicService';
import AddComic from '../components/AddComic.vue'


export default {
  components: { ComicList, AddComic },
  name: 'collection-details',
  data() {
    return {
      comics: [],
      addingComic: false
    }
  },
  methods: {
    getComics() {
      ComicService.listSimpleByCollectionId(this.$route.params.id)
      .then(response => {
        if (response.status == 200) {
          this.comics = response.data;
        }
      })
    },
    comicAdded() {
      this.addingComic = false;
      this.getComics();
    }
  },
  created() {
    this.getComics();
  }
}
</script>

<style>


</style>