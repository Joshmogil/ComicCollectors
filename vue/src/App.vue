<template>
  <div id="app">  
  components: { SidebarItems },

    <SiteHeader></SiteHeader>
    <div class="side-nav">
    </div>
      <nav class="links">
        <sidebar-items></sidebar-items>
      </nav>
         <router-view />
  </div>
</template>

  <script>
import SidebarItems from './components/SidebarItems.vue';



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





</style>