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

      <p>
        New Releases from API? Random 5 comics that are loaded?
        <strong>SOmething visual here?</strong>
      </p>
      <img src="../images/borat.jpg" alt="something visual" />
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
    let slideIndex = 0;
    showSlides();

    function showSlides() {
      let i;
      let slides = document.getElementsByClassName("mySlides");
      let dots = document.getElementsByClassName("dot");
      for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";  
      }
      slideIndex++;
      if (slideIndex > slides.length) {slideIndex = 1}    
      for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
      }
      slides[slideIndex-1].style.display = "block";  
      dots[slideIndex-1].className += " active";
      setTimeout(showSlides, 2000); // Change image every 2 seconds
    }
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

.about {
  border-style: solid;
  border-radius: 9px;
  border-color: var(--light-accent);
  background-color: var(--light-accent);
  text-decoration-color: white;
}
.count-lines {
  min-height: fit-content;
}
</style>
