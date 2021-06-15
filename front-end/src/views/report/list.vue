<template>
  <div>
    <Header />
    <v-container
      ><br />
      <div>
        업무 일지 목록
        {{ this.$store.getters.getProjectINInfo }}
      </div>
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

              <div
                v-for="reportDetail in selectedDateReport"
                :key="reportDetail.id"
              >
                <v-card color="white">
                  <v-card-title>
                    {{ reportDetail.name }} ({{ reportDetail.prj_title }})
                  </v-card-title>
                  <v-card-text>
                    {{ reportDetail.rpt_content }}
                  </v-card-text>
                </v-card>
                <br />
              </div>
              <!-- <div
                v-if="selectedDate == today"
                @click="openUpdateDialog(reportDetail)"
              >
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="warning">수정</v-btn>
                </v-card-actions>
              </div> -->
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
                    <span v-html="selectedEvent.content"></span>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn text color="secondary" @click="selectedOpen = false">
                      Cancel
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-menu>
            </v-sheet>
          </v-col> </v-row
        ><br />
      </v-container>
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
                  v-model="today"
                  label="Date"
                  required
                  readonly
                  outlined
                >
                </v-text-field>
              </v-col>

              <v-col cols="12" v-for="(input, k) in inputs" :key="k">
                <v-dialog
                  ref="startDialog"
                  v-model="startModel"
                  :return-value.sync="input.start_time"
                  width="290px"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      v-model="input.start_time"
                      :id="'start_time' + k"
                      label="시작 시간"
                      readonly
                      v-bind="attrs"
                      v-on="on"
                      outlined
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-if="startModel"
                    v-model="input.start_time"
                    :id="'start_time' + k"
                    full-width
                  >
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" @click="startModel = false"
                      >취소</v-btn
                    >
                    <v-btn
                      text
                      color="primary"
                      @click="$refs.startDialog[k].save(input.start_time)"
                      >저장</v-btn
                    >
                  </v-time-picker>
                </v-dialog>

                <v-dialog
                  ref="endDialog"
                  v-model="endModel"
                  :return-value.sync="input.end_time"
                  width="290px"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      v-model="input.end_time"
                      :id="'end_time' + k"
                      label="끝난 시간"
                      readonly
                      v-bind="attrs"
                      v-on="on"
                      outlined
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-if="endModel"
                    v-model="input.end_time"
                    full-width
                  >
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" @click="endModel = false"
                      >취소</v-btn
                    >
                    <v-btn
                      text
                      color="primary"
                      @click="$refs.endDialog[k].save(input.end_time)"
                      >저장</v-btn
                    >
                  </v-time-picker>
                </v-dialog>
                <v-textarea
                  label="업무 내용"
                  required
                  outlined
                  :id="'content' + k"
                  v-model="input.content"
                ></v-textarea>

                <v-btn
                  fab
                  dark
                  small
                  color="indigo"
                  @click="remove(k)"
                  v-show="k || (!k && inputs.length > 1)"
                  ><v-icon dark>mdi-minus</v-icon></v-btn
                >&nbsp;&nbsp;
                <v-btn
                  fab
                  dark
                  small
                  color="indigo"
                  @click="add(k)"
                  v-show="k === inputs.length - 1"
                  ><v-icon dark>mdi-plus</v-icon></v-btn
                >
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
    };
  },
  mounted() {
    // this.getReportList;
    this.getToday();
    //this.getReportDetailList;
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
  },
  methods: {
    updateReport() {},
    openUpdateDialog(selectedEvent) {
      this.updateDialog = true;
      this.selectedReportDetail = selectedEvent;
    },
    getEvents() {
      if (this.$store.getters.getProjectINInfo[0].authority == null) {
        this.$axios.get("/api/report/detail/getAll").then((res) => {
          this.$store.commit("setReport", res.data);
          console.log(this.$store.getters.getUserReport);
        });
      } else {
        const reportList = [];
        console.log(this.$store.getters.getProjectINInfo.length);
        for (let i = 0; i < this.$store.getters.getProjectINInfo.length; i++) {
          if (
            this.$store.getters.getProjectINInfo[i].authority == "ROLE_ADMIN"
          ) {
            console.log("admin");
            console.log(this.$store.getters.getProjectINInfo[i].prj_no);
            this.$axios
              .get("/api/report/detail/getADMIN", {
                params: {
                  prj_no: this.$store.getters.getProjectINInfo[i].prj_no,
                },
              })
              .then((res) => {
                console.log(res.data);
                for (let i = 0; i < res.data.length; i++) {
                  reportList.push(res.data[i]);
                }
                console.log(reportList);
              });
          } else if (
            this.$store.getters.getProjectINInfo[i].authority == "ROLE_USER"
          ) {
            console.log("user");
            console.log(this.$store.getters.getProjectINInfo[i].prj_in_no);
            this.$axios
              .get("/api/report/detail/getUSER", {
                params: {
                  prj_in_no: this.$store.getters.getProjectINInfo[i].prj_in_no,
                },
              })
              .then((res) => {
                console.log("완료");
                console.log(res.data);
                for (let i = 0; i < res.data.length; i++) {
                  reportList.push(res.data[i]);
                }
                console.log(reportList);
              });
          }
        }
        this.reportList = reportList;
        console.log(this.reportList);
        this.$store.commit("setReport", this.reportList);
        console.log(this.$store.getters.getUserReport);
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
        console.log(reportDetailList, reportDetailList.length);
        for (let i = 0; i < reportDetailList.length; i++) {
          console.log(reportDetailList[i].name);
          const startTime = this.$moment(
            reportDetailList[i].rpt_start_time
          ).format("YYYY-MM-DD HH:mm");
          const endTime = this.$moment(reportDetailList[i].rpt_end_time).format(
            "YYYY-MM-DD HH:mm"
          );
          console.log(reportDetailList[i].prj_title);
          events.push({
            name: reportDetailList[i].name,
            start: startTime,
            end: endTime,
            color: this.colors[this.rnd(0, this.colors.length - 1)],
            content: reportDetailList[i].rpt_content,
            prj_title: reportDetailList[i].prj_title,
          });
        }
        console.log(events);
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
      console.log(date);
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
    // getCount() {
    //   return this.reportDetailList.length;
    // },
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
      console.log(this.today);
    },
    rptWritePOST() {},
  },
};
</script>

<style>
.scroll {
  overflow-y: scroll;
}
</style>