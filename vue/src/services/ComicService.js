import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  find(id) {
    return http.get(`/comics/${id}`);
  },

  addComicToCollections(newComics) {
    return http.post('/collections/addcomics', newComics,
      {
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        }

      });
  },

  searchByCharacterName(characterName) {
    return http.get(`/comics/character/${characterName}`)
  },

  searchMarvel(characterName) {
    return http.get(`/marvelcomics/${characterName}`)
  }
}