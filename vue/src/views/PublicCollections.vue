<template>
  <div class="public-collections">
    <div id="left-pane">
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
.public-collections {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: center;
}

#collection-list {
    flex-grow: 0;
    margin-bottom: 5px;
}

#left-pane {
    width: 66%;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-content: flex-start;
}
</style>