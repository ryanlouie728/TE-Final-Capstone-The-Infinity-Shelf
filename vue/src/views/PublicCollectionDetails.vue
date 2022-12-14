<template>
  <div>
    <div class="collection-details" v-if="collection.collectionPublic === true">
      <div
        class="collection-button-holder"
        v-show="this.collection.userId == this.$store.state.user.id"
      >
        <icon-button
          v-on:click="updatingCollection = true"
          :buttonText="'Edit'"
          :iconName="'edit'"
        />
        <icon-button
          v-on:click="exportAsCsv"
          :buttonText="'Export'"
          :iconName="'download'"
        />
        <icon-button
          v-on:click="selectFile"
          :buttonText="'Import'"
          :iconName="'upload'"
        />
      </div>
      <div id="left-pane">
        <div id="img">
          <img
            v-bind:src="this.collection.collectionCoverUrl"
            alt=""
            id="cover-image"
          />
        </div>
        <div id="coll-desc">
          <div id="coll-desc-title">
            <h1>{{ this.collection.collectionName }}</h1>
            <h3 v-if="!this.collection.collectionPublic">(PRIVATE)</h3>
            <h3 v-if="this.collection.collectionPublic">(PUBLIC)</h3>
          </div>

          <p>{{ this.collection.collectionDescription }}</p>
        </div>
        <update-collection
          v-if="updatingCollection"
          v-bind:collection="this.collection"
          @cancelled="
            updatingCollection = false;
            getCollection();
          "
          @updated="
            updatingCollection = false;
            getCollection();
          "
        />
        <div id="add-comic">
          <h2 banner>
            Comics in {{ this.collection.collectionName }} Collection
          </h2>
          <comic-list
            v-bind:base="this.collection"
            v-bind:showRemove="
              this.collection.comics.length > 0 &&
              this.$store.state.user.id == this.collection.userId
            "
            ref="comics"
            @resetComics="getCollection()"
            @clicked="comicClicked()"
            v-bind:comics="this.collection.comics"
          />
        </div>
        <div class="character-list">
          <h2 class="count-title">Top Character Appearances</h2>
          <div
            class="count-row"
            v-for="character in this.collection.characterCounts.slice(0, 5)"
            v-bind:key="character.name"
          >
            <h4 class="count-name">{{ character.name }}</h4>
          </div>
        </div>
        <div class="creator-list">
          <h2 class="count-title">Top Creator Appearances</h2>
          <div
            class="count-row"
            v-for="creator in this.collection.creatorCounts.slice(0, 5)"
            v-bind:key="creator.name"
          >
            <h4 class="count-name">{{ creator.name }}</h4>
          </div>
        </div>
      </div>
    </div>
    <div class="private" v-if="collection = {}">
      <h1>
        You are attempting to access a collection that is either Private or does
        not exist
      </h1>
      <h3>Go back from whence ye came!</h3>
    </div>
  </div>
</template>

<script>
import ComicList from "../components/ComicList.vue";
import CollectionService from "../services/CollectionService";
import IconButton from "../components/IconButton.vue";

export default {
  components: { ComicList, IconButton },
  props: ["collection-details"],
  name: "collection-details",
  data() {
    return {
      collection: {
        comics: [],
        characterCounts: [],
        creatorCounts: [],
      },
      addingComic: false,
      updatingCollection: false,
      removingComic: false,
      selectedComics: [],
    };
  },
  methods: {
    collectionPrivacy() {
      return this.collection.collectionPublic ? "Public" : "Private";
    },
    getCollection() {
      CollectionService.displayPublicCollection(this.$route.params.id).then(
        (response) => {
          if (response.status == 200) {
            this.collection = response.data;
          }
        }
      );
    },
    comicClicked() {
      if (this.removingComic) {
        this.selectedComics.push(this.$refs.comics.clickedId);
        console.log(this.selectedComics);

        CollectionService.removeComicFromCollection(
          this.collection.collectionId,
          this.$refs.comics.clickedId
        ).then((response) => {
          if (response.status == 200) {
            this.getCollection();
          }
        });
      } else {
        this.$router.push({
          name: "comic-details",
          params: {
            id: this.$refs.comics.clickedId,
          },
        });
      }
    },
    comicAdded() {
      this.addingComic = false;
      this.getCollection();
    },
    exportAsCsv() {
      let fileText = "";
      for (let comic of this.collection.comics) {
        fileText += comic.id + ",";
      }
      fileText = fileText.slice(0, fileText.lastIndexOf(","));
      this.downloadStringAsFile(fileText);
    },
    downloadStringAsFile(text) {
      const link = document.createElement("a");
      const file = new Blob([text], { type: "text/plain" });
      link.href = URL.createObjectURL(file);
      link.download =
        this.collection.collectionName +
        "-" +
        this.collection.collectionId +
        ".csv";
      link.click();
      URL.revokeObjectURL(link.href);
    },
    selectFile() {
      let selector = document.createElement("input");
      selector.style.display = "none";
      selector.id = "csv-import";
      selector.type = "file";
      selector.onchange = this.importCsv;
      document.body.appendChild(selector);
      selector.click();
      //console.log(selector.files)
    },
    importCsv() {
      //let file = document.getElementById('csv-import').files[0]
      let file = document.getElementById("csv-import").files[0];
      let reader = new FileReader();

      reader.addEventListener("load", () => {
        if (reader.result) {
          let comics = reader.result.split(",");
          for (let id of comics) {
            CollectionService.addComicToCollection(
              this.collection.collectionId,
              id
            ).then((response) => {
              if (response.status == 200) {
                this.getCollection();
              }
            });
          }
        }
      });
      reader.readAsText(file);
    },
  },
  created() {
    this.getCollection();
  },
};
</script>

<style scoped>
.collection-details {
  position: static;
}

.private {
  text-align: center;
}

#left-pane {
  width: 100%;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  row-gap: 5px;
  grid-template-areas:
    "img add-comic add-comic"
    "coll-desc  character-list creator-list";
}
#add-comic {
  grid-area: add-comic;
}

#add-comic > .comic-list {
  overflow-y: auto;
  height: 425px;
}

.collection-button-holder {
  border-top: solid 2px var(--medium-accent);
  width: 185px;
  display: flex;
  flex-direction: column;
  position: absolute;
  top: 210px;
  left: 10px;
  font-weight: bold;
  animation: fade-in 0.1s linear;
}

#img {
  grid-area: img;
  display: block;
  text-align: center;
}
#cover-image {
  height: 500px;
}
#coll-desc {
  grid-area: coll-desc;
  text-align: center;
  display: flex;
  flex-direction: column;
}

#coll-desc-title {
  display: flex;
  align-items: center;
  justify-content: center;
}

#coll-desc-title h1 {
  margin-right: 10px;
}

#remove-collection {
  grid-area: remove-coll;
  display: flex;
  align-items: center;
}
.character-list {
  grid-area: character-list;
  width: 50%;
  min-width: fit-content;
  min-height: fit-content;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}
.creator-list {
  grid-area: creator-list;
  width: 50%;
  min-width: fit-content;
  min-height: fit-content;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}
.character-list * {
  margin: 0px;
  padding: 5px;
}
.creator-list * {
  margin: 0px;
  padding: 5px;
}
</style>