<template>
  <div class="public-collections">
    <div id="left-pane">
      <h1>Public Collections</h1>
      <collection-list v-bind:collections="collections" />
    </div>
    <!-- <div id="sidebar"></div> -->
  </div>
</template>
  
  <script>
import collectionService from "../services/CollectionService";
import CollectionList from "../components/CollectionList.vue";

export default {
  components: { CollectionList },
  name: "public-collections",
  data() {
    return {
      collections: [],
    };
  },
  methods: {
    getCollections() {
      collectionService
        .listPublic()
        .then((response) => {
          if (response.status == 200) {
            this.collections = response.data;
          }
        });
    },
  },
  created() {
    this.getCollections();
  },
};
</script>
  
<style>
h1{
  text-align: center;
}
.public-collections {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: center;
}

#collection-list {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    flex-grow: 0;
    margin-bottom: 5px;
}
</style>