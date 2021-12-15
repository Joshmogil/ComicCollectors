<template>
  <div id="characters-main">
      <div id="characters" v-for="character in characterStats"
      v-bind:key="character.characterId"
      >
        <div>
          <div>{{character.characterName}}</div>
          <div>
            <div class="stat-container">
              <div class="card">
                <div class="side stat-img"><img :src="character.imgUrl" alt="" /></div>
                <div class="side back">{{ character.description }}</div>
              </div>
            </div>
          </div>
    
            <!-- <img class="stat-img" :src="character.imgUrl" alt="" /></div> -->
          <div>Appearances: {{character.count}}</div>
        </div>

      </div>

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

/* need to fix the width on these */

.stat-container {
  cursor: pointer;
  height: 250px;
  perspective: 600;
  position: relative;
  width: 188px;
}

.stat-img{
  max-width: 150px;
}

#characters{
  display: flex;
}

#characters-main{
  display:flex;
  flex-wrap: wrap;
  gap: 10px;
}

</style>

