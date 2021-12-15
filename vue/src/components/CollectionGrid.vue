<template>
  <div>
    <div id="collection-table">
      <section
        id="vertical-collections"
        v-for="collection in homeCollections"
        v-bind:key="collection.collectionId"
      >
        <h3>
          <router-link
            v-bind:to="{
              name: 'collectionDetails',
              params: { collectionId: collection.collectionId },
              query: { collectionUserId: collection.userId }
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
      isLoading: true,
    };
  },
  methods: {
    getCollectionsForStore() {
      collectionService.getAllCollections().then((response) => {
        this.$store.commit("SET_HOME_COLLECTIONS", response.data);
        this.isLoading = false;
      });
    },

    getComics(id) {
      collectionService.getComics(id).then((response) => {
        this.listOfComics = response.data;
      });
    },
  },
  computed: {
    homeCollections() {
      return this.$store.state.homeCollections;
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

img {
  margin: auto;
  height: 100%;
  width: 100%;
}
</style>