<template>
  <div class="friend-list">
    <div id="friend-list">
        <h4 id="friend-list-title">Friends</h4>
        <div class="friend" v-for="friend in this.friends" v-bind:key="friend.id">
            <p>{{ friend.friendName }}</p>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'friend-list',
  props: ['friends']
}
</script>

<style>
#friend-list {
    width: auto;
    padding: 10px;
    display: flex;
    flex-direction: column;
}

#friend-list-title {
    color: var(--white);
    margin-top: 20px;
    margin-bottom: 5px;
    text-align: center;
    font-size: 2rem;
    font-weight: bolder;
}

.friend > p {
    text-align: center;
    color: var(--white);
    margin: 0px;
}
</style>