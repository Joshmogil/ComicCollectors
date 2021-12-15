<template>
  <div class="collection">
        <h3>
      <router-link
        v-bind:to="{
          name: 'userDetails',
          params: { userId: userViewedIdOnly }
        }"
      >
    <h2>User: {{ userViewed.username }}</h2>    
      </router-link>
    </h3>
    

    <h1>{{ detailCollection.collectionName }}</h1>

    <section id="horizontal-collection">
      <div v-for="comic in detailCollection.comicList" v-bind:key="comic.comicId">
        <router-link
          v-bind:to="{ name: 'comicDetails', params: { id: comic.comicId } }"
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
</template>

<script>
import collectionService from "@/services/CollectionService.js";
import userService from "@/services/UserService.js";

export default {
  name: "collection",
  data() {
    return {
      collection: {
        collectionId: "",
        userId: "",
        collectionName: "",
        comicList: [],
      },
      isLoading: true
    };
  },
  
  methods: {
    seeIfCurrentUserIsUserViewed(){
      if (this.currentUser.id == this.userViewed.userId){
      this.$store.commit("SET_CURRENT_USER_IS_USER_VIEWED", true);
      }
      else {
              this.$store.commit("SET_CURRENT_USER_IS_USER_VIEWED", false);

      }

    },
    getDetailCollectionForStore() {
      collectionService
        .get(this.$route.params.collectionId).then(response => {
        this.$store.commit("SET_DETAIL_COLLECTION", response.data);
        this.isLoading = false;
      });
    },
    getCollectionData() {
      collectionService
        .get(this.$route.params.collectionId)
        .then(response => {
          this.collection = response.data;
        });
    },
    /* getUserData(userId){
        userService.find(userId).then(response => {
          this.user = response.data;
        })
    }, */

    findStoreData() {
      return this.$store.state.collections.find((collection) => {
        collection.collectionId == this.$route.params.collectionId;
      });
    },

    getComics() {
      collectionService
        .getComics(this.$route.params.collectionId)
        .then(response => {
          this.collection.comics = response.data;
        });
    },
    getUserViewed(userId){
      userService.find(userId).then(response => {
this.$store.commit("SET_USER_VIEWED", response.data);         
});
    },
    deleteCollection(){

    },
    deleteComicFromCollection(){

    }
  },
  computed: {
      detailCollection(){
      return this.$store.state.detailCollection;
      },
      currentUser(){
        return this.$store.state.user;
      },
      userViewed(){
        return this.$store.state.userViewed;
      },
      userViewedIdOnly(){
        return this.$store.state.detailCollection.userId;
      },
      currentUserIsUserViewed() {
      return this.$store.state.currentUserIsUserViewed;
    }
    
  },
  created() {
    this.getDetailCollectionForStore();
    this.getUserViewed(this.userViewedIdOnly);
    this.seeIfCurrentUserIsUserViewed();
  },
};
</script>
<style>

#horizontal-collection {
  display: flex;
  flex-direction: row;
  max-width: 800px;
  height: auto;
  justify-content: space-between;


}

img {
  margin: auto;
  height: 100%;
  width: 100%;
  margin: 5px;
  border-radius: 10px;
  box-shadow: 5px 10px #888888;
}


</style>