<template>
  <div>
    <Header />
    <v-container
      ><br />
      <v-header>업무 일지 목록</v-header>
      <div align="right">
        <v-btn color="primary" class="mr-2" @click="$router.push('/prjList')">
          주간
        </v-btn>
        <v-btn color="primary" class="mr-2"> 월간 </v-btn>
        <v-btn color="primary" @click="daily"> 전체 </v-btn>
      </div>
      <br />
      <v-container fluid>
        <v-row>
          <v-col cols="6" md="3">
            <v-card
              class="pa-2"
              outlined
              tile
              elevation="1"
              height="400"
              width="400"
            >
              개인
              <div>
                <h5>report</h5>
                <h5 v-for="report in reportList" :key="report.id">
                  {{ report.rpt_no }}
                </h5>
              </div>
            </v-card>
          </v-col>
          <v-col cols="12" md="9">
            <v-sheet class="pa-2" outlined tile elevation="1" height="600">
              주간
              <v-slide-group v-model="report" class="pa-4" show-arrows>
                <v-slide-item v-for="day in weekdays" :key="day.id">
                  <v-card
                    color="grey lighten-2"
                    class="ma-1"
                    height="500"
                    width="142"
                  >
                    <v-card-title>{{ day.weekday }} </v-card-title>
                  </v-card>
                </v-slide-item>
              </v-slide-group>
            </v-sheet>
          </v-col> </v-row
        ><br />
      </v-container>
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
  name: "rptList",
  components: {
    Header, //헤더 컴포넌트 추가
    Footer, //풋터 컴포넌트 추가
  },

  data() {
    return {
      reportList: [],
      weekdays: [
        { weekday: "월" },
        { weekday: "화" },
        { weekday: "수" },
        { weekday: "목" },
        { weekday: "금" },
      ],
      dialog5: false,
      date: "",
      modal2: false,
      modal3: false,
      inputs: [{ start_time: null, end_time: null, content: "" }],
    };
  },
  mounted() {
    this.getReportList;
    this.getToday();
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
  },
  methods: {
    add(k) {
      this.inputs.push({ start_time: null, end_time: null, content: "" });
    },
    remove(k) {
      this.inputs.splice(k, 1);
    },
    // rptList() {
    //   this.$router.push("/rptList");
    // },
    rptWrite() {
      this.$router.push("/rptWrite");
    },
    daily() {
      this.$router.push("/dailyRpt");
    },

    getToday() {
      this.date = this.$moment(new Date()).format("YYYY-MM-DD");
      console.log(this.date);
    },
    rptWritePOST() {},
  },
};
</script>

<style>
</style>