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
    flex-grow: 1;
    display: flex;
    flex-direction: column;

}

.comic {
    box-sizing: border-box;
    border: solid 2px black;
    height: 75px;
    width: 100%;
    padding: 5px;
    display: flex;
} 

.thumbnail {
    height: 100%;
    width: auto;
    margin-right: 10px;
}
</style>