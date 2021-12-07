<template>
  <div id="app">
    
    <SiteHeader></SiteHeader>
    <router-view />
          <router-link id="home" class="nav" v-bind:to="{ name: 'home' }">Home</router-link>


  </div>
</template>

<style>

/* @font-face {
  font-family: "SF Comic Strip";
  src: local("SF Comic Strip"),
 url(./src/Fonts/SFComicStrip-Bold.ttf) format("truetype");
} */

</style>