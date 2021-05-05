<template>
  <v-container>
    <v-card elevation="4" tile>
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

          <v-btn color="warning" href="/"> Login </v-btn>
        </v-form>
      </v-card-text>
    </v-card>
  </v-container>
  <!-- <div>
    <b-container fluid="md">
      <b-form v-if="show" action="/join" method="post">
        <b-form-group
          id="input-group-1"
          label="Email address:"
          label-for="input-1"
        >
          <b-form-input
            id="input-1"
            v-model="user.email"
            :state="validation"
            type="email"
            placeholder="Enter email"
            required
          ></b-form-input>
          <b-form-invalid-feedback :state="validation">
            이메일을 입력하세요.
          </b-form-invalid-feedback>
          <b-form-valid-feedback :state="validation">
            사용 가능한 이메일입니다.
          </b-form-valid-feedback>
        </b-form-group>

        <b-form-group id="input-group-2" label="Your Name:" label-for="input-2">
          <b-form-input
            id="input-2"
            v-model="user.name"
            placeholder="Enter name"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-3" label="Password:" label-for="input-3">
          <b-form-input
            type="password"
            id="input-3"
            v-model="user.password"
            placeholder="Enter password"
            required
          ></b-form-input>
        </b-form-group>
      </b-form>

      <b-button type="button" variant="primary" @click="onSubmit"
        >Submit</b-button
      >
      <b-button type="button" variant="danger" @click="onReset">Reset</b-button>
      <b-button variant="info" href="/">Login</b-button>
      <b-button variant="warning" href="/prjList">prjList</b-button>
    </b-container>
  </div> -->
</template>

<script>
console.log("join.vue 진입");

export default {
  data() {
    return {
      user: {
        email: "",
        name: "",
        password: "",
      },
      show: true,
    };
  },
  computed: {
    validation() {
      return this.user.email.length > 0;
    },
  },
  methods: {
    onSubmit: function () {
      //event.preventDefault();
      alert(JSON.stringify(this.user));
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