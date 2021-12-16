<template>
  <div id="sidebar">
             <div class= "nav-link">
          <router-link v-bind:to="{ name: 'login' }"><img src="@/assets/login.png" class="header-button"></router-link> 
        </div>
        <div class= "nav-link">
          <router-link v-bind:to="{ name: 'logout' }"><img src="@/assets/logout.png" class="header-button"></router-link> 
        </div>
        <div class= "nav-link">
          <router-link v-bind:to="{ name: 'home' }"><img src="@/assets/Home.png" class="header-button">
        </router-link>
        </div>
        <div class= "nav-link">
          <router-link v-bind:to="{ name: 'userDetails', params: { userId: this.$store.state.user.id || 0 }  }"><img src="@/assets/mycollections.png" class="header-button"></router-link>
        </div>
        <div class= "nav-link">
          <router-link v-bind:to="{ name: 'statistics' }"><img src="@/assets/statistics.png" class="header-button"></router-link>
        </div>
        <div class= "nav-link">
          <router-link v-bind:to="{ name: 'search' }"><img src="@/assets/search.png" class="header-button"></router-link>
        </div>
    </div>
</template>

<script>
export default {

}
</script>

<style>