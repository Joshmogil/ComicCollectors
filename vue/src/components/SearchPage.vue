<template>
  <div>
    <input type="text" class="form-control" v-model="searchInput" />
    <img src="@/assets/search.png" class="header-button" v-on:click="marvelCharacterSearch">

    <section id="search-container">
      <div
        v-for="comic in getResults"
        v-bind:key="comic.comicId"
      >
        <router-link
          v-bind:to="{ name: 'comicDetails', params: { id: comic.comicId } }"
        >
          <div class="search-card">
            <div class="card">
              <div class="side"><img :src="comic.imgUrl" alt="" /></div>
              <div class="side back">{{ comic.comicTitle }}</div>
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
    databaseCharacterSearch() {
      comicService.searchByCharacterName(this.searchInput).then(response => {
        this.$store.commit("SET_RESULTS", response.data);
        this.isLoading = false;
      });
    },

    marvelCharacterSearch(){
      comicService.searchMarvel(this.searchInput).then(response => {
        this.$store.commit("SET_RESULTS", response.data);
        this.isLoading = false;
       });
    }
    
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