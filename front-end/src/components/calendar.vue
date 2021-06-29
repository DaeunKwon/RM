<template>
  <v-row class="fill-height">
    <v-col>
      <v-sheet height="64">
        <v-toolbar flat color="white">
          <v-btn outlined class="mr-4" color="grey darken-2" @click="setToday">
            Today
          </v-btn>
          <v-btn fab text small color="grey darken-2" @click="prev">
            <v-icon small>mdi-chevron-left</v-icon>
          </v-btn>
          <v-toolbar-title v-if="$refs.calendar">
            {{ $refs.calendar.title }}
          </v-toolbar-title>
          <v-btn fab text small color="grey darken-2" @click="next">
            <v-icon small>mdi-chevron-right</v-icon>
          </v-btn>

          <v-spacer></v-spacer>
          <v-btn @click="com_detail">상세페이지</v-btn>

          <v-menu bottom right>
            <template v-slot:activator="{ on, attrs }">
              <v-btn outlined color="grey darken-2" v-bind="attrs" v-on="on">
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
      <v-sheet height="734">
        <v-calendar
          ref="calendar"
          v-model="focus"
          color="primary"
          :events="events"
          :event-color="getEventColor"
          :type="type"
          @click:event="showEvent"
          @click:more="viewDay"
          @click:date="viewDay"
          @change="monthNamelist"
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
              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>
              <v-btn icon>
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </v-toolbar>
            <v-card-text>
              <span v-html="selectedEvent.details"></span>
            </v-card-text>
            <v-card-actions>
              <v-btn text color="secondary" @click="selectedOpen = false">
                Cancel{{ datepick }}
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-menu>
      </v-sheet>
    </v-col>
  </v-row>
</template>


<script>
export default {
  data: () => ({
    ranklist: [],
    focus: "",
    type: "month",
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
    names: [],
  }),
  mounted() {
    this.$refs.calendar.checkChange();
    this.datepick();
  },
  computed: {
    datepick() {
      this.focus = this.$store.getters.getDate;
    },
  },
  methods: {
    viewDay({ date }) {
      this.focus = date;
      this.$store.commit("setComdate", this.focus);
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
    monthNamelist() {
      const events = [];

      this.$axios
        .get("/api/commute/monthNamelist", {
          params: {
            com_d8: this.$store.getters.getDate,
            prj_no: this.$store.state.userINProject.prj_no,
          },
        })
        .then((res) => {
          const rankList = [];

          for (let i = 0; i < res.data.length; i++) {
            rankList.push(res.data[i]);

            events.push({
              name: rankList[i].name,
              start: rankList[i].com_start,
              end: rankList[i].com_end,
              color: this.colors[this.rnd(0, this.colors.length - 1)],
            });
          }
        })
        .then(() => {
          ///////// 달력 안 이름 css

          document
            .querySelectorAll(
              ".v-calendar-monthly .v-calendar-weekly__week .v-event strong"
            )
            .forEach(function (each) {
              each.style.display = "none";
            });
          document
            .querySelectorAll(
              ".v-calendar-monthly .v-calendar-weekly__week .v-event "
            )
            .forEach(function (each) {
              each.style.width = "100%";
              each.style.margin = "0px";
            });
          document
            .querySelectorAll(".v-calendar .v-event")
            .forEach(function (each) {
              each.style.display = "inline-block";
            });
        });
      this.events = events;
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
    com_detail() {
      this.$router.push("/com_detail");
    },
  },
};
</script>
<style scoped>
strong {
  display: none;
}
.v-calendar .v-event {
  display: inline-block;
}
</style>