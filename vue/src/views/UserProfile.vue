<template>
  <div @mouseup="mouseUp()" class = "user-profile">
    <div id="left-pane">
        <collection-list 
        v-bind:showAdd="this.$store.state.user.username == this.$route.params.username"
        ref="collections" v-bind:dragging="this.dragging" v-bind:collections="this.user.collections" 
        @dropped="comicDropped()"
        @addCollection="creatingCollection = true"
        @resetComics="getUser()"
        />
        <h2>Uncatagorized Comics</h2>
        <comic-list 
            ref="uncategorized"
            @resetComics="getUser()"
            v-bind:drag="this.$store.state.user.username == this.$route.params.username" v-bind:comics="this.user.base.comics" @down="comicClicked()" v-bind:showAdd="this.$store.state.user.username == this.$route.params.username" v-bind:showRemove="(this.$store.state.user.username == this.$route.params.username) && (this.user.base.comics.length > 0)"
        v-bind:base="this.user.base"
        @addComic="addingComic = true"/>
    </div>
    
    <create-collection v-if="creatingCollection" 
    @collectionCreated="collectionCreated()"/>
    <add-comic v-if="addingComic" @added="comicAdded()" v-bind:collection="this.user.base" />
    <!-- <confirm 
        v-bind:message="'Do you wish to continue?'"
        v-bind:function="this.print"
        v-bind:arguments="['one', 'two']"
    /> -->
    
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
    components: { CollectionList, CreateCollection, ComicList, AddComic },
    name: "user-profile",
    data() {
        return {
            creatingCollection: false,
            trading: false,
            addingComic: false,
            dragging: false,
            userId: '',
            user: {
                base: {
                    comics: []
                },
                collections: []
            }
        }
    }, 
    methods: {
        getId() {
            UserService.getIdByUsername(this.$route.params.username)
            .then(response => {
                if (response.status == 200) {
                this.userId = response.data;
                this.getUser();
                }
            })
        },
        getUser() {
            UserService.getProfileById(this.userId)
            .then(response => {
                if (response.status == 200) {
                    this.user = response.data;
                }
            })
            .then(() => {
                if (typeof this.$refs.uncategorized !== 'undefined') {
                    this.$refs.uncategorized.addDragEvents();
                    this.resetComicFormat();
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
        },
        comicClicked() {
            if (!window.event.ctrlKey && !this.$refs.uncategorized.removing) {
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
                        this.resetComicFormat();
                    })
                }
            })
        },
        mouseUp() {
            if (!this.$refs.uncategorized.removing) {
                this.getUser();
            }
        },
        resetComicFormat() {
            let comics = document.querySelectorAll(".comic");
            for (let comic of comics) {
                comic.style.display = 'flex';
                comic.style.position = '';
                comic.style.zIndex = '';
                comic.style.top = '';
                comic.style.left = '';
            }
        },
        print(one, two) {
            console.log(one);
            console.log(two);
        }
    },
    created() {
        this.getId();
    }
}

</script>

<style>
.user-profile {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: center;
}

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