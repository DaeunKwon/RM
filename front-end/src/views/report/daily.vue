<template>
  <div>
    <Header />
    <v-container
      ><br />
      <div align="left" class="display-1">업무 일지 목록 (전체)</div>
      <div align="right">
        <v-btn color="primary" class="mr-2" @click="rptList"> 주간 </v-btn>
        <v-btn color="primary" class="mr-2" @click="monthly"> 월간 </v-btn>
        <v-btn color="primary" @click="daily"> 전체 </v-btn>
      </div>
      <br />

      <v-data-table
        :headers="headers"
        :items="reports"
        :single-expand="singleExpand"
        :expanded.sync="expanded"
        item-key="name"
        show-expand
        class="elevation-2"
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title></v-toolbar-title>
            <v-spacer></v-spacer>
            <v-switch
              v-model="singleExpand"
              label="하나씩 펼쳐보기"
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
          text: "프로젝트명",
          align: "start",
          sortable: false,
          value: "prj_title",
        },
        { text: "작성자", value: "name" },
        { text: "작성일자", value: "rpt_write_d8" },
        { text: "수정일자", value: "rpt_mod_d8" },
        { text: "삭제여부", value: "rmv_YN" },
        { text: "", value: "data-table-expand" },
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