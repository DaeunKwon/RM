

<template>
  <div>
    <Header />

    <v-container>
      <v-layout>
        <v-flex></v-flex><v-flex></v-flex>
        <v-flex>
          <h1>출퇴근 관리</h1>
        </v-flex>
        <v-spacer> </v-spacer>
        <v-flex>
          <v-dialog v-model="dialog" persistent max-width="290">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                class="combtn"
                color="primary"
                v-bind="attrs"
                v-on="on"
                :disabled="test"
              >
                출근
              </v-btn>
            </template>
            <v-card>
              <v-card-title class="headline"> 출근하시겠습니까? </v-card-title>
              <v-card-text
                >한 번 누르면 출근시간은<br />
                변경할 수 없습니다.</v-card-text
              >
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click="dialog = false">
                  Disagree
                </v-btn>
                <v-btn
                  color="green darken-1"
                  text
                  @click="[gotoWork(), (dialog = false)]"
                >
                  Agree
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>

          <v-dialog v-model="dialog2" persistent max-width="290">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                class="combtn"
                :disabled="test2"
                color="primary"
                v-bind="attrs"
                v-on="on"
              >
                퇴근
              </v-btn>
            </template>

            <v-card>
              <v-card-title class="headline"> 퇴근하시겠습니까? </v-card-title>
              <v-card-text
                >한 번 누르면 퇴근시간은<br />
                변경할 수 없습니다.</v-card-text
              >
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click="dialog2 = false">
                  Disagree
                </v-btn>
                <v-btn
                  color="green darken-1"
                  text
                  @click="[offWork(), (dialog2 = false)]"
                >
                  Agree
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-flex>
      </v-layout>

      <v-layout fluid grid-list-sm pa-15>
        <v-row justify="center">
          <v-col cols="5">
            <Datepicker :date="this.date" />
          </v-col>
        </v-row>
      </v-layout>

      <div>
        <v-layout>
          <v-row align="center" justify="center">
            <v-col cols="2" sm="2"> 현재시간 </v-col>
            <v-col cols="6" sm="4">
              <v-text-field v-model="time" readonly solo> </v-text-field>
            </v-col>
          </v-row>
        </v-layout>

        <!-- <v-layout>
        <v-row justify="center">
          <v-col> </v-col>
          <v-col> 근무시간 </v-col>
        </v-row>
        <v-flex>
          <v-progress-linear v-model="knowledge" height="25">
            <strong>{{ Math.ceil(knowledge) }}%</strong>
          </v-progress-linear>
        </v-flex>
      </v-layout>
      <br /> -->

        <v-layout>
          <v-row align="center" justify="center">
            <v-col cols="2" sm="2"> 근무상태 </v-col>
            <v-col cols="6" sm="4">
              <v-text-field v-model="work" solo readonly></v-text-field>
            </v-col>
          </v-row>
        </v-layout>
      </div>

      <v-layout>
        <v-row align="center" justify="center">
          <Mydatatable />
        </v-row>
      </v-layout>
    </v-container>
    <Footer />
  </div>
</template> 


<script>
import Header from "../../views/common/00_header"; //import 헤더 추가
import Footer from "../../views/common/90_footer"; //import 풋터 추가
import Mydatatable from "../../components/mydatatable"; // 주간 표 추가
import Datepicker from "../../components/datepicker.vue"; //날짜 선택컴포넌트
var nowdate = new Date().toISOString().substr(0, 10);

export default {
  data() {
    return {
      nowdate: nowdate,
      date: "",
      menu: false,
      dialog: false,
      dialog2: false,
      time: "",
      skill: 20,
      power: 78,
      dateif: true,
      test: "",
      test2: "",
      work: "출근전",

      // test
    };
  },

  name: "com_detail",
  components: {
    Header, //헤더 컴포넌트 추가
    Footer, //풋터 컴포넌트 추가
    Mydatatable, // 주간 표
    Datepicker,
  },

  created() {
    this.getNow();
    this.checkwork();

    this.$axios
      .post(
        "http://localhost:8090/api/commute/checkoffWork",
        {
          com_d8: nowdate,
          prj_no: this.$store.state.userINProject[0].prj_no,
          email: this.$store.getters.getCurrentUser.email,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
      .then((res) => {
        console.log(res.data);
        //퇴근
        if (res.data) {
          this.test2 = true;
          this.work = "퇴근";
          // 미퇴근
        } else {
        }
      });
  },

  computed: {},
  methods: {
    com_detail() {
      this.$router.push("/com_detail");
    },

    getNow() {
      const timer = setInterval(() => {
        const today = new Date();
        this.time =
          today.getHours() +
          ":" +
          today.getMinutes() +
          ":" +
          today.getSeconds();
      }, 1000);
    },

    gotoWork() {
      alert("출근등록");

      this.$axios
        .post(
          "http://localhost:8090/api/commute/gotoWork",
          {
            com_d8: nowdate,
            prj_no: this.$store.state.userINProject[0].prj_no,
            email: this.$store.getters.getCurrentUser.email,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {
          console.log(res);
          this.test = true;
          this.test2 = false;
          this.work = "근무중";
        });
    },

    offWork() {
      alert("퇴근등록");

      this.$axios
        .post(
          "http://localhost:8090/api/commute/offWork",
          {
            com_d8: nowdate,
            prj_no: this.$store.state.userINProject[0].prj_no,
            email: this.$store.getters.getCurrentUser.email,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {
          console.log(res);
          this.test2 = true;
          this.work = "퇴근";
        });
    },
    checkwork() {
      this.$axios
        .post(
          "http://localhost:8090/api/commute/checkWork",
          {
            com_d8: nowdate,
            prj_no: this.$store.state.userINProject[0].prj_no,
            email: this.$store.getters.getCurrentUser.email,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {
          console.log(res.data);
          console.log(this.$store.getters.getCurrentUser.email);
          // 출근
          if (res.data) {
            this.test = true;
            this.test2 = false;
            this.work = "근무중";
            // 미출근
          } else {
            this.test = false;
            this.test2 = true;
            this.work = "출근전";
          }
        });
    },
  },
};
</script>
<style scoped>
.combtn {
  width: 100px;
  height: 500px;
}
.v-text-field__details {
  display: none;
}
.v-progress-linear {
  width: 300px;
}
</style>


