<template>
  <div class = "user-profile">
    <collection-list v-bind:collections="this.user.collections" />
    <create-collection v-if="creatingCollection" @collectionCreated="collectionCreated()"/>
    <div id="sidebar">
        <button v-on:click.prevent="creatingCollection = true">New Collection</button>
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
import CreateCollection from '../components/CreateCollection.vue';
import UserService from '../services/UserService';

export default {
    components: { CollectionList, CreateCollection},
    name: "user-profile",
    data() {
        return {
            creatingCollection: false,
            user: {}
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
        }
    },
    created() {
        this.getUser();
    }
}
</script>

<style>
#friend-list {
    width: 100%;
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