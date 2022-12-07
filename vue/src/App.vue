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
:root{
  --dark-accent: #000505;
  --medium-accent: #3B3355;
  --light-accent: #5D5D81;
  --main-background: #BFCDE0;
  --white: #FEFCFD;
}

html {
  box-sizing: border-box;
  min-height: 100vh;
  background-color: var(--main-background);
}

body {
  box-sizing: border-box;
  margin: 0px;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

#app {
  box-sizing: border-box;
  padding: 8px;
  flex-grow: 1;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 75px 50px 1fr 100px;
  grid-template-areas: 
    "title"
    "nav"
    "body"
    "footer";
}
header.h1 {
  grid-area: title;
}

#nav {
  grid-area: nav;
  margin-top: 0.2em;
  margin-bottom: 0.2em;
  padding-top: 0.2em;
  padding-bottom: 0.2em;
  height: fit-content;
  background-color: var(--white);
  border-top: solid 2px var(--dark-accent);
  border-bottom: solid 2px var(--dark-accent);
}

#router-view {
  grid-area: body;
}

#sidebar {
    width: 200px;
    background-color: var(--light-accent);
    border: solid 2px var(--dark-accent);
}

</style>