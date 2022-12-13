<template>
  <div class="create-collection">
    <div id="drag-handle">Create New Collection</div>
    <label for="title-input">Collection Title:</label>
    <input
      v-model="collection.collectionName"
      id="title-input"
      name="title-input"
      type="text"
    />
    <label for="description-input">Description</label>
    <textarea v-model="collection.collectionDescription" maxlength="250" />
    <div class="button-holder">
      <app-button v-on:click.prevent="create()" buttonText="Create" />
      <app-button v-on:click.prevent="cancel()" buttonText="Cancel" />
      <!-- <button v-on:click.prevent="create()">Create</button>
        <button v-on:click.prevent="cancel()">Cancel</button> -->
    </div>
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService";
import AppButton from "./Button.vue";
export default {
  name: "create-collection",
  components: { AppButton },
  props: ["creatingCollection"],
  data() {
    return {
      collection: {
        userId: this.$store.state.user.id,
        collectionName: "",
        collectionDescription: "",
        collectionCoverUrl: "",
      },
    };
  },
  methods: {
    create() {
      CollectionService.createCollection(this.collection).then((response) => {
        if (response.status == 200) {
          this.collection.collectionName = "";
          this.collection.collectionDescription = "";
          this.$emit("collectionCreated");
        } else {
          console.log("problem creating collection");
        }
      });
    },
    cancel() {
      this.collection.collectionName = "";
      this.collection.collectionDescription = "";
      this.$emit("collectionCreated");
    },
  },
};
</script>

<style>
.create-collection {
  /* display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: fixed;
    width: 200px;
    height: auto;
    left: calc(50vw - 100px);
    top: calc(50vh - 100px);
    background-color: var(--light-accent);
    padding: 10px;
    border: solid 2px black;
    border-radius: 9px; */

    width: 400px;
    height: auto;
    background-color: var(--light-accent);
    position: fixed;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    display: flex;
    flex-direction: column;
    align-items: center;
    border: solid 2px black;
    border-radius: 9px;
}

.create-collection > input {
  width: 100%;
}

.create-collection > textarea {
  width: 90%;
  height: 100px;
  margin-bottom: 10px;
  resize: none;
}
.button-holder {
  width: 80%;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  padding-bottom: 5px;
}
</style>