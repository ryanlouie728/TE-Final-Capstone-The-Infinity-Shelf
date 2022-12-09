<template>
  <div class="update-collection">
    <label for="title-input">Title:</label>
    <input v-model="collectionUpdate.collectionName" id="title-input" name="title-input" type="text"/>
    <label for="description-input">Description</label>
    <textarea v-model="collectionUpdate.collectionDescription" />
    <div class="button-holder">
        <button v-on:click.prevent="update()">Update</button>
        <button v-on:click.prevent="cancel()">Cancel</button>
    </div>
    
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService"
export default {
    name: "update-collection",
    props: ["collection"],
    data(){
        return{
            updating: false,
            collectionUpdate: {
                collectionName: '', 
                collectionDescription: '',
        }
    }
    },
    methods: {
        update(){
            this.updating = true
            const collUpdate = {
                userId: this.collection.userId,
                collectionName: this.collectionUpdate.collectionName,
                collectionDescription: this.collectionUpdate.collectionDescription,
                collectionId: this.collection.collectionId,
                collectionPublic: this.collection.collectionPublic,
                collectionCoverUrl: this.collection.collectionCoverUrl,
                comics: this.collection.comics,
                characterCounts: this.collection.characterCounts,
                creatorCounts: this.collection.creatorCounts
            }
            CollectionService.updateCollection(collUpdate).then(response => {
                if(response.data == 200){
                    this.updating = false
                    // need to make this reload page
                }
            });
        },
        cancel(){
            this.collectionUpdate.collectionName = '',
            this.collectionUpdate.collectionDescription = ''
            this.updating = false
        }
    }

}
</script>

<style>

</style>