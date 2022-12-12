<template>
  <div id="app">

    <nav>
      <div id="nav-links">
          <div class="nav-link-holder">
        <router-link class="nav-link" v-bind:to="{ name: 'home' }">Home</router-link>
      </div>
      <div class="nav-link-holder">
        <router-link class="nav-link" v-bind:to="{ name: 'collections' }">Browse</router-link>
      </div>
      <div class="nav-link-holder">
        <router-link class="nav-link" v-bind:to="{ name: 'user-profile', params: { username: this.$store.state.user.username } }">Profile</router-link>
      </div>
      <div class="nav-link-holder">
        <router-link class="nav-link" v-bind:to="{ name: 'trade'}">Trade</router-link>
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
          <h5 id="names">Ben Brauns</h5>
          <h5 id="names">Hannah Reed</h5>
          <h5 id="names">Ryan Louie</h5>
          <h5 id="names">Zach Smith</h5>
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
  --main-background: #BFCDE0;
  --white: #FEFCFD;
  --background-blur: rgba(0,0,0,0.4);
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
  flex-grow: 1;
  display: grid;
  grid-template-columns: 200px 1fr 200px;
  grid-template-rows: 50px 1fr 100px;
  row-gap: 20px;
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
  margin-bottom: 5px;
  display: flex;
  align-items: center;
  justify-content: center;
}

#nav-links > div > a {
  width: 80%;
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
  color: var(--white);
  height: 35px;
  background-color: var(--medium-accent);
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

#thanos {
  margin-right: 18px;
  
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
  grid-area: footer;
  background-color: var(--light-accent);
  border: solid 2px var(--dark-accent);
  border-radius: 9px 9px 9px 9px;
  padding-left: 5px;
  padding-right: 5px;
  height: max-content;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.creators-footer {

  display: flex;
  flex-direction: row;
  max-height: 100px;
  align-items: center;
  padding-right: 77px
}
.creators-footer-names {
  display: flex;
  flex-direction: column;
  max-height: 125px;
  flex-wrap: wrap;
  align-items: flex-start;
  padding-left: 15px
}

#names {
  padding-right: 5px;
  height: fit-content;
  margin-top: 15px;
  margin-bottom: 10px;
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