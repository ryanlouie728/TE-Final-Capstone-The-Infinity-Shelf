<template>
  <div class = "collections">
  <div id    = "collection-list">
    <div class = "collection" v-for          = "coll in this.collections" v-bind:key = "coll.collectionId" v-on:click="openCollection(coll.collectionId)">
        <img class = "collection-thumbnail" v-if = "coll.collectionCoverUrl" v-bind:src  = "coll.collectionCoverUrl">
        <div class = "collection-text">
            <h3  class = "collection-name"> {{ coll.collectionName }} </h3>
            <p   class = "collection-description"> {{ coll.collectionDescription }} </p>
        </div>
    </div>
    </div>
    <div id="sidebar">

    </div>
    
  </div>
</template>

<script>
import collectionService from '../services/CollectionService';

export default {
    name: "collections",
    data() {
        return {
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
        openCollection(id) {
            this.$router.push({
                name: 'collection-details',
                params: {
                    id: id
                }
            })
        }
    },
    created() {
        this.getCollections();
    }
}
</script>

<style>

#collection-list {
    flex-grow: 1;
    margin-right: 5px;
}

.collection {
    border: solid 2px black;
    height: 75px;
    padding: 5px;
    display: flex;
    flex-direction: row;
}

.collection:hover {
    background-color: lightgrey;
}

.collection-text {
    display: flex;
    flex-direction: column;
}

.collection-name {
    margin-top: 0px;
    margin-bottom: 5px;
}
.collection-description {
    margin: 0px;
}

.collection-thumbnail {
    
    height: 100%;
    width: auto;
    margin-right: 10px;
}
</style>