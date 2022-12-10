<template>
  <div id="app">
    <header id="header">
      <h1>The Infinity Shelf</h1>
      <img id="thanos" src="https://media.tenor.com/DRbhYjCbnD0AAAAi/thanos-gangnam-style.gif">
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
    <router-view id="router-view" :key="this.$route.path" />
    <footer id="footer">
      <h4>Copyright</h4>
    </footer>
  </div>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Rubik+Bubbles&display=swap");

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
  font-family: "Bebas Neue", sans-serif;
  letter-spacing: .1rem;
}

#app {
  box-sizing: border-box;
  padding: 8px;
  flex-grow: 1;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 75px 50px 1fr 100px;
  row-gap: 5px;
  grid-template-areas: 
    "title"
    "nav"
    "body"
    "footer";
}
#header {
  grid-area: title;
  display: flex;
  flex-direction: row;
  flex-grow: 1;
  justify-content: space-between;
  align-items: center;

}

#header > img {
  height: 80px;
  display: flex;
  flex-direction: row;
}


#nav {
  box-sizing: border-box;
  grid-area: nav;
  padding: 3px 5px;
  height: 50px;
  background-color: var(--light-accent);
  border: solid 2px var(--dark-accent);
  display: flex;
  align-items: center;
  border-radius: 9px;
  
}

.nav-link-holder {
  height: 80%;
  background-color: var(--medium-accent);
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 5px;
  padding: 0px 15px;
  cursor:pointer;
  border-radius: 9px;
  letter-spacing: .125rem;
}

.nav-link-holder:hover {
  background-color: var(--main-background);
  color: var(--dark-accent);
  font-size: 150%;
 
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

#footer {
  background-color: var(--light-accent);
  border: solid 2px var(--dark-accent);
  border-radius: 9px 9px 9px 9px;
}

.button {
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  text-decoration: none;
  background-color: var(--medium-accent);
  color: var(--white);
  cursor: pointer;
  border: none;
}

.button:hover {
  background-color: var(--dark-accent);
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