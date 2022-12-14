<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 id="banner" class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        id="alerts"
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >
        Invalid username and password!
      </div>
      <div
        id="alerts"
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
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
      <div class="register">
        <router-link :to="{ name: 'register' }" id="register"
          >Need an account?</router-link
        >
      </div>
      <!-- <button id="sign-in" class="sign-in" type="submit">Sign in</button> -->
      <div class="submit">
        <app-button type="submit" buttonText="Sign in" />
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import AppButton from "../components/Button.vue";

export default {
  name: "login",
  components: { AppButton },
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
            this.$router.push("/");
          }
          console.log(this.$store.state.user)
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
#password {
  margin-left: 5px;
}
.username-group,
.password-group {
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
