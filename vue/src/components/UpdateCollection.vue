<template>
  <div class="update-collection">
    <label for="title-input">Title:</label>
    <input v-model="collectionUpdate.collectionName" id="title-input" name="title-input" type="text"/>
    <label for="description-input">Description</label>
    <textarea v-model="collectionUpdate.collectionDescription" />
    <label for="privacy-boolean">Collection Privacy</label>
    <select name="privacy-boolean" id="privacy-boolean">
        <option value="Public">Public</option>
        <option value="Private">Private</option>
    </select>

    <label for="collection-cover">Collection Cover Image</label>
    <select name="collection-cover" id="collection-cover">
    </select>

    <div class="button-holder">
        <button v-on:click.prevent="update()">Update</button>
        <button v-on:click.prevent="cancel()">Cancel</button>
    </div>
    
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService"
import UserService from "../services/UserService"

export default {
    name: "update-collection",
    props: ["collection"],
    data(){
        return{
            components: {UserService},
            collectionUpdate: {
                collectionName: '', 
                collectionDescription: '',
                collectionPublic: this.collection.collectionPublic
        }
    }
    },
    methods: {
        update(){
            if(this.collectionUpdate.collectionName != '' || this.collectionUpdate.collectionDescription != ''){
            const collUpdate = {
                userId: this.collection.userId,
                collectionName: this.collectionUpdate.collectionName != '' ? this.collectionUpdate.collectionName : this.collection.collectionName,
                collectionDescription: this.collectionUpdate.collectionDescription != '' ? this.collectionUpdate.collectionDescription : this.collection.collectionDescription,
                collectionId: this.collection.collectionId,
                collectionPublic: this.collection.collectionPublic,
                collectionCoverUrl: this.collection.collectionCoverUrl,
                comics: this.collection.comics,
                characterCounts: this.collection.characterCounts,
                creatorCounts: this.collection.creatorCounts
            }
            CollectionService.updateCollection(collUpdate).then(response => {
                if(response.status == 200){
                    window.location.reload();
                }
            });
            }
        },
        cancel(){
            window.location.reload();
        }
    }

}
</script>

<style>

</style>