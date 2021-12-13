import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  find(id) {
    return http.get(`/users/id/${id}`);
  },

  getUserCollections(id){
    
    return http.get(`/collections/user/${id}`);
  }

  

}