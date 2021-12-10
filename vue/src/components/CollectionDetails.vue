<template>
  <div class="collection">
    <h2> Test </h2>
            <h1>{{this.name}}</h1>

      <section id= "horizontal-collection" v-for="comic in this.comics" v-bind:key="comic.id">
        <img :src='comic.img' alt="">
      </section>
            <h3><router-link v-bind:to="{ name: 'userDetails', params: { userId: this.userId } }">
</router-link></h3>
  </div>
</template>

<script>
//import collectionService from "@/services/CollectionService.js";

export default {
  name: "collection",
  data() {
    return {
      collectionId: 3,
      userId: 3,
      name: 'still testing',
      comics: [
        

      ],
    };
  },
  methods: {
    getCollectionData() {
     /*  axios.get(`/collection`).then((response) => {
        this.collections = response.data.results;
      }); */
      return this.$store.state.collections.find((collection) => {
        collection.collectionId == this.$route.params.collectionId
      }); 
    },
    findStoreData(){
      return this.$store.state.collections.find((collection) => {
        collection.collectionId == this.$route.params.collectionId
      }); 
  },
  computed: {
    }
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
    /* this.data = this.$store.state.collections.find((collection) => {
        collection.id == this.$route.params.collectionId
      }); */
/* this.data = {
  collectionId: 1,
      userId: 1,
      name: 'Practice',
      comics: [
        

      ], 

}

*/
this.data = this.findStoreData();
  }

  
};
</script>
<style>

</style>