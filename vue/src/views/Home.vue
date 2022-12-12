<template>
  <div class="home">
    <div id="left-pane">
      <h1>The Infinity Shelf</h1>
      <p>Description of our website</p>
      <p>New Releases from API?</p>
      <div class="stat-info">
        <div class="character">
          <h3 class="character-title">Characters and their Appearances</h3>
          <div
            class="count-row"
            v-for="character in aggregate.characters.slice(0, 5)"
            v-bind:key="character.name"
          >
            <h4 class="count-name">
              {{ character.name + ", Appearances: " + character.count }}
            </h4>
          </div>
        </div>
        <div class="creator">
          <h3 class="creator-title">Creators and their Appearances</h3>
          <div
            class="count-row"
            v-for="creator in aggregate.creators.slice(0, 5)"
            v-bind:key="creator.name"
          >
            <h4 class="count-name">
              {{ creator.name + ", Appearances: " + creator.count }}
            </h4>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService";

export default {
  name: "home",
  data() {
    return {
      aggregate: {
        creators: [],
        characters: [],
      },
    };
  },
  methods: {
    getAggregate() {
      CollectionService.getAggregateStats().then((response) => {
        if (response.status == 200) {
          this.aggregate = response.data;
          console.log(this.aggregate);
        }
      });
    },
  },
  created() {
    this.getAggregate();
  },
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

  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: flex-start;
}

.stat-info {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-content: flex-start;
  flex-grow: 1;
  justify-content: space-around;
}
h1 {
  font-size: 40px;
}
img {
  height: 40px;
  width: 40px;
}
</style>
