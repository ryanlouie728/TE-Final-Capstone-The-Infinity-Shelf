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
          for free to add friends, trade, and explore collections.
        </p>
      </div>
      <div class="slideshow">
        <img class="slideshow-image" :src="image" />
      </div>
      <div class="stat-info">
        <div class="character">
          <h3 class="character-title">
            Top 5 Characters In All Collections
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
          <h3 class="creator-title">Top 5 Creators In All Collections</h3>
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
      pictures: [
        'Captain-America.jpg',
        'Avengers.jpg',
        'Amazing-Spider-Man-Fantastic-Four.jpg',
        'Human-Torch.jpg',
        'Amazing-Spider-Man.jpg',
        'Journey-Into-Thor.jpg',
        'X-Men-First-Appearance.jpg',
        'Incredible-Hulk.jpg'

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
      this.currentIndex++;
      if (this.currentIndex > this.pictures.length - 1) {
        this.currentIndex = 0
      }
      setTimeout(this.rotatePicture, 2500)
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
    this.getAggregate();
  }
};
</script>

<style scoped>
.slideshow {
  display: flex;
  justify-content: center;
  width: 100%;
}
.slideshow-image {
  height: auto;
  width: 800px;
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
  margin-bottom: 10px;
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

h3 {
  font-size: 25px;
}


</style>
