<template>
  <div class = "user-profile">
    <div id="left-pane">
        <collection-list v-bind:collections="this.user.collections" />
        <h2>Uncatagorized Comics</h2>
        <comic-list v-bind:comics="this.user.base.comics" />
    </div>
    
    <create-collection v-if="creatingCollection" @collectionCreated="collectionCreated()"/>
    <add-comic v-if="addingComic" @added="comicAdded()" v-bind:collection="this.user.base" />
    <!-- <trade v-bind:user="user" v-if="trading" @tradeCreated="tradeCreated()" /> -->
    <div id="sidebar">
        <button v-on:click.prevent="creatingCollection = true">New Collection</button>
        <button v-on:click.prevent="addingComic = true">Add Comic</button>
        <div id="friend-list">
            <h4 id="friend-list-title">Friends</h4>
            <div class="friend" v-for="friend in this.user.friends" v-bind:key="friend.id">
                <p>{{ friend.friendName }}</p>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import CollectionList from '../components/CollectionList.vue'
import ComicList from '../components/ComicList.vue';
import CreateCollection from '../components/CreateCollection.vue';
import AddComic from '../components/AddComic.vue'

import UserService from '../services/UserService';

export default {
    components: { CollectionList, CreateCollection, ComicList, AddComic},
    name: "user-profile",
    data() {
        return {
            creatingCollection: false,
            trading: false,
            addingComic: false,
            user: {
                base: {
                    comics: []
                },
                collections: []
            }
        }
    }, 
    methods: {
        getUser() {
            UserService.getProfileById(this.$store.state.user.id)
            .then(response => {
                if (response.status == 200) {
                    this.user = response.data;
                }
            })
        },
        collectionCreated() {
            this.getUser(),
            this.creatingCollection = false;
        },
        tradeCreated() {
            console.log("trade");
        },
        comicAdded() {
            this.addingComic = false;
            this.getUser();
        }
    },
    created() {
        this.getUser();
    }
}
</script>

<style>
#collection-list {
    flex-grow: 0;
    margin-bottom: 5px;
}
#left-pane {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-content: flex-start;
}
#friend-list {
    width: auto;
    padding: 10px;
    display: flex;
    flex-direction: column;
}

#friend-list-title {
    color: var(--white);
    margin-top: 20px;
    margin-bottom: 5px;
    text-align: center;
    font-size: 2rem;
    font-weight: bolder;
}

.friend > p {
    text-align: center;
    color: var(--white);
    margin: 0px;
}
</style>