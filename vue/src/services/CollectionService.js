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

  addCollection(newCollection) {
    return http.post('/collections/create', newCollection,
      {
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        }

      });
  },

  getComics(id) {
    return http.get(`/collections/comics/${id}`);
  },

  deleteCollection(id) {
    return http.delete(`/collections/delete/${id}`);
  }

}