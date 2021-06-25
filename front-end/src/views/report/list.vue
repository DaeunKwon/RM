<template>
  <div>
    <Header />
    <v-container container--fluid class="header-padding"
      ><br />
      <div align="left" class="display-1">업무 일지 목록 (캘린더)</div>
      <div align="right">
        <v-btn text color="primary" class="mr-2" @click="rptList">
          캘린더로 보기
        </v-btn>
        <v-btn text color="primary" @click="daily"> 리스트로 보기 </v-btn>
      </div>
      <br />
      <v-layout>
        <v-flex full-width>
          <v-row>
            <v-col cols="6" md="3">
              <v-card
                class="pa-2 scroll"
                outlined
                tile
                elevation="1"
                height="680"
                width="400"
              >
                <v-app-bar light color="white" flat>
                  <!-- <v-app-bar-nav-icon></v-app-bar-nav-icon> -->

                  <v-toolbar-title alight="center">{{
                    selectedDate
                  }}</v-toolbar-title>

                  <!-- <v-spacer></v-spacer> -->

                  <!-- <v-btn icon>
                  <v-icon>mdi-magnify</v-icon>
                </v-btn> -->
                </v-app-bar>
                <div v-if="selectedDateReport.length == 0">
                  작성된 업무 일지가<br />없습니다.
                </div>
                <div
                  v-for="reportDetail in selectedDateReport"
                  :key="reportDetail.id"
                >
                  <v-card color="white" class="pa-2 scroll">
                    <v-card-title class="justify-center">
                      {{ reportDetail.prj_title }}<br />
                      ({{ reportDetail.name }})
                    </v-card-title>
                    <v-card-text>
                      {{ reportDetail.rpt_start_time | moment("HH:mm:ss") }} ~
                      {{ reportDetail.rpt_end_time | moment("HH:mm:ss") }}

                      <br />
                      <span v-html="reportDetail.rpt_content"></span>
                    </v-card-text>
                  </v-card>
                  <br />
                </div>
              </v-card>
            </v-col>
            <v-col cols="12" md="9">
              <v-sheet height="64">
                <v-toolbar flat color="white">
                  <v-btn
                    outlined
                    class="mr-4"
                    color="grey darken-2"
                    @click="setToday"
                  >
                    Today
                  </v-btn>
                  <v-btn fab text small color="grey darken-2" @click="prev">
                    <v-icon small>mdi-chevron-left</v-icon>
                  </v-btn>
                  <v-btn fab text small color="grey darken-2" @click="next">
                    <v-icon small>mdi-chevron-right</v-icon>
                  </v-btn>
                  <v-toolbar-title v-if="$refs.calendar">
                    {{ $refs.calendar.title }}
                  </v-toolbar-title>
                  <v-spacer></v-spacer>
                  <v-menu bottom right>
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn
                        outlined
                        color="grey darken-2"
                        v-bind="attrs"
                        v-on="on"
                      >
                        <span>{{ typeToLabel[type] }}</span>
                        <v-icon right>mdi-menu-down</v-icon>
                      </v-btn>
                    </template>
                    <v-list>
                      <v-list-item @click="type = 'day'">
                        <v-list-item-title>Day</v-list-item-title>
                      </v-list-item>
                      <v-list-item @click="type = 'week'">
                        <v-list-item-title>Week</v-list-item-title>
                      </v-list-item>
                      <v-list-item @click="type = 'month'">
                        <v-list-item-title>Month</v-list-item-title>
                      </v-list-item>
                      <v-list-item @click="type = '4day'">
                        <v-list-item-title>4 days</v-list-item-title>
                      </v-list-item>
                    </v-list>
                  </v-menu>
                </v-toolbar>
              </v-sheet>
              <v-sheet height="600">
                <v-calendar
                  ref="calendar"
                  v-model="focus"
                  :value="focus"
                  color="primary"
                  :events="events"
                  :event-color="getEventColor"
                  :type="type"
                  @click:event="showEvent"
                  @click:more="viewDay"
                  @click:date="viewSelectedDay"
                  @change="getEvents"
                ></v-calendar>

                <v-menu
                  v-model="selectedOpen"
                  :close-on-content-click="false"
                  :activator="selectedElement"
                  offset-x
                >
                  <v-card color="grey lighten-4" min-width="350px" flat>
                    <v-toolbar :color="selectedEvent.color" dark>
                      <v-btn
                        icon
                        v-if="
                          this.$store.getters.getCurrentUser.name ==
                          selectedEvent.name
                        "
                        @click="openUpdateDialog(selectedEvent)"
                      >
                        <v-icon>mdi-pencil</v-icon>
                      </v-btn>
                      <v-toolbar-title
                        ><span v-html="selectedEvent.name" /> (<span
                          v-html="selectedEvent.prj_title"
                        />)</v-toolbar-title
                      >
                    </v-toolbar>

                    <v-card-text>
                      <span v-html="selectedEvent.start"></span>
                      ~ <span v-html="selectedEvent.end"></span><br /><br />

                      <pre v-html="selectedEvent.content"></pre>
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn
                        text
                        color="secondary"
                        @click="selectedOpen = false"
                      >
                        Cancel
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-menu>
              </v-sheet>
            </v-col> </v-row
          ><br />
        </v-flex>
      </v-layout>
      <br /><br />
      <Footer />
    </v-container>

    <v-dialog v-model="updateDialog" max-width="600px">
      <v-card>
        <v-card-title>
          <span class="headline">업무 일지 수정</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6">
                <v-text-field
                  label="Project title"
                  required
                  readonly
                  outlined
                  v-model="selectedReportDetail.prj_title"
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="selectedReportDetail.write_d8"
                  label="Date"
                  required
                  readonly
                  outlined
                >
                </v-text-field>
              </v-col>
              <!-- <v-col> {{ inputs }} </v-col> -->
              <v-dialog v-model="openTimeFlag" width="290px">
                <v-time-picker v-model="openTime" full-width>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="openTimeFlag = false"
                    >취소</v-btn
                  >
                  <v-btn text color="primary" @click="saveTime()">저장</v-btn>
                </v-time-picker>
              </v-dialog>
            </v-row>
            <v-row v-for="(input, k) in inputs" :key="k">
              <v-col cols="12">
                <v-text-field
                  v-model="input.rpt_start_time"
                  :id="'start_time' + k"
                  label="시작 시간"
                  outlined
                  readonly
                  @click="openTimeSaver(inputs, k, 'rpt_start_time')"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  v-model="input.rpt_end_time"
                  :id="'end_time' + k"
                  label="시작 시간"
                  outlined
                  readonly
                  @click="openTimeSaver(inputs, k, 'rpt_end_time')"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-textarea
                  v-model="input.rpt_content"
                  label="업무 내용"
                  required
                  outlined
                  :id="'content' + k"
                ></v-textarea>
                <v-btn
                  class="sm-2 mr-4"
                  fab
                  dark
                  small
                  color="indigo"
                  @click="remove(k)"
                  v-show="k || (!k && inputs.length > 1)"
                >
                  <v-icon dark>mdi-minus</v-icon>
                </v-btn>
                <v-btn
                  class="sm-2"
                  fab
                  dark
                  small
                  color="indigo"
                  @click="add(k)"
                  v-show="k === inputs.length - 1"
                >
                  <v-icon dark>mdi-plus</v-icon>
                </v-btn>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="updateDialog = false">
            닫기
          </v-btn>
          <v-btn color="blue darken-1" text @click="updateReport"> 수정 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
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
      startModel: false,
      endModel: false,
      updateDialog: false,
      selectedDate: "",
      reportList: [],
      selectedDateReport: [],
      reportDetailList: [],
      dialog5: false,
      today: "",
      modal2: false,
      modal3: false,
      inputs: [{ start_time: null, end_time: null, content: "" }],
      focus: "",
      type: "week",
      typeToLabel: {
        month: "Month",
        week: "Week",
        day: "Day",
        "4day": "4 Days",
      },
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
      events: [],
      colors: [
        "blue",
        "indigo",
        "deep-purple",
        "cyan",
        "green",
        "orange",
        "grey darken-1",
      ],
      selectedReportDetail: [],
      selectedReport: [],
      openTimeFlag: false,
      openTimeValues: [],
      openTimeType: "",
      openTimeIndex: -1,
      openTime: "",
    };
  },
  mounted() {
    this.getToday();
    if (this.focus == "") {
      this.selectedDate = this.today;
      const selectedDateReport = [];
      setTimeout(() => {
        for (let i = 0; i < this.$store.getters.getUserReport.length; i++) {
          if (
            this.$moment(
              this.$store.getters.getUserReport[i].rpt_start_time
            ).format("YYYY-MM-DD") == this.today
          ) {
            selectedDateReport.push(this.$store.getters.getUserReport[i]);
          }
        }
      }, 400);
      this.selectedDateReport = selectedDateReport;
    }
  },
  computed: {},
  methods: {
    updateReport() {
      const report = new FormData();
      report.append("rpt_no", this.selectedReportDetail.rpt_no);
      report.append("rpt_mod_writer", this.selectedReportDetail.rpt_writer);

      const reportDetail = new FormData();
      for (let i = 0; i < this.inputs.length; i++) {
        reportDetail.append("rpt_no", this.selectedReportDetail.rpt_no);
        reportDetail.append(
          "rpt_start_time",
          this.selectedReportDetail.write_d8 +
            " " +
            this.inputs[i].rpt_start_time
        );
        reportDetail.append(
          "rpt_end_time",
          this.selectedReportDetail.write_d8 + " " + this.inputs[i].rpt_end_time
        );
        reportDetail.append("rpt_content", this.inputs[i].rpt_content);
      }
      reportDetail.append("flag", 1);

      this.$axios
        .post("/api/report/update/detail", reportDetail)
        .then((res) => {
          alert("업무 일지가 수정되었습니다.");
          this.updateDialog = false;
          this.$router.push("/rptList");
        });
    },
    openUpdateDialog(selectedEvent) {
      this.updateDialog = true;
      this.selectedReportDetail = selectedEvent;

      const selectedReport = [];
      for (let i = 0; i < this.$store.getters.getUserReport.length; i++) {
        if (
          this.$store.getters.getUserReport[i].rpt_no == selectedEvent.rpt_no &&
          this.$store.getters.getUserReport[i].name ==
            this.$store.getters.getCurrentUser.name
        ) {
          selectedReport.push(this.$store.getters.getUserReport[i]);
        }
      }
      this.selectedReport = selectedReport;
      this.inputs = this.selectedReport;
      this.inputs.forEach((input) => {
        input.rpt_start_time = this.$moment(input.rpt_start_time).format(
          "HH:mm:ss"
        );
        input.rpt_end_time = this.$moment(input.rpt_end_time).format(
          "HH:mm:ss"
        );
      });
    },
    getEvents() {
      if (this.$store.getters.getCurrentUser.authority == "ROLE_ROOT") {
        this.$axios.get("/api/report/detail/getAll").then((res) => {
          this.$store.commit("setReport", res.data);
        });
      } else {
        const reportList = [];
        for (let i = 0; i < this.$store.getters.getProjectINInfo.length; i++) {
          if (
            this.$store.getters.getProjectINInfo[i].authority == "ROLE_ADMIN"
          ) {
            this.$axios
              .get("/api/report/detail/getADMIN", {
                params: {
                  prj_no: this.$store.getters.getProjectINInfo[i].prj_no,
                },
              })
              .then((res) => {
                for (let i = 0; i < res.data.length; i++) {
                  reportList.push(res.data[i]);
                }
              });
          } else if (
            this.$store.getters.getProjectINInfo[i].authority == "ROLE_USER"
          ) {
            this.$axios
              .get("/api/report/detail/getUSER", {
                params: {
                  prj_in_no: this.$store.getters.getProjectINInfo[i].prj_in_no,
                },
              })
              .then((res) => {
                for (let i = 0; i < res.data.length; i++) {
                  reportList.push(res.data[i]);
                }
              });
          } else if (this.$store.getters.getProjectINInfo == null) {
            reportList = [];
          }
        }
        this.reportList = reportList;
        console.log(this.reportList);

        this.$store.commit("setReport", this.reportList);
      }

      // function delay(item) {
      //   return new Promise((resolve) =>
      //     setTimeout(() => {
      //       console.log(item);
      //       resolve();
      //     }, 500)
      //   );
      // }
      // async function loop1(array) {
      //   array.forEach(async (item) => {
      //     await delay(item);
      //   });
      //   console.log("Done!");
      // }
      // loop1([1, 2, 3]);

      setTimeout(() => {
        const events = [];
        const reportDetailList = this.$store.getters.getUserReport;
        this.reportDetailList = reportDetailList;
        for (let i = 0; i < reportDetailList.length; i++) {
          const startTime = this.$moment(
            reportDetailList[i].rpt_start_time
          ).format("YYYY-MM-DD HH:mm");
          const endTime = this.$moment(reportDetailList[i].rpt_end_time).format(
            "YYYY-MM-DD HH:mm"
          );
          const date = this.$moment(reportDetailList[i].rpt_start_time).format(
            "YYYY-MM-DD"
          );
          events.push({
            name: reportDetailList[i].name,
            start: startTime,
            end: endTime,
            color: this.colors[this.rnd(0, this.colors.length - 1)],
            content: reportDetailList[i].rpt_content,
            prj_title: reportDetailList[i].prj_title,
            rpt_no: reportDetailList[i].rpt_no,
            rpt_writer: reportDetailList[i].rpt_writer,
            write_d8: date,
          });
        }
        this.events = events;
      }, 500);
    },
    viewDay({ date }) {
      this.focus = date;
      this.type = "day";
    },
    viewSelectedDay({ date }) {
      this.focus = date;
      this.selectedDate = date;
      const selectedDateReport = [];
      for (let i = 0; i < this.$store.getters.getUserReport.length; i++) {
        if (
          this.$moment(
            this.$store.getters.getUserReport[i].rpt_start_time
          ).format("YYYY-MM-DD") == date
        ) {
          selectedDateReport.push(this.$store.getters.getUserReport[i]);
        }
      }
      this.selectedDateReport = selectedDateReport;
    },
    getCount() {
      return this.reportDetailList.length;
    },
    getEventColor(event) {
      return event.color;
    },
    setToday() {
      this.focus = "";
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event;
        this.selectedElement = nativeEvent.target;
        setTimeout(() => (this.selectedOpen = true), 10);
      };

      if (this.selectedOpen) {
        this.selectedOpen = false;
        setTimeout(open, 10);
      } else {
        open();
      }

      nativeEvent.stopPropagation();
    },

    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
    rptList() {
      this.$router.push("/rptList");
    },
    add(k) {
      this.inputs.push({ start_time: null, end_time: null, content: "" });
    },
    remove(k) {
      this.inputs.splice(k, 1);
    },
    rptList() {
      this.$router.push("/rptList");
    },
    daily() {
      this.$router.push("/dailyRpt");
    },
    monthly() {
      this.$router.push("/monthlyRpt");
    },
    getToday() {
      this.today = this.$moment(new Date()).format("YYYY-MM-DD");
    },
    openTimeSaver(inputs, k, type) {
      this.openTimeFlag = true;
      this.openTimeValues = inputs;
      this.openTimeIndex = k;
      this.openTimeType = type;
    },
    saveTime() {
      this.openTimeFlag = false;
      this.openTimeValues[this.openTimeIndex][this.openTimeType] =
        this.openTime;
    },
  },
};
</script>

<style scoped>
.scroll {
  overflow-y: scroll;
}
</style>