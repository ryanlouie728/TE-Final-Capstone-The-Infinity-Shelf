<template>
  <div class="friend-page">
    <div class="friends-page-list" id="friend-list">
        <h4 class="title">Friends</h4>
        <friend v-for="friend in this.friendPage.friends" v-bind:friend="friend" v-bind:key="friend.friendId"/>
    </div>
    <div class="friends-page-list" id="request-list">
      <h4 class="title">Requests</h4>
      <friend-request v-for="request in this.friendPage.requests" v-bind:key="request.requestId" v-bind:request="request" />
    </div>
    <div class="friends-page-list" id="pending-list">

    </div>
  </div>
</template>

<script>
import FriendService from '../../services/FriendService'
import UserService from '../../services/UserService'
import Friend from './Friend.vue'
import FriendRequest from './FriendRequest.vue'

export default {
  name: 'friend-list',
  components: { Friend, FriendRequest },
  data() {
    return {
      userId: '',
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

.friend {
  width: 100%;
  display: flex;
  height: 50px;
  justify-content: center;
  align-items: center;
}

.friend-link {
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  background-color: var(--medium-accent);
  width: 75%;
  height: 75%;
  margin: 0px;
  color: var(--white);
  text-decoration: none;
}
.friend-link > p {
  margin: 0px;
}

.friend-request {
  width: 100%;
  display: flex;
  height: 50px;
  justify-content: space-between;
  align-items: center;
}

.friend-request-link {
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  background-color: var(--medium-accent);
  width: 50%;
  height: 75%;
  margin: 0px;
  color: var(--white);
  text-decoration: none;
}

.friend-request-button {
  height: 75%;
  width: 20%;
  border-radius: 10px;
  border: none;
  background-color: var(--medium-accent);
  color: var(--white);
}

.friend-request-link > p {
  font-size: .8rem;
  margin: 0px;
}


</style>