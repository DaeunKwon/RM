<template>
  <v-container class="header-padding">
    <v-layout>
      <v-flex md6 offset-md3>
        <v-card>
          <v-card-title class="justify-center">Report Management</v-card-title>
          <v-card-text>
            <v-form ref="form" lazy-validation method="post" action="/login">
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    v-model="email"
                    :rules="emailRules"
                    label="E-mail"
                    required
                    name="email"
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field
                    v-model="password"
                    :counter="10"
                    :rules="passwordRules"
                    label="Password"
                    required
                    type="password"
                    name="password"
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-btn
                    color="primary"
                    class="mr-4"
                    type="submit"
                    @click="login"
                  >
                    로그인
                  </v-btn>

                  <v-btn color="warning" @click="$router.replace('/join')">
                    회원가입
                  </v-btn>
                </v-col>
              </v-row>
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
    emailRules: [(v) => !!v || "이메일을 입력하세요."],
    password: "",
    passwordRules: [(v) => !!v || "비밀번호를 입력하세요."],
  }),
  methods: {
    login() {
      if (this.email == "" || this.password == "") {
        alert("빈 칸을 채워주세요.");
      }
    },
  },
};
</script>

<style>
</style>