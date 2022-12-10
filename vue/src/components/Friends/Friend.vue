<template>
    <div class="friend">
        <div class="friend">
            <router-link class="friend-link button" v-bind:to="{ name: 'user-profile', params: {username: this.friend.friendName }}">
                <p>{{ this.friend.friendName }}</p>
            </router-link>
        </div>
    </div>
</template>

<script>

export default {
    name: 'friend',
    props: ['friend'],
}
</script>

<style>
.friend {
  width: 100%;
  display: flex;
  height: 50px;
  justify-content: center;
  align-items: center;
}

.friend-link {
  border-radius: 10px;
  width: 75%;
  height: 75%;
  margin: 0px;
}
.friend-link > p {
  margin: 0px;
}
</style>