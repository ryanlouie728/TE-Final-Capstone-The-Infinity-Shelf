<template>
  <div class="trade">
    <input v-on:keyup="getUsers()" @change="userSelected" v-model="userInput" type="text" list="usernames">
    <datalist id="usernames">
        <option v-for="user in this.users"  v-bind:key="user.id" :value="user.username">{{user.username}}</option>
    </datalist>
    <div class="trade-block" id="current-comics">
        <div class="trade-title-block">
            <h3>{{this.$store.state.user.username}}</h3>
            <h3>{{userInput}}</h3>
        </div>
        <div class="trade-list-block">
            <div class="trade-comic-list-holder">
                <comic-list ref="userComics" @clicked="userComicClicked()" class="trade-comic-list" v-bind:comics="userComics"></comic-list>
            </div>
            <div class="trade-comic-list-holder">
                <comic-list ref="tradeComics" @clicked="tradeComicClicked()" class="trade-comic-list" v-bind:comics="tradeComics"></comic-list>
            </div>
        </div>
    </div>
    <div class="trade-block" id="proposed-trade">

        <div class="trade-title-block">
            <h3>{{this.$store.state.user.username}}</h3>
            <h3>{{userInput}}</h3>
        </div>
        <div class="trade-list-block">
            <div class="trade-comic-list-holder">
                <comic-list ref="selectedUserComics" @clicked="selectedUserComicClicked()" class="trade-comic-list" v-bind:comics="userSelectedComics"></comic-list>
            </div>
            <div class="trade-comic-list-holder">
                <comic-list ref="selectedTradeComics" @clicked="selectedTradeComicClicked()" class="trade-comic-list" v-bind:comics="tradeSelectedComics"></comic-list>
            </div>
        </div>
    </div>
    <app-button
        v-on:click.prevent="confirming = true"
    />
    <confirm 
        v-if="confirming"
        :message="'Create Trade Request?'"
        :function="populateTrade"
        :arguments="[]"
        @cancel="confirming = false"
    />
  </div>
</template>

<script>
import ComicService from '../services/ComicService';
import UserService from '../services/UserService';
import ComicList from '../components/ComicList.vue';
import CollectionService from '../services/CollectionService';
import AppButton from '../components/Button.vue'
import TradeService from '../services/TradeService';
import Confirm from '../components/Confirm.vue'

export default {
  components: { ComicList, AppButton, Confirm },
    name: 'trade',
    data() {
        return {
            confirming: false,
            user: {
                collections: []
            },
            userCollections: [],
            userInput: '',
            users: [],
            userComics: [],
            userSelectedComics: [],
            tradeComics: [],
            tradeSelectedComics: [],
            trade: {
                tradeId: 0,
                comics: [],
                users: [],
                status: 'pending'
            }
        }
    },
    methods: {
        getUserCollections() {
            CollectionService.getCollectionsByUserId(this.$store.state.user.id)
            .then(respone => {
                if (respone.status == 200) {
                    this.setUserComics(respone.data)
                }
            })
        },
        setUserComics(collections) {
            this.userComics = []
            for (let collection of collections) {
                this.userComics.push(...collection.comics)
            }
        },
        getUsers() {
            UserService.getByUsername(this.userInput)
            .then(response => {
                if (response.status == 200) {
                    this.users = response.data;
                }
            })
        },
        userComicClicked() {
            let comic = this.userComics.find(comic => {
                return comic.id == this.$refs.userComics.clickedId;
            });
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
        tradeComicClicked() {
            let comic = this.tradeComics.find(comic => {
                return comic.id == this.$refs.tradeComics.clickedId;
            })
            this.tradeSelectedComics.push(comic);
            this.tradeComics.splice(this.tradeComics.indexOf(comic), 1);
        },
        selectedTradeComicClicked() {
            let comic = this.tradeSelectedComics.find(comic => {
                return comic.id == this.$refs.selectedTradeComics.clickedId;
            })
            this.tradeSelectedComics.splice(this.tradeSelectedComics.indexOf(comic), 1);
            this.tradeComics.push(comic);
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
                    this.tradeComics.push(...response.data.base.comics)
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
        },
        populateTrade() {
            this.confirming = false;
            let selected = this.users.find(user => {
                return user.username == this.userInput;
            })
            this.trade.users = []
            this.trade.userSelectedComics = []
            this.trade.tradeSelectedComics = []
            this.trade.users.push({
                userDto: {
                    id: this.$store.state.user.id
                },
                role: 'sender'
            })
            this.trade.users.push({
                userDto: {
                    id: selected.id
                },
                role: 'recipient'
            })

            for (let comic of this.userSelectedComics) {
                this.trade.comics.push({
                    comicDto: {
                        id: comic.id
                    },
                    from: {
                        id: this.$store.state.user.id
                    },
                    to: {
                        id: selected.id
                    },
                    collectionId: comic.collectionId
                })
            }
            for (let comic of this.tradeSelectedComics) {
                this.trade.comics.push({
                    comicDto: {
                        id: comic.id
                    },
                    from: {
                        id: selected.id
                    },
                    to: {
                        id: this.$store.state.user.id
                    },
                    collectionId: comic.collectionId
                })
            }
            this.createTrade();
        },
        createTrade() {
            TradeService.createTrade(this.trade)
            .then(response => {
                if (response.status == 200) {
                    this.userInput = ''
                    this.userSelectedComics = []
                    this.tradeSelectedComics = []
                    console.log(response.data)
                }
            })
            .catch(error => {
                console.log(error)
            })
        }
    },
    created() {
        this.getUserCollections()
    },
    mounted() {
        this.getUsers();
        //this.getCurrentUserComics();
    }
}
</script>

<style>
.trade {
    width: 100%;
    display: flex;
    flex-direction: column;
    background-color: var(--light-accent);
}

.trade-block {
    box-sizing: border-box;
    padding: 10px;
    width: 100%;
    
}

.trade-title-block {
    display: flex;
    justify-content: space-around;
}

.trade-list-block {
    display: flex;
    justify-content: space-between;
}

#user-select {
    width: 100px;
}

.trade-comic-list-holder {
    display: flex;
    height: 185px;
    overflow: auto;
    width: 47.5%;
    border: inset 2px var(--medium-accent);
    border-radius: 9px;
}

.trade-comic-list .comic {
    width: 100px;
    height: 179px;
}
</style>