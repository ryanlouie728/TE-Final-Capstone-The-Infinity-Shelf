<template>
  <div class="comics-list">
      <table>
          <tr>
              <th>Title</th>
              <th>Issue Number</th>
              <th>Description</th>
              <th>ThumbNail</th>
          </tr>
          <tr v-for="comic in comics" v-bind:key="comic.title">
              <td>{{ comic.title}}</td>
              <td>{{ comic.issueNumber }}</td>
              <td>{{ comic.description }}</td>
              <td>{{ comic.thumbnailUrl }}</td>
          </tr>
      </table>
  </div>
</template>

<script>
import comicService from '../services/ComicService.js'

export default {
    name: 'comics-list',
    data(){
        return {
            comics: []
        }
    },
    created() {
        comicService.list().then( response => {
            this.comics = response.data;
        })
    }
}
</script>

<style>

</style>