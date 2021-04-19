<template>
  <div>
    <b-form v-if="show">
      <b-form-group
        id="input-group-1"
        label="Email address:"
        label-for="input-1"
        description="We'll never share your email with anyone else."
      >
        <b-form-input
          id="input-1"
          v-model="user.email"
          type="email"
          placeholder="Enter email"
          required
        ></b-form-input>
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
          id="input-3"
          v-model="user.password"
          placeholder="Enter password"
          required
        ></b-form-input>
      </b-form-group>

      <b-button type="button" variant="primary" @click="onSubmit"
        >Submit</b-button
      >
      <b-button type="button" variant="danger" @click="onReset">Reset</b-button>
      <b-button variant="info" href="/login">Login</b-button>
    </b-form>
    <b-card class="mt-3" header="Form Data Result">
      <pre class="m-0">{{ user }}</pre>
    </b-card>
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
  methods: {
    onSubmit() {
      //event.preventDefault();
      alert(JSON.stringify(this.user));
      // this.user.email = this.email;
      // this.user.name = this.name;
      // this.user.password = this.password;
      this.$axios.post(
        "/joinPOST",
        {
          email: this.email,
          name: this.name,
          password: this.password,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
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