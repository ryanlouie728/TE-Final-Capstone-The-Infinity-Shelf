<template>
  <div id="app">
    <header id="header">
      <h1>Comic Book Collection</h1>
    </header>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'collections' }">All Collections</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'user-collections', params: { username: this.$store.state.user.username } }">My Collections</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'login' }" v-if="this.$store.state.token == ''">Login</router-link>
    </div>
    <router-view id="router-view" />
  </div>
</template>

<style>
html {
  box-sizing: border-box;
  min-height: 100vh;
}

body {
  box-sizing: border-box;
  margin: 0px;
  padding: 8px;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

#app {
  flex-grow: 1;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 100px 1fr 100px;
  grid-template-areas: 
    "nav"
    "body"
    "footer";
}

header {
  grid-area: nav;
}

#nav {
  grid-area: nav;
  margin-top: 0.2em;
  margin-bottom: 0.2em;
  padding-top: 0.2em;
  padding-bottom: 0.2em;
  background-color: #BFBDB8;
  border-top: solid 1px black;
  border-bottom: solid 1px black;
}

#router-view {
  grid-area: body;
  display: flex;
  flex-direction: row;
}

#sidebar {
    width: 200px;
    background-color: lightgray;
    border: solid 2px darkgray;
}

</style>