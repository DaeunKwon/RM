<template>
  <v-container>
    <v-card>
      <v-card-title>업무 일지 관리</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="email"
            :counter="10"
            :rules="nameRules"
            label="E-mail"
            required
          ></v-text-field>

          <v-text-field
            v-model="password"
            :rules="emailRules"
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

          <v-btn color="warning" href="/join"> Join </v-btn>
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
      email: "",
      password: "",
      show: true,
    };
  },
  methods: {
    login() {
      this.$axios
        .post(
          "http://localhost:8090/api/user/login",
          JSON.stringify({
            email: this.email,
            password: this.password,
          }),
          {
            headers: {
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {
          console.log(res);
          alert("로그인 성공");
          this.$router.push("/prjList");
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style>
</style>