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
    return  {
        comic:{
      id: "",
      title: "",
      img: "",
      description: ""
        }
    }
  },
  methods: {

      getComic() {
              comicService.find(this.$route.params.id).then (response => {
         this.comic = response.data;
              });

      }
  }, 

  created() {

    this.getComic();

  }
}

</script>
<style>

</style>