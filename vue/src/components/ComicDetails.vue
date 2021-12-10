<template>
  <div class="comic">
    <h1>
      {{this.title}}
    </h1>
            <img :src='this.img' alt="">
<p>
  {{this.description}}
  </p>
  </div>
</template>

<script>
import comicService from "@/services/ComicService.js";

export default {
  name: "comic",
  data() {
    return {
      id: "",
      title: "",
      img: "",
      description: ""
    }
    },
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