<template>
  <div>
    <Header />
    <v-container
      ><br />
      <div align="left" class="display-1">업무 일지 목록 (전체)</div>
      <div align="right">
        <v-btn text color="primary" class="mr-2" @click="rptList">
          Calendar
        </v-btn>
        <v-btn text color="primary" @click="daily"> List </v-btn>
      </div>
      <br />
      <v-card>
        <v-card-title>
          <v-spacer></v-spacer>
          <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
          ></v-text-field>
        </v-card-title>
        <v-data-table
          :headers="headers"
          :items="reports"
          :single-expand="singleExpand"
          :expanded.sync="expanded"
          :search="search"
          item-key="rpt_no"
          show-expand
          class="elevation-2"
        >
          <template v-slot:[`item.rpt_write_d8`]="{ item }">
            {{ item.rpt_write_d8 | moment("YYYY-MM-DD HH:mm:ss") }}
          </template>
          <template v-slot:[`item.rpt_mod_d8`]="{ item }">
            {{ item.rpt_mod_d8 | moment("YYYY-MM-DD HH:mm:ss") }}
          </template>
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
            <br />
            <div v-if="item.detailList.length < 1">
              <tr :colspan="headers.length">
                <span>No Contents.</span
                ><br />
              </tr>
            </div>
            <tr
              :colspan="headers.length"
              v-for="detail in item.detailList"
              :key="detail.rpt_detail_no"
            >
              <span
                >{{ detail.rpt_start_time | moment("HH:mm:ss") }} ~
                {{ detail.rpt_end_time | moment("HH:mm:ss") }} :
                {{ detail.rpt_content }}</span
              ><br />
            </tr>
            <br />
          </template>
        </v-data-table>
      </v-card>

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
      search: "",
      reports: [],
      reportList: [],
      expanded: [],
      singleExpand: false,

      headers: [
        {
          text: "프로젝트명",
          align: "start",
          // sortable: false,
          value: "prj_title",
        },
        { text: "작성자", value: "name" },
        {
          text: "작성일자",
          value: "rpt_write_d8",
          dataType: "Date",
        },
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
    // getReportList() {
    //   this.$axios
    //     .get("/api/report/list")
    //     .then((res) => {
    //       this.reportList = res.data;
    //       console.log(this.reportList);
    //       return this.reportList;
    //     })
    //     .catch((e) => {
    //       console.log(e);
    //     });
    // },
    getReports() {
      if (this.$store.getters.getProjectINInfo[0].authority == null) {
        this.$axios.get("/api/report/getAll").then((res) => {
          //this.$store.commit("setReportList", res.data);
          // console.log(this.$store.getters.getReportList);
          this.reports = res.data;
          //console.log(this.$store.getters.getReportList[0].detailList);
        });
      } else {
        //const reportList = [];
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
                // console.log(reports);
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
                // console.log(reports);
              });
          }
        }

        this.reports = reports;
        // console.log(this.reports);

        // this.$store.commit("setReportList", this.reports);
        // console.log(this.$store.getters.getReportList);
      }

      // console.log(this.$store.state.userReportList[0].detailList);
      setTimeout(() => {
        //  console.log(this.reports.length);
        for (let i = 0; i < this.reports.length; i++) {
          this.$axios
            .get("/api/report/detail/setDetailList", {
              params: {
                rpt_no: this.reports[i].rpt_no,
              },
            })
            .then((res) => {
              // console.log(res.data);
              this.reports[i].detailList = res.data;
              //  console.log(this.reports[i].detailList);
            });
        }
        //(this.reports);
        this.$store.commit("setReportList", this.reports);
        // console.log(this.$store.getters.getReportList);
        // console.log(this.$store.getters.getReportList[0].detailList);
      }, 500);
    },
  },
  methods: {
    rptList() {
      this.$router.push("/rptList");
    },
    daily() {
      this.$router.push("/dailyRpt");
    },
  },
};
</script>