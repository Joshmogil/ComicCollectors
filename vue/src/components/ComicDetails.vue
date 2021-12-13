<template>
  <div class="comic">
    <h1>
      {{this.comic.comicTitle}}
    </h1>
            <img id="comic-photo" :src='this.comic.imgUrl' alt="">
<p>
  {{this.comic.description}}
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
      comicId: "",
      comicTitle: "",
      imgUrl: "",
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

#comic-photo{
  
  width:auto;
  height: 75vh;
  display: flex;

}
</style>