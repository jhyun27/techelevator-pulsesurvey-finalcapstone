<template>
  <div id="register" class="text-center">
    <div id="app">
      <v-app id="inspire">
        <v-content>
          <v-container fluid fill-height>
            <v-layout align-center justify-center>
              <v-flex xs12 sm8 md4>
                <v-card class="elevation-12">
                  <v-toolbar color="primary" dark flat>
                    <v-toolbar-title>Create Account</v-toolbar-title>
                    <v-spacer></v-spacer>
                  </v-toolbar>
                  <v-card-text>
                    <v-form @submit.prevent="register">
                      <div
                        class="alert alert-danger"
                        role="alert"
                        v-if="registrationErrors"
                      >There were problems registering this user.</div>
                      <div
                        class="alert alert-success"
                        role="alert"
                        v-if="this.$route.query.registration"
                      >Thank you for registering, please sign in.</div>
                      <v-text-field
                        label="Username"
                        name="username"
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

                      <v-text-field
                        id="confirmPassword"
                        label="Confirm Password"
                        name="confirmpassword"
                        prepend-icon="lock"
                        type="password"
                        v-model="user.confirmPassword"
                        required
                      ></v-text-field>

                      <router-link :to="{ name: 'login' }">Have an account?</router-link>
                      <v-spacer></v-spacer>
                      <v-btn type="submit" color="primary">Create Account</v-btn>
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
export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user"
      },
      registrationErrors: false
    };
  },
  methods: {
    register() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/register`, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.user)
      })
        .then(response => {
          if (response.ok) {
            this.$router.push({
              path: "/login",
              query: { registration: "success" }
            });
          } else {
            this.registrationErrors = true;
          }
        })

        .then(err => console.error(err));
    }
  }
};
</script>

<style>
</style>
