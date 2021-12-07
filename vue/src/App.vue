<template>
  <div id="app">
    <router-link v-bind:to="{ name: 'header' }">header</router-link>&nbsp;|&nbsp;
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
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