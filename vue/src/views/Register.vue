<template>
  <div id="register" class="text-center">
    <div class="container-register">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div id="radios">
      <input id="parent"
        v-model="user.userRole"
        type="radio"
        name="userRole"
        value="ROLE_ADMIN"
      />
      <label for="parent">Parent</label>
      <br />
      <input id="child" 
        v-model="user.userRole"
        type="radio"
        name="userRole"
        value="child"
      />
      <label for="child">Child</label>
    </div>
            <div v-if="user.userRole === 'child'">
        <input v-model="user.ageCheck" type="checkbox" name="agecheck" />
        <label for=" agecheck"> Are you older than 13? </label>
      </div>
       <br />
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
      <br>
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <br>
      
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <br />
     
      <div class="account">

      <div class="cont1">
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      </div>

      <div class="cont2">
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      </div>
      </div>
    </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
        ageCheck: false,
        userRole: "",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else if (this.user.ageCheck == false && this.user.userRole == "child") {
        this.registrationErrors = true;
        this.registrationErrorMsg =
          "You must be 13 or older to be able to have an account";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style >
#radios {
  display: flex;
}
#child, #parent{
  margin-left: 20px;
  margin-bottom: 8px;
}

.btn {
  margin-left: 15px;
  background-color: #2ab675;
  border: 2px solid #035349
}

.account {
  
  display: flex;
  justify-content: space-evenly;
}
.container-register {
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-register {
  border: 1px solid rgb(150, 145, 145);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0px 20px 20px 20px;
  background-color: white;
  box-shadow: inset 0 0 15px rgb(93, 134, 125);
  width: 25em;
  margin-top: 35px;
  height: 75%;
}

img {
  padding-top: 1em;
  max-width: 90%;
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
#register {
  padding-left: 1em;
  
}
#bottom-buttons {
  padding-top: 1em;
  padding-bottom:1em;
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
