<template>
  <div class="add-friend">
    <h2>Add Friend</h2>
    <user-selector 
        ref="selector"
    />
    <div id="button-holder">
        <button 
            class="button"
            v-on:click.prevent="confirming=true"
        >Add</button>
        <button 
            class="button"
            v-on:click.prevent="$emit('cancelled')"
        >Cancel</button>
    </div>
    
    <confirm 
        v-if="this.confirming"
        v-bind:message="'Add Friend?'"
        v-bind:function="this.addFriend"
        v-bind:arguments="[]"
        @cancel="confirming=false"
    />
  </div>
</template>

<script>
import FriendService from '../../services/FriendService'
import Confirm from '../Confirm.vue'
import UserSelector from '../UserSelector.vue'

export default {
    components: { Confirm, UserSelector },
    name: 'add-friend',
    data() {
        return {
            confirming: false,
            confirmMessage: 'Send Friend Request?'
        }
    },
    methods: {
        addFriend() {
            let friend = this.$refs.selector.users.find(user => {
                return user.username == this.$refs.selector.userInput;
            })
            console.log(friend)
            if (typeof friend !== 'undefined') {
                FriendService.createFriendRequest(friend.id)
                .then(() => {
                    this.$emit('added');
                })
            }
        }
    }
}
</script>

<style>
.add-friend {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    font-size: 1.5rem;
    width: 300px;
    height: fit-content;
    border-radius: 10px;
    border: solid 2px var(--dark-accent);
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: var(--light-accent);
    position: fixed;
}

.add-friend > h2 {
    margin: 0px;
    color: var(--white);
}
.add-friend > .user-selector {
    margin-bottom: 5px;
}

.add-friend > #button-holder {
    width: 100%;
    display: flex;
    height: 40px;
    padding: 5px;
    box-sizing: border-box;
}

.add-friend button {
    flex-grow: 1;
    height: 100%;
    border-radius: 10px;
}

.add-friend button:first-child {
    margin-right: 5px;
}

</style>