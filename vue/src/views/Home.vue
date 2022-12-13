<template>
  <div class="home">
    <div id="left-pane">
      <h1>The Infinity Shelf</h1>
      <div class="about">
        <p>
          Welcome to the <strong>Infinity Shelf</strong>, a Marvel fanatic
          haven. Personalize and keep track of all your favorite Marvel comics
          in private or public collections.
        </p>
        <p>
          <router-link
            style="text-decoration: none"
            :to="{ name: 'register' }"
            id="register"
            >Sign-up</router-link
          >
          for free to add friends, trade, and explore collections
        </p>
      </div>
      <!-- <div class="slideshow-container">

            <div class="mySlides fade">
              <div class="numbertext">1 / 3</div>
              <img src="../images/Amazing-Spider-Man.jpg" style="width:100%">
              <div class="text">Caption Text</div>
            </div>

            <div class="mySlides fade">
              <div class="numbertext">2 / 3</div>
              <img src="../images/X-Men-First-Appearance.jpg" style="width:100%">
              <div class="text">Caption Two</div>
            </div>

            <div class="mySlides fade">
              <div class="numbertext">3 / 3</div>
              <img src="../images/Amazing-Spider-Man-Fantastic-Four.jpg" style="width:100%">
              <div class="text">Caption Three</div>
            </div>

      </div> -->
      <div class="stat-info">
        <div class="character">
          <h3 class="character-title">
            Top 5 Characters throughout Collections
          </h3>
          <div
            class="count-lines"
            v-for="character in aggregate.characters.slice(0, 5)"
            v-bind:key="character.name"
          >
            <h4 class="count-name">
              {{ character.name }} <br />
              {{ "Appearances Count: " + character.count }}
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
              {{ creator.name }} <br />
              {{ "Appearances Count: " + creator.count }}
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
  mounted(){
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

#left-pane img {
  margin: auto;
  height: auto;
  width: 400px;
}

#left-pane {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.stat-info {
  display: flex;
  flex-direction: row;
  min-height: fit-content;
  justify-content: space-around;
}
h1 {
  font-size: 50px;
  display: flex;
  align-items: left;
}
img {
  height: 40px;
  width: 40px;
}

p {
  font-size: 20px;
  padding-left: 20px;
}

.count-lines {
  min-height: fit-content;
}


</style>
