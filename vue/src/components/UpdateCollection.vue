<template>
  <div class="update-collection">
      <form action="update()">
    <label for="title-input">Title:</label>
    <input v-model="collectionUpdate.collectionName" id="title-input" name="title-input" type="text"/>
    <label for="description-input">Description</label>
    <textarea v-model="collectionUpdate.collectionDescription" />
    <label for="privacy-boolean">Collection Privacy</label>
    <input type="radio" name="public" id="public" value="true" v-model="privacy">
    <label for="public">Public</label>
    <input type="radio" name="private" id="private" value="false" v-model="privacy">
    <label for="private">Private</label>

    <label for="collection-cover">Select A Collection Cover Image</label>
    <ul name="collection-cover" id="collection-cover">
        <li class="cover-images" v-for="comic in collection.comics" v-bind:key="comic.id"><img v-on:click="setCover(comic)" v-bind:src="comic.thumbnailUrl" /></li>
    </ul>
      </form>

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
            privacy: this.collection.collectionPublic,
            coverUrl: '',
            components: {UserService},
            collectionUpdate: {
                collectionName: '', 
                collectionDescription: '',
                collectionPublic: this.collection.collectionPublic
        }
    }
    },
    methods: {
        setCover(comic){
            this.coverUrl = comic.thumbnailUrl;
            this.update();
        },
        update(){
            console.log(this.privacy);
            const collUpdate = {
                userId: this.collection.userId,
                collectionName: this.collectionUpdate.collectionName != '' ? this.collectionUpdate.collectionName : this.collection.collectionName,
                collectionDescription: this.collectionUpdate.collectionDescription != '' ? this.collectionUpdate.collectionDescription : this.collection.collectionDescription,
                collectionId: this.collection.collectionId,
                collectionPublic: this.privacy,
                collectionCoverUrl: this.coverUrl != '' ? this.coverUrl: this.collection.collectionCoverUrl,
                comics: this.collection.comics,
                characterCounts: this.collection.characterCounts,
                creatorCounts: this.collection.creatorCounts
            }
            CollectionService.updateCollection(collUpdate).then(response => {
                if(response.status == 200){
                    window.location.reload();
                }
            });
        },
        cancel(){
            window.location.reload();
        }
    }
}
</script>

<style>

</style>