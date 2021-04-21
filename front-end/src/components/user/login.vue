<template>
  <div>
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
      <b-button variant="info" href="/">Join</b-button>
      <b-button variant="warning" href="/prjList">prjList</b-button>
    </b-form>
  </div>
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
          "/login",
          JSON.stringify({
            email: this.user.email,
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