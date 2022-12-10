<template>
  <div class="user-selector">
    <input 
        v-on:keyup="getUsers()" 
        v-model="userInput" 
        type="text" 
        list="usernames"
    />
    <datalist id="usernames">
        <option 
            v-for="user in this.users"  v-bind:key="user.id" 
            :value="user.username"
        >{{user.username}}</option>
    </datalist>
  </div>
</template>

<script>
import UserService from '../services/UserService';

export default {
    name: 'user-selector',
    data() {
        return {
            userInput: '',
            users: []
        }
    },
    methods: {
        getUsers() {
            UserService.getByUsername(this.userInput)
            .then(response => {
                if (response.status == 200) {
                    this.users = response.data
                } else {
                    this.users = []
                }
            })
        }
    },
    created() {
        this.getUsers();
    }
}
</script>

<style>
.user-selector {
    display: flex;
    justify-content: center;
    align-items: center;
}


</style>