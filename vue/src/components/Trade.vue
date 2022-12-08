<template>
  <div class="trade">
    <input v-on:keyup="getUsers()" @change="UserSelected" v-model="userInput" type="text" list="usernames">
    <datalist id="usernames">
        <option v-for="user in this.users" v-on:click="userSelected(user.id)" v-bind:key="user.id" :value="user.username">{{user.username}}</option>
    </datalist>
    <!-- <select id="user-select" name="user">

    </select> -->
    <!-- <input id="" type="text"> -->
  </div>
</template>

<script>
import UserService from '../services/UserService';

export default {
    name: 'trade',
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
                    this.users = response.data;
                    //console.log(this.users);
                }
            })
        },
        UserSelected() {
            console.log(this.userInput)
        }
    },
    created() {
        this.getUsers();
    }
}
</script>

<style>
.trade {
    background-color: var(--dark-accent);
    position: fixed;
    width: 200px;
    top: calc(50vh - 100px);
    left: calc(50vw - 100px);
    height: 200px;
}

#user-select {
    width: 100px;
}
</style>