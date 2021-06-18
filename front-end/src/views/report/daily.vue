<template>
  <div>
    <Header />
    <v-container
      ><br />
      <div>업무 일지 목록 (전체) {{ this.$store.state.userReportList }}</div>
      <div align="right">
        <v-btn color="primary" class="mr-2" @click="rptList"> 주간 </v-btn>
        <v-btn color="primary" class="mr-2" @click="monthly"> 월간 </v-btn>
        <v-btn color="primary" @click="daily"> 전체 </v-btn>
      </div>
      <br />
      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th class="text-center">Date</th>
              <th class="text-center">Writer</th>
              <th class="text-center">Content</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="report in reportList" :key="report.no">
              <td>{{ $moment(report.rpt_write_d8).format("YYYY-MM-DD") }}</td>
              <td>{{ report.rpt_writer }}</td>
              <td>{{ report.rpt_no }}</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>

      <v-data-table
        :headers="headers"
        :items="desserts"
        :single-expand="singleExpand"
        :expanded.sync="expanded"
        item-key="name"
        show-expand
        class="elevation-1"
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>Expandable Table</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-switch
              v-model="singleExpand"
              label="Single expand"
              class="mt-2"
            ></v-switch>
          </v-toolbar>
        </template>
        <template v-slot:expanded-item="{ headers, item }">
          <td :colspan="headers.length">More info about {{ item.name }}</td>
        </template>
      </v-data-table>

      <br /><br />
      <Footer />
    </v-container>
  </div>
</template>

<script>
import Header from "../../views/common/00_header"; //import 헤더 추가
import Footer from "../../views/common/90_footer"; //import 풋터 추가
import http from "../../http-common";

export default {
  name: "daily",
  components: {
    Header, //헤더 컴포넌트 추가
    Footer, //풋터 컴포넌트 추가
  },

  data() {
    return {
      reports: [],
      reportList: [],
      expanded: [],
      singleExpand: false,
      headers: [
        {
          text: "Dessert (100g serving)",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Calories", value: "calories" },
        { text: "Fat (g)", value: "fat" },
        { text: "Carbs (g)", value: "carbs" },
        { text: "Protein (g)", value: "protein" },
        { text: "Iron (%)", value: "iron" },
        { text: "", value: "data-table-expand" },
      ],
      desserts: [
        {
          name: "Frozen Yogurt",
          calories: 159,
          fat: 6.0,
          carbs: 24,
          protein: 4.0,
          iron: "1%",
        },
        {
          name: "Ice cream sandwich",
          calories: 237,
          fat: 9.0,
          carbs: 37,
          protein: 4.3,
          iron: "1%",
        },
        {
          name: "Eclair",
          calories: 262,
          fat: 16.0,
          carbs: 23,
          protein: 6.0,
          iron: "7%",
        },
        {
          name: "Cupcake",
          calories: 305,
          fat: 3.7,
          carbs: 67,
          protein: 4.3,
          iron: "8%",
        },
        {
          name: "Gingerbread",
          calories: 356,
          fat: 16.0,
          carbs: 49,
          protein: 3.9,
          iron: "16%",
        },
        {
          name: "Jelly bean",
          calories: 375,
          fat: 0.0,
          carbs: 94,
          protein: 0.0,
          iron: "0%",
        },
        {
          name: "Lollipop",
          calories: 392,
          fat: 0.2,
          carbs: 98,
          protein: 0,
          iron: "2%",
        },
        {
          name: "Honeycomb",
          calories: 408,
          fat: 3.2,
          carbs: 87,
          protein: 6.5,
          iron: "45%",
        },
        {
          name: "Donut",
          calories: 452,
          fat: 25.0,
          carbs: 51,
          protein: 4.9,
          iron: "22%",
        },
        {
          name: "KitKat",
          calories: 518,
          fat: 26.0,
          carbs: 65,
          protein: 7,
          iron: "6%",
        },
      ],
    };
  },
  mounted() {
    this.getReportList;
    this.getReports;
  },
  computed: {
    getReportList() {
      this.$axios
        .get("/api/report/list")
        .then((res) => {
          this.reportList = res.data;
          console.log(this.reportList);
          return this.reportList;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getReports() {
      if (this.$store.getters.getProjectINInfo[0].authority == null) {
        this.$axios.get("/api/report/getAll").then((res) => {
          this.$store.commit("setReportList", res.data);
          console.log(this.$store.getters.getReportList);
        });
      } else {
        const reportList = [];
        const reports = [];
        //console.log(this.$store.getters.getProjectINInfo.length);
        for (let i = 0; i < this.$store.getters.getProjectINInfo.length; i++) {
          if (
            this.$store.getters.getProjectINInfo[i].authority == "ROLE_ADMIN"
          ) {
            this.$axios
              .get("/api/report/getADMIN", {
                params: {
                  prj_no: this.$store.getters.getProjectINInfo[i].prj_no,
                },
              })
              .then((res) => {
                for (let i = 0; i < res.data.length; i++) {
                  reports.push(res.data[i]);
                }
                console.log(reports);
              });
          } else if (
            this.$store.getters.getProjectINInfo[i].authority == "ROLE_USER"
          ) {
            this.$axios
              .get("/api/report/getUSER", {
                params: {
                  prj_in_no: this.$store.getters.getProjectINInfo[i].prj_in_no,
                },
              })
              .then((res) => {
                //console.log("완료");
                //console.log(res.data);
                for (let i = 0; i < res.data.length; i++) {
                  reports.push(res.data[i]);
                }
                console.log(reports);
              });
          }
        }

        this.reports = reports;
        console.log(this.reports);

        this.$store.commit("setReportList", this.reports);
        console.log(this.$store.getters.getReportList);
      }
    },
  },
  methods: {
    rptList() {
      this.$router.push("/rptList");
    },
    daily() {
      this.$router.push("/dailyRpt");
    },
    monthly() {
      this.$router.push("/monthlyRpt");
    },
  },
};
</script>