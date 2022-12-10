<template>
  <div class="friend-pending">
    <router-link class="friend-request-link button" v-bind:to="{ name: 'user-profile', params: {username: this.request.toName }}">
            <p>{{ this.request.toName }}</p>
    </router-link>
    <button 
        class="friend-pending-cancel button"
        v-on:click.prevent="startConfirm('Cancel this Request?')"
    >Cancel</button>
    <confirm
        v-if="this.confirming"
        v-bind:message="this.confirmMessage"
        v-bind:function="this.cancelRequest"
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
    name: 'friend-pending',
    props: ['request'],
    data() {
        return {
            confirming: false,
            confirmMessage: ''
        }
    },
    methods: {
        startConfirm(message) {
            this.confirmMessage = message
            this.confirming = true
        },
        cancelRequest() {
            FriendService.cancelRequest(this.request.requestId)
            .then(() => {
                this.$emit('cancelled');
            })
        }
    }
}
</script>

<style scoped>
.friend-pending, .friend-pending * {
  --height: 75%;
}

.friend-pending {
    width: 100%;
    display: flex;
    height: 50px;
    justify-content: space-between;
    align-items: center;
}

.friend-request-link {
    border-radius: 10px;
    width: 63%;
    height: var(--height);
    margin: 0px;
    margin-right: 5px;
}

.friend-pending-cancel {
    border-radius: 10px;
    width: 33%;
    height: var(--height);
    background-color: var(--light-accent);
    border: solid 2px var(--dark-accent);
}

.friend-pending-cancel:hover {
    background-color: var(--dark-accent);
}

.friend-request-link > p {
  font-size: .8rem;
  margin: 0px;
}
</style>