<template>
  <v-container>
    <v-card>
      <v-card-title>업무 일지 관리</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="user.email"
            :rules="emailRules"
            label="E-mail"
            required
          ></v-text-field>

          <v-text-field
            v-model="user.password"
            :counter="10"
            :rules="passwordRules"
            label="Password"
            required
            type="password"
          ></v-text-field>

          <v-checkbox
            v-model="checkbox"
            :rules="[(v) => !!v || 'You must agree to continue!']"
            label="Do you agree?"
            required
          ></v-checkbox>

          <v-btn
            :disabled="!valid"
            color="success"
            class="mr-4"
            @click="validate"
          >
            Validate
          </v-btn>

          <v-btn color="primary" class="mr-4" @click="login"> Login </v-btn>

          <v-btn color="warning" @click="$router.replace('/join')">
            Join
          </v-btn>
        </v-form>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      user: {
        email: "",
        password: "",
      },
      show: true,
    };
  },
  methods: {
    login() {
      if (this.user.email && this.user.password)
        this.$store.dispatch("login", this.user).then(() => {
          alert("로그인 성공");
          this.$router.push("/prjList");
        });
    },
  },
};
</script>

<style>
</style>