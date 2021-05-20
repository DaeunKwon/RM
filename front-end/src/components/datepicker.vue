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
            label="Picker in menu"
            prepend-icon="mdi-calendar"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="date" no-title scrollable>
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="menu = false"> Cancel </v-btn>
          <v-btn text color="primary" @click="$refs.menu.save(date)">
            OK
          </v-btn>
        </v-date-picker>
      </v-menu>
    </v-col>
  </v-row>
</template>

<script>
import eventBus from "../assets/js/eventbus.js";
export default {
  data: () => ({
    date: new Date().toISOString().substr(0, 10),
    menu: false,
  }),
  created() {
    eventBus.$on("date", (date) => {
      this.date = date;
    });
  },
};
</script>