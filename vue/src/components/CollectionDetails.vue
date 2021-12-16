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
    <div v-if="isCurrentUserTheUserViewed">
      <div class="btn-container">
        <img
          src="@/assets/editcollection.png"
          class="header-button"
          v-if="!isLoading && !showEditCollection && $store.state.token != ''"
          v-on:click="changeShowEditCollection()"
        />

        <img
          src="@/assets/deletecollection.png"
          class="header-button"
          v-on:click="deleteCollection()"
        />
      </div>
    </div>
    <div v-if="!showEditCollection">
      <section id="search-container">
        <div
          v-for="comic in detailCollection.comicList"
          v-bind:key="comic.comicId"
        >
          <router-link
            v-bind:to="{ name: 'comicDetails', params: { id: comic.comicId } }"
          >
            <div class="search-card">
              <div class="card">
                <div class="side"><img :src="comic.imgUrl" alt="" /></div>
                <div class="side back">{{ comic.comicTitle }}</div>
              </div>
            </div>
          </router-link>
        </div>
      </section>
    </div>
    <div v-if="showEditCollection">
      <section id="search-container">
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
                    <div class="search-card">
                      <div class="card">
                        <div class="side">
                          <img :src="comic.imgUrl" alt="" />
                        </div>
                        <div class="side back">{{ comic.comicTitle }}</div>
                      </div>
                    </div>
                  </router-link>
                </div>
              </td>
            </tr>
          </tbody>
        </table>

        <img
          src="@/assets/delete.png"
          class="header-button"
          v-on:click="deleteComicFromCollection()"
        />

        <img
          src="@/assets/cancel.png"
          class="header-button"
          v-on:click="changeShowEditCollection()"
        />
      </section>
    </div>
  </div>
</template>

<script>
import collectionService from "@/services/CollectionService.js";
import userService from "@/services/UserService.js";
import comicService from "@/services/ComicService.js";

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
      message: "",
      selectAll: false,
      // isCurrentUserTheUserViewed: false
    };
  },

  methods: {
    changeShowEditCollection() {
      this.showEditCollection = !this.showEditCollection;
    },

    seeIfCurrentUserIsUserViewed() {
      if (this.$store.state.user.id == this.userViewed.userId) {
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
    allSelected() {
      this.detailCollection.comicList.forEach((comic) => {
        comic.selected = true;
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
      if (
        confirm(
          "Are you sure you want to delete this collection and all associated comics? This action cannot be undone."
        )
      ) {
        if (this.isCurrentUserTheUserViewed) {
          collectionService
            .deleteCollection(this.detailCollection.collectionId)
            .then((response) => {
              if (response.status === 200) {
                this.message = "success";
              }
            })
            .catch((error) => {
              this.handleErrorResponse(error, "adding");
              this.isLoading = false;
            });
        }
      }
      this.$router.push({ name: 'userDetails', params: { userId: this.$store.state.user.id || 0 }  });
      this.$router.go();
    },
    deleteComicFromCollection() {
      if (this.isCurrentUserTheUserViewed) {
        let dtoList = [];
        this.detailCollection.comicList.forEach((comic) => {
          if (comic.selected === true) {
            const addComicDTO = {
              comicId: comic.comicId,
              collectionId: this.detailCollection.collectionId,
            };
            dtoList.push(addComicDTO);
          }
        });
        //call service
        comicService
          .deleteComics(dtoList)
          .then((response) => {
            if (response.status === 204) {
              this.message = "success";
            }
          })
          .catch((error) => {
            this.handleErrorResponse(error, "adding");
            this.isLoading = false;
          });
      }
      
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
    collectionUserId() {
      return this.$route.query.collectionUserId;
    },
    isCurrentUserTheUserViewed() {
      return (
        this.$store.state.user.id === this.$store.state.detailCollection.userId
      );
    },
  },
  created() {
    this.getDetailCollectionForStore();
    this.getUserViewed(this.$route.query.collectionUserId);
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
  /* box-shadow: 2px 2px 4px 5px #888888; */
}
</style>