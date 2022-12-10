<template>
  <div class="friend-list">
    <div class="friends-page-list" id="friend-list">
        <h4 class="title">Friends</h4>
        <div class="friend" v-for="friend in this.friendPage.friends" v-bind:key="friend.friendId">
          <router-link class="friend-link" v-bind:to="{ name: 'user-profile', params: {username: friend.friendName }}">
            <p>{{ friend.friendName }}</p>
          </router-link>
        </div>
    </div>
    <div class="friends-page-list" id="request-list">

    </div>
    <div class="friends-page-list" id="pending-list">

    </div>
  </div>
</template>

<script>
import FriendService from '../services/FriendService'
import UserService from '../services/UserService'

export default {
  name: 'friend-list',
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
  justify-content: center;
  height: 50px;
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

</style>