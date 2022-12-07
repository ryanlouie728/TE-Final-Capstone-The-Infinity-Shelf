<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 id="banner" class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div id="alerts" class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label id="username-title" for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label id="password-title" for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <label id="confirm-password-title" for="username" class="sr-only">Confirm Password</label>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <router-link :to="{ name: 'login' }" id="account">Have an account?</router-link>
      <button id="register" class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
.form-register {
  display: grid;
  grid-template-columns: 150px 200px;
  row-gap: 10px;
  grid-template-areas: 
    "banner banner"
    "alerts alerts"
    "u-title u-input"
    "p-title p-input"
    "cp-title cp-input"
    "account register";
}


#banner {
  grid-area: banner;
}

#alerts {
  grid-area: alerts;
}

#username-title {
  grid-area: u-title;

}

#username {
  grid-area: u-input;

}

#password-title {
  grid-area: p-title;

}

#password {
  grid-area: p-input;

}

#confirm-password-title {
  grid-area: cp-title;
}

#confirmPassword {
  grid-area: cp-input;
}

#account {
  grid-area: account;
  justify-self: center;
}

#register {
  grid-area: register;
  justify-self: center;
}

</style>
