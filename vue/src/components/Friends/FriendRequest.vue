<template>
    <div class="friend-request">
        <router-link class="friend-request-link button" v-bind:to="{ name: 'user-profile', params: {username: this.request.fromName }}">
            <p>{{ this.request.fromName }}</p>
        </router-link>
        <div class="request-choice-holder">
          <button 
            v-on:click.prevent="startConfirm('Accept Request?', requestAccepted)" class="friend-request-button button"
          >&#10004;</button>
          <button v-on:click.prevent="startConfirm('Reject Request?', requestRejected)" class="friend-request-button button">&#10006;</button>
        </div>
        
        <confirm
          v-if="this.confirming"
          v-bind:message="this.confirmMessage"
          v-bind:function="this.confirmFunction"
          v-bind:arguments="[]"
          @cancel="confirming=false"
        />
    </div>
</template>

<script>
import FriendService from '../../services/FriendService'
import Confirm from '../Confirm.vue'

export default {
    components: { Confirm },
    name: 'friend-request',
    props: ['request'],
    data() {
      return {
        confirming: false,
        confirmMessage: 'Accept Friend?',
        confirmFunction: this.requestAccepted
      }
    },
    methods: {
      startConfirm(message, cFunction) {
        this.confirmMessage = message
        this.confirmFunction = cFunction
        this.confirming = true
      },
      requestAccepted() {
        console.log(this.$store.state.user.username)
        FriendService.acceptRequest(this.request.requestId)
        .then(() => {
          this.$emit('accepted')
        })
      },
      requestRejected() {
        FriendService.rejectRequest(this.request.requestId)
        .then(() => {
          this.$emit('rejected')
        })
      }
    }
}
</script>

<style scoped>
.friend-request, .friend-request * {
  --height: 75%;
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
  flex-grow: 1;
  height: var(--height);
  margin: 0px;
  margin-right: 5px;
}

.request-choice-holder {
  display: flex;
  justify-content: space-between;
  width: 70px;
  height: var(--height);
}

.friend-request-button {
  height: 100%;
  flex-grow: 1;
  border-radius: 10px;
  border: none;
}

.friend-request-button:first-child {
  margin-right: 5px;
}

.friend-request-link > p {
  font-size: .8rem;
  margin: 0px;
}
</style>