<template>
  <div class = "user-collections">
    <collection-list v-bind:collections="this.collections" />
    <create-collection v-if="creatingCollection" @collectionCreated="collectionCreated()"/>
    <div id="sidebar">
        <button v-on:click.prevent="creatingCollection = true">New Collection</button>
    </div>
  </div>
</template>

<script>
import collectionService from '../services/CollectionService';
import CollectionList from '../components/CollectionList.vue'
import CreateCollection from '../components/CreateCollection.vue';

export default {
    components: { CollectionList, CreateCollection},
    name: "user-collections",
    data() {
        return {
            creatingCollection: false,
            collections: []
        }
    }, 
    methods: {
        getCollections() {
            collectionService.listByUserId(this.$store.state.user.id)
            .then((response) => {
                if (response.status == 200) {
                    this.collections = response.data
                }
            })
        },
        collectionCreated() {
            this.getCollections(),
            this.creatingCollection = false;
        }
    },
    created() {
        this.getCollections();
    }
}
</script>

<style>

</style>