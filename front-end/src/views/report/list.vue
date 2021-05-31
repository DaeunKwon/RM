<template>
  <div>
    <Header />
    <v-container
      ><br />
      <div>업무 일지 목록</div>
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
              {{ today }}
              <div>
                <br />
                <h5
                  v-for="reportDetail in reportDetailList"
                  :key="reportDetail.id"
                >
                  {{ reportDetail.rpt_content }}
                </h5>
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
                color="primary"
                :events="events"
                :event-color="getEventColor"
                :type="type"
                @click:event="showEvent"
                @click:more="viewDay"
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
                    <v-btn icon>
                      <v-icon>mdi-pencil</v-icon>
                    </v-btn>
                    <v-toolbar-title
                      v-html="selectedEvent.name"
                    ></v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-btn icon>
                      <v-icon>mdi-heart</v-icon>
                    </v-btn>
                    <v-btn icon>
                      <v-icon>mdi-dots-vertical</v-icon>
                    </v-btn>
                  </v-toolbar>
                  <v-card-text>
                    <span :value="selectedEvent.rpt_start_time"></span>
                    <span :value="selectedEvent.rpt_end_time"></span>
                  </v-card-text>
                  <v-card-actions>
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
  data: () => ({
    events: [],
  }),
  data() {
    return {
      reportList: [],
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
      names: [
        "Meeting",
        "Holiday",
        "PTO",
        "Travel",
        "Event",
        "Birthday",
        "Conference",
        "Party",
      ],
    };
  },
  mounted() {
    this.getReportList;
    this.getToday();
    this.getReportDetailList;
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
    getReportDetailList() {
      this.$axios.get("/api/report/detail/list").then((res) => {
        this.reportDetailList = res.data;
      });
    },
  },
  methods: {
    getEvents() {
      const events = [];
      this.$axios.get("/api/report/detail/list").then((res) => {
        const reportDetailList = res.data;

        for (let i = 0; i < reportDetailList.length; i++) {
          const startTime = new Date(reportDetailList[i].rpt_start_time);
          const endTime = new Date(reportDetailList[i].rpt_end_time);
          //console.log(startTime);
          events.push({
            name: "Test",
            start: new Date(reportDetailList[i].rpt_start_time),
            end: new Date(reportDetailList[i].rpt_end_time),
            color: this.colors[this.rnd(0, this.colors.length - 1)],
          });
          console.log(events[i].start);
        }
        this.events = events;
        console.log(this.events[0].end);
      });
    },
    viewDay({ date }) {
      this.focus = date;
      this.type = "day";
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

    updateRange({ start, end }) {
      const events = [];
      const min = new Date(`${start.date}T00:00:00`);
      const max = new Date(`${end.date}T23:59:59`);
      const days = (max.getTime() - min.getTime()) / 86400000;
      const eventCount = this.rnd(days, days + 20);
      const reportCount = this.getCount();
      console.log("reportCount:" + reportCount);

      for (let i = 0; i < eventCount; i++) {
        const allDay = this.rnd(0, 3) === 0;
        const firstTimestamp = this.rnd(min.getTime(), max.getTime());
        console.log("firstTimestamp:" + firstTimestamp);
        console.log(firstTimestamp - (firstTimestamp % 900000));
        const first = new Date(firstTimestamp - (firstTimestamp % 900000));

        const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000;
        console.log(secondTimestamp);
        const second = new Date(first.getTime() + secondTimestamp);
        console.log(first.getTime());
        console.log("first:" + first);

        events.push({
          name: this.names[this.rnd(0, this.names.length - 1)],
          start: first,
          end: second,
          color: this.colors[this.rnd(0, this.colors.length - 1)],
          timed: !allDay,
        });
      }

      this.events = events;
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
    getCount() {
      return this.reportDetailList.length;
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
    rptWrite() {
      this.$router.push("/rptWrite");
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
</style>