<template>
  <div class="home">
    <h1>Home</h1>
    <!-- <p>You must be authenticated to see this</p> -->
    <comics-list></comics-list>
  </div>
</template>

<script>
import ComicsList from '../components/ComicsList.vue';


export default {
  name: "home",
  components: {
    ComicsList
  }
};
</script>
