import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  getAllCollections() {
    return http.get(`/collections`);
  },

  get(id) {
    return http.get(`/collections/${id}`);
  },

  addCollection(collection) {
    return http.post(`/collections/${collection.collectionId}`);
  },

  getComics(id) {
    return http.get(`/collections/comics/${id}`);
  }



}