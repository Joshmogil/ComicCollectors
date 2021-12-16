<template>
  <div class="comic">
    <h1>
      {{ detailComic.comicTitle }}
    </h1>
    <img id="comic-photo" :src="detailComic.imgUrl" alt="" />
    <p>
      {{ detailComic.description }}
    </p>
    {{this.message}}
    <div class="btn-container">
      
      <img src="@/assets/addtocollection.png" class="header-button"
      v-if="!isLoading && !showAddToCollection && $store.state.token != ''"
        v-on:click="changeShowAddToCollection()">
    </div>
    <form v-if="showAddToCollection">
      Which collections would you like to add this comic to?
      <div id="listOfUserCollections">
        <div class="text-uppercase text-bold">
        </div>
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th>
                <label class="form-checkbox">
                  <input type="checkbox" v-model="selectAll" @click="allSelected()" />
                  <i class="form-icon"></i>
                </label>
              </th>
              <th>Select All</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="collection in userCollections"
              v-bind:key="collection.collectionId"
            >
              <td>
                <label class="form-checkbox">
                  <input
                    type="checkbox"
                    :value="collection.collectionName"
                    v-model="collection.selected"
                  />
                  <div class="form-icon"></div>
                </label>
              </td>
              <td>{{ collection.collectionName }}</td>
            </tr>
          </tbody>
        </table>
      </div>

      
      <img src="@/assets/save.png" class="header-button" v-on:click="mainAddComic">
      
      <img src="@/assets/cancel.png" class="header-button" v-on:click="changeShowAddToCollection()">
    </form>
  </div>
</template>

<script>
import userService from "../services/UserService";
import comicService from "@/services/ComicService.js";

export default {
  name: "comic",
  data() {
    return {
      comic: {
        marvelId: "",
        comicTitle: "",
        imgUrl: "",
        description: "",
      },
      collections: [],
      showAddToCollection: false,
      message: "",
      isLoading: true,
      selectAll: false
    };
  },
  computed: {
    detailComic() {
      return this.$store.state.detailComic;
    },
    currentUser() {
      return this.$store.state.user;
    },
    userCollections() {
      return this.$store.state.userCollections;
    },
  },
  methods: {
    allSelected(){
        this.userCollections.forEach(collection => {
        collection.selected = true;
          
        });
    },
    changeShowAddToCollection(){
        this.showAddToCollection = !this.showAddToCollection;
    },
    mainAddComic(){
      let collectionList = [];
        this.userCollections.forEach(collection => {

          if (collection.selected === true){
            const addComicDTO = {
                comicId: this.detailComic.comicId,
                collectionId: collection.collectionId,
              };
            collectionList.push(addComicDTO);         
  
          }
        });
        //call service 
        comicService
        .addComicToCollections(collectionList)
        .then(response => {
          if (response.status === 201) {
            this.message = "success";
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "adding");
          this.isLoading = false;
        });

        this.$router.push({ name: 'userDetails', params: { userId: this.$store.state.user.id || 0 }  });
        this.$router.go();

    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " +
          verb +
          " comic. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " comic. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " comic. Request could not be created.";
      }
    },

    getDetailComicForStore() {
      comicService.find(this.$route.params.id).then(response => {
        this.$store.commit("SET_DETAIL_COMIC", response.data);
        this.isLoading = false;
      });
    },

    getComic() {
      comicService.find(this.$route.params.id).then(response => {
        this.comic = response.data;
      });
    },
  },

  getUserCollectionsForStore() {
    userService
      .getUserCollections(this.$store.state.user.id)
      .then(response => {
        this.$store.commit("SET_USER_COLLECTIONS", response.data);
        this.isLoading = false;
      });
  },

  getUserCollections() {
    userService
      .getUserCollections(this.$route.params.userId)
      .then(response => {
        this.collections = response.data;
      });
  },

  created() {
    this.getDetailComicForStore();

    userService
      .getUserCollections(this.$store.state.user.id)
      .then(response => {
        this.$store.commit("SET_USER_COLLECTIONS", response.data);
        this.isLoading = false;
      });
  }
};
</script>
<style>
#comic-photo {
  width: auto;
  height: 75vh;
  display: flex;
}
</style>