

<template>
  <div>
    <div><Header /></div>

    <v-container class="header-padding">
      <br />
      <div align="left" class="display-1">출퇴근 관리</div>

      <v-layout fluid grid-list-sm pa-15>
        <ul style="list-style: none">
          <li style="margin-bottom: 50px; margin-top: -70px">
            <div align="right">
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
                  <v-card-title class="headline">
                    출근하시겠습니까?
                  </v-card-title>
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
                  <v-card-title class="headline">
                    퇴근하시겠습니까?
                  </v-card-title>
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
            </div>
          </li>

          <li>
            <v-row>
              <v-col>
                <Datepicker2
                  style="height: 300px; margin-top: 0px"
                  :date="this.date"
                />
              </v-col>
              <v-col style="margin-top: -50px">
                <div style="margin-top: 50px">
                  <ul style="list-style: none">
                    <li class="textF" align="left">
                      현재 프로젝트
                      <v-text-field
                        style="width: 300px"
                        v-model="prj_title"
                        readonly
                        solo
                      >
                      </v-text-field>
                    </li>

                    <li class="textF" align="left">
                      현재시간
                      <v-text-field
                        style="width: 300px"
                        v-model="time"
                        readonly
                        solo
                      >
                      </v-text-field>
                    </li>

                    <li class="textF" align="left">
                      근무상태

                      <v-text-field
                        style="width: 300px"
                        v-model="work"
                        solo
                        readonly
                      ></v-text-field>
                    </li>
                    <li class="textF" align="left">근무시간</li>
                    <v-text-field
                      style="width: 300px"
                      v-model="worktime"
                      solo
                      readonly
                    ></v-text-field>
                  </ul>
                </div>
              </v-col>
            </v-row>
          </li>

          <li style="margin-top: -20px"><Mydatatable /></li>
        </ul>
      </v-layout>
    </v-container>
    <Footer />
  </div>
</template> 


<script>
import Header from "../../views/common/00_header"; //import 헤더 추가
import Footer from "../../views/common/90_footer"; //import 풋터 추가
import Mydatatable from "../../components/mydatatable"; // 주간 표 추가
import Datepicker2 from "../../components/datepicker2.vue"; //날짜 선택컴포넌트
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
      start: "",
      end: "",
      prj_title: this.$store.state.userINProject.prj_title,
    };
  },

  name: "com_detail",
  components: {
    Header, //헤더 컴포넌트 추가
    Footer, //풋터 컴포넌트 추가
    Mydatatable, // 주간 표
    Datepicker2,
  },

  created() {
    this.getNow();
    this.checkwork();
    this.rptcheck();
    this.$axios
      .post(
        "http://localhost:8090/api/commute/checkoffWork",
        {
          com_d8: new Date().toISOString().substr(0, 10),
          prj_no: this.$store.state.userINProject.prj_no,
          email: this.$store.getters.getCurrentUser.email,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
      .then((res) => {
        
        //퇴근
        if (res.data) {
          this.test2 = true;
          this.work = "퇴근";
        
          // 미퇴근
        } else {
        }
      });
  },

  computed: {
    worktime() {
      this.datepick = this.$store.getters.getDate;
      this.$axios
        .post(
          "http://localhost:8090/api/commute/worktime",
          {
            com_d8: this.datepick,
            prj_no: this.$store.state.userINProject.prj_no,
            email: this.$store.getters.getCurrentUser.email,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {
          if (res.data[0] == null) {
            this.start = "";
            this.end = "";
          } else if (res.data[0] !== null) {
            if (res.data[0].com_start == null && res.data[0].com_end == null) {
              this.start = "";
              this.end = "";
            } else if (
              res.data[0].com_start !== null &&
              res.data[0].com_end == null
            ) {
              this.start = res.data[0].com_start.substring(10);
              this.end = "";
            } else if (
              res.data[0].com_start !== null &&
              res.data[0].com_end !== null
            ) {
              this.start = res.data[0].com_start.substring(10);
              this.end = res.data[0].com_end.substring(10);
            }
          }
        });
      return this.start + " ~ " + this.end;
    },
  },
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
            com_d8: new Date().toISOString().substr(0, 10),
            prj_no: this.$store.state.userINProject.prj_no,
            email: this.$store.getters.getCurrentUser.email,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {
          this.test = true;
          this.test2 = true;
       
        });
    },

    offWork() {
      alert("퇴근등록");

      this.$axios
        .post(
          "http://localhost:8090/api/commute/offWork",
          {
            com_d8: nowdate,
            prj_no: this.$store.state.userINProject.prj_no,
            email: this.$store.getters.getCurrentUser.email,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {
    
        });
    },
    checkwork() {
      this.$axios
        .post(
          "http://localhost:8090/api/commute/checkWork",
          {
            com_d8: new Date().toISOString().substr(0, 10),
            prj_no: this.$store.state.userINProject.prj_no,
            email: this.$store.getters.getCurrentUser.email,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {
      

          // 출근
          if (res.data) {
            this.test = true;
            this.test2 = true;
            this.work = "근무중";
        
            // 미출근
          } else {
            this.test = false;
            this.test2 = true;
            this.work = "출근전";
         
          }
        });
    },
    rptcheck() {
      this.$axios
        .post(
          "http://localhost:8090/api/commute/rptCheck",
          {
            com_d8: new Date().toISOString().substr(0, 10),
            prj_no: this.$store.state.userINProject.prj_no,
            email: this.$store.getters.getCurrentUser.email,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {
          if (res.data == false) {
            this.test2 = true;
          } else {
            this.test2 = false;
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
.textF {
  margin-top: -5px;
}
</style>


