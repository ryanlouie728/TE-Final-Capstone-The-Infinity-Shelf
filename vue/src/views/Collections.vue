<template>
  <div class = "user-collections">
    <collection-list v-bind:collections="collections" />
    <div id="sidebar">

    </div>
  </div>
</template>

<script>
import collectionService from '../services/CollectionService';
import CollectionList from '../components/CollectionList.vue'

export default {
  components: { CollectionList },
    name: "user-collections",
    data() {
        return {
            collections: []
        }
    }, 
    methods: {
        getCollections() {
            collectionService.listByUserId(this.$store.state.user.id)
            .then((response) => {
                if (response.status == 200) {
                    this.collections = response.data
                }
            })
        }
    },
    created() {
        this.getCollections();
    }
}
</script>

<style>

</style>