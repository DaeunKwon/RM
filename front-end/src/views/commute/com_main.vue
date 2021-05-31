<template>
  <div>
    <Header />
    <v-container>
      <v-layout>
        <v-flex style="width: 500px">
          <v-container style="height: 800px">
            <v-layout>
              <v-flex>
                <h4>근태관리 메인페이지</h4>
              </v-flex>
            </v-layout>
            <v-layout>
              <v-flex>
                <Datepicker :date="this.date" />
              </v-flex>
              <v-flex>
                <v-text-field value="프로젝트1"> </v-text-field>
              </v-flex>
            </v-layout>
            <v-layout><h3>참여인원: 4명</h3> </v-layout>
            <v-layout>
              <v-avatar style="margin: 20px" color="green" rounded size="93"
                >출근<br />4명</v-avatar
              >
              <v-avatar style="margin: 20px" color="grey" rounded size="93"
                >미출근<br />0명</v-avatar
              >
            </v-layout>
            <v-col>
              <v-row align="center" justify="center">
                <template>
                  <v-data-table
                    style="width: 300px"
                    :headers="headers"
                    :items="contents"
                    hide-default-footer
                    disable-sort
                    class="elevation-1"
                  ></v-data-table>
                </template>
              </v-row>
            </v-col>
            <v-col> 월 근무일수 </v-col>
            <v-col>
              <v-row align="center" justify="center">
                <template>
                  <v-data-table
                    style="width: 300px"
                    :headers="headers2"
                    :items="contents2"
                    hide-default-footer
                    disable-sort
                    class="elevation-2"
                  ></v-data-table>
                </template>
              </v-row>
            </v-col>
          </v-container>
        </v-flex>

        <v-flex style="width: 1200px">
          <v-container>
            <v-layout>
              <v-flex> <Calendar /> </v-flex>
            </v-layout>
            <br />
            <v-layout>
              <v-flex> </v-flex>
            </v-layout>
          </v-container>
        </v-flex>
      </v-layout>
    </v-container>
    <Footer />
  </div>
</template>

<script>
import Calendar from "../../components/calendar.vue"; //달력 컴포넌트
import Header from "../../views/common/00_header"; //import 헤더 추가
import Footer from "../../views/common/90_footer"; //import 풋터 추가
import Datepicker from "../../components/datepicker.vue"; //날짜 선택컴포넌트

export default {
  data: () => ({
    date: "",

    headers: [
      { text: "순위", value: "rank" },
      { text: "이름", value: "name" },
      { text: "출근시간", value: "start" },
      { text: "퇴근시간", value: "end" },
    ],
    contents: [
      {
        rank: "1",
        name: "조재승",
        start: "08:50",
        end: "17:00",
      },
      {
        rank: "2",
        name: "권다은",
        start: "08:52",
        end: "17:00",
      },
      {
        rank: "3",
        name: "나인원",
        start: "08:53",
        end: "17:00",
      },
      {
        rank: "4",
        name: "홍길동",
        start: "09:00",
        end: "17:00",
      },
    ],
    headers2: [
      { text: "이름", value: "name" },
      { text: "출근일수", value: "total" },
      { text: "근무시간", value: "time" },
    ],
    contents2: [
      {
        name: "조재승",
        total: "23일",
        time: "230시간",
      },
      {
        name: "권다은",
        total: "22일",
        time: "220시간",
      },
      {
        name: "홍길동",
        total: "19일",
        time: "200시간",
      },
    ],
  }),

  components: {
    Header,
    Footer,
    Calendar,
    Datepicker,
  },
};
</script>

<style scoped>
</style>