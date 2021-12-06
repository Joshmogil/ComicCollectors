import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8081"
});

export default {

  find(id) {
    return http.get(`/users/${id}`);
  }

}