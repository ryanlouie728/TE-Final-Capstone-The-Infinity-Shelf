<template>
  <div class="collections">
    <div id="collection-list">
        <div class="collection" v-for="coll in this.collections" v-bind:key="coll.collectionId">
            <img class="collection-thumbnail" v-if="coll.collectionCoverUrl" v-bind:src="coll.collectionCoverUrl">
            <div class="collection-text">
                <h3> {{ coll.collectionName }} </h3>
                <p> {{ coll.collectionDescription }} </p>
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
}

.collection {
    display: flex;
    flex-direction: row;
}

.collection-text {
    display: flex;
    flex-direction: column;
}

.collection-thumbnail {
    height: auto;
    max-width: 100px;
    margin-right: 10px;
}

#sidebar {
    width: 200px;
    background-color: red;
}
</style>