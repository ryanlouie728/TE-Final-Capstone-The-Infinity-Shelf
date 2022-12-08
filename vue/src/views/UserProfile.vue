<template>
  <div @mouseup="mouseUp()" class = "user-profile">
    <div id="left-pane">
        <collection-list 
        ref="collections" v-bind:dragging="this.dragging" v-bind:collections="this.user.collections" 
        @dropped="comicDropped()"
        @addCollection="creatingCollection = true"
        />
        <h2>Uncatagorized Comics</h2>
        <comic-list ref="uncategorized" v-bind:drag="true" v-bind:comics="this.user.base.comics" @down="comicClicked()" v-bind:showAdd="true" @addComic="addingComic = true"/>
    </div>
    
    <create-collection v-if="creatingCollection" 
    @collectionCreated="collectionCreated()"/>
    <add-comic v-if="addingComic" @added="comicAdded()" v-bind:collection="this.user.base" />
    <div id="sidebar">
        <button v-on:click.prevent="creatingCollection = true">New Collection</button>
        <!-- <button v-on:click.prevent="addingComic = true">Add Comic</button> -->
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
import CollectionService from '../services/CollectionService';

export default {
    components: { CollectionList, CreateCollection, ComicList, AddComic},
    name: "user-profile",
    data() {
        return {
            creatingCollection: false,
            trading: false,
            addingComic: false,
            dragging: false,
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
            .then(() => {
                this.$refs.uncategorized.addDragEvents();
                this.resetComicFormat();
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
        },
        comicClicked() {
            if (!window.event.ctrlKey) {
                this.$router.push({
                    name: 'comic-details',
                    params: {
                        id: this.$refs.uncategorized.clickedId
                    }
                })
            } else {
                this.dragging = true;
            }
        },
        comicDropped() {
            this.returnComic = false;
            let collId = this.$refs.collections.dragTargetId;
            let comicId = this.$refs.uncategorized.clickedId;
            this.$refs.uncategorized.return = false;
            CollectionService.addComicToCollection(collId, comicId)
            .then(response => {
                if (response.status == 200) {
                    CollectionService.removeComicFromCollection(this.user.base.collectionId, comicId)
                    .then(() => {
                        this.getUser();
                    })
                }
            })
        },
        mouseUp() {

            this.getUser();
            this.resetComicFormat();
        },
        resetComicFormat() {
            console.log('reset');
            let comics = document.querySelectorAll(".comic");
            for (let comic of comics) {
                comic.style.display = 'flex';
                comic.style.position = '';
                comic.style.zIndex = '';
                comic.style.top = '';
                comic.style.left = '';
            }
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