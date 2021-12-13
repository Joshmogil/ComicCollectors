<template>
  <div>
    <h1>{{ this.$route.params.userId }}</h1>
    <div id="collection-table">
      <section id="vertical-collections">
        <div v-for="collection in this.collections" v-bind:key="collection.id">
          <h3>
            <router-link
              v-bind:to="{
                name: 'collectionDetails',
                params: { collectionId: collection.collectionid },
              }"
            >
              {{ collection.name }}
            </router-link>
          </h3>

          <section id="horizontal-collection">
            <div v-for="comic in collection.comicList" v-bind:key="comic.id">
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
      user: {},
      collections: [],
    };
  },

  created() {
    this.getUserCollections();
  },

  methods: {
    getUserCollections() {
      userService
        .getUserCollections(this.$route.params.userId)
        .then((response) => {
          this.collections = response.data;
        });
    },

    find() {
      userService.find(this.$route.params.userId).then((response) => {
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
</style>