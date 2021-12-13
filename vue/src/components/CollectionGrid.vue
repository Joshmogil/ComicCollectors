<template>
  <div>
    <div id="collection-table">
      <section
        id="vertical-collections"
        v-for="collection in this.$store.state.collections"
        v-bind:key="collection.collectionId"
      >
        <h3>
          <router-link
            v-bind:to="{
              name: 'collectionDetails',
              params: { collectionId: collection.collectionId },
            }"
          >
            {{ collection.collectionName }}
          </router-link>
        </h3>

        <section
          id="horizontal-collection"
          v-for="comic in collection.comicList"
          v-bind:key="comic.comicId"
        >
          {{ comic.comicId }}
          <router-link
            v-bind:to="{ name: 'comicDetails', params: { id: comic.comicId } }"
          >
            <div class="card-container">
              <div class="card">
                <div class="side"><img :src="comic.imgUrl" alt="" /></div>
                <div class="side back">{{ comic.description }}</div>
              </div>
            </div>
          </router-link>
        </section>
        <!-- <vue-custom-scrollbar class="scroll-area"  @ps-scroll-y="scrollHanle"> -->
        <!-- </vue-custom-scrollbar> -->
      </section>
    </div>
  </div>
</template>

<script>
import collectionService from "@/services/CollectionService.js";

export default {
  name: "collection-grid",

  data() {
    return {
      collections: [],
      
    };
  },
  methods: {
    getCollections() {
      collectionService.getAllCollections().then((response) => {
        this.collections = response.data;
        this.collections[0].name = "api called";
      });
    },
    getCollectionsForStore() {
      collectionService.getAllCollections().then((response) => {
        this.$store.commit("SET_ALL_COLLECTIONS", response.data);
        this.isLoading = false;
      });
    },

    getComics(id) {
      collectionService.getComics(id).then((response) => {
        this.listOfComics = response.data;
      });
    },
  },
  created() {
    this.getCollectionsForStore();
  },
};
</script>

<style>
h1 {
  text-align: center;
}

#vertical-collections {
  display: flex;
  justify-content: center;
  flex-direction: row;
}

#horizontal-collection {
  display: flex;
  justify-content: center;
  flex-direction: column;
  padding: 15px 12px;
  box-sizing: border-box;
  width: 20rem;
  height: 20rem;
}

td {
  align-content: center;
}

img {
  margin: auto;
  height: 100%;
  width: 100%;
}
</style>