<template>
  <div class="collection">
    <h2>Test</h2>
    <h1>{{ this.collection.collectionName }}</h1>

    <section id="horizontal-collection">
      <div v-for="comic in this.collection.comicList" v-bind:key="comic.comicId">
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
        {{ this.collection.userId }}
      </router-link>
    </h3>
  </div>
</template>

<script>
import collectionService from "@/services/CollectionService.js";

export default {
  name: "collection",
  data() {
    return {
      collection: {
        collectionId: 3,
        userId: 3,
        name: "still testing",
        comicList: [],
      },
    };
  },
  methods: {
    getCollectionData() {
      collectionService
        .get(this.$route.params.collectionId)
        .then((response) => {
          this.collection.collectionid = response.data.collectionId;
          this.collection.userId = response.data.userId;
          this.collection.name = response.data.collectionName;
        });
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
  created() {
    this.getCollectionData();
  },
};
</script>
<style>
</style>