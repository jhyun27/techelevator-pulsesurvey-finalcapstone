<template>
  <v-app-bar app color="primary" dark>
    <router-link :to="{ name: 'home'}" tag="v-btn">
      <v-btn tile>
        <span class="mr-2">Home</span>
        <v-icon>mdi-home</v-icon>
      </v-btn>
    </router-link>
    <v-spacer></v-spacer>

    <div v-if="authUser!=null">Username: {{authUser.sub}}</div>

    <v-spacer></v-spacer>
    <router-link v-if="!authUser.sub" :to="{ name: 'login'}" tag="v-btn">
      <v-btn tile>
        <span class="mr-2">Login</span>
        <v-icon>mdi-login</v-icon>
      </v-btn>
    </router-link>
    <router-link v-if="authUser.sub" :to="{ name: 'logout'}" tag="v-btn">
      <v-btn tile @click="logout">
        <span class="mr-2">Logout</span>
        <v-icon>mdi-login</v-icon>
      </v-btn>
    </router-link>
    <router-link :to="{ name: 'register'}" tag="v-btn">
      <v-btn tile>
        <span class="mr-2">Register</span>
        <v-icon>mdi-account-plus</v-icon>
      </v-btn>
    </router-link>
  </v-app-bar>
</template>

<script>
import auth from "@/auth.js";

export default {
  name: "nav-bar",
  data: () => ({
    //
    authUser: null
  }),

  created() {
    this.authUser = auth.getUser();
    console.log(this.authUserReactive.sub);
  },
  methods: {
    logout() {
      auth.logout();
    }
  },
  computed: {
    authUserReactive(vm) {
      return vm.authUser;
    }
  }
};
</script>

<style>
v-btn {
  margin: 0 5px;
}
</style>