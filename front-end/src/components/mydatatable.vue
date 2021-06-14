<template>
  <div>
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr v-for="item in test" :key="item.name">
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
          <tr>
            <td v-for="item in getweeklist" :key="item.name">
              {{ Math.floor(item.com_total / 60) }} 시간
              {{ item.com_total % 60 }} 분
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    <div align="center" justify="center">
      <br />
      이번주 총 근무시간 : {{ sumsum }} {{ getweekTime }}
    </div>
  </div>
</template>

<script>
var moment = require("moment");
var end = moment([2015, 0, 4]);
var nowdate = new Date().toISOString().substr(0, 10);

export default {
  data: () => ({
    datepick: "",
    weeklist: [],
    test1: "",
    sumsum: "",
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
  }),
  created() {},
  mounted() {},
  computed: {
    test() {
      this.datepick = this.$store.getters.getDate;
      const diff = moment().diff(end, "hours");
      const days = Math.floor(diff / 24);
      const final = days % 7;
      return (this.week = [
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
      ]);
    },
    getweekTime() {
      this.datepick = this.$store.getters.getDate;
      this.$axios
        .post(
          "http://localhost:8090/api/commute/weekTime",
          {
            com_d8: this.datepick,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {
          this.weeklist = res.data;

          let sum = 0;
          this.weeklist.forEach((day) => {
            sum += Number(day.com_total);
          });
          this.sumsum = Math.floor(sum / 60) + " 시간 " + (sum % 60) + " 분";
          console.log(">>>> ", this.sumsum);
        });
    },

    getweeklist() {
      return this.weeklist;
    },
  },
  watch: {},
  methods: {},
};
</script>
