<template>
  <div>
    <Header />
    <v-container class="header-padding">
      <br />
      <div align="left" class="display-1">근태관리 메인페이지</div>
      <v-layout>
        <v-flex style="width: 300px">
          <v-container class="left">
            <br />
            <v-layout>
              <v-flex>
                <Datepicker :date="this.date" />
              </v-flex>
              <v-flex>
                <v-text-field
                  v-model="projectTitle"
                  readonly
                  style="width: 80%"
                >
                </v-text-field>
              </v-flex>
            </v-layout>
            <v-layout
              ><h3>참여인원: {{ peoplecount }}명</h3>
            </v-layout>
            <v-layout>
              <v-avatar style="margin: 20px" color="green" rounded size="93"
                >출근<br />{{ getgotocount }}명</v-avatar
              >
              <v-avatar style="margin: 20px" color="grey" rounded size="93"
                >미출근<br />{{ peoplecount - getgotocount }}명</v-avatar
              >
            </v-layout>
            <v-col>
              <v-row align="center" justify="center">
                <template>
                  <v-simple-table style="width: 350px">
                    <template v-slot:default>
                      <thead style="background-color: #e6e6fa">
                        <tr>
                          <th class="text-center">순위{{ rank }}</th>
                          <th class="text-center">이름</th>
                          <th class="text-center">출근시간</th>
                          <th class="text-center">퇴근시간</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(item, i) in getranklist" :key="item.data">
                          <td>{{ i + 1 }}</td>
                          <td width="100px">{{ item.name }}</td>
                          <td>{{ item.com_start.substring(10) }}</td>
                          <td>
                            {{
                              (item.com_end == null
                                ? "빈값대체임"
                                : item.com_end
                              ).substring(10)
                            }}
                          </td>
                        </tr>
                      </tbody>
                    </template>
                  </v-simple-table>
                </template>
              </v-row>
            </v-col>
            <v-col> {{ getMonth }}월 근무일수 </v-col>
            <v-col>
              <v-row align="center" justify="center">
                <template>
                  <v-simple-table style="width: 350px">
                    <template v-slot:default>
                      <thead style="background-color: #e6e6fa">
                        <tr>
                          <th class="text-center">이름{{ month }}</th>
                          <th class="text-center">출근일수</th>
                          <th class="text-center">근무시간</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="item in monthlist" :key="item.data">
                          <td>{{ item.name }}</td>
                          <td>{{ item.monthC }}일</td>
                          <td>
                            {{ Math.floor(item.monthS / 60) }} 시간
                            {{ item.monthS % 60 }} 분
                          </td>
                        </tr>
                      </tbody>
                    </template>
                  </v-simple-table>
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
      <Footer />
    </v-container>
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
    pcount: "",
    ranklist: [],
    gotocount: "",
    monthlist: [],
  }),

  components: {
    Header,
    Footer,
    Calendar,
    Datepicker,
  },
  created() {
    this.$store.commit("setComdate", new Date().toISOString().substr(0, 10));
  },
  computed: {
    getMonth() {
      return this.$store.getters.getDate.substring(5, 7);
    },
    projectTitle() {
      return this.$store.state.userINProject.prj_title;
    },
    peoplecount() {
      console.log(this.$route.query.project.prj_no);
      this.$axios
        .get("/api/commute/prjpeople", {
          params: { prj_no: this.$store.state.userINProject.prj_no },
        })
        .then((res) => {
          console.log(res.data);
          this.pcount = res.data;
        });
      return this.pcount;
    },
    rank() {
      this.$axios
        .get("/api/commute/rank", {
          params: {
            com_d8: this.$store.getters.getDate,
            prj_no: this.$store.state.userINProject.prj_no,
          },
        })
        .then((res) => {
          console.log(res.data);
          this.ranklist = res.data;
        });
    },
    getranklist() {
      return this.ranklist;
    },
    getgotocount() {
      this.$axios
        .get("/api/commute/gotocount", {
          params: {
            com_d8: this.$store.getters.getDate,
            prj_no: this.$store.state.userINProject.prj_no,
          },
        })
        .then((res) => {
          console.log(res.data);
          this.gotocount = res.data;
        });
      return this.gotocount;
    },
    month() {
      this.$axios
        .post(
          "/api/commute/monthlist",
          {
            com_d8: this.$store.getters.getDate,
            prj_no: this.$store.state.userINProject.prj_no,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {
          console.log(res.data);
          this.monthlist = res.data;
        });
    },
    getmonthlist() {
      return this.monthlist;
    },
  },

  methods: {},
};
</script>

<style scoped>
.left {
  width: 330px;
  height: 800px;
}
</style>