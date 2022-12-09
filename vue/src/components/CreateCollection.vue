<template>
  <div class="create-collection">
    <label for="title-input">Collection Title:</label>
    <input v-model="collection.collectionName" id="title-input" name="title-input" type="text"/>
    <label for="description-input">Description</label>
    <textarea v-model="collection.collectionDescription" />
    <div class="button-holder">
        <button v-on:click.prevent="create()">Create</button>
        <button v-on:click.prevent="cancel()">Cancel</button>
    </div>
    
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService';
export default {
    name: 'create-collection',
    props: ['creatingCollection'],
    data() {
        return{
            collection: {
                userId: this.$store.state.user.id,
                collectionName: '',
                collectionDescription: '',
                collectionCoverUrl: ''
            }
        }
    },
    methods: {
        create() {
            CollectionService.createCollection(this.collection)
            .then((response) => {
                if (response.status == 200) {
                    this.collection.collectionName = '';
                    this.collection.collectionDescription = '';
                    this.$emit('collectionCreated');
                } else {
                    console.log('problem creating collection');
                }
            })
        },
        cancel() {
            this.collection.collectionName = '';
            this.collection.collectionDescription = '';
            this.$emit('collectionCreated');
        }
    }
}
</script>

<style>
.create-collection {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: fixed;
    width: 200px;
    height: auto;
    left: calc(50vw - 100px);
    top: calc(50vh - 100px);
    background-color: darkgrey;
    padding: 10px;
}

.create-collection > input {
    width: 100%;
}

.create-collection > textarea {
    width: 100%;
    height: 100px;
    margin-bottom: 10px;
    resize: none;
}
.button-holder {
    display: flex;
}


</style>