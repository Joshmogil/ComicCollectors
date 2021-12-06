import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8081"
});

export default {

  get(id) {
    return http.get(`/collections/${id}`);
  },

  addCollection() {
      return http.post(`/collections`);
  }

}