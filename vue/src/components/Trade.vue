<template>
  <div class="trade">
    <input v-on:keyup="getUsers()" @change="userSelected" v-model="userInput" type="text" list="usernames">
    <datalist id="usernames">
        <option v-for="user in this.users"  v-bind:key="user.id" :value="user.username">{{user.username}}</option>
    </datalist>
    <p>My Comics</p>
    <div class="comic-list-holder">
        <comic-list ref="userComics" @clicked="userComicClicked()" class="trade-comic-list" v-bind:comics="userComics"></comic-list>
    </div>
    <div class="comic-list-holder">
        <comic-list ref="selectedUserComics" @clicked="selectedUserComicClicked()" class="trade-comic-list" v-bind:comics="userSelectedComics"></comic-list>
    </div>
    <div class="comic-list-holder">
        <comic-list ref="tradeComics" @clicked="selectedUserComicClicked()" class="trade-comic-list" v-bind:comics="tradeComics"></comic-list>
    </div>
  </div>
</template>

<script>
import ComicService from '../services/ComicService';
import UserService from '../services/UserService';
import ComicList from './ComicList.vue';

export default {
  components: { ComicList },
    name: 'trade',
    props: ['user'],
    data() {
        return {
            userInput: '',
            users: [],
            userComics: [],
            userSelectedComics: [],
            tradeComics: [],
            tradeSelectedComics: []
        }
    },
    methods: {
        getUsers() {
            UserService.getByUsername(this.userInput)
            .then(response => {
                if (response.status == 200) {
                    this.users = response.data;
                    //console.log(this.users);
                }
            })
        },
        getCurrentUserComics() {
            for (let collection of this.user.collections) {
                ComicService.listSimpleByCollectionId(collection.collectionId)
                .then(response => {
                    if (response.status == 200) {
                        this.userComics.push(...response.data)
                    }
                })
            }
        },
        userComicClicked() {
            let comic = this.userComics.find(comic => {
                return comic.id == this.$refs.userComics.clickedId;
            });
            // console.log(comic)
            // console.log(this.userComics)
            this.userSelectedComics.push(comic)
            this.userComics.splice(this.userComics.indexOf(comic), 1)
        },
        selectedUserComicClicked() {
            let comic = this.userSelectedComics.find(comic => {
                return comic.id == this.$refs.selectedUserComics.clickedId;
            })
            this.userSelectedComics.splice(this.userSelectedComics.indexOf(comic), 1)
            this.userComics.push(comic)
        },
        userSelected() {
            let selected = this.users.find(user => {
                return user.username == this.userInput;
            })
            if (this.userInput == "" || typeof selected == 'undefined') {
                this.tradeComics = [];
                return;
            }
            
            UserService.getProfileById(selected.id)
            .then(response => {
                if (response.status == 200) {
                    this.tradeComics = [];
                    for (let collection of response.data.collections) {
                        ComicService.listSimpleByCollectionId(collection.collectionId)
                        .then(response => {
                            if (response.status == 200) {
                                this.tradeComics.push(...response.data)
                            }
                        })
                    }
                } else {
                    this.tradeComics = [];
                }
            })
        }
    },
    mounted() {
        this.getUsers();
        this.getCurrentUserComics();
    }
}
</script>

<style>
.trade {
    background-color: var(--medium-accent);
    position: fixed;
    width: 66vw;
    top: calc(25vh - 100px);
    left: calc(25vw - 100px);
    height: fit-content;
}

#user-select {
    width: 100px;
}

.comic-list-holder {
    display: flex;
    height: 185px;
    overflow: auto;
    width: 95%;
    border: solid 1px var(--dark-accent);
}

.trade-comic-list {
    width: 100%;
}

.trade-comic-list .comic {
    width: 100px;
    height: 179px;
}
</style>