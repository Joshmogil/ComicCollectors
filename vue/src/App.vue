<template>
  <div id="app">  

    <SiteHeader></SiteHeader>
    <!-- <SidebarItems></SidebarItems> -->
    <div class="side-nav">
    </div>
      <nav class="links">
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
      </nav>
         <router-view />
  </div>
</template>

  <script>



  </script>

<style>

.header-button{
  height: 10vh;
  width: auto;
}

a{
  text-decoration: none;
  color: rgb(196, 37, 196);
}

.links{
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}


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
  background-image: url(../src/assets/site-background.jpeg);
}
@import url('https://fonts.googleapis.com/css2?family=Bangers&family=Readex+Pro&display=swap');

#app{
  background-image: url("/assets/Light-Blue-Background.png"); 
  font-family: 'Bangers';
}

#search-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  max-width: auto;
}
.search-card {
  cursor: pointer;
  height: 250px;
  perspective: 600;
  position: relative;
  width: 188px;
}

.user-card-container {
  display: flex;
  cursor: pointer;
  height: 250px;
  perspective: 600;
  position: relative;
  width: 188px;
   overflow: hidden;
  
}

.collection-comics {
  display: flex;
  justify-content: center;
  flex-direction: column;
  padding: 15px 12px;
  box-sizing: border-box;
  width: 20rem;
  height: 20rem;

}

.small-button {
  height: 7vh;
  width: auto;
}





</style>