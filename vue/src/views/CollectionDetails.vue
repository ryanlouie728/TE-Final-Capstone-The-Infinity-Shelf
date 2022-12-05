<template>
  <div id="collection-details">
    <comic-list v-bind:comics="this.comics" />
  </div>
</template>

<script>
import ComicList from '../components/ComicList.vue'
import ComicService from '../services/ComicService';
export default {
  components: { ComicList },
  name: 'collection-details',
  data() {
    return {
      comics: []
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
    }
  },
  created() {
    this.getComics();
  }
}
</script>

<style>

</style>