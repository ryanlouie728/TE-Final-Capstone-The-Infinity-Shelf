<template>
  <div class="user-bar">
    <button 
        class="nav-link-holder"
        v-if="this.$store.state.token != ''"
    >Friends</button>
    <div class="nav-link-holder" v-if="this.$store.state.token != ''">
        <router-link class="nav-link" v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">Logout</router-link>
    </div>
    <div class="nav-link-holder" v-if="this.$store.state.token == ''">
        <router-link class="nav-link" v-bind:to="{ name: 'login' }" v-if="this.$store.state.token == ''">Login</router-link>
    </div>
        
    <friend-page 
        
    />
  </div>
</template>

<script>
import FriendPage from './Friends/FriendPage.vue'

export default {
    components: {FriendPage},
    data() {
        return {

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
    justify-content: end;
}

</style>