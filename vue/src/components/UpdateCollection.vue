<template>
  <div
    class="update-collection"
    v-on:keyup.enter="update"
    v-on:keyup.esc="cancel"
  >
    <form action="update()">
      <label for="title-input">Title: </label>
      <input
        v-model="collectionUpdate.collectionName"
        id="title-input"
        name="title-input"
        type="text"
      />
      <label for="description-input">Description: </label>
      <textarea
        v-model="collectionUpdate.collectionDescription"
        @keyup.enter="update()"
        
      />
      <label for="privacy-boolean">Collection Privacy</label>
      <input
        type="radio"
        name="public"
        id="public"
        value="true"
        v-model="privacy"
      />
      <label for="public">Public</label>
      <input
        type="radio"
        name="private"
        id="private"
        value="false"
        v-model="privacy"
      />
      <label for="private">Private</label>

      <label for="collection-cover">Select A Collection Cover Image</label>
      <ul name="collection-cover" id="collection-cover">
        <template v-for="comic in collection.comics">
          <li v-bind:key="comic.id" v-if="!isSelected(comic)">
            <img
              class="cover-images"
              v-on:click="setCover(comic)"
              v-bind:src="comic.thumbnailUrl"
            />
          </li>
        </template>
      </ul>
    </form>

    <div class="button-holder">
      <!-- <button v-on:click.prevent="update()">Update</button> -->
      <!-- <button v-on:click.prevent="cancel()">Cancel</button> -->
      <app-button v-on:click.prevent="update()" buttonText="Update"/>
      <app-button v-on:click.prevent="cancel()" buttonText="Cancel"/>
      
    </div>
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService";
import AppButton from "./Button.vue";

export default {
  name: "update-collection",
  components: { AppButton },
  props: ["collection"],
  data() {
    return {
      privacy: this.collection.collectionPublic,
      coverUrl: "",
      collectionUpdate: {
        collectionName: "",
        collectionDescription: "",
        collectionPublic: this.collection.collectionPublic,
      },
    };
  },
  methods: {
    setCover(comic) {
      this.coverUrl = comic.thumbnailUrl;
    },
    isSelected(comic) {
      if (comic.thumbnailUrl === this.collection.collectionCoverUrl) {
        return true;
      } else {
        return false;
      }
    },
    update() {
      const collUpdate = {
        userId: this.collection.userId,
        collectionName:
          this.collectionUpdate.collectionName != ""
            ? this.collectionUpdate.collectionName
            : this.collection.collectionName,
        collectionDescription:
          this.collectionUpdate.collectionDescription != ""
            ? this.collectionUpdate.collectionDescription
            : this.collection.collectionDescription,
        collectionId: this.collection.collectionId,
        collectionPublic: this.privacy,
        collectionCoverUrl:
          this.coverUrl != ""
            ? this.coverUrl
            : this.collection.collectionCoverUrl,
        comics: this.collection.comics,
        characterCounts: this.collection.characterCounts,
        creatorCounts: this.collection.creatorCounts,
      };
      CollectionService.updateCollection(collUpdate).then((response) => {
        if (response.status == 200) {
          window.location.reload();
        }
      });
    },
    cancel() {
      window.location.reload();
    },
  },
};
</script>

<style>
.cover-images {
  height: 164px;
  width: 104px;
}
ul {
  list-style-type: none;
}
textarea{
  width: 300px;
  height: 100px;
}
</style>