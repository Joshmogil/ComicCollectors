<template>
  <div id="header">
      <router-link id="home" class="nav" v-bind:to="{ name: 'home' }">Home</router-link>
      <header> Comic Collectors</header>
      <router-link id="logging" class="nav" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
</template>

<script>
export default {

}
</script>

<style>

#header{
    display: flex;
    justify-content: center;
    font-size: 80px;
    font: 'Comic Book','Times New Roman';
    font-style: italic, bold ;
    background-color: #DAD53A;
    color: black;
    

}
.nav{
  display: flex;
font-size: 20px;
align-items: flex-end;
}
#home{
  justify-items: flex-start;
  
}

a:visited{
  color: #806300;
}


</style>