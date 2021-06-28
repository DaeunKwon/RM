<template>
  <v-row>
    <v-col cols="12" sm="6" md="10">
      <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        :return-value.sync="date"
        transition="scale-transition"
        offset-y
        min-width="auto"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
            v-model="date"
            prepend-icon="mdi-calendar"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="date" no-title scrollable>
          <v-spacer></v-spacer>

          <v-btn text color="primary" @click="menu = false"> Cancel </v-btn>
          <v-btn
            text
            color="primary"
            @click="[$refs.menu.save(date), dateCommand()]"
          >
            OK
          </v-btn>
        </v-date-picker>
      </v-menu>
    </v-col>
  </v-row>
</template>

<script>
export default {
  data: () => ({
    date: "",
    menu: false,
  }),
  created() {
    this.now();
  },
  computed: {
    today: {
      get() {
        return this.$store.getters.getDate;
      },
      set() {},
    },
  },

  methods: {
    dateCommand() {
      this.$store.commit("setComdate", this.date);
    },
    now() {
      this.date = new Date().toISOString().substr(0, 10);
    },
  },
};
</script>