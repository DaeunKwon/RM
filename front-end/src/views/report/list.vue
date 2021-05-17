<template>
  <div>
    <Header />
    <v-container
      ><br />
      <v-header>업무 일지 목록</v-header>
      <div align="right">
        <v-btn
          color="primary"
          class="mr-2"
          @click="this.$router.replace('/rptList')"
        >
          주간
        </v-btn>
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
import { getReportListAPI } from "@/api/index";

export default {
  name: "rptList",
  components: {
    Header, //헤더 컴포넌트 추가
    Footer, //풋터 컴포넌트 추가
  },

  data() {
    return {
      headers: [
        { text: "번호", align: "center", value: "rpt_no" },
        { text: "제목", align: "center", value: "rpt_title" },
      ],
      document: [],
      options: {
        multiSort: true,
        sortBy: [],
        sortDesc: [],
        page: 1,
        itemsPerPage: 5,
      },
      footerOptions: {
        "items-per-page-options": [5, 10, 25, 50, 100],
      },
      totalCount: 0,
      loading: false,
      conditions: [
        { text: "번호", value: "rpt_no" },
        { text: "제목", value: "rpt_title" },
      ],
      schType: "",
      schVal: "",
    };
  },
  mounted() {
    this.getReportList();
  },
  watch: {
    options: {
      handler() {
        this.getReportList();
      },
      deep: true,
    },
  },
  methods: {
    getReportDataFromAPI(page, itemsPerPage, sort) {
      return getReportListAPI({
        params: {
          schType: this.schType,
          schVal: this.schVal,
          page: page,
          rows: itemsPerPage,
          sort: encodeURIComponent(sort),
        },
      })
        .then((response) => {
          return response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getReportList() {
      const vm = this;
      this.loading = true;
      // eslint-disable-next-line
      return new Promise((resolve, reject) => {
        const { sortBy, sortDesc, page, itemsPerPage } = this.options;
        let sort = [];
        if (sortBy.length > 0) {
          // eslint-disable-next-line
          sortBy.forEach((value, index) => {
            sort.push(
              value
                .replace(/[A-Z]/g, function (str) {
                  return "_" + str[0];
                })
                .toUpperCase() +
                " " +
                (sortDesc[index] ? "desc" : "asc")
            );
          });
        } else {
          sort.push("DOC_NO desc");
        }
        let items = this.getReportDataFromAPI(page, itemsPerPage, sort).then(
          (response) => {
            items = response.data;
            const total = response.total;
            setTimeout(() => {
              vm.loading = false;
              this.document = items;
              this.totalCount = total;
            }, 1000);
          }
        );
      });
    },
    onClickRow(event, data) {
      this.movePage("/detail?docNo=" + data.item.docNo);
    },
    rptWrite() {
      this.$router.push("/rptWrite");
    },
    daily() {
      this.$router.push("/dailyRpt");
    },
  },
};
</script>

<style>
</style>