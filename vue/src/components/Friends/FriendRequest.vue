<template>
    <div class="friend-request">
        <router-link class="friend-request-link" v-bind:to="{ name: 'user-profile', params: {username: this.request.fromName }}">
            <p>{{ this.request.fromName }}</p>
        </router-link>
        <button v-on:click.prevent="requestAccepted(this.request.requestId)" class="friend-request-button">&#10004;</button>
        <button v-on:click.prevent="requestRejected(this.request.requestId)" class="friend-request-button">&#10006;</button>
    </div>
</template>

<script>
export default {
    name: 'friend-request',
    props: ['request'],
    methods: {
    requestAccepted(requestId) {
      console.log('accept' + requestId)
    },
    requestRejected(requestId) {
      console.log('reject' + requestId)
    }
    }
}
</script>

<style>

</style>