<template>
  <div class="user-bar">
    <div 
        id="friends-button"
        class="buttonStyle"
        v-if="this.$store.state.token != ''"
        v-on:click.self="showFriends = !showFriends"
        :class="{'button-active': showFriends}"
    >
        <i class="material-icons">group</i>
        Friends
        <friend-page 
            v-if="showFriends"
        />
    </div>
    
    <div class="user-link-holder" v-if="this.$store.state.token != ''">
        <router-link class="buttonStyle" v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">
            <i class="material-icons">logout</i>
            Logout
        </router-link>
    </div>
    <div class="user-link-holder" v-if="this.$store.state.token == ''">
        <router-link class="buttonStyle" v-bind:to="{ name: 'login' }" v-if="this.$store.state.token == ''">
            <i class="material-icons">logout</i>
            Login
        </router-link>
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

<style scoped>

.user-link-holder {
    display: flex;

}

.user-bar {
    flex-grow: 1;
    display: flex;
    height: 100%;
    align-items: center;
    justify-content: flex-end;
    user-select: none;
}


#friends-button {
    position: relative;
    display: flex;
    align-items: center;
}

#friends-button > i {
    margin-right: 10px;
}

.buttonStyle {
    text-decoration: none;
    margin-right: 5px;
    padding: 5px 10px;
    border-radius: 9px;
    cursor: pointer;
    font-family: "Bebas Neue", sans-serif;
    background-color: var(--dark-accent);
    color: var(--white);
    text-align: center;
    vertical-align: middle;
    display: flex;
    align-items: center;
}

.buttonStyle:hover {
    background-color: var(--medium-accent);
    
}
.buttonStyle > i {
    margin-right: 10px;
}

.button-active {
  background-color: var(--medium-accent);
}

.button-active:hover {
  background-color: var(--dark-accent);
}

</style>