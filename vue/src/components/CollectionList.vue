<template>
    <div id = "collection-list">
        <div 
            class="collection" 
            v-for="coll in this.collections" 
            v-bind:key="coll.collectionId" 
            v-on:click="openCollection(coll.collectionId)"
            v-on:mouseup="mouseUp()" 
            @mouseover="mouseOver(coll.collectionId)"
            @mouseOut="dragTargetId = ''"
        > 
            <img class="collection-thumbnail" v-if="coll.collectionCoverUrl" v-bind:src="coll.collectionCoverUrl">
            <div class="no-thumbnail" v-if="coll.collectionCoverUrl == null"><p>No Thumbnail</p></div>
            <div class="collection-text">
                <h3 class="collection-name"> {{ coll.collectionName }} </h3>
                <p class="collection-description"> {{coll.collectionDescription.substring(0,200)}}<span v-if="coll.collectionDescription.length > 200">...</span> </p>
            </div>
        </div>
        <div v-if="showAdd" v-on:click.prevent="addCollectionEvent()" class="collection" id="add-collection-card">
            <h5>Add New Collection</h5>
        </div>
    </div>
</template>

<script>
export default {
    name: 'collection-list',
    props: ['collections', 'dragging', 'showAdd'],
    data() {
        return {
            dragTargetId: ''
        }
    },
    methods: {
        openCollection(id) {
            this.$router.push({
                name: 'collection-details',
                params: {
                    id: id
                }
            })
        },
        mouseOver(id) {
            if (this.dragging) {
                this.dragTargetId = id;
            }
        },
        mouseUp() {
            if (this.dragging) {
                this.$emit('dropped')
            }
        },
        addCollectionEvent() {
            this.$emit('addCollection')
        }
    }
}
</script>

<style>
#collection-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    flex-grow: 1;
    margin-right: 5px;
}

.collection {
    border: solid 2px var(--dark-accent);
    background-color: white;
    height: 175px;
    padding: 5px;
    display: flex;
    margin-bottom: 3px;
    margin-left: 3px;
    flex-direction: row;
    cursor: pointer;
    pointer-events: all;
    border-radius: 9px;
    width: 400px;
    transition: all 200ms ease;
}

.collection:hover {
    transform: translateY(-3px);
    background-color: var(--medium-accent);
    color: var(--white);
}
.hover {
    background-color: var(--medium-accent);
    color: var(--white);
}

.collection-text {
    overflow: hidden;
    max-width: 275px;
    width: 275px;
    height: 99%;
    display: flex;
    word-wrap: break-word;
    flex-direction: column;
    background-color: beige;
    padding-left: 15px;
    border-radius: 9px;
    border: 2px solid black;
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
    border-radius: 9px;
}

.no-thumbnail{
    line-height: 140px;
    text-align: center;
    height: 100%;
    width: 123px;
    margin-right: 10px;
    border-radius: 9px;
    background-color: gray;
    border: 1px solid black;
}

.banner {
    width: 100%;
    height: fit-content;
    margin-bottom: 5px;
}

#add-collection-card {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    border-radius: 9px;
}

#add-collection-card > h5 {
    font-size: 1.25rem;
}
</style>