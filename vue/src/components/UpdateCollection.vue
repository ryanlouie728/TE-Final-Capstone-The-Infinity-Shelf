<template>
  <div
    class="update-collection"
    v-on:keyup.enter="update"
    v-on:keyup.esc="cancel"
    v-on:click.self="cancel"
  >
    <div class="screen-holder">
      <form action="update()">
        <div id="title-input-pair">
          <label class="input-label" for="title-input">Title: </label>
          <input
            v-model="collectionUpdate.collectionName"
            id="title-input-update-collection"
            name="title-input"
            type="text"
          />
        </div>
        
        <label class="input-label" for="description-input">Description: </label>
        <textarea maxlength="250"
          v-model="collectionUpdate.collectionDescription"
          @keyup.enter="update()"
        />

        <div id="privacy-input-holder">
          <label class="input-label" for="privacy-boolean">Collection Privacy:</label>
          <div class="privacy-option">
            <label for="public">Public:</label>
            <input
              type="radio"
              name="public"
              id="public"
              value="true"
              v-model="collectionUpdate.collectionPublic"
            />
          </div>
          
          <div class="privacy-option">
            <label for="private">Private:</label>
            <input
              type="radio"
              name="private"
              id="private"
              value="false"
              v-model="collectionUpdate.collectionPublic"
            />
          </div>        
        </div>
        

        <label class="input-label" for="collection-cover">Select A Collection Cover Image:</label>
        <comic-list
          id="cover-select-list"
          @clicked="setCover(clickedComic)"
          ref="comics"
          :comics="collection.comics"
          :showAdd="false"
          :drag="false"
          :showRemove="false"
          :base="false"
          :onlyThumbnail="true"
        />
      </form>

      <div class="button-holder">
        <app-button v-on:click.prevent="confirming = true" buttonText="Update"/>
        <app-button v-on:click.prevent="cancel()" buttonText="Cancel"/>
      </div>    
    </div>
    <confirm
        v-if="this.confirming"
        :message="'Confirm Update?'"
        :function="update"
        :arguments="[]"
        @cancel="confirming = false"
      />
    </div>
</template>

<script>
import CollectionService from "../services/CollectionService";
import AppButton from "./Button.vue";
import ComicList from './ComicList';
import Confirm from './Confirm.vue';

export default {
  name: "update-collection",
  components: { AppButton, ComicList, Confirm },
  props: ["collection"],
  data() {
    return {
      privacy: this.collection.collectionPublic,
      confirming: false,
      collectionUpdate: this.collection,
    };
  },
  methods: {
    setCover(comic) {
      this.collectionUpdate.collectionCoverUrl = comic.thumbnailUrl;
    },
    update() {
      this.confirming = false;
      CollectionService.updateCollection(this.collectionUpdate).then((response) => {
        if (response.status == 200) {
          this.$emit('updated')
        }
      });
    },
    cancel() {
      this.$emit('cancelled')
    },
    setSelected(comic) {
      this.$refs.comics.selected = [comic];
    }
  },
  computed: {
    clickedComic() {
      let comic = this.collection.comics.find(comic => {
        return comic.id == this.$refs.comics.clickedId;
      })
      this.setSelected(comic);
      return comic
    }
  }
};
</script>

<style>
.update-collection {
  background-color: var(--background-blur);
  top: 0px;
  left: 0px;
  position: fixed;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.screen-holder {
  display: flex;
  flex-direction: column;
  border-style: solid;
  border-radius: 10px;
  color: var(--dark-acent);
  padding: 20px;
  width: 300px;
  height: fit-content;
  max-height: 525px;
  overflow-y: auto;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: var(--light-accent);
}

#cover-select-list {
  height: 200px;
  overflow: auto;
  margin-bottom: 10px;
  justify-content: space-evenly;
}

#privacy-input-holder {
  display: flex;
  flex-direction: column;
}

.privacy-option {
  width: 75px;
  text-align: right;
  margin-left: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.privacy-option > label {
  width: 75px;
}

.input-label {
  font-size: 1.25rem;

}

#title-input-update-collection{
  width: 97%;
  margin-top: 2px;
  margin-bottom: 2px;
}

.cover-images {
  height: 164px;
  width: 104px;
}
ul {
  list-style-type: none;
}

.button-holder{
  display: flex;
  width: 100%;
  flex-direction: row;
  justify-content: space-evenly;
  
}
textarea{
  width: 98%;
  height: 100px;
  font-family:Arial, Helvetica, sans-serif;
}
#title-input{
  width: 90%;
  margin-top: 2px;
  margin-bottom: 2px;

}


</style>