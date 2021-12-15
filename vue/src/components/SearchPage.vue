<template>
  <div>
    <input type="text" class="form-control" v-model="this.searchInput" />
    <button class="btn btn-search" v-on:click="characterSearch">Search</button>

    <section id="horizontal-collection">
      <div
        v-for="comic in getResults"
        v-bind:key="comic.comicId"
      >
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
import comicService from "@/services/ComicService.js";

export default {
  name: "search",
  data() {
    return {
      searchInput: "",
      isLoading: true,
    };
  },

  methods: {
    characterSearch() {
      comicService.searchByCharacterName(this.searchInput).then(response => {
        this.$store.commit("SET_RESULTS", response.data);
        this.isLoading = false;
      });
    },
  },
  computed: {
    getResults() {
      return this.$store.state.results;
    },
  },
};
</script>

<style>
</style>