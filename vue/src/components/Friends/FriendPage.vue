<template>
  <div id="friend-page" class="friend-page" v-show="loaded">
    <add-friend
      v-if="addingFriend" 
      @cancelled="addingFriend = false"
      @added="getFriendPage(); addingFriend=false;"
    />
    
    <div 
      class="friends-page-list" 
      id="request-list"
      v-if="friendPage.requests.length > 0"
    >
      <h4 class="title">Requests</h4>
      <friend-request 
        v-for="request in this.friendPage.requests" v-bind:key="request.requestId" v-bind:request="request" 
        @accepted="getFriendPage()"
        @rejected="getFriendPage()"
      />
    </div>
    <div 
      class="friends-page-list" 
      id="pending-list"
      v-if="friendPage.pending.length > 0"
    >
      <h4 class="title">Pending</h4>
      <friend-pending 
      v-for="request in this.friendPage.pending" v-bind:key="request.requestId" v-bind:request="request" 
        @cancelled="getFriendPage()"
      />
    </div>
    <div class="friends-page-list" id="friend-list">
        <h4 class="title">Friends</h4>
        <friend v-for="friend in this.friendPage.friends" v-bind:friend="friend" v-bind:key="friend.friendId"/>
        <div
          v-if="this.$store.state.user.username == this.$route.params.username"
          class="add-friend-button"
          v-on:click.prevent="addingFriend = true"
        >+</div>
    </div>
  </div>
</template>

<script>
import FriendService from '../../services/FriendService'
import UserService from '../../services/UserService'
import Friend from './Friend.vue'
import FriendRequest from './FriendRequest.vue'
import FriendPending from './FriendPending.vue'
import AddFriend from './AddFriend.vue'

export default {
  name: 'friend-list',
  components: { Friend, FriendRequest, FriendPending, AddFriend },
  data() {
    return {
      addingFriend: false,
      userId: '',
      loaded: false,
      friendPage: {
        friends: [],
        pending: [],
        requests: []
      }
    }
  },
  methods: {
    getId() {
      UserService.getIdByUsername(this.$route.params.username)
      .then(response => {
        if (response.status == 200) {
          this.userId = response.data;
          this.getFriendPage();
        }
      })
    },
    getFriendPage() {
      FriendService.getFriendPageByUserId(this.userId)
      .then(response => {
        if (response.status == 200) {
          this.friendPage = response.data;
          this.setStyle();
        }
      })
    },
    setStyle() {
      let friendsButton = document.getElementById('friends-button')
      let friendsPage = document.querySelector('.friend-page')
      friendsPage.style.top = friendsButton.offsetTop + 20 + 'px';
      friendsPage.style.left = '0px';
      this.loaded = true;
    }
  },
  mounted() {
    this.getId();
  }
}
</script>

<style>
.friend-page {
  overflow: auto;
  height: 450px;
  width: 175px;
  background-color: var(--light-accent);
  position: relative;
  border-radius: 10px;
  display: block;
  position: absolute;
  /* transform: translate(28px, 0px); */
}

.friends-page-list {
    width: auto;
    padding: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.friends-page-list > .title {
    color: var(--white);
    margin-top: 20px;
    margin-bottom: 5px;
    text-align: center;
    font-size: 2rem;
    font-weight: bolder;
}

.add-friend-button {
  box-sizing: border-box;
  width: 75%;
  height: 37.5px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 10px;
  background-color: var(--medium-accent);
  color: var(--white);
  font-size: 2rem;
  font-weight: bolder;
  cursor: pointer;
}

.add-friend-button:hover {
  background-color: var(--dark-accent);
}

</style>