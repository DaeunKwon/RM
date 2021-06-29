<template>
  <div>
    <Header />
    <v-container class="header-padding">
      <div align="left" class="display-1">
        프로젝트 {{ $route.query.flag == 0 ? "등록" : "수정" }}
      </div>
      <v-container>
        <br />

        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-if="$route.query.flag == 1"
            v-model="$route.query.project.prj_title"
            label="프로젝트명"
            required
            outlined
          ></v-text-field>
          <v-text-field
            v-else-if="$route.query.flag == 0"
            v-model="title"
            label="프로젝트명"
            required
            outlined
          ></v-text-field>

          <v-select
            v-if="$route.query.flag == 1"
            v-model="$route.query.project.cond"
            :items="cond_items"
            label="진행상황"
            required
            outlined
          ></v-select>
          <v-select
            v-else-if="$route.query.flag == 0"
            :items="cond_items"
            v-model="cond"
            label="진행상황"
            required
            outlined
          ></v-select>
          <!-- <v-row> {{ start_date }}</v-row> -->
          <v-row>
            <v-col cols="12" lg="6">
              <v-menu
                ref="menu1"
                v-model="start_d8"
                :close-on-content-click="false"
                :return-value.sync="start_date"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-if="$route.query.flag == 1"
                    :value="
                      start_date.length <= 0
                        ? $moment($route.query.project.start_d8).format(
                            'YYYY-MM-DD'
                          )
                        : start_date
                    "
                    label="프로젝트 시작 날짜"
                    v-bind="attrs"
                    v-on="on"
                    outlined
                  ></v-text-field>
                  <v-text-field
                    v-else
                    v-model="start_date"
                    label="프로젝트 시작 날짜"
                    v-bind="attrs"
                    v-on="on"
                    outlined
                  ></v-text-field>
                </template>
                <v-date-picker v-model="start_date" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="start_d8 = false"
                    >취소</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.menu1.save(start_date)"
                    >저장</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
            <v-spacer></v-spacer>
            <v-col cols="12" lg="6">
              <v-menu
                ref="menu2"
                v-model="end_d8"
                :close-on-content-click="false"
                :return-value.sync="end_date"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-if="$route.query.flag == 1"
                    :value="
                      end_date.length <= 0
                        ? $moment($route.query.project.end_d8).format(
                            'YYYY-MM-DD'
                          )
                        : end_date
                    "
                    label="프로젝트 종료 날짜"
                    v-bind="attrs"
                    v-on="on"
                    outlined
                  ></v-text-field>
                  <v-text-field
                    v-else
                    v-model="end_date"
                    label="프로젝트 종료 날짜"
                    v-bind="attrs"
                    v-on="on"
                    outlined
                  ></v-text-field>
                </template>
                <v-date-picker v-model="end_date" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="end_d8 = false"
                    >취소</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.menu2.save(end_date)"
                    >저장</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-row>

          <v-select
            v-if="$route.query.flag == 1"
            v-model="$route.query.leader.email"
            outlined
            :hint="`${leader.name}, ${leader.email}`"
            :items="userList"
            item-text="name"
            item-value="email"
            label="팀장"
            required
            persistent-hint
          ></v-select>
          <v-select
            v-else-if="$route.query.flag == 0"
            outlined
            v-model="leader"
            :hint="`${leader.name}, ${leader.email}`"
            :items="userList"
            item-text="name"
            item-value="email"
            label="팀장"
            required
            persistent-hint
            return-object
          ></v-select>
          <br />
          <v-row>
            <v-col cols="12" lg="6">
              <v-menu
                ref="menu3"
                v-model="lead_in_d8"
                :close-on-content-click="false"
                :return-value.sync="lead_in_date"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-if="$route.query.flag == 1"
                    :value="
                      lead_in_date.length <= 0
                        ? $moment($route.query.leader.prj_in_d8).format(
                            'YYYY-MM-DD'
                          )
                        : lead_in_date
                    "
                    label="참여 시작 날짜"
                    v-bind="attrs"
                    v-on="on"
                    outlined
                  ></v-text-field>
                  <v-text-field
                    v-else
                    v-model="lead_in_date"
                    label="참여 시작 날짜"
                    v-bind="attrs"
                    v-on="on"
                    outlined
                  ></v-text-field>
                </template>
                <v-date-picker v-model="lead_in_date" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="lead_in_d8 = false"
                    >취소</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.menu3.save(lead_in_date)"
                    >저장</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
            <v-spacer></v-spacer>
            <v-col cols="12" lg="6">
              <v-menu
                ref="menu4"
                v-model="lead_out_d8"
                :close-on-content-click="false"
                :return-value.sync="lead_out_date"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-if="$route.query.flag == 1"
                    :value="
                      lead_out_date.length <= 0
                        ? $moment($route.query.leader.prj_out_d8).format(
                            'YYYY-MM-DD'
                          )
                        : lead_out_date
                    "
                    label="참여 종료 날짜"
                    v-bind="attrs"
                    v-on="on"
                    outlined
                  ></v-text-field>
                  <v-text-field
                    v-else
                    v-model="lead_out_date"
                    label="참여 종료 날짜"
                    v-bind="attrs"
                    v-on="on"
                    outlined
                  ></v-text-field>
                </template>
                <v-date-picker v-model="lead_out_date" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="lead_out_d8 = false"
                    >취소</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.menu4.save(lead_out_date)"
                    >저장</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-row>

          <div v-if="$route.query.flag == 1">
            <v-dialog v-model="openCalendarFlag" width="290px">
              <v-date-picker v-model="openCalendarDate" full-width>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="openCalendarFlag = false"
                  >취소</v-btn
                >
                <v-btn text color="primary" @click="saveCalendarDate()"
                  >저장</v-btn
                >
              </v-date-picker>
            </v-dialog>
            <v-row v-for="(input, k) in inputs" :key="k">
              <v-col cols="12">
                <v-select
                  outlined
                  v-model="input.email"
                  :id="'follower' + k"
                  :items="userList"
                  item-text="name"
                  item-value="email"
                  label="팀원"
                  required
                  persistent-hint
                  return-object
                >
                </v-select>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  v-model="input.prj_in_d8"
                  :id="'in_date' + k"
                  label="참여 시작 날짜"
                  readonly
                  outlined
                  @click="openCalendar(inputs, k, 'prj_in_d8')"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  v-model="input.prj_out_d8"
                  :id="'out_date' + k"
                  label="참여 종료 날짜"
                  readonly
                  outlined
                  @click="openCalendar(inputs, k, 'prj_out_d8')"
                ></v-text-field>
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
          </div>

          <div v-else>
            <v-dialog v-model="openCalendarFlag" width="290px">
              <v-date-picker v-model="openCalendarDate" full-width>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="openCalendarFlag = false"
                  >취소</v-btn
                >
                <v-btn text color="primary" @click="saveCalendarDate()"
                  >저장</v-btn
                >
              </v-date-picker>
            </v-dialog>
            <v-row v-for="(input, k) in inputs" :key="k">
              <v-col cols="12">
                <v-select
                  outlined
                  v-model="input.follower"
                  :id="'follower' + k"
                  :items="userList"
                  item-text="name"
                  item-value="email"
                  label="팀원"
                  required
                  persistent-hint
                  return-object
                >
                </v-select>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  v-model="input.in_date"
                  :id="'in_date' + k"
                  label="참여 시작 날짜"
                  readonly
                  outlined
                  @click="openCalendar(inputs, k, 'in_date')"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  v-model="input.out_date"
                  :id="'out_date' + k"
                  label="참여 종료 날짜"
                  readonly
                  outlined
                  @click="openCalendar(inputs, k, 'out_date')"
                ></v-text-field>

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
              <br />
            </v-row>
          </div>

          <br /><br /><br />

          <v-textarea
            v-if="$route.query.flag == 1"
            v-model="$route.query.project.prj_content"
            outlined
            label="프로젝트 내용"
            required
          ></v-textarea>
          <v-textarea
            v-else-if="$route.query.flag == 0"
            outlined
            v-model="content"
            label="프로젝트 내용"
            required
          ></v-textarea>

          <v-textarea
            v-if="$route.query.flag == 1"
            v-model="$route.query.project.prj_remark"
            outlined
            label="특이사항"
            required
          ></v-textarea>
          <v-textarea
            v-else-if="$route.query.flag == 0"
            outlined
            v-model="remark"
            label="특이사항"
            required
          ></v-textarea>

          <br />
          <v-btn color="primary" class="mr-4" @click="prjWrite()">
            {{ $route.query.flag == 0 ? "저장" : "수정" }}
          </v-btn>
          <v-btn color="success" @click="main"> 목록 </v-btn>
        </v-form>
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
  name: "prjWrite",
  components: {
    Header, //헤더 컴포넌트 추가
    Footer, //풋터 컴포넌트 추가
  },
  data() {
    return {
      valid: false,
      dialog: false,
      dialog2: false,
      menu1: false,
      menu2: false,
      menu3: false,
      menu4: false,
      start_date: "",
      end_date: "",
      lead_in_date: "",
      lead_out_date: "",
      userList: [],

      title: "",
      cond_items: ["예정", "진행중", "완료"],
      cond: "",
      start_d8: false,
      end_d8: false,
      leader: { name: "", email: "" },
      lead_in_d8: false,
      lead_out_d8: false,
      inputs: [{ in_date: null, out_date: null }],
      follow_in_d8: false,
      follow_out_d8: false,
      content: "",
      remark: "",
      show: true,
      //start_date: new Date().toISOString().substr(0, 10),
      openCalendarFlag: false,
      openCalendarValues: [],
      openCalendarType: "",
      openCalendarIndex: 0,
      openCalendarDate: "",
    };
  },
  mounted() {
    this.getUserList;
    // this.setUpdatingProject;
    if (!!this.$route.query.follower) {
      this.inputs = this.$route.query.follower;
      this.inputs.forEach((input) => {
        input.prj_in_d8 = this.$moment(input.prj_in_d8).format("YYYY-MM-DD");
        input.prj_out_d8 = this.$moment(input.prj_out_d8).format("YYYY-MM-DD");
      });
      // console.log(this.$route.query.project);
      // this.$store.commit("setUpdatingProject", this.$route.query.project);
      // console.log(this.$store.getters.getUpdatingProject);
    }
  },
  computed: {
    getUserList() {
      this.$axios
        .get("/api/user/list")
        .then((res) => {
          this.userList = res.data;
          return this.userList;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // setUpdatingProject() {
    //   if (this.$route.query.flag == 1) {
    //     this.$store.commit("setUpdatingProject", this.$route.query.project);
    //   } else {
    //   }
    //   console.log(this.$store.getters.getUpdatingProject);
    // },
  },
  methods: {
    prjWrite() {
      if (this.$route.query.flag == 0) {
        if (
          this.title !== "" &&
          this.cond !== "" &&
          this.start_date !== "" &&
          this.end_date !== "" &&
          this.writer !== "" &&
          this.mod_writer !== "" &&
          this.content !== "" &&
          this.leader.email !== "" &&
          this.lead_in_date !== "" &&
          this.lead_out_date !== "" &&
          this.inputs.length > 0
        ) {
          var nullflag = 0;
          for (let i = 0; i < this.inputs.length; i++) {
            if (
              this.inputs[i].in_date == null ||
              this.inputs[i].out_date == null
            ) {
              alert("빈 칸을 작성해주세요.");
            } else {
              nullflag++;
            }
          }

          if (nullflag == this.inputs.length) {
            const project = new FormData();
            project.append("title", this.title);
            project.append("cond", this.cond);
            project.append("start_date", this.start_date);
            project.append("end_date", this.end_date);
            project.append("writer", this.$store.getters.getCurrentUser.email);
            project.append(
              "mod_writer",
              this.$store.getters.getCurrentUser.email
            );
            project.append("content", this.content);
            project.append("remark", this.remark);

            this.$axios.post("/api/project/write", project).then((res) => {
              const leader = new FormData();
              leader.append("email", this.leader.email);
              leader.append("prj_in_d8", this.lead_in_date);
              leader.append("prj_out_d8", this.lead_out_date);
              leader.append("prj_no", res.data);
              leader.append("flag", this.$route.query.flag);

              this.$axios.post("/api/project/in/leader", leader);

              const follower = new FormData();
              for (let i = 0; i < this.inputs.length; i++) {
                follower.append("email", this.inputs[i].follower.email);
                follower.append("prj_in_d8", this.inputs[i].in_date);
                follower.append("prj_out_d8", this.inputs[i].out_date);
                follower.append("prj_no", res.data);
                follower.append("flag", this.$route.query.flag);
              }

              this.$axios
                .post("/api/project/in/follower", follower)
                .then((res) => {
                  alert("프로젝트가 등록되었습니다.");
                  this.$router.push("/main");
                });
            });
          }
        } else {
          alert("빈 칸을 작성해주세요.");
        }
      } else {
        // console.log(typeof this.title);
        // console.log(this.title);
        // console.log(this.$store.getters.getUpdatingProject.prj_title);
        const project = new FormData();
        project.append("prj_no", this.$route.query.project.prj_no);
        switch (true) {
          case this.title == "":
            project.append("prj_title", this.$route.query.project.prj_title);
          case this.title !== "":
            project.append("prj_title", this.title);

          case this.cond == "":
            project.append("cond", this.$route.query.project.cond);
          case this.cond !== "":
            project.append("cond", this.cond);

          case this.content == "":
            project.append("content", this.$route.query.project.prj_content);
          case this.content !== "":
            project.append("content", this.content);

          case this.remark == "":
            project.append("remark", this.$route.query.project.prj_remark);
          case this.remark !== "":
            project.append("remark", this.remark);
        }
        switch (true) {
          case this.start_date == "":
            project.append(
              "start_date",
              this.$moment(this.$route.query.project.start_d8).format(
                "YYYY-MM-DD"
              )
            );
            break;
          case this.start_date !== "":
            project.append("start_date", this.start_date);
        }

        switch (true) {
          case this.end_date == "":
            project.append(
              "end_date",
              this.$moment(this.$route.query.project.end_d8).format(
                "YYYY-MM-DD"
              )
            );
            break;
          case this.end_date !== "":
            project.append(
              "end_date",
              this.$moment(this.end_date).format("YYYY-MM-DD")
            );
        }

        project.append("mod_writer", this.$store.getters.getCurrentUser.email);

        this.$axios.post("/api/project/update", project).then((res) => {
          // console.log("프로젝트 수정");
        });

        const leader = new FormData();
        switch (true) {
          case this.lead_in_date == "":
            leader.append(
              "prj_in_d8",
              this.$moment(this.$route.query.leader.prj_in_d8).format(
                "YYYY-MM-DD"
              )
            );
            break;
          case this.lead_in_date !== "":
            leader.append(
              "prj_in_d8",
              this.$moment(this.lead_in_date).format("YYYY-MM-DD")
            );
        }
        switch (true) {
          case this.lead_out_date == "":
            leader.append(
              "prj_out_d8",
              this.$moment(this.$route.query.leader.prj_out_d8).format(
                "YYYY-MM-DD"
              )
            );
            break;
          case this.lead_out_date !== "":
            leader.append(
              "prj_out_d8",
              this.$moment(this.lead_out_date).format("YYYY-MM-DD")
            );
        }
        leader.append("email", this.$route.query.leader.email);
        leader.append("prj_no", this.$route.query.project.prj_no);
        leader.append("flag", this.$route.query.flag);

        this.$axios
          .post("/api/project/in/update/leader", leader)
          .then((res) => {
            // console.log("팀장 수정");
          });

        const follower = new FormData();
        for (let i = 0; i < this.inputs.length; i++) {
          if (typeof this.inputs[i].email === "object") {
            follower.append("email", this.inputs[i].email.email);
          } else {
            follower.append("email", this.inputs[i].email);
          }
          //follower.append("email", this.inputs[i].follower.email);
          follower.append("prj_in_d8", this.inputs[i].prj_in_d8);
          follower.append("prj_out_d8", this.inputs[i].prj_out_d8);
          follower.append("prj_no", this.$route.query.project.prj_no);
        }
        follower.append("flag", this.$route.query.flag);

        this.$axios
          .post("/api/project/in/update/follower", follower)
          .then((res) => {
            alert("프로젝트가 수정되었습니다.");
            this.$router.push("/main");
          });
      }
    },
    add(k) {
      this.inputs.push({ in_date: null, out_date: null });
    },
    remove(k) {
      this.inputs.splice(k, 1);
    },
    main() {
      this.$router.push("/main");
    },
    openCalendar(inputs, k, type) {
      this.openCalendarFlag = true;
      this.openCalendarValues = inputs;
      this.openCalendarIndex = k;
      this.openCalendarType = type;
    },
    saveCalendarDate() {
      this.openCalendarFlag = false;
      this.openCalendarValues[this.openCalendarIndex][this.openCalendarType] =
        this.openCalendarDate;
    },
  },
};
</script>

<style>
</style>