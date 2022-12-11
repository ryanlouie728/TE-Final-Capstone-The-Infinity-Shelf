<template>
  <div class="sidebar">
    <friend-page />
  </div>
</template>

<script>
import FriendPage from './Friends/FriendPage.vue'

export default {
    components: { FriendPage },
    name: 'sidebar'
}
</script>

<style>

.sidebar {
  width: 200px;
  background-color: var(--light-accent);
  border: solid 2px var(--dark-accent);
  border-radius: 9px;
}

</style>