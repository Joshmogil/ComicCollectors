<template>
  <div>
    <h1>{{ userViewed.username }}</h1>
    <div class="btn-container">
      
        <button
          class="btn newCollection"
          v-if="!isLoading && !showNewCollection && $store.state.token != ''"
          v-on:click="changeShowNewCollection()"
        >
          New Collection
        </button>
      
    </div>

<div class="contains-buttons-etc">
    <form v-if="showNewCollection">
        Collection Name:
        <input
          type="text"
          class="form-control"
          v-model="newCollection.collectionName"
        />
        <button class="btn btn-submit" v-on:click="saveNewCollection">
          Save
        </button>
        <button class="btn btn-cancel" v-on:click="changeShowNewCollection()">
          Cancel
        </button>
      </form>
    </div>
    <div id="collection-table">
      <section id="collections">
        <div
          v-for="collection in userCollections"
          v-bind:key="collection.collectionId"
        >
          <h2>
            <router-link
              v-bind:to="{
                name: 'collectionDetails',
                params: { collectionId: collection.collectionId },
                query: { collectionUserId: collection.userId },
              }"
            >
              {{ collection.collectionName }}
            </router-link>
          </h2>

          <section id="collection-comics">
            <div
              v-for="comic in collection.comicList.slice(0, 5)"
              v-bind:key="comic.comicId"
            >
              <router-link
                v-bind:to="{
                  name: 'comicDetails',
                  params: { id: comic.comicId },
                }"
              >
                <div class="user-card-container">
                  <div class="card">
                    <div class="side"><img :src="comic.imgUrl" alt="" /></div>
                    <div class="side back">{{ comic.comicTitle }}</div>
                  </div>
                </div>
              </router-link>
            </div>
          </section>
        </div>
      </section>
    </div>

    <div class="collections-list">
      <div class="status-message error" v-show="errorMsg !== ''">
        {{ errorMsg }}
      </div>
      <!--
      <div class="loading" v-if="isLoading">
        <img src="../src/assets/marvel.gif" />
      </div>
      add a v-else to router link before div tag
      -->
    </div>
  </div>
</template>

<script>
import userService from "../services/UserService";
import collectionService from "../services/CollectionService";

export default {
  data() {
    return {
      isLoading: true,
      errorMsg: "",
      collections: [],
      showNewCollection: false,
      creationSuccess: false,
      creationAttempted: false,
      newCollection: {
        collectionName: "",
        userId: "",
      },
    };
  },

  created() {
    this.getUserCollectionsForStore();
    this.getUserViewed();
  },

  computed: {
    userCollections() {
      return this.$store.state.userCollections;
    },
    currentUser() {
      return this.$store.state.user;
    },
    userViewed() {
      return this.$store.state.userViewed;
    },
  },

  methods: {
    changeShowNewCollection() {
      this.showNewCollection = !this.showNewCollection;
    },
    saveNewCollection() {
      const newCollectionDTO = {
        collectionName: this.newCollection.collectionName,
        userId: this.$store.state.user.id,
      };
      collectionService
        .addCollection(newCollectionDTO)
        .then((response) => {
          if (response.status === 201) {
            // this.retrieveCollections();
            // this.showNewCollection = false;
            // this.newCollection = {
            //   collectionName: "",
            // };
            // this.creationSuccess = response.data;
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "adding");
          this.isLoading = false;
        });
      this.$router.go();
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " +
          verb +
          " collection. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " collection. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " collection. Request could not be created.";
      }
    },
    getUserCollectionsForStore() {
      userService
        .getUserCollections(this.$route.params.userId)
        .then((response) => {
          this.$store.commit("SET_USER_COLLECTIONS", response.data);
          this.isLoading = false;
        });
    },
    getUserCollections() {
      userService
        .getUserCollections(this.$route.params.userId)
        .then((response) => {
          this.collections = response.data;
        });
    },

    getUserViewed() {
      userService.find(this.$route.params.userId).then((response) => {
        this.$store.commit("SET_USER_VIEWED", response.data);
      });
    },
  },
};
</script>

<style>
#collections {
  display: flex;
  flex-direction: column;
}

#collection-comics {
  display: flex;
  justify-content: center;
  flex-direction: row;
  width: auto;
  justify-content: space-evenly;
}
.newcollection-user{
  display: flex;
  align-content: center;
}

</style>