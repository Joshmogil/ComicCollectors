<template>
  <div class="collection">
    
  </div>
</template>

<script>
import comicService from "@/services/ComicService.js";

export default {
  name: "comic",
  methods: {}, 
  created() {
    comicService
      .find(this.comicId)
      .then(response => {
        this.$store.commit("SET_COMIC", response.data);
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