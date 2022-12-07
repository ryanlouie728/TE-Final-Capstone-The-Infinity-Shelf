<template>
    <div id = "collection-list">
        <div class="banner">
            <h2 v-if="this.$route.name == 'collections'">Public Collections</h2>
            <h2 v-if="this.$route.name == 'user-collections'">User Collections</h2>
        </div>
        <div class="collection" v-for="coll in this.collections" v-bind:key="coll.collectionId" v-on:click="openCollection(coll.collectionId)">
            <img class="collection-thumbnail" v-if="coll.collectionCoverUrl" v-bind:src="coll.collectionCoverUrl">
            <div class="collection-text">
                <h3  class="collection-name"> {{ coll.collectionName }} </h3>
                <p   class="collection-description"> {{ coll.collectionDescription }} </p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'collection-list',
    props: ['collections'],
    methods: {
        openCollection(id) {
            this.$router.push({
                name: 'collection-details',
                params: {
                    id: id
                }
            })
        }
    }
}
</script>

<style>
#collection-list {
    flex-grow: 1;
    margin-right: 5px;
}

.collection {
    border: solid 2px var(--dark-accent);
    height: 75px;
    padding: 5px;
    display: flex;
    flex-direction: row;
}

.collection:hover {
    background-color: var(--medium-accent);
    
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

.banner {
    width: 100%;
    height: fit-content;
    margin-bottom: 5px;
}
</style>