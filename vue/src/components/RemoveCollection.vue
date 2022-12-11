<template>
  <div class="remove-collection">
      <app-button v-on:click="removeCollection" buttonText="Remove Collection" />
      <!-- <button v-on:click="removeCollection">Remove Collection</button> -->
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService';
import AppButton from './Button.vue';

export default {
    name: 'remove-collection',
    components: { AppButton },
    props: ['collection'],
    methods: {
        removeCollection(){
            if(confirm('Are you sure?')){
            CollectionService.deleteCollection(this.collection.collectionId).then((response) => {
                if(response.status == 200){
                    this.$router.push({name: 'collections'});
                }
            });
            }
        }
    }
}
</script>

<style>

</style>