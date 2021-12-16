<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="Promt">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <label for="username" class="sr-only"> <h2>Username</h2></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <div class="password">
        <label for="password" class="sr-only"><h2>Password</h2></label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
      </div>
      <router-link :to="{ name: 'register' }"><h2>Need an account?</h2></router-link>
      <button id = "submitButton" type="submit"> <img src="@/assets/signin.png" class="small-button"></button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$store.commit("SET_IS_LOGGED_IN", true); 
            this.$router.push("/");
            //this.$router.go();

           
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
    
  },
};
</script>

<style>

form {
  text-align: center;
}
.text-center {
  display: flex;
  justify-content: flex-start;
}

.Promt {
  display: flex;
  justify-content: center;
}

#login {
  display:flex;
  justify-content: center;

}


#submitButton {
  background: transparent;
  border: none;
}

</style>
