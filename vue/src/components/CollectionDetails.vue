<template>
  <div class="collection">
    <h2> Test </h2>
            <h1>{{this.collection.name}}</h1>

      <section id= "horizontal-collection" v-for="comic in this.collection.comics" v-bind:key="comic.id">
        <router-link v-bind:to="{ name: 'comicDetails', params: { id: comic.id}}">
        <div class="card-container">
  <div class="card">
    <div class="side"><img :src='comic.img' alt=""></div>
    <div class="side back">{{comic.description}}</div>
  </div>
</div>
        </router-link>
      </section>
            <h3><router-link v-bind:to="{ name: 'userDetails', params: { userId: this.collection.userId } }">
              {{this.collection.userId}}
</router-link></h3>
  </div>
</template>

<script>
import collectionService from "@/services/CollectionService.js";
// import apiService from "@/services/apiService.js"

export default {
  name: "collection",
  data() {
    return {
      collection:{
      collectionId: 3,
      userId: 3,
      name: 'still testing',
      comics: [
        

      ]}
    };
  },
  methods: {
    getCollectionData() {
     /*  axios.get(`/collection`).then((response) => {
        this.collections = response.data.results;
      }); */
      collectionService.get(this.$route.params.collectionId).then(response =>{
         this.collection.collectionid = response.data.collectionId;
          this.collection.userId = response.data.userId;
            this.collection.name = response.data.collectionName;
       
          
      })
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
    /* this.collection.name = 'created is called';
       collectionService.get(this.$route.params.collectionId).then(response =>{
         this.collection = response.data;  
         this.collection.name = 'is running';
      }); */

/*       apiService.list().then( (response) =>{
        this.collection = response.data;
      }); */

      
   






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
//this.collection = this.findStoreData();
  }

  
};
</script>
<style>

</style>