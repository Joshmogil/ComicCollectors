<template>
  <div id="app">  
    <SiteHeader></SiteHeader>
    <div class="side-nav">
      <!-- <SidebarItems></SidebarItems> -->
    </div>
      <nav class="links">
        <div class= "nav-link">
          <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        </div>
        <div class= "nav-link">
          <router-link v-bind:to="{ name: 'login' }">Login</router-link> 
        </div>
        <div class= "nav-link">
          <router-link v-bind:to="{ name: 'userDetails', params: { userId: this.$store.state.user.id || 0 }  }">My Collections</router-link>
        </div>
        <div class= "nav-link">
          <router-link v-bind:to="{ name: 'statistics' }">Statistcis</router-link>
        </div>
        <div class= "nav-link">
          <router-link v-bind:to="{ name: 'search' }">Search</router-link>
        </div>
      </nav>
         <router-view />
  </div>
</template>

  <script>


  </script>

<style>


.links{
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}

/* @font-face {
  font-family: "SF Comic Strip";
  src: local("SF Comic Strip"),
 url(./src/Fonts/SFComicStrip-Bold.ttf) format("truetype");
 */
.card-container {
  cursor: pointer;
  height: 250px;
  perspective: 600;
  position: relative;
  width: 188px;
}
.card {
  height: 100%;
  position: absolute;
  transform-style: preserve-3d;
  transition: all 1s ease-in-out;
  width: 100%;
}
.card:hover {
  transform: rotateY(180deg);
}
.card .side {
  backface-visibility: hidden;
  border-radius: 6px;
  height: 100%;
  position: absolute;
  overflow: hidden;
  width: 100%;
}
.card .back {
  background: #eaeaed;
  color: black;
  line-height: 150px;
  text-align: center;
  transform: rotateY(180deg);
}

/* .nav-link {

border: 1px Solid rgb(0, 0, 19);

} */

#app{
  background:#F1F3F4;
  
}







</style>