<template>
  <div>
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <td class="text-left" v-for="item in getweeklist" :key="item.name">
              {{ item.day }}
            </td>
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
        Sun: "(일)",
        Mon: "(월)",
        Tue: "(화)",
        Wed: "(수)",
        Tur: "(목)",
        Fri: "(금)",
        Sat: "(토)",
      },
    ],
  }),
  created() {},
  mounted() {},
  computed: {
    getweekTime() {
      this.datepick = this.$store.getters.getDate;
      this.$axios
        .post(
          "http://localhost:8090/api/commute/weekTime",
          {
            com_d8: this.datepick,
            prj_no: this.$store.state.userINProject.prj_no,
            email: this.$store.getters.getCurrentUser.email,
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
