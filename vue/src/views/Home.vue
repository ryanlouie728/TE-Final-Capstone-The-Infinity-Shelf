<template>
  <div class="home">
    <div id="left-pane">
      <h2>Aggregate Stats</h2>
    </div>
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService';

export default {
  name: "home",
  data() {
    return {
      aggergate: {}
    }
  },
  methods: {
    getAggregate() {
      CollectionService.getAggregateStats()
      .then(response => {
        if (response.status == 200) {
          this.aggergate = response.data
          console.log(this.aggergate)
        }
      })
    }
  },
  created() {
    this.getAggregate();
  }
};
</script>

<style scoped>
.home {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
}

#left-pane {
  width: 66%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: flex-start;
}
</style>
