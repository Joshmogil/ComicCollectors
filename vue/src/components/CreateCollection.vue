<template>
  <form v-on:submit.prevent>
    <div class="field">
      <label for="title">Title</label>
      <input type="text" name="title" v-model="message.title" />
    </div>
    <div class="field">
      <label for="messageText">Collection</label>
      <input type="text" name="messageText" v-model="message.messageText" />
    </div>
    <div class="actions">
      <button type="submit" v-on:click="saveCollection()">Save Message</button>
    </div>
  </form>
</template>

<script>
import collectionService from "../services/CollectionService";

export default {
  name: "create-collection",
  data() {
    return {
      collection: {
        collectionId: Math.floor(Math.random() * (1000 - 100) + 100),
        comicId: this.comicId,
        collectionName: ""
      }
    };
  },
  methods: {
    saveCollection() {
collectionService
const newCollection = {
    collectionId: this.collection.collectionId,
    comicId: this.collection.comicId,
    collectionName: this.collection.collectionName
}
          .addCollection(newCollection)
          .then(response => {
            if (response.status === 201) {
              this.$router.push(`/collections/${newCollection.collectionId}`);
            }
          })
    }
  }
};
</script>

<style>

</style>