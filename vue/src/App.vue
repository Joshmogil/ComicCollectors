<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <SiteHeader></SiteHeader>
    <router-view />
  </div>
</template>

<style>
header{
font: 'Comic Book';
font-style: italic, bold ;
align-content: center;
}
 .loginbutton{

 }

body{

}
</style>