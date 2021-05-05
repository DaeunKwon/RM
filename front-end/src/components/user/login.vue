<template>
  <v-container>
    <v-card elevation="4" tile>
      <v-card-title class="justify-center">업무 일지 관리</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="email"
            :rules="emailRules"
            label="E-mail"
            required
          ></v-text-field>

          <v-text-field
            v-model="password"
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

          <v-btn color="warning" href="/join"> Join </v-btn>
        </v-form>
      </v-card-text>
    </v-card>
  </v-container>
  <!-- <div>
    <b-form inline>
      <label class="sr-only" for="inline-form-input-email">Email</label>
      <b-form-input
        id="inline-form-input-email"
        class="mb-2 mr-sm-2 mb-sm-0"
        placeholder="email"
        v-model="user.email"
        required
      ></b-form-input>

      <label class="sr-only" for="inline-form-input-password">Password</label>
      <b-input-group prepend="@" class="mb-2 mr-sm-2 mb-sm-0">
        <b-form-input
          id="inline-form-input-password"
          placeholder="Password"
          v-model="user.password"
          required
        ></b-form-input>
      </b-input-group>

      <b-form-checkbox class="mb-2 mr-sm-2 mb-sm-0"
        >Remember me</b-form-checkbox
      >

      <b-button type="button" variant="primary" @click="login">Login</b-button>
      <b-button variant="info" href="/join">Join</b-button>
      <b-button variant="warning" href="/prjList">prjList</b-button>
    </b-form>
  </div> -->
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