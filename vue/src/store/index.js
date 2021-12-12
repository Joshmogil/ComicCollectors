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

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    
    // collections: [],
    // Trigger is you want all the collections including their info and comics
    // Do is call http://localhost:8080/collections/ to get the info on the collections, add to an array
    // Then a separate call to http://localhost:8080/collections/comics/ --NEED TO WRITE THIS API ENDPOINT
    // > to get all the comics in all the collections ordered by collection id

    //Array for JUST the info about the collections use http://localhost:8080/collections/
    /* allCollectionInfo:[
      {
        collectionId:1,
        userId:1,
        name: "Josh's Fantastic Collection"
      },
      {
        collectionId:2,
        userId:2,
        name: "Jim's Awesome Collection"
      },
      {
        collectionId:3,
        userId:3,
        name: "Sav's Awesome Collection"
      },
      {
        collectionId:4,
        userId:4,
        name: "Dave's Awesome Collection"
      }
      
    ], */
    //Array for JUST the comics inside of the collections
    /* allComicsInCollections:[

      {
        collectionId: 1,
        id: 1001,
        title: "Fantastic Spiderman",
        img: require("../assets/Amazing_spiderman.jpg"),
        description: "description"
      },
      {
        collectionId: 1,
        id: 1002,
        title: "Gwen Stacy",
        img: require("../assets/Aqua1.jpg"),
        description: "description"

      },
      {
        collectionId: 1,
        id: 1003,
        title: "Green Goblin",
        img: require("../assets/Hulk1.jpg"),
        description: "description"

      },
      {
        collectionId: 1,
        id: 1004,
        title: "Spider Clones",
        img: require("../assets/Hulk2.jpg"),
        description: "description"

      }
          
    ], */

    // For Testing Purposes

    //Not hard coded approach

    //1. Add the basic comic information collectionId, userId, name by looping through http://localhost:8080/collections/
    //2. Add a empty comics array for each collection
    //3. For each collection, make a call to http://localhost:8080/collections/comics/the collection id
    //4. Add those comics to the empty comic array for that collection

    collectionsApiStore:[
      {
        collectionId: 0,
        userId:0,
        name: "",
        comics:[
          {
            id: 0,
            title: "",
            img: "",
            description: ""
          },
        ]
      },

    ],
    
    collections:[
      {
        collectionId:1,
        userId:1,
        name: "Josh's Fantastic Collection",
        comics:[
          {
            id: 1001,
            title: "Fantastic Spiderman",
            img: require("../assets/Amazing_spiderman.jpg"),
            description: "description"
          },
          {
            id: 1002,
            title: "Gwen Stacy",
            img: require("../assets/Aqua1.jpg"),
            description: "description"

          },
          {
            id: 1003,
            title: "Green Goblin",
            img: require("../assets/Hulk1.jpg"),
            description: "description"

          },
          {
            id: 1004,
            title: "Spider Clones",
            img: require("../assets/Hulk2.jpg"),
            description: "description"

          }
        ]

      },
      {
        collectionId:2,
        userId:2,
        name: "Jim's Awesome Collection",
        comics:[
          {
            id: 1001,
            title: "Fantastic Spiderman",
            img: require("../assets/Xmen1.jpg"),
            description: "description"
          },
          {
            id: 1002,
            title: "Gwen Stacy",
            img: require("../assets/Xmen2.jpg"),
            description: "description"

          },
          {
            id: 1003,
            title: "Green Goblin",
            img: require("../assets/Spiderman2.jpg"),
            description: "description"

          },
          {
            id: 1004,
            title: "Spider Clones",
            img: require("../assets/Amazing_spiderman.jpg"),
            description: "description"

          }
        ]

      },
      {
        collectionId:3,
        userId:3,
        name: "Sav's Awesome Collection",
        comics:[
          {
            id: 1001,
            title: "Fantastic Spiderman",
            img: require("../assets/Aqua2.jpg"),
            description: "description"
          },
          {
            id: 1002,
            title: "Gwen Stacy",
            img: require("../assets/Thor3.jpg"),
            description: "description"

          },
          {
            id: 1003,
            title: "Green Goblin",
            img: require("../assets/Xmen2.jpg"),
            description: "description"

          },
          {
            id: 1004,
            title: "Spider Clones",
            img: require("../assets/Spiderman3.jpg"),
            description: "description"

          }
        ]

      },
      {
        collectionId:4,
        userId:4,
        name: "Dave's Awesome Collection",
        comics:[
          {
            id: 1001,
            title: "Fantastic Spiderman",
            img: require("../assets/wv3.jpg"),
            description: "description"
          },
          {
            id: 1002,
            title: "Gwen Stacy",
            img: require("../assets/im1.jpg"),
            description: "description"

          },
          {
            id: 1003,
            title: "Green Goblin",
            img: require("../assets/ch1.jpg"),
            description: "description"

          },
          {
            id: 1004,
            title: "Spider Clones",
            img: require("../assets/gl1.jpg"),
            description: "description"

          }
        ]

      },

    ],
    comicLists: [
      
    ]




  },

  //GET COLLECTIONS FROM API

  // getters: {},

  // methods: {
  //   getCollections({commit}) {
  //     axios.get('http://localhost:8080')
  //       .then(response => {
  //         commit('SET_COLLECTIONS', response.data)
  //       })
  //   }
  // },


  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_COLLECTION(state, collection) {
      state.collection = collection;
      localStorage.setItem('collection',JSON.stringify(collection));
    },
    SET_ALL_COLLECTIONS(state, data) {
      state.collections = data;
    },
    SET_COMIC(state, comic) {
      state.comic = comic;
      localStorage.setItem('comic',JSON.stringify(comic));
    }
  }
})
