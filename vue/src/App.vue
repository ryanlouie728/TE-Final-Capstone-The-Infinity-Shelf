<template>
  <div id="app">

    <nav>
      <div id="nav-links">
          <div class="nav-link-holder">
        <router-link v-bind:class="{'nav-link-current': this.$route.name == 'home'}" class="nav-link" v-bind:to="{ name: 'home' }">
          <i class="material-icons">home</i>
          <p>Home</p>
        </router-link>
      </div>
      <div class="nav-link-holder">
        <router-link v-bind:class="{'nav-link-current': this.$route.name == 'collections'}" class="nav-link" v-bind:to="{ name: 'collections' }">
          <i class="material-icons">search</i>
          <p>Browse</p>
        </router-link>
      </div>
      <div class="nav-link-holder">
        <router-link v-bind:class="{'nav-link-current': this.$route.name == 'user-profile'}" class="nav-link" v-bind:to="{ name: 'user-profile', params: { username: this.$store.state.user.username } }">
          <i class="material-icons">person</i>
          <p>Profile</p>
        </router-link>
      </div>
      <div class="nav-link-holder">
        <router-link v-bind:class="{'nav-link-current': this.$route.name == 'trade'}" class="nav-link" v-bind:to="{ name: 'trade'}">
          <i class="material-icons">sync_alt</i>
          <p>Trade</p>
        </router-link>
      </div>
        </div>
    </nav>
    
    <header id="header">
      <img id="iron-man" src='./images/Iron-man.gif'>
      <div id="title-nav-block">
        <h1>The Infinity Shelf</h1>
      </div>
      <user-bar />
      <img id="thanos" src='./images/thanos-gangnam.gif'>
    </header>
    <router-view id="router-view" :key="this.$route.path" />
    <footer id="footer">
      <h3>The Infinity Shelf &copy; 2022</h3>
      <div class="creators-footer">
        <h4>Created By: </h4>
        <div class="creators-footer-names">
          <p>Ben Brauns | Hannah Reed | Ryan Louie | Zach Smith</p>
        </div>
      </div>
    </footer>
  </div>
</template>

<script>

import UserBar from './components/UserBar.vue'
export default {
  components: { UserBar }

}
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Rubik+Bubbles&display=swap");

:root{
  --dark-accent: #000505;
  
  --medium-accent: #3B3355;
  
  --light-accent: #5D5D81;
  /* --light-accent: #F0131E; */
  /* --main-background: #BFCDE0; */
  --main-background: #d3e0ee;
  --white: #FEFCFD;
  --background-blur: rgba(0,0,0,0.4);
  --brass-accent: #B4A24C;
  
}

#app {
  height: 100vh;
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
  text-decoration-color: var(--white);
  letter-spacing: .1rem;
}

#app {
  box-sizing: border-box;
  flex-grow: 1;
  display: grid;
  grid-template-columns: 200px 1fr 200px;
  grid-template-rows: 50px 1fr 75px;
  row-gap: 20px;
  column-gap: 10px;
  grid-template-areas: 
    "title title title"
    "nav body sidebar"
    "footer footer footer";
}
#header {
  grid-area: title;
  display: flex;
  flex-direction: row;
  flex-grow: 1;
  justify-content: space-between;
  align-items: center;
  background-color: var(--light-accent);
}

#header > img {
  height: 40px;
}

nav {
  grid-area: nav;
}

#nav-links {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  height: 100%;
  width: 100%;
}

#nav-links > div {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

#nav-links > div > a {
  margin-left: 10px;
  width: 100%;
  text-align: left;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
}

#nav-links > div > a > i {
  display: flex;
  align-items: center;
  height: 100%;
  margin-right: 10px;
  /* color: var(--dark-accent); */
}
#nav-links > div > a > p {
  margin: 0px;
  margin-top: 3px;
  height: 100%;
  display: flex;
  align-items: center;
}

#title-nav-block {
  grid-area: title;
  display: flex;
  justify-content: space-between;
  align-items: center;
  user-select: none;
}

.nav-link {
  box-sizing: border-box;
  font-family: "Bebas Neue", sans-serif;
  letter-spacing: .125rem;
  font-weight: bold;
  text-decoration: none;
  height: 35px;
  background-color: var(--main-background);
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 5px;
  padding: 0px 15px;
  cursor: pointer;
  border-radius: 9px;
  font-size: 1rem;
  text-align: center;
  width: 75px;
  border: none;
}
.nav-link * {
  color: var(--dark-accent);
}


#thanos {
  margin-right: 18px;
}

#iron-man {
  margin-left: 10px;
}

.nav-link:hover {
  background-color: var(--light-accent);
}


.nav-link-holder > * {
  font-weight: bold;
  text-decoration: none;
  color: var(--white);
}

.nav-link-holder:hover > * {
  color: var(--white);
}

#router-view {
  grid-area: body;
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

#footer {
  height: 75px;
  grid-area: footer;
  background-color: var(--light-accent);
  height: max-content;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

#footer > h3 {
  margin: 3px 0px 0px 0px;
}

.creators-footer > * {
  margin: 0px;
}

.creators-footer-names > *{
  margin: 0px 0px 5px 0px;
}
.creators-footer {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.creators-footer-names {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
}

#names {
  height: fit-content;
  padding-right: 10px;
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

.nav-link-current {
  background-color: var(--dark-accent);
  color: var(--white);
}

.nav-link-current:hover {
  background-color: var(--dark-accent);
}

.nav-link-current p {
  color: var(--white);
}
.nav-link-current i {
  color: var(--white);
}




</style>