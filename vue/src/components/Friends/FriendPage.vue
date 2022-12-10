<template>
  <div class="friend-page" v-if="loaded">
    <div class="friends-page-list" id="friend-list">
        <h4 class="title">Friends</h4>
        <friend v-for="friend in this.friendPage.friends" v-bind:friend="friend" v-bind:key="friend.friendId"/>
    </div>
    <div 
      class="friends-page-list" 
      id="request-list"
      v-if="(this.$store.state.user.username == this.$route.params.username) && (friendPage.requests.length > 0)"
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
      v-if="(this.$store.state.user.username == this.$route.params.username) && (friendPage.pending.length > 0)"
    >
      <h4 class="title">Pending</h4>
      <friend-pending 
      v-for="request in this.friendPage.pending" v-bind:key="request.requestId" v-bind:request="request" 
        @cancelled="getFriendPage()"
      />
    </div>
  </div>
</template>

<script>
import FriendService from '../../services/FriendService'
import UserService from '../../services/UserService'
import Friend from './Friend.vue'
import FriendRequest from './FriendRequest.vue'
import FriendPending from './FriendPending.vue'

export default {
  name: 'friend-list',
  components: { Friend, FriendRequest, FriendPending },
  data() {
    return {
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
          this.loaded = true;
        }
      })
    },
    requestAccepted(requestId) {
      console.log('accept' + requestId)
    },
    requestRejected(requestId) {
      console.log('reject' + requestId)
    }
  },
  mounted() {
    this.getId();
  }
}
</script>

<style>
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
</style>