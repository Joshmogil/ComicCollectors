<template>
  <div id="home">
    <div class="collections-list">
      <div class="status-message error" v-show="errorMsg !== ''">
        {{ errorMsg }}
      </div>
      <!--
      <div class="loading" v-if="isLoading">
        <img src="../src/assets/marvel.gif" />
      </div>
      add a v-else to router link before div tag
      -->
      <div class="btn-container">
        <button
          class="btn newCollection"
          v-if="!isLoading && !showNewCollection && $store.state.token != ''"
          v-on:click="showNewCollection = !showNewCollection"
        >
          New Collection
        </button>
      </div>

      <form v-if="showNewCollection">
        Collection Name:
        <input
          type="text"
          class="form-control"
          v-model="newCollection.collectionName"
        />
        <button class="btn btn-submit" v-on:click="saveNewCollection">
          Save
        </button>
        <button
          class="btn btn-cancel"
          v-on:click="showNewCollection = !showNewCollection"
        >
          Cancel
        </button>
      </form>

      <!--   <h1>Our top collections</h1>
  <div id="collection-table">
    <section id= "vertical-collections" v-for="collection in this.$store.state.collections" v-bind:key="collection.collectionId">
      <h3><router-link v-bind:to="{ name: 'collectionDetails', params: { collectionId: collection.collectionId } }">
        {{collection.collectionName}}
        </router-link>  </h3>


      <section id= "horizontal-collection" v-for="comic in collection.comics" v-bind:key="comic.id">
        <router-link v-bind:to="{ name: 'comicDetails', params: { id: comic.id}}">
          <div class="card-container">
  <div class="card">
    <div class="side"><img :src='comic.img' alt=""></div>
    <div class="side back">{{comic.description}}</div>
  </div>
</div>
        
        </router-link>
      </section>
    </section>
  </div> -->
    </div>
  </div>
</template>

<script>
import collectionService from "../services/CollectionService";

export default {
  data() {
    return {
      isLoading: true,
      showNewCollection: false,
      newCollection: {
        collectionName: "",
      },
      errorMsg: "",
    };
  },
  created() {
    this.retrieveCollections();
  },
  methods: {
    retrieveCollections() {
      //what ids to put in - random?
      collectionService.getAllCollections().then((response) => {
        this.$store.commit("SET_ALL_COLLECTIONS", response.data);
        this.isLoading = false;
      });
      /*   if (this.$route.name == "Home" && response.status === 200 && response.data.length > 0) {
          this.$router.push(`/collections/${response.data[0].id}`);
        } */
    },
    saveNewCollection() {
      this.isLoading = true;
      collectionService
        .addCollection(this.newCollection)
        .then((response) => {
          if (response.status === 201) {
            this.retrieveCollections();
            this.showNewCollection = false;
            this.newCollection = {
              collectionName: "",
            };
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "adding");
          this.isLoading = false;
        });
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " +
          verb +
          " collection. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " collection. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " collection. Request could not be created.";
      }
    },
    //unnecessary hex code
    generateHexCode() {
      var bg = Math.floor(Math.random() * 16777215).toString(16);
      if (bg.length !== 6) bg = this.generateHexCode();
      return bg;
    },
  },
};
</script>

<style>
.btn-container{
  display: flex;
  justify-content: center;
}
</style>