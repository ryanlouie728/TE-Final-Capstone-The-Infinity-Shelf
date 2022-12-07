<template>
  <div class="comic-list">
    <div class="comic" v-for="comic in this.comics" v-bind:key="comic.id" v-on:click.prevent="clicked(comic.id)">
        <img class="thumbnail" v-if="comic.thumbnailUrl" v-bind:src="(comic.thumbnailUrl)"/>
        <div class="comic-text">
            <h3 class="comic-title">{{comic.title}}</h3>
            <!-- <p class="comic-description">{{comic.description}}</p> -->
        </div>
    </div>
  </div>
</template>

<script>
export default {
    props: ['comics'],
    name: 'comic-list',
    data() {
        return {
            clickedId: ''
        }
    },
    methods: {
        clicked(id) {
            this.clickedId = id;
            this.$emit('clicked')
        }
    }
}
</script>

<style>
.comic-list {
    display: flex;
    flex-direction: row;
    width: 100%;
    flex-wrap: wrap;
    align-content: flex-start;
}

.comic {
    box-sizing: border-box;
    border: solid 2px var(--dark-accent);
    width: 120px;
    height: 200px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-right: 2px;
    margin-bottom: 2px;
}

.comic-title {
    font-size: .5rem;
}

.comic-text {
    text-align: center;
    height: 25px;
}

.thumbnail {
    height: auto;
    width: 92%;
}
</style>