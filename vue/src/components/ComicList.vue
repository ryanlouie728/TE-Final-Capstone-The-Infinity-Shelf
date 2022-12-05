<template>
  <div class="comic-list">
    <div class="comic" v-for="comic in this.comics" v-bind:key="comic.id">
        <img v-if="comic.thumbnail" v-bind:src="(comic.thumbnail)"/>
        <div class="comic-text">
            <h3 class="comic-title">{{comic.title}}</h3>
            <p class="comic-description">{{comic.description}}</p>
        </div>
    </div>
  </div>
</template>

<script>
export default {
    props: ['comics'],
    name: 'comic-list'
}
</script>

<style>

</style>