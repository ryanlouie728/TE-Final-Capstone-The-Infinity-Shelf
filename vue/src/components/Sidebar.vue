<template>
  <div class="sidebar">
    <friend-list />
  </div>
</template>

<script>
import FriendList from './FriendList.vue'

export default {
    components: { FriendList },
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