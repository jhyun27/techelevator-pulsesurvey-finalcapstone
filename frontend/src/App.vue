<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="nav-button">
        <v-btn tile :to="{ name: 'home'}">
          <span class="mr-2">Home</span>
          <v-icon>mdi-home</v-icon>
        </v-btn>
      </div>

      <div class="welcome" v-if="isAuthUserReactive!=false">Welcome, {{authUser.sub}}</div>

      <v-spacer></v-spacer>
      <div v-if="isAdminUserReactive" class="nav-button">
        <v-btn tile v-if="isAdminUserReactive" :to="{ name: 'users'}">
          <span class="mr-2">Admin: View Users</span>
          <v-icon>mdi-account-search</v-icon>
        </v-btn>
      </div>
      <div v-if="!isAuthUserReactive" class="nav-button">
        <v-btn tile :to="{ name: 'register'}">
          <span class="mr-2">Register</span>
          <v-icon>mdi-account-plus</v-icon>
        </v-btn>
      </div>

      <div v-if="!isAuthUserReactive" class="nav-button">
        <v-btn tile :to="{ name: 'login'}">
          <span class="mr-2">Login</span>
          <v-icon>mdi-login</v-icon>
        </v-btn>
      </div>
      <div v-else class="nav-button">
        <v-btn tile @click="logout" :to="{ name: 'logout'}">
          <span class="mr-2">Logout</span>
          <v-icon>mdi-login</v-icon>
        </v-btn>
      </div>
    </v-app-bar>

    <v-content>
      <router-view class="content" @loginSuccess="login" />
    </v-content>
  </v-app>
</template>

<script>
import auth from "@/auth.js";

export default {
  name: "App",

  data: () => ({
    //
    authUser: {},
    isAuthUser: false,
    isAdminUser: false
  }),

  created() {
    this.updateUserInfo();
  },
  methods: {
    updateUserInfo() {
      this.authUser = auth.getUser();
      if (this.authUser.sub) {
        this.isAuthUser = true;
      }
      if (this.authUser.rol.toLowerCase().includes("admin")) {
        this.isAdminUser = true;
      }
    },
    logout() {
      console.log("logout successful");
      auth.logout();
      this.isAuthUser = false;
      this.authUser.sub = null;
      this.isAdminUser = false;
    },
    login() {
      console.log("login successful");
      this.updateUserInfo()
        .then(() => {
          if (this.authUser.sub) {
            this.isAuthUser = true;
          } else {
            this.isAuthUser = false;
          }
        })
        .then(() => {
          if (this.authUser.rol.toLowerCase().includes("admin")) {
            this.isAdminUser = true;
          } else {
            this.isAdminUser = false;
          }
        });
    }
  },
  computed: {
    authUserReactive(vm) {
      return vm.authUser;
    },
    isAuthUserReactive(vm) {
      return vm.isAuthUser;
    },
    isAdminUserReactive(vm) {
      return vm.isAdminUser;
    }
  }
};
</script>

<style>
:root {
  --light-blue: #32abe3;
}
body {
  margin: 0;
  padding: 0;
}
.content {
  width: 80%;
  margin: 20px auto !important;
}
.nav-button {
  margin: 0 5px;
  background-color: #212121 !important;
}
.welcome {
  margin: 0 15px;
  font-size: 120%;
}
.v-btn:before {
  background-color: currentColor;
  background-color: #212121 !important;
}
.v-btn:hover {
  background-color: #444444 !important;
}
</style>