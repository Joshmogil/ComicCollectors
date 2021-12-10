<template>
  <div>
  <h1>{{this.userId}}</h1>
  <div id="collection-table">
    
    <section id= "vertical-collections" v-for="collection in this.collections" v-bind:key="collection.id">
      <h3><router-link v-bind:to="{ name: 'collectionDetails', params: { collectionId: collection.collectionid } }">
        {{collection.name}}
        </router-link>  </h3>
<!-- <vue-custom-scrollbar class="scroll-area"  @ps-scroll-y="scrollHanle"> -->

      <section id= "horizontal-collection" v-for="comic in collection.comics" v-bind:key="comic.id">
        <img :src='comic.img' alt="">
      </section>
<!-- </vue-custom-scrollbar> -->
    </section>



  </div>
</div>
</template>

<script>
import collectionService from '../services/CollectionService';

export default {
  data() {
    return {
      isLoading: true,
      errorMsg: '',
      userId: "",
      collections: []
    };
  },
  created() {
    this.retrieveCollections();
  },
  methods: {
    retrieveCollections() {
        //what ids to put in - random?
      collectionService.getAllCollections().then(response => {
        this.$store.commit("SET_ALL_COLLECTIONS", response.data);
        this.isLoading = false;

        if (this.$route.name == "Home" && response.status === 200 && response.data.length > 0) {
          this.$router.push(`/collections/${response.data[0].id}`);
        }
      });
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " + verb + " collection. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " collection. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " collection. Request could not be created.";
      }},
  }
};
</script>

<style>


</style>