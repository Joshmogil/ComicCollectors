import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    currentUserId: currentUser.userId || 0,
    collectionsApiStore: [
      {
        collectionId: 0,
        userId: 0,
        name: "",
        comics: [
          {
            id: 0,
            title: "",
            img: "",
            description: ""
          },
        ]
      },

    ],

    homeCollections: [

    ],
    userCollections: [],
    detailCollection: [],
    detailComic: [],
    userViewed: [],
    results: [],
    currentUserIsUserViewed: false


  },

  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_HOME_COLLECTIONS(state, homeCollections) {
      state.homeCollections = homeCollections;
      localStorage.setItem('homeCollections', JSON.stringify(homeCollections));
    },
    SET_USER_COLLECTIONS(state, userCollections) {
      state.userCollections = userCollections;
      localStorage.setItem('userCollections', JSON.stringify(userCollections));
    },
    SET_DETAIL_COLLECTION(state, collection) {
      state.detailCollection = collection;
      localStorage.setItem('detailCollection', JSON.stringify(collection));
    },
    SET_DETAIL_COMIC(state, comic) {
      state.detailComic = comic;
      localStorage.setItem('detailComic', JSON.stringify(comic));
    },
    SET_USER_VIEWED(state, user) {
      state.userViewed = user;
      localStorage.setItem('userViewed', JSON.stringify(user));
    },
    SET_RESULTS(state, results) {
      state.results = results;
      localStorage.setItem('results', JSON.stringify(results));
    },
    SET_CURRENT_USER_IS_USER_VIEWED(state, boolean) {
      state.currentUserIsUserViewed = boolean;
      localStorage.setItem('currentUserIsUserViewed', JSON.stringify(boolean));
    }
  }
})
