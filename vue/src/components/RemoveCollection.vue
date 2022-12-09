<template>
  <div class="remove-collection">
      <button v-on:click="removeCollection">Remove Collection</button>
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService';

export default {
    name: 'remove-collection',
    props: ['collection'],
    methods: {
        removeCollection(){
            CollectionService.deleteCollection(this.collection.collectionId).then((response) => {
                if(response.status == 200){
                    this.$router.push({name: 'collections'});
                }
            });
        }
    }
}
</script>

<style>

</style>