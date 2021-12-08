<template>
  <div id="sidebar">
      <router-link id="home" class="nav" v-bind:to="{ name: 'home' }">Home</router-link>
          <router-link id="login" class="nav" v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>       
          <router-link id="logout" class="nav" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
</template>

<script>
export default {

}
</script>

<style>