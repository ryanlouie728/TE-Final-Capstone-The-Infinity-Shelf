<template>
    <div class="friend">
        <div class="friend">
            <router-link class="friend-link" v-bind:to="{ name: 'user-profile', params: {username: this.friend.friendName }}">
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

</style>