<template>
  <div>
    <Header />
    <v-container class="header-padding"
      ><br />
      <div align="left" class="display-1">업무 일지 목록 (전체)</div>
      <div align="right">
        <v-btn text color="primary" class="mr-2" @click="rptList">
          캘린더로 보기
        </v-btn>
        <v-btn text color="primary" @click="daily"> 리스트로 보기 </v-btn>
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
            {{ item.rpt_write_d8 | moment("YYYY-MM-DD") }}
          </template>
          <template v-slot:[`item.rpt_mod_d8`]="{ item }">
            {{ item.rpt_mod_d8 | moment("YYYY-MM-DD") }}
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
            <div v-if="headers.length == 0">
              <tr>
                <td>No Content</td>
              </tr>
            </div>
            <tr
              :colspan="headers.length"
              v-for="detail in item.detailList"
              :key="detail.rpt_detail_no"
            >
              <td>
                {{ detail.rpt_start_time | moment("HH:mm:ss") }} ~
                {{ detail.rpt_end_time | moment("HH:mm:ss") }} :
                <pre>{{ detail.rpt_content }}</pre>
              </td>
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
          value: "prj_title",
        },
        { text: "작성자", value: "name" },
        {
          text: "작성일자",
          value: "rpt_write_d8",
          dataType: "Date",
        },
        { text: "수정일자", value: "rpt_mod_d8" },
        //{ text: "삭제여부", value: "rmv_YN" },
        { text: "", value: "data-table-expand" },
      ],
    };
  },
  mounted() {
    this.getReportList;
    this.getReports;
  },
  computed: {
    getReports() {
      if (this.$store.getters.getCurrentUser.authority == "ROLE_ROOT") {
        this.$axios.get("/api/report/getAll").then((res) => {
          this.reports = res.data;
        });
      } else {
        const reports = [];
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
                for (let i = 0; i < res.data.length; i++) {
                  reports.push(res.data[i]);
                }
              });
          }
        }

        this.reports = reports;
      }

      setTimeout(() => {
        for (let i = 0; i < this.reports.length; i++) {
          this.$axios
            .get("/api/report/detail/setDetailList", {
              params: {
                rpt_no: this.reports[i].rpt_no,
              },
            })
            .then((res) => {
              this.reports[i].detailList = res.data;
            });
        }
        this.$store.commit("setReportList", this.reports);
      }, 400);
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

<style scoped>
</style>