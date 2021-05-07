<template>
  <v-container>
    <v-card>
      <v-card-title>업무 일지 관리</v-card-title>
      <v-card-subtitle>회원가입</v-card-subtitle>
      <v-card-text>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="name"
            :rules="nameRules"
            label="Name"
            required
          ></v-text-field>

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

          <v-btn
            :disabled="!valid"
            color="success"
            class="mr-4"
            @click="validate"
          >
            Validate
          </v-btn>

          <v-btn color="primary" class="mr-4" @click="join"> Join </v-btn>

          <v-btn color="warning" @click="$router.replace('/')"> Login </v-btn>
        </v-form>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
console.log("join.vue 진입");

export default {
  data() {
    return {
      email: "",
      name: "",
      password: "",
      show: true,
    };
  },
  computed: {
    validation() {
      return this.user.email.length > 0;
    },
  },
  methods: {
    submit: function () {
      //event.preventDefault();
      this.$axios
        .post(
          "http://localhost:8090/api/user/join",
          JSON.stringify({
            email: this.email,
            name: this.name,
            password: this.password,
          }),
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {
          console.log(res);
          alert("환영합니다.");
          this.$router.push("/");
        });
    },

    onReset(event) {
      event.preventDefault();
      // Reset our form values
      this.user.email = "";
      this.user.name = "";
      this.user.password = "";
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>