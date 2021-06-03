<template>
  <v-container>
    <v-layout>
      <v-flex md6 offset-md3>
        <v-card>
          <v-card-title class="justify-center">Report Management</v-card-title>
          <v-card-subtitle class="justify-center">Join</v-card-subtitle>
          <v-card-text>
            <v-form ref="form" v-model="valid" lazy-validation>
              <v-text-field
                v-model="name"
                :rules="nameRules"
                label="Name"
                required
              ></v-text-field
              ><br />

              <v-layout row>
                <v-flex xs9>
                  <v-text-field
                    v-model="email"
                    :rules="emailRules"
                    label="E-mail"
                    required
                  ></v-text-field>
                </v-flex>
                <v-flex x1 class="pt-3">
                  <v-btn dark color="light grey" @click="emailCheck"
                    >중복확인</v-btn
                  >
                </v-flex>
              </v-layout>
              <div>
                {{ emailAlert }}
              </div>
              <br />
              <v-text-field
                v-model="password"
                :rules="passwordRules"
                label="Password"
                required
                type="password"
              ></v-text-field>

              <br />

              <v-btn color="primary" class="mr-4" @click="join"> Join </v-btn>

              <v-btn color="warning" @click="$router.replace('/')">
                Login
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
  data() {
    return {
      email: "",
      name: "",
      password: "",
      doubleCheck: "",
      emailAlert: "",
      show: true,
    };
  },
  data: () => ({
    email: "",
    emailRules: [(v) => !!v || "이메일을 입력하세요."],
    name: "",
    nameRules: [(v) => !!v || "이름을 입력하세요."],
    password: "",
    passwordRules: [(v) => !!v || "비밀번호를 입력하세요."],
  }),

  methods: {
    join: function () {
      //event.preventDefault();
      if (this.email == "" || this.name == "" || this.password == "") {
        alert("모든 항목을 채워주세요.");
        return;
      }
      if (this.doubleCheck && this.name && this.password) {
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
      }
    },

    emailCheck() {
      this.$axios
        .post("http://localhost:8090/api/user/emailCheck", this.email, {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          console.log(res);
          if (res.data) {
            this.doubleCheck = true;
            this.emailAlert = "사용 가능한 이메일입니다.";
          } else {
            this.doubleCheck = false;
            this.emailAlert = "이미 가입된 이메일입니다.";
          }
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