<template>
  <div id="login" class="text-center">
    <div id="app">
      <v-app id="inspire">
        <v-content>
          <v-container fluid fill-height>
            <v-layout align-center justify-center>
              <v-flex xs12 sm8 md4>
                <v-card class="elevation-12">
                  <v-toolbar color="primary" dark flat>
                    <v-toolbar-title>Login form</v-toolbar-title>
                    <v-spacer></v-spacer>
                  </v-toolbar>
                  <v-card-text>
                    <v-form @submit.prevent="login">
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
                      <v-text-field
                        label="Login"
                        name="login"
                        prepend-icon="person"
                        type="text"
                        v-model="user.username"
                        required
                        autofocus
                      ></v-text-field>

                      <v-text-field
                        id="password"
                        label="Password"
                        name="password"
                        prepend-icon="lock"
                        type="password"
                        v-model="user.password"
                        required
                      ></v-text-field>

                      <router-link :to="{ name: 'register' }">Need an account?</router-link>
                      <v-spacer></v-spacer>
                      <v-btn type="submit" color="primary">Login</v-btn>
                    </v-form>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-flex>
            </v-layout>
          </v-container>
        </v-content>
      </v-app>
    </div>
  </div>
</template>

<script>
import auth from "../auth";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      userFetch: {},
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/login`, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.user)
      })
        .then(response => {
          if (response.ok) {
            return response.text();
          } else {
            this.invalidCredentials = true;
          }
        })
        .then(token => {
          if (token != undefined) {
            if (token.includes('"')) {
              token = token.replace(/"/g, "");
            }
            auth.saveToken(token);
            this.$router.push({ path: "/", query: { login: "success" } });
            this.$emit("loginSuccess");
          }
        })
        .then(() => {
          this.userFetch = auth.getUser();
        })
        .then(() => {
          console.log(this.userFetch);
        })
        .catch(err => console.error(err));
    }
  }
};
</script>

<style>
</style>
