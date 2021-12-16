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
        <img
          class="btn newCollection small-button"
          v-if="!isLoading && !showNewCollection && $store.state.token != ''"
          v-on:click="changeShowNewCollection()"
           src="@/assets/newcollection.png" 
        >
         
      
      </div>
<div class="contains-buttons-etc">
      <form v-if="showNewCollection">
        Collection Name:
        <input
          type="text"
          class="form-control"
          v-model="newCollection.collectionName"
        />
        
        <img src="@/assets/save.png" class="small-button" v-on:click="saveNewCollection" >
        
        <img src="@/assets/cancel.png" class="small-button" v-on:click="changeShowNewCollection()">
      </form>
      </div>
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
        userId: "",
      },
      errorMsg: "",
      creationSuccess: false,
      creationAttempted: false,
    };
  },
  created() {
    this.retrieveCollections();
  },
  methods: {
    changeShowNewCollection() {
      this.showNewCollection = !this.showNewCollection;
    },
    retrieveCollections() {
      //what ids to put in - random?
      collectionService.getAllCollections().then((response) => {
        this.$store.commit("SET_HOME_COLLECTIONS", response.data);
        this.isLoading = false;
      });
      /*   if (this.$route.name == "Home" && response.status === 200 && response.data.length > 0) {
          this.$router.push(`/collections/${response.data[0].id}`);
        } */
    },
    saveNewCollection() {
      const newCollectionDTO = {
        collectionName: this.newCollection.collectionName,
        userId: this.$store.state.user.id,
      };
      // this.newCollection.userId = this.$store.state.user.id;
      // this.creationAttemped = true;
      // this.isLoading = true;

      collectionService
        .addCollection(newCollectionDTO)
        .then((response) => {
          if (response.status === 201) {
            // this.retrieveCollections();
            // this.showNewCollection = false;
            // this.newCollection = {
            //   collectionName: "",
            // };
            // this.creationSuccess = response.data;
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "adding");
          this.isLoading = false;
        });
        this.$router.push({ name: 'userDetails', params: { userId: this.$store.state.user.id || 0 }  })
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
.btn-container {
  display: flex;
  justify-content: center;
}
</style>