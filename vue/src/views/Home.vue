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
      <img class="slideshow-image" :src="image">
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

function getImageUrl(image) {
  var images = require.context("../images/", false)
  return images('./' + image)
}


export default {
  name: "home",
  data() {
    return {
      currentIndex: 0,
      currentPicture: '',
      pictures: [
        'borat.jpg',
        'no-cover.jpg',
        'Amazing-Spider-Man-Fantastic-Four.jpg',
        'Amazing-Spider-Man.jpg'
      ],
      paths: [],
      aggregate: {
        creators: [],
        characters: []
      },
    };
  },
  methods: {
    rotatePicture() {
      if (this.currentIndex > this.pictures.length - 1) {
        this.currentIndex = 0
      }
      this.currentPicture = this.pictures[this.currentIndex];
      this.currentIndex++;
      setTimeout(this.rotatePicture, 5000)
    },
    getAggregate() {
      CollectionService.getAggregateStats().then((response) => {
        if (response.status == 200) {
          this.aggregate = response.data;
          console.log(this.aggregate);
        }
      });
    }
  },
  computed: {
    image() {
      return this.paths[this.currentIndex]
    }
  },
  created() {
    for (let picture of this.pictures) {
      this.paths.push(getImageUrl(picture))
    }
  },
  mounted(){
    this.rotatePicture();

  }
};
</script>

<style scoped>
.slideshow-image {
  height: 500px;
  width: 500px;
}
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

.about {
  text-decoration-color: white;
}
.count-lines {
  min-height: fit-content;
}
</style>
