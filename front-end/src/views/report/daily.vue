<template>
  <div>
    <Header />
    <v-container
      ><br />
      <div>업무 일지 목록 (전체)</div>
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
      reportList: [],
    };
  },
  mounted() {
    this.getReportList;
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