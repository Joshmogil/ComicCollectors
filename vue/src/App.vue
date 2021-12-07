<template>
  <div id="app">
    
    <SiteHeader></SiteHeader>
    <div class="side-nav">
          <router-link id="home" class="nav" v-bind:to="{ name: 'home' }">Home</router-link>
          <router-link id="logging" class="nav" v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>       
          <router-link id="logging" class="nav" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
           <router-view />



  </div>
</template>

<style>

/* @font-face {
  font-family: "SF Comic Strip";
  src: local("SF Comic Strip"),
 url(./src/Fonts/SFComicStrip-Bold.ttf) format("truetype");
} */



</style>