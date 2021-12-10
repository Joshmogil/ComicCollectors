<template>
  <div class="collection">
    <h2> Test </h2>
            <h1>{{collection.name}}</h1>

      <section id= "horizontal-collection" v-for="comic in this.comics" v-bind:key="comic.id">
        <img :src='comic.img' alt="">
      </section>
  </div>
</template>

<script>
//import collectionService from "@/services/CollectionService.js";

export default {
  name: "collection",
  data() {
    return {
      id: "",
      name: "",
      comics: [
        

      ],
    };
  },
  methods: {
    
  },
  created() {
    /* collectionService
      .get(this.collectionId)
      .then(response => {
        this.$store.commit("SET_COLLECTION", response.data);
      })
      .catch(error => {
        if (error.response.status == 404) {
          this.$router.push("/not-found");
        }
      }); */
    this.data = this.$store.state.collections.find((collection) => {
        collection.id == this.$route.params.collectionId
      });
  }
};
</script>
<style>

</style>