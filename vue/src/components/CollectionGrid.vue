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
    
      <div id= "vertical-collections" v-for="collection in this.collections" v-bind:key="collection.id">
     <div>
       
      
      <h3>{{collection.name}} </h3>
      <vue-custom-scrollbar class="scroll-collection"  :settings="settings" @ps-scroll-y="scrollHanle">
      <section id= "horizontal-collection" v-for="comic in collection.comics" v-bind:key="comic.id">
        <img :src='comic.img' alt="">
      </section>
      </vue-custom-scrollbar>
      </div>
    </div>  


      </div>
     
    
</div>
</template>

<script>
//import axios from "axios";
import vueCustomScrollbar from 'vue-custom-scrollbar'
import "vue-custom-scrollbar/dist/vueScrollbar.css"
// import { component } from 'vue/types/umd'
export default {
   name:"collection-grid",

  components: {
    vueCustomScrollbar
  },

   data() {
    return {
      collections: [],
    };
  },
  methods: {
    getCollections() {
      // axios.get(`/collection`).then((response) => {
      //   this.collections = response.data.results;
      //});
      this.collections = this.$store.state.collections;
    }
  },
  beforeMount() {
    this.getCollection();
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