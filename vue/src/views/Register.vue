<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 id="banner" class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div
        id="alerts"
        class="alert alert-danger"
        role="alert"
        v-if="registrationErrors"
      >
        {{ registrationErrorMsg }}
      </div>
      <div class="username-group">
        <label id="username-title" for="username" class="sr-only"
          >Username</label
        >
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
      </div>
      <div class="password-group">
        <label id="password-title" for="password" class="sr-only"
          >Password</label
        >
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
      </div>
      <div class="confirm-password-group">
        <label id="confirm-password-title" for="username" class="sr-only"
          >Confirm Password</label
        >
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
      </div>
      <div class="register">
      <router-link :to="{ name: 'login' }" id="account"
        >Have an account?</router-link
      ></div>
      
      <!-- <button
        id="register"
        class="btn btn-lg btn-primary btn-block"
        type="submit"
      >
        Create Account
      </button> -->
      <div class="submit">
        <app-button type="submit" buttonText="Create Account" />
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import AppButton from "../components/Button.vue";

export default {
  name: "register",
  components: { AppButton },
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
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

<style>
.alert-danger {
  color: red;
  text-align: center;
  font-size: 20px;
}

.text-center {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.text-center div {
  margin: 10px;;
}
#username,
#password,
#confirmPassword {
  margin-left: 5px;
}
.username-group,
.password-group,
.confirm-password-group {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
#banner,
.register,
.submit {
  text-align: center;
}
</style>
