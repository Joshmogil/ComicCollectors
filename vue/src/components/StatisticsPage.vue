<template>
  <div>
      <ul v-for="character in characterStats"
      v-bind:key="character.characterId"
      >
          <li>{{character.characterName}}</li>
          <li><img class="stat-img" :src="character.imgUrl" alt="" /></li>
          <li>Appearances: {{character.count}}</li>

      </ul>

  </div>
</template>

<script>
import comicService from "@/services/ComicService.js";
export default {
  data() {
    return {
      isLoading: true,
    };
  },
  methods: {
    getCharacterStats() {
      comicService.getCharacterStatistics().then(response => {
        this.$store.commit("SET_CHARACTER_STATS", response.data);
        this.isLoading = false;
      });
    },
  },
  computed: {
    characterStats() {
      return this.$store.state.characterStats;
    },
  },

  created() {
    this.getCharacterStats();
  },
};
</script>

<style>
.stat-img{
  max-width: 150px;
}
</style>