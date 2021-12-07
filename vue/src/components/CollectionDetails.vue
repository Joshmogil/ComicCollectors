<template>
  <div class="collection">
    <div class="loading" v-if="isLoading">
        <img src="../src/assets/marvel.gif" />
      </div>
  </div>
</template>

<script>
import collectionService from "@/services/CollectionService.js";

export default {
  name: "collection",
  data() {
    return {
      isLoading: false
    }
  },
  methods: {
    
  },
  created() {
    collectionService
      .get(this.collectionId)
      .then(response => {
        this.$store.commit("SET_COLLECTION", response.data);
      })
      .catch(error => {
        if (error.response.status == 404) {
          this.$router.push("/not-found");
        }
      });
  }
};
</script>
<style>

</style>