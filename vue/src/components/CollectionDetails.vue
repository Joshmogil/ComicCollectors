<template>
  <div class="collection">
    <h2>User: {{ user.username }}</h2>

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
    <h3>
      <router-link
        v-bind:to="{
          name: 'userDetails',
          params: { userId: this.collection.userId },
        }"
      >
        
      </router-link>
    </h3>
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
    getDetailCollectionForStore() {
      collectionService
        .get(this.$route.params.collectionId).then((response) => {
        this.$store.commit("SET_DETAIL_COLLECTION", response.data);
        this.isLoading = false;
      });
    },
    getCollectionData() {
      collectionService
        .get(this.$route.params.collectionId)
        .then((response) => {
          this.collection = response.data;
        });
    },
    getUserData(userId){
        userService.find(userId).then((response) => {
          this.user = response.data;
        })
    },

    findStoreData() {
      return this.$store.state.collections.find((collection) => {
        collection.collectionId == this.$route.params.collectionId;
      });
    },

    getComics() {
      collectionService
        .getComics(this.$route.params.collectionId)
        .then((response) => {
          this.collection.comics = response.data;
        });
    },
  },
  computed: {
      detailCollection(){
      return this.$store.state.detailCollection;
      },
      user(){
        return this.$store.state.user;
      }
    
  },
  created() {
    this.getDetailCollectionForStore();
  },
};
</script>
<style>


</style>