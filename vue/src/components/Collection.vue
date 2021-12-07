<template>
  <div class="collection">
    
  </div>
</template>

<script>
import collectionService from "@/services/CollectionService.js";

export default {
  name: "collection",
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