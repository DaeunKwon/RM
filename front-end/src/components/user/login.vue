<template>
  <v-container>
    <v-layout>
      <v-flex md6 offset-md3>
        <v-card>
          <v-card-title class="justify-center">Report Management</v-card-title>
          <v-card-text>
            <v-form ref="form" lazy-validation method="post" action="/login">
              <v-text-field
                v-model="email"
                :rules="emailRules"
                label="E-mail"
                required
                name="email"
              ></v-text-field>

              <v-text-field
                v-model="password"
                :counter="10"
                :rules="passwordRules"
                label="Password"
                required
                type="password"
                name="password"
              ></v-text-field
              ><br />

              <v-btn color="primary" class="mr-4" type="submit" @click="login">
                Login
              </v-btn>

              <v-btn color="warning" @click="$router.replace('/join')">
                Join
              </v-btn>
            </v-form>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      email: "",
      password: "",
      show: true,
    };
  },
  data: () => ({
    email: "",
    emailRules: [(v) => !!v || "E-mail is required"],
    password: "",
    passwordRules: [(v) => !!v || "Password is required"],
  }),
  methods: {
    login() {
      console.log("login");
    },
  },
};
</script>

<style>
</style>