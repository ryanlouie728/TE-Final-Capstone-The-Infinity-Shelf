<template>
  <div class="home">
    <div id="left-pane">
      <h1>The Infinity Shelf</h1>
      <p>This application allows users to manage their comic book collections and arrange trades with other users.</p>
      <p>New Releases from API? Random 5 comics that are loaded? SOmething visual here?</p>
      <div class="stat-info">
        <div class="character">
          <h3 class="character-title">Top 5 Characters throughout Collections</h3>
          <div
            class="count-lines"
            v-for="character in aggregate.characters.slice(0, 5)"
            v-bind:key="character.name"
          >
            <h4 class="count-name">
              {{ character.name}} <br>
              {{ 'Appearances Count: ' + character.count}}
            </h4>
          </div>
        </div>
        <div class="creator">
          <h3 class="creator-title">Top 5 Creators throughout Collections</h3>
          <div
            class="count-lines"
            v-for="creator in aggregate.creators.slice(0, 5)"
            v-bind:key="creator.name"
          >
            <h4 class="count-name">
              {{ creator.name}} <br>
              {{ 'Appearances Count: ' + creator.count}}
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
}

.stat-info {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
h1 {
  font-size: 40px;
}
img {
  height: 40px;
  width: 40px;
}

p {
  font-size: 20px;
}
.count-lines{
  max-height: 50px;

}
</style>
