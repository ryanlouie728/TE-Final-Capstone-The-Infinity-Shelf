<template>
  <div class="user-bar">
    <div 
        id="friends-button"
        class="nav-link-holder"
        v-if="this.$store.state.token != ''"
        v-on:click.self="showFriends = !showFriends"
        :class="{'button-active': showFriends}"
    >Friends
    <friend-page 
        v-if="showFriends"
    />
    </div>
    
    <div class="nav-link-holder" v-if="this.$store.state.token != ''">
        <router-link class="nav-link" v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">Logout</router-link>
    </div>
    <div class="nav-link-holder" v-if="this.$store.state.token == ''">
        <router-link class="nav-link" v-bind:to="{ name: 'login' }" v-if="this.$store.state.token == ''">Login</router-link>
    </div>
        
    
  </div>
</template>

<script>
import FriendPage from './Friends/FriendPage.vue'

export default {
    components: {FriendPage},
    data() {
        return {
            showFriends: false
        }
    }
}
</script>

<style>

.user-bar {
    flex-grow: 1;
    display: flex;
    height: 100%;
    align-items: center;
    justify-content: flex-end;
    user-select: none;
}


#friends-button {
    overflow: visible;
    position: relative;
}
</style>