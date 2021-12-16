<template>
  <h1>Logout</h1>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    
    this.$store.commit("SET_IS_LOGGED_IN", false);
    
    this.$router.push("/login");
    this.$router.go();
  }
};
</script>
