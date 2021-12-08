<template>
  <div id="app">
    
    <SiteHeader></SiteHeader>
    <div class="side-nav">
          <SidebarItems></SidebarItems>
            </div>

           <router-view />



  </div>
</template>

<style>
#app{
  display: grid;
  grid-template-columns: 1fr 7fr;
  grid-template-areas: 
  "header header"
  "side other";
}
SiteHeader {
  grid-area: header;
}
.side-nav{
  grid-area: side;
justify-items: flex-start;
}
router-view{
  grid-area: other;
}
/* @font-face {
  font-family: "SF Comic Strip";
  src: local("SF Comic Strip"),
 url(./src/Fonts/SFComicStrip-Bold.ttf) format("truetype");
} */



</style>