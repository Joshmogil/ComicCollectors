<template>
<div>
  <h1>Our top collections</h1>
  <div id="collection-table">
    <!-- <table>
      <tbody>
      <tr v-for="collection in $store.state.collections" v-bind:key="collection.id">
        <td>{{ collection.id }}</td>
        <td v-for="comic in collection.comics" v-bind:key="comic.id">
          {{comic.id}} 
          <img :src='comic.img' alt="">
        </td>

      </tr>
      </tbody>
    </table>  -->
    
    <section id= "vertical-collections" v-for="collection in this.collections" v-bind:key="collection.id">
      <h3><router-link v-bind:to="{ name: 'collectionDetails', params: { collectionId: collection.collectionid } }">
        {{collection.name}}
        </router-link>  </h3>
<!-- <vue-custom-scrollbar class="scroll-area"  @ps-scroll-y="scrollHanle"> -->

      <section id= "horizontal-collection" v-for="comic in collection.comics" v-bind:key="comic.id">
        <router-link v-bind:to="{ name: 'comicDetails', params: { id: comic.id}}">
          <div class="card-container">
  <div class="card">
    <div class="side"><img :src='comic.img' alt=""></div>
    <div class="side back">{{comic.description}}</div>
  </div>
</div>
        
        </router-link>
      </section>
<!-- </vue-custom-scrollbar> -->
    </section>



  </div>
</div>
</template>

<script>
//import axios from "axios";

// import { component } from 'vue/types/umd'
export default {
   name:"collection-grid",

   data() {
    return {
      collections: [
        

      ],
    };
  },
  methods: {
    getCollections() {
     /*  axios.get(`/collection`).then((response) => {
        this.collections = response.data.results;
      }); */
      this.collections = this.$store.state.collections;
    }
  },
  created() {
   this.getCollections();
  }

  
 
 }
</script>

<style>

h1{
  text-align: center;
}

#vertical-collections{
  display: flex;
  justify-content: center;
  flex-direction: row;
}

#horizontal-collection{
  display: flex;
  justify-content: center;
  flex-direction: column;
  padding: 15px 12px;
  box-sizing: border-box;
  width: 20rem; 
  height: 20rem;
  
}

td{
  align-content: center;
}

img{
    margin: auto;
    height:100%;
    width:100%;
    
}



</style>