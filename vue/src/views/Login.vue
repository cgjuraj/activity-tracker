<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <img src="../assets/read-logo-3-01.png" alt="Read" />
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
          
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <div id="bottom-buttons">
      <router-link id="account" :to="{ name: 'register' }">Need an account?</router-link>
      <button id="signin" type="submit">Sign in</button> </div>
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
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>
<style scoped>
.alert  {
color: red;
}

img {
  padding-top: 1.5em;
  max-width: 90%;
}
#signin {
  background-color: #2ab675;
  border: 2px solid #035349
  ;
}

.form-signin {
  border: 1px solid rgb(150, 145, 145);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0px 20px 20px 20px;
  box-shadow: inset 0 0 15px rgb(93, 134, 125);
  width: 25em;
  margin-top: 35px;
  height: 75%;
  background-color: white;
}
#account {
  text-decoration: none;
  margin-right: 25px;
  padding-top: 25px;
  color: #035349;
}
h1 {
  border: none;
  padding-bottom: 0em;
  padding-top: .5em;
  margin-top: .5em;
  color: #035349;
}
#login {
  padding-left: 1em;
}
#bottom-buttons {
  padding-top: 1em;
}
.text-center {
  background-color: rgb(241, 238, 238);
  height: 100vh;
  display: flex;
  justify-content: space-evenly;
  box-shadow: inset 0 0 10px rgb(192, 189, 189);
  border-radius: 5px;
}

</style>