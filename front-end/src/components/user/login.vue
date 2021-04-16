<template>
  <div>
    <b-form inline @submit="onSubmit">
      <label class="sr-only" for="inline-form-input-email">Email</label>
      <b-form-input
        id="inline-form-input-email"
        class="mb-2 mr-sm-2 mb-sm-0"
        placeholder="email"
        v-model="email"
      ></b-form-input>

      <label class="sr-only" for="inline-form-input-password">Password</label>
      <b-input-group prepend="@" class="mb-2 mr-sm-2 mb-sm-0">
        <b-form-input
          id="inline-form-input-password"
          placeholder="Password"
          v-model="password"
        ></b-form-input>
      </b-input-group>

      <b-form-checkbox class="mb-2 mr-sm-2 mb-sm-0"
        >Remember me</b-form-checkbox
      >

      <b-button type="submit" variant="primary">Login</b-button>
      <b-button variant="info" href="/">Join</b-button>
    </b-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data: () => ({
    email: "",
    password: "",
  }),
  methods: {
    onSubmit() {
      console.log(this.email);
      console.log(this.password);
    },
  },
};
</script>

<style>
</style>