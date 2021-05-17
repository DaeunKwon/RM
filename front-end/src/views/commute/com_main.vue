<template>
  <div>
    <v-container style="width= 100%">
      <Header />
      <div id="app">
        <Calendar />
      </div>
    </v-container>
  </div>
</template>

<script>
import Calendar from "../../components/calendar.vue";
import Header from "../../views/common/00_header"; //import 헤더 추가
import Footer from "../../views/common/90_footer"; //import 풋터 추가
export default {
  components: {
    Header, //헤더 컴포넌트 추가
    Footer, //풋터 컴포넌트 추가
    Calendar, //달력 추가
  },
};
</script>

<style scoped>
#app {
  display: inline;
  width: 700px;
  float: right;
}
</style>