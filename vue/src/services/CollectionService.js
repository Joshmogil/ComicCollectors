import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8081"
});

export default {

  getAllCollections(){
    return http.get(`/collections`);
  },
  
  get(id) {
    return http.get(`/collections/${id}`);
  },

  addCollection(collection) {
      return http.post(`/collections/${collection.collectionId}`);
  }

}