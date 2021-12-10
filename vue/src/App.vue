<template>
  <div id="app">  
    <SiteHeader></SiteHeader>
    <div class="side-nav">
      <SidebarItems></SidebarItems>
    </div>
      <nav class="links">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link v-bind:to="{ name: 'login' }">Login</router-link> 
        <router-link v-bind:to="{ name: 'userDetails' }">My Collections</router-link>
      </nav>
         <router-view />
  </div>
</template>

  <script>


  </script>

<style>


.links{
  display: flex;
  flex-direction: column;
}

/* @font-face {
  font-family: "SF Comic Strip";
  src: local("SF Comic Strip"),
 url(./src/Fonts/SFComicStrip-Bold.ttf) format("truetype");
 */



</style>