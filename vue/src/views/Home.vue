<template>
<div>
  <div class="home">
    <home-page />
  </div>
  <div class = "collection-grid">
    <collection-grid/>
  </div>
</div>
</template>

<script>
import HomePage from '@/components/HomePage';
import CollectionGrid from '@/components/CollectionGrid';

export default {
  components: {
    HomePage,
    CollectionGrid
    
  }
};
</script>
