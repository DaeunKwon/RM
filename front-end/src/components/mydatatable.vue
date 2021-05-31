<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr v-for="item in week" :key="item.name">
          <th class="text-left">{{ item.Sun }} (일)</th>
          <th class="text-left">{{ item.Mon }} (월)</th>
          <th class="text-left">{{ item.Tue }} (화)</th>
          <th class="text-left">{{ item.Wed }} (수)</th>
          <th class="text-left">{{ item.Tur }} (목)</th>
          <th class="text-left">{{ item.Fri }} (금)</th>
          <th class="text-left">{{ item.Sat }} (토)</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in weekdata" :key="item.name">
          <td>{{ item.sunday }}</td>
          <td>{{ item.monday }}</td>
          <td>{{ item.tuesday }}</td>
          <td>{{ item.wednesday }}</td>
          <td>{{ item.thursday }}</td>
          <td>{{ item.friday }}</td>
          <td>{{ item.saturday }}</td>
        </tr>
      </tbody>
      <span>{{ test }}</span>
    </template>
  </v-simple-table>
</template>

<script>
var moment = require("moment");
var now = moment(new Date());
var end = moment([2015, 0, 4]);

import eventBus from "../assets/js/eventbus.js";
export default {
  data: () => ({
    datepick: now,
    week: [
      {
        Sun: "",
        Mon: "",
        Tue: "",
        Wed: "",
        Tur: "",
        Fri: "",
        Sat: "",
      },
    ],
    weekdata: [
      {
        sunday: "1",
        monday: "2",
        tuesday: "3",
        wednesday: "4",
        thursday: "5",
        friday: "6",
        saturday: "7",
      },
    ],
  }),
  created() {
    eventBus.$on("datepick", (datepick) => {
      this.datepick = datepick;
      console.log(this.datepick);
    });
  },

  computed: {
    test() {
      const diff = moment(this.datepick).diff(end, "hours");
      const days = Math.floor(diff / 24);
      const final = days % 7;
      this.week = [
        {
          Sun: moment(this.datepick)
            .add(0 - final, "d")
            .format("YYYY-MM-DD"),
          Mon: moment(this.datepick)
            .add(1 - final, "d")
            .format("YYYY-MM-DD"),
          Tue: moment(this.datepick)
            .add(2 - final, "d")
            .format("YYYY-MM-DD"),
          Wed: moment(this.datepick)
            .add(3 - final, "d")
            .format("YYYY-MM-DD"),
          Tur: moment(this.datepick)
            .add(4 - final, "d")
            .format("YYYY-MM-DD"),
          Fri: moment(this.datepick)
            .add(5 - final, "d")
            .format("YYYY-MM-DD"),
          Sat: moment(this.datepick)
            .add(6 - final, "d")
            .format("YYYY-MM-DD"),
        },
      ];
    },
  },
};
</script>
