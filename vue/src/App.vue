<template>
  <div id="app">
    <header id="header">
      <h1>Comic Book Collection</h1>
    </header>
    <div id="nav">
      <div class="nav-link-holder">
        <router-link class="nav-link" v-bind:to="{ name: 'home' }">Home</router-link>
      </div>
      <div class="nav-link-holder">
        <router-link class="nav-link" v-bind:to="{ name: 'collections' }">All Collections</router-link>
      </div>
      <div class="nav-link-holder">
        <router-link class="nav-link" v-bind:to="{ name: 'user-profile', params: { username: this.$store.state.user.username } }">Profile</router-link>
      </div>
      <div class="nav-link-holder">
        <router-link class="nav-link" v-bind:to="{ name: 'trade'}">Trade</router-link>
      </div>
      <div class="nav-link-holder" v-if="this.$store.state.token != ''">
        <router-link class="nav-link" v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">Logout</router-link>
      </div>
      <div class="nav-link-holder" v-if="this.$store.state.token == ''">
        <router-link class="nav-link" v-bind:to="{ name: 'login' }" v-if="this.$store.state.token == ''">Login</router-link>
      </div>
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
  padding: 0px 5px;
  height: 30px;
  background-color: var(--light-accent);
  border-top: solid 2px var(--dark-accent);
  border-bottom: solid 2px var(--dark-accent);
  display: flex;
  align-items: center;
}

.nav-link-holder {
  height: 80%;
  background-color: var(--medium-accent);
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 5px;
  padding: 0px 5px;
  cursor:pointer;
}

.nav-link-holder:hover {
  background-color: var(--main-background);
  color: var(--dark-accent);
}

.nav-link-holder > * {
  font-weight: bold;
  text-decoration: none;
  color: var(--white);
}

.nav-link-holder:hover > * {
  color: var(--dark-accent);
}

#router-view {
  grid-area: body;
}

#sidebar {
  width: 200px;
  background-color: var(--light-accent);
  border: solid 2px var(--dark-accent);
}

#sidebar > button {
  display: block;
  margin-top: 10px;
  margin-inline: auto;
  width: max-content;
  cursor: pointer;
}

::-webkit-scrollbar {
    width: 10px;
}

::-webkit-scrollbar-track {
  background: var(--medium-accent);
}

::-webkit-scrollbar-thumb {
  background: #888;
}

::-webkit-scrollbar-thumb:hover {
  background: #555;
}

</style>