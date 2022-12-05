<template>
  <div id="app">
    <header id="header">
      <h1>Comic Book Collection</h1>
    </header>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: '' }">All Collections</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: '' }">My Collections</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>
#nav {
    margin-top: 0.2em;
    margin-bottom: 0.2em;
    padding-top: 0.2em;
    padding-bottom: 0.2em;
    background-color: #BFBDB8;
    border-top: solid 1px black;
    border-bottom: solid 1px black;
}

</style>