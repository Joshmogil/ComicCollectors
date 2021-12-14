<template>
  <div class="comic">
    <h1>
      {{this.comic.comicTitle}}
    </h1>
            <img id="comic-photo" :src='this.comic.imgUrl' alt="">
<p>
  {{this.comic.description}}
  </p>


<div class="btn-container">
        <button
          class="btn"
          v-if="!isLoading && !showNewCollection && $store.state.token != ''"
          v-on:click="showAddToCollection = !showAddToCollection"
        >
          Add To Collection
        </button>
      </div>
<form v-if="showAddToCollection">
        Which collections would you like to add this comic to?
        <div id="listOfUserCollections">
	<div class="text-uppercase text-bold">Collections selected: {{selected}}</div>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>
					<label class="form-checkbox">
    <input type="checkbox" v-model="selectAll" @click="select">
    <i class="form-icon"></i>
  </label>
				</th>
				<th>Select All</th>
			</tr>
		</thead>
		<tbody>
			<tr v-for="collection in this.collections" v-bind:key="collection.collectionId">
				<td>
					<label class="form-checkbox">
            <input type="checkbox" :value="collection.collectionName" v-model="selected">
						<collection class="form-icon"></collection>
            </label>
				</td>
				<td>{{collection.collectionName}}</td>
			</tr>
		</tbody>
	</table>
</div>


        <button class="btn btn-submit" v-on:click="saveNewCollection" >
          Save
        </button>
        <button
          class="btn btn-cancel"
          v-on:click="showNewCollection = !showNewCollection"
        >
          Cancel
        </button>
      </form>



  </div>
</template>

<script>
import userService from "../services/UserService";
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
        },
        collections: [],
        showAddToCollection: false,
    }
  },
  methods: {

      getComic() {
              comicService.find(this.$route.params.id).then (response => {
         this.comic = response.data;
              });

      }
  }, 

  getUserCollections() {
      userService
        .getUserCollections(this.$route.params.userId)
        .then((response) => {
          this.collections = response.data;
        });
    },

  created() {

    this.getComic();
    this.getUserCollections;

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