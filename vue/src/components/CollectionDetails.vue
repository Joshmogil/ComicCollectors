<template>
  <div class="collection">
    <h3>
      <router-link
        v-bind:to="{
          name: 'userDetails',
          params: { userId: this.$route.query.collectionUserId },
        }"
      >
        <h2>User: {{ userViewed.username }}</h2>
      </router-link>
    </h3>

    <h1>{{ detailCollection.collectionName }}</h1>
    <div class="btn-container">
      <button
        class="btn editpage"
        v-if="!isLoading && !showEditCollection && $store.state.token != ''"
        v-on:click="changeShowEditCollection()"
      >
        Edit Collection
      </button>
       <button
        class="btn deleteButton"
        v-on:click="deleteCollection()"
      >
        Delete Collection
      </button>
    </div>
    <div v-if="!showEditCollection">
      <section id="horizontal-collection">
        <div
          v-for="comic in detailCollection.comicList"
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
        </div>
      </section>
    </div>
    <div v-if="showEditCollection">
      <section id="horizontal-collection">
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th>
                <label class="form-checkbox">
                  <input
                    type="checkbox"
                    v-model="selectAll"
                    @click="allSelected()"
                  />
                  <i class="form-icon"></i>
                </label>
              </th>
              <th>Select All</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="comic in detailCollection.comicList"
              v-bind:key="comic.comicId"
            >
              <td>
                <label class="form-checkbox">
                  <input
                    type="checkbox"
                    :value="comic.comicId"
                    v-model="comic.selected"
                  />
                  <div class="form-icon"></div>
                </label>
              </td>
              <td>
                <div>
                  <router-link
                    v-bind:to="{
                      name: 'comicDetails',
                      params: { id: comic.comicId },
                    }"
                  >
                    <div class="card-container">
                      <div class="card">
                        <div class="side">
                          <img :src="comic.imgUrl" alt="" />
                        </div>
                        <div class="side back">{{ comic.description }}</div>
                      </div>
                    </div>
                  </router-link>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </section>
    </div>
  </div>
</template>

<script>
import collectionService from "@/services/CollectionService.js";
import userService from "@/services/UserService.js";

export default {
  name: "collection",
  data() {
    return {
      collection: {
        collectionId: "",
        userId: "",
        collectionName: "",
        comicList: [],
      },
      isLoading: true,
      showEditCollection: false,
    };
  },

  methods: {
    changeShowEditCollection() {
      this.showEditCollection = !this.showEditCollection;
    },

    seeIfCurrentUserIsUserViewed() {
      if (this.currentUser.id == this.userViewed.userId) {
        this.$store.commit("SET_CURRENT_USER_IS_USER_VIEWED", true);
      } else {
        this.$store.commit("SET_CURRENT_USER_IS_USER_VIEWED", false);
      }
    },
    getDetailCollectionForStore() {
      collectionService
        .get(this.$route.params.collectionId)
        .then((response) => {
          this.$store.commit("SET_DETAIL_COLLECTION", response.data);
          this.isLoading = false;
        });
    },
    getCollectionData() {
      collectionService
        .get(this.$route.params.collectionId)
        .then((response) => {
          this.collection = response.data;
        });
    },
    allSelected(){
        this.userCollections.forEach(collection => {
        collection.selected = true;
          
        });
    },


    findStoreData() {
      return this.$store.state.collections.find((collection) => {
        collection.collectionId == this.$route.params.collectionId;
      });
    },

    getComics() {
      collectionService
        .getComics(this.$route.params.collectionId)
        .then((response) => {
          this.collection.comics = response.data;
        });
    },
    getUserViewed(userId) {
      userService.find(userId).then((response) => {
        this.$store.commit("SET_USER_VIEWED", response.data);
      });
    },
    deleteCollection() {
      if (confirm("Are you sure you want to delete this collection and all associated comics? This action cannot be undone.")) {
        collectionService
        .deleteCollection(this.detailCollection.collectionId)
        .then(response => {
          if (response.status === 200) {
            this.message = "success";
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "adding");
          this.isLoading = false;
        });
      }
    },
    deleteComicFromCollection() {},
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
    }
  },
  computed: {
    detailCollection() {
      return this.$store.state.detailCollection;
    },
    currentUser() {
      return this.$store.state.user;
    },
    userViewed() {
      return this.$store.state.userViewed;
    },
    userViewedIdOnly() {
      return this.$store.state.detailCollection.userId;
    },
    currentUserIsUserViewed() {
      return this.$store.state.currentUserIsUserViewed;
    },
  },
  created() {
    this.getDetailCollectionForStore();
    this.getUserViewed(this.$route.query.collectionUserId);
    this.seeIfCurrentUserIsUserViewed();
  },
};
</script>
<style>
#horizontal-collection {
  display: flex;
  flex-direction: row;
  max-width: 800px;
  height: auto;
  justify-content: space-between;
}

img {
  margin: auto;
  height: 100%;
  width: 100%;
  margin: 5px;
  border-radius: 10px;
  box-shadow: 5px 10px #888888;
}
</style>