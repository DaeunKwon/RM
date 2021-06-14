<template>
  <div>
    <v-sheet tile height="54" class="d-flex">
      <v-btn icon class="ma-2" @click="$refs.calendar.prev()">
        <v-icon>mdi-chevron-left</v-icon>
      </v-btn>
      <v-toolbar-title v-if="$refs.calendar">
        {{ $refs.calendar.title }}
      </v-toolbar-title>
      <v-btn icon class="ma-2" @click="$refs.calendar.next()">
        <v-icon>mdi-chevron-right</v-icon>
      </v-btn>
      <v-flex> <v-btn @click="com_detail"> 근태 상세페이지 </v-btn></v-flex>
    </v-sheet>

    <v-sheet height="700">
      <v-calendar
        dark
        ref="calendar"
        v-model="value"
        :weekdays="weekday"
        :type="type"
        :events="events"
        :event-overlap-mode="mode"
        :event-overlap-threshold="30"
        @click:date="viewDay"
      ></v-calendar>
    </v-sheet>
  </div>
</template>
         
        

<script>
export default {
  data: () => ({
    type: "month",
    mode: "stack",
    weekday: [0, 1, 2, 3, 4, 5, 6],
    value: "",
    events: [],
    colors: ["blue"],
    names: ["Meeting"],
  }),
  mounted() {
    this.$refs.calendar.checkChange();
  },
  computed: {},
  methods: {
    viewDay({ date }) {},

    com_detail() {
      ////근태 상세페이지 이동

      this.$router.push("/com_detail");
    },
  },
};
</script>
<style scoped>
.v-toolbar__title {
  line-height: 2.5;
}
</style>
