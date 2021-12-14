<template>
  <div>
    <h1>{{ user.username }}</h1>
    <div id="collection-table">
      <section id="collections">
        <div v-for="collection in userCollections" v-bind:key="collection.collectionId">
          <h3>
            <router-link
              v-bind:to="{
                name: 'collectionDetails',
                params: { collectionId: collection.collectionId },
              }"
            >
              {{ collection.name }}
            </router-link>
          </h3>

          <section id="collection-comics">
            <div v-for="comic in collection.comicList" v-bind:key="comic.comicId">
              <router-link
                v-bind:to="{
                  name: 'comicDetails',
                  params: { id: comic.comicId },
                }"
              >
                <div class="card-container">
                  <div class="card">
                    <div class="side"><img :src="comic.imgUrl" alt="" /></div>
                    <div class="side back">{{ comic.description }}</div>
                  </div>
                </div>
              </router-link>
            </div>
          </section>
         
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import userService from "../services/UserService";
export default {
  data() {
    return {
      isLoading: true,
      errorMsg: "",
      collections: [],
    };
  },

  created() {
    this.getUserCollectionsForStore();
  },

  computed: {
      userCollections(){
      return this.$store.state.userCollections;
      },
      user(){
        return this.$store.state.user;
      }
    
  },

  methods: {
    getUserCollectionsForStore() {
      userService
        .getUserCollections(this.$route.params.userId).then((response) => {
        this.$store.commit("SET_USER_COLLECTIONS", response.data);
        this.isLoading = false;
      });
    },
    getUserCollections() {
      userService
        .getUserCollections(this.$route.params.userId)
        .then((response) => {
          this.collections = response.data;
        });
    },

    getUser(userId){
      userService.find(userId).then((response) => {
        this.user = response.data;
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
  },
};
</script>

<style>

#collections{
  display: flex;
  flex-direction: column;
  
}

#collection-comics {
  display: flex;
  justify-content: center;
  flex-direction: row;
  width: auto;
  justify-content: space-evenly;
  
}

</style>