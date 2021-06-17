<template>
  <v-container>
    <v-layout>
      <v-flex md6 offset-md3>
        <v-card>
          <v-card-title class="justify-center">Report Management</v-card-title>
          <v-card-subtitle class="justify-center">Join</v-card-subtitle>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    v-model="name"
                    :rules="nameRules"
                    label="Name"
                    required
                  ></v-text-field
                ></v-col>
                <v-col cols="9">
                  <v-text-field
                    v-model="email"
                    :rules="emailRules"
                    label="Email"
                    required
                  ></v-text-field
                ></v-col>
                <v-col cols="3">
                  <v-btn dark color="green" @click="emailCheck">중복확인</v-btn>
                </v-col>

                <div :color="doubleCheck == true ? 'green' : 'red'">
                  {{ emailAlert }}
                </div>
                <v-col cols="12">
                  <v-text-field
                    v-model="password"
                    :rules="passwordRules"
                    label="Password"
                    required
                    type="password"
                  ></v-text-field>
                </v-col>

                <v-col>
                  <v-btn color="primary" class="mr-4" @click="join">
                    Join
                  </v-btn>

                  <v-btn color="warning" @click="$router.replace('/')">
                    Login
                  </v-btn>
                </v-col>
              </v-row>
            </v-container>
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
    emailAlert: "",
    doubleCheck: "",
  }),

  methods: {
    join: function () {
      //event.preventDefault();
      if (this.email == "" || this.name == "" || this.password == "") {
        alert("모든 항목을 채워주세요.");
        return;
      } else if (this.doubleCheck == false) {
        alert("이메일 중복 확인이 필요합니다.");
        return;
      } else if (this.doubleCheck && this.name && this.password) {
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