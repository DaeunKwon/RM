<template>
  <div>
    <Header />
    <v-container
      ><br />
      <v-header>업무 일지 목록</v-header>
      <div align="right">
        <v-btn color="primary" class="mr-2" @click="rptList"> 주간 </v-btn>
        <v-btn color="primary" class="mr-2" @click="monthly"> 월간 </v-btn>
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
                  {{ report.rpt_title }}
                </h5>
              </div>
            </v-card>
          </v-col>
          <v-col cols="12" md="9">
            <v-sheet class="pa-2" outlined tile elevation="1" height="600">
              주간
              <v-slide-group v-model="report" class="pa-4" show-arrows>
                <v-slide-item v-for="day in 5" :key="day">
                  <v-card
                    color="grey lighten-2"
                    class="ma-1"
                    height="500"
                    width="142"
                  >
                  </v-card>
                </v-slide-item>
              </v-slide-group>
            </v-sheet>
          </v-col> </v-row
        ><br />
        <!-- <v-card elevation="10" outlined width="100%" class="mx-auto">
          <v-card-title> Board </v-card-title>
          <v-card-text>
            <v-row>
              <v-col align-self="end" cols="12" md="2"> </v-col>
              <v-col cols="12" md="8"> </v-col>
              <v-col align-self="center">
                <Button
                  @click="getReportList"
                  color="blue-grey darken-1"
                  rounded
                  small
                  block
                  iconName="mdi-magnify"
                  btnName="Search"
                ></Button>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <template slot="items" slot-scope="props">
                  <td>{{ props.item.rpt_no }}</td>
                  <td>{{ props.item.rpt_title }}</td>
                </template>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card> -->
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
    };
  },
  mounted() {
    this.getReportList();
  },
  methods: {
    rptList() {
      this.$router.push("/rptList");
    },
    rptWrite() {
      this.$router.push("/rptWrite");
    },
    daily() {
      this.$router.push("/dailyRpt");
    },
    getReportList() {
      http
        .get("/api/report/list")
        .then((res) => {
          this.reportList = res.data;
          console.log(this.reportList);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>

<style>
</style>