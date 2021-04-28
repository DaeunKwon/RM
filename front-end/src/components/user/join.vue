<template>
  <div>
    <b-container fluid="md">
      <b-form v-if="show">
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

        <b-button type="button" variant="primary" @click="onSubmit"
          >Submit</b-button
        >
        <b-button type="button" variant="danger" @click="onReset"
          >Reset</b-button
        >
        <b-button variant="info" href="/login">Login</b-button>
        <b-button variant="warning" href="/prjList">prjList</b-button>
      </b-form>
    </b-container>
  </div>
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
    onSubmit() {
      //event.preventDefault();
      alert(JSON.stringify(this.user));
      this.$axios
        .post(
          "/api/user/join",
          JSON.stringify({
            email: this.user.email,
            name: this.user.name,
            password: this.user.password,
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
          this.$router.push("/login");
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

    // prjList() {
    //   this.$axios.get("/prjList");
    // },
  },
};
</script>