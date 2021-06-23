<template>
  <div>
    <Header />
    <v-container class="header-padding">
      <br />
      <div align="left" class="display-1">프로젝트 목록</div>
      <div align="right">
        <v-btn
          v-scroll="onScroll"
          class="mx-2"
          fab
          fixed
          bottom
          right
          dark
          color="indigo"
          @click="prjWrite"
          v-if="$store.getters.getCurrentUser.authority == 'ROLE_ROOT'"
        >
          <v-icon dark>mdi-pencil</v-icon>
        </v-btn>
      </div>
      <br />
      <div>
        <div>
          <v-sheet class="mx-auto" elevation="8" max-width="1000"
            ><br />
            <div>진행중인 프로젝트</div>
            <div v-if="projectList.length == 0">
              <br /><br /><br />
              <h5>프로젝트가 없습니다.</h5>
              <br /><br /><br />
            </div>
            <v-slide-group
              v-model="ingModel"
              class="pa-4"
              active-class="success"
              show-arrows
            >
              <v-slide-item
                v-for="project in projectList"
                :key="project.prj_no"
              >
                <v-card
                  :color="project.rmv_YN == 'Y' ? 'red' : 'green'"
                  class="ma-4"
                  height="200"
                  width="200"
                  @click="com_main(project)"
                >
                  <v-card-title
                    >{{ project.prj_title }} <v-spacer></v-spacer>
                    <v-btn
                      icon
                      @click="openDeleteDialog(project)"
                      v-if="
                        $store.getters.getCurrentUser.authority ==
                          'ROLE_ROOT' && project.rmv_YN == 'N'
                      "
                    >
                      <v-icon>mdi-delete-outline</v-icon></v-btn
                    ></v-card-title
                  >

                  <v-card-text class="cardText">
                    <v-btn
                      color="grey lighten-2"
                      light
                      @click.stop="openonWorkDialog(project)"
                      :disabled="project.rmv_YN == 'Y'"
                      v-if="
                        $store.getters.getCurrentUser.authority !==
                          'ROLE_ROOT' && project.com_start == undefined
                      "
                    >
                      출근
                    </v-btn>
                    <v-btn
                      v-else
                      color="grey lighten-2"
                      light
                      @click.stop="openoffWorkDialog(project)"
                      :disabled="
                        (project.rpt_no == 0 && project.com_end == null) ||
                        (project.com_end !== null && project.rpt_no !== 0)
                      "
                    >
                      퇴근
                    </v-btn>
                    &nbsp;

                    <v-btn
                      color="grey lighten-2"
                      light
                      @click.stop="openReportDialog(project)"
                      v-if="
                        $store.getters.getCurrentUser.authority !== 'ROLE_ROOT'
                      "
                      :disabled="
                        project.rpt_no !== 0 || project.com_start == undefined
                      "
                    >
                      업무일지
                    </v-btn>
                  </v-card-text>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn icon @click.stop="openDialogView(project)">
                      <v-icon>mdi-information-outline</v-icon>
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-slide-item>
            </v-slide-group>
          </v-sheet>
        </div>
        <div class="mt-12">
          <v-sheet class="mx-auto" elevation="8" max-width="1000"
            ><br />완료된 프로젝트
            <div v-if="doneProjectList.length == 0">
              <br /><br /><br />
              <h5>프로젝트가 없습니다.</h5>
              <br /><br /><br />
            </div>
            <v-slide-group
              v-model="doneModel"
              class="pa-4"
              active-class="success"
              show-arrows
              @click="com_main"
            >
              <v-slide-item
                v-for="project in doneProjectList"
                :key="project.id"
              >
                <v-card
                  :color="project.rmv_YN == 'Y' ? 'red' : 'grey lighten-1'"
                  class="ma-4"
                  height="200"
                  width="200"
                >
                  <v-card-title
                    >{{ project.prj_title }}<v-spacer></v-spacer>
                    <v-btn
                      icon
                      @click="openDeleteDialog(project)"
                      v-if="
                        $store.getters.getCurrentUser.authority == 'ROLE_ROOT'
                      "
                    >
                      <v-icon>mdi-delete-outline</v-icon></v-btn
                    ></v-card-title
                  >
                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn icon @click="openDialogView(project)">
                      <v-icon>mdi-information-outline</v-icon>
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-slide-item>
            </v-slide-group>
          </v-sheet>
        </div>
        <br />

        <v-dialog v-model="deleteDialog" max-width="290">
          <v-card>
            <v-card-title>삭제하시겠습니까?</v-card-title>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn color="green darken-1" text @click="deleteDialog = false">
                취소
              </v-btn>

              <v-btn color="red darken-1" text @click="deleteProject">
                삭제
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <v-dialog v-model="onworkDialog" max-width="290">
          <v-card>
            <v-card-title>출근하시겠습니까?</v-card-title>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn color="red darken-1" text @click="onworkDialog = false">
                취소
              </v-btn>

              <v-btn
                color="green darken-1"
                text
                @click="[gotoWork(), (onworkDialog = false)]"
              >
                확인
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-dialog v-model="offworkDialog" max-width="290">
          <v-card>
            <v-card-title>퇴근하시겠습니까?</v-card-title>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn color="red darken-1" text @click="offworkDialog = false">
                취소
              </v-btn>

              <v-btn
                color="green darken-1"
                text
                @click="[offWork(), (offworkDialog = false)]"
              >
                확인
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <v-dialog v-model="reportDialog" max-width="600px">
          <v-card>
            <v-card-title>
              <span class="headline">업무 일지 작성</span>
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
                      v-model="selectedProject.prj_title"
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
              <v-btn color="blue darken-1" text @click="reportDialog = false">
                닫기
              </v-btn>
              <v-btn color="blue darken-1" text @click="saveReport">
                저장
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <v-dialog v-model="dialogView" max-width="600px">
          <v-card>
            <v-card-title>
              <span class="headline">프로젝트 정보</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <!-- <v-row>
                  <v-col cols="3">
                    <v-subheader>프로젝트명</v-subheader>
                  </v-col>
                  <v-col cols="9">
                    <v-text-field
                      required
                      outlined
                      readonly
                      :value="selectedProject.prj_title"
                    ></v-text-field>
                  </v-col>
                </v-row> -->
                <v-row>
                  <v-col>
                    <v-text-field
                      label="프로젝트명"
                      required
                      outlined
                      readonly
                      :value="selectedProject.prj_title"
                    ></v-text-field>
                    <v-text-field
                      label="진행상황"
                      required
                      outlined
                      readonly
                      :value="selectedProject.cond"
                    ></v-text-field>
                    <v-text-field
                      label="작성자"
                      required
                      outlined
                      readonly
                      :value="selectedProject.prj_writer"
                    ></v-text-field>
                    <v-text-field
                      label="작성 일자"
                      required
                      outlined
                      readonly
                      :value="
                        $moment(selectedProject.prj_write_d8).format(
                          'YYYY-MM-DD HH:mm:ss'
                        )
                      "
                    ></v-text-field>
                    <v-text-field
                      label="수정자"
                      required
                      outlined
                      readonly
                      :value="selectedProject.prj_writer"
                    ></v-text-field>
                    <v-text-field
                      label="수정 일자"
                      required
                      outlined
                      readonly
                      :value="
                        $moment(selectedProject.prj_mod_d8).format(
                          'YYYY-MM-DD HH:mm:ss'
                        )
                      "
                    ></v-text-field>
                    <v-text-field
                      label="시작 날짜"
                      required
                      outlined
                      readonly
                      :value="
                        $moment(selectedProject.start_d8).format('YYYY-MM-DD')
                      "
                    ></v-text-field>
                    <v-text-field
                      label="종료 날짜"
                      required
                      outlined
                      readonly
                      :value="
                        $moment(selectedProject.end_d8).format('YYYY-MM-DD')
                      "
                    ></v-text-field>
                    <v-text-field
                      label="팀장"
                      required
                      outlined
                      readonly
                      :value="leaderInfo.name"
                    ></v-text-field>
                    <v-text-field
                      label="참여 시작 날짜"
                      required
                      outlined
                      readonly
                      :value="
                        $moment(leaderInfo.prj_in_d8).format('YYYY-MM-DD')
                      "
                    ></v-text-field>
                    <v-text-field
                      label="참여 종료 날짜"
                      required
                      outlined
                      readonly
                      :value="
                        $moment(leaderInfo.prj_out_d8).format('YYYY-MM-DD')
                      "
                    ></v-text-field>
                    <div v-for="follower in followerList" :key="follower.email">
                      <v-text-field
                        label="팀원"
                        required
                        outlined
                        readonly
                        :value="follower.name"
                      ></v-text-field>
                      <v-text-field
                        label="참여 시작 날짜"
                        required
                        outlined
                        readonly
                        :value="
                          $moment(follower.prj_in_d8).format('YYYY-MM-DD')
                        "
                      ></v-text-field>
                      <v-text-field
                        label="참여 종료 날짜"
                        required
                        outlined
                        readonly
                        :value="
                          $moment(follower.prj_out_d8).format('YYYY-MM-DD')
                        "
                      ></v-text-field>
                    </div>
                    <v-textarea
                      label="내용"
                      required
                      outlined
                      readonly
                      :value="selectedProject.prj_content"
                    ></v-textarea>
                    <v-textarea
                      label="특이사항"
                      required
                      outlined
                      readonly
                      :value="selectedProject.prj_remark"
                    ></v-textarea>
                    <v-text-field
                      label="삭제여부"
                      required
                      outlined
                      readonly
                      :value="selectedProject.rmv_YN"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                v-if="selectedProject.authority !== 'ROLE_USER'"
                color="blue darken-1"
                text
                @click="
                  updateProject(selectedProject, leaderInfo, followerList)
                "
                >수정</v-btn
              >
              <v-btn color="blue darken-1" text @click="dialogView = false"
                >닫기</v-btn
              >
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>

      <br /><br />
    </v-container>
    <vfooter />
  </div>
</template>

<script>
import Header from "../../views/common/00_header"; //import 헤더 추가
import vfooter from "@/views/common/90_footer"; //import 풋터 추가
import http from "../../http-common";

export default {
  name: "main_",
  data() {
    return {
      fab: true,
      show: false,
      ingModel: null,
      doneModel: null,
      deleteDialog: false,
      onworkDialog: false,
      offworkDialog: false,
      reportDialog: false,
      startDialog: false,
      endDialog: false,
      dialogView: false,
      startModel: false,
      endModel: false,
      projectList: [],
      doneProjectList: [],
      // leaderList: [],
      inputs: [{ start_time: null, end_time: null, content: "" }],
      today: "",
      selectedProject: "",
      leaderInfo: "",
      deletedProject: "",
      followerList: [],
    };
  },

  components: {
    Header, //헤더 컴포넌트 추가
    vfooter, //풋터 컴포넌트 추가
  },
  mounted() {
    this.getDoneProjectList;
    this.getUserInfo;
    this.getToday();
  },
  computed: {
    getUserInfo() {
      this.$axios.get("/api/user/info").then((res) => {
        this.$store.commit("setCurrentUser", res.data);

        this.$axios
          .get("/api/project/in/info", {
            params: { email: this.$store.getters.getCurrentUser.email },
          })
          .then((res) => {});

        //projectList 안에 프로젝트 번호에 해당되는 권한도 가져옴
        this.$axios
          .get("/api/project/main", {
            params: {
              email: this.$store.getters.getCurrentUser.email,
              authority: this.$store.getters.getCurrentUser.authority,
            },
          })
          .then((res) => {
            this.projectList = res.data;
            const userINProject = [];
            for (let i = 0; i < this.projectList.length; i++) {
              console.log(this.projectList[0].prj_no);
              console.log(this.projectList[1].prj_no);
              console.log(new Date().toISOString().substr(0, 10));
              console.log(this.$store.getters.getCurrentUser.email);
              this.$axios
                .post(
                  "/api/commute/checkWork",
                  {
                    com_d8: new Date().toISOString().substr(0, 10),
                    prj_no: this.projectList[i].prj_no,
                    email: this.$store.getters.getCurrentUser.email,
                  },
                  {
                    headers: {
                      "Content-Type": "application/json",
                    },
                  }
                )
                .then((res) => {});
              userINProject.push({
                prj_in_no: this.projectList[i].prj_in_no,
                prj_no: this.projectList[i].prj_no,
                authority: this.projectList[i].authority,
                prj_title: this.projectList[i].prj_title,
              });
            }
            this.$store.commit("setProjectINinfo", userINProject);
            return this.projectList;
          })

          .catch((e) => {
            console.log(e);
          });

        this.$axios
          .get("/api/project/main/done", {
            params: {
              email: this.$store.getters.getCurrentUser.email,
              authority: this.$store.getters.getCurrentUser.authority,
            },
          })
          .then((res) => {
            this.doneProjectList = res.data;
            return this.getDoneProjectList;
          })
          .catch((e) => {
            //console.log(e);
          });
      });
    },
    work() {},
  },
  methods: {
    onScroll(e) {
      if (typeof window === "undefined") return;
      const top = window.pageYOffset || e.target.scrollTop || 0;
      this.fab = top > 0;
    },
    updateProject(selectedProject, leaderInfo, followerList) {
      this.$router.push({
        name: "prjWrite",
        query: {
          flag: 1,
          project: selectedProject,
          leader: leaderInfo,
          follower: followerList,
        },
      });
    },
    prjWrite() {
      this.$router.push({ name: "prjWrite", query: { flag: 0 } });
    },

    add(k) {
      this.inputs.push({ start_time: null, end_time: null, content: "" });
    },
    remove(k) {
      this.inputs.splice(k, 1);
    },
    getToday() {
      this.today = this.$moment(new Date()).format("YYYY-MM-DD");
    },
    openDialogView(project) {
      this.dialogView = true;
      this.selectedProject = project;

      this.$axios
        .get("/api/project/in/leader/info", {
          params: { prj_no: this.selectedProject.prj_no },
        })
        .then((res) => {
          this.leaderInfo = res.data;
        });

      this.$axios
        .get("/api/project/in/follower/info", {
          params: { prj_no: this.selectedProject.prj_no },
        })
        .then((res) => {
          this.followerList = res.data;
        });
    },
    openReportDialog(project) {
      this.reportDialog = true;
      this.selectedProject = project;
    },
    saveReport() {
      //로그인한 유저의 프로젝트 참여 번호도 보내야 함
      const report = new FormData();
      report.append("prj_no", this.selectedProject.prj_no);
      report.append("rpt_writer", this.selectedProject.prj_in_no);
      report.append("rpt_mod_writer", this.selectedProject.prj_in_no);

      this.$axios.post("/api/report/write", report).then((res) => {
        const reportDetail = new FormData();
        for (let i = 0; i < this.inputs.length; i++) {
          reportDetail.append(
            "start_time",
            this.today + " " + this.inputs[i].start_time
          );
          reportDetail.append(
            "end_time",
            this.today + " " + this.inputs[i].end_time
          );
          reportDetail.append("content", this.inputs[i].content);
          reportDetail.append("rpt_no", res.data);
        }
        reportDetail.append("flag", 0);

        this.$axios
          .post("/api/report/write/detail", reportDetail)
          .then((res) => {
            alert("업무 일지가 등록되었습니다.");
            this.reportDialog = false;
            this.$router.push("/rptList");
          });
      });
    },
    openDeleteDialog(project) {
      this.deleteDialog = true;
      this.selectedProject = project;
    },
    deleteProject() {
      this.$axios
        .post("/api/project/delete", this.selectedProject.prj_no, {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          alert("프로젝트가 삭제되었습니다.");
          this.deleteDialog = false;
          this.$router.push("/main");
        });
    },

    com_main(project) {
      this.$store.commit("setProjectINinfo", project);
      this.$router.push({
        name: "com_main",
        query: { project: project },
      });
    },
    openonWorkDialog(project) {
      this.onworkDialog = true;
      this.selectedProject = project;
      console.log(this.selectedProject.prj_no);
    },
    gotoWork() {
      alert("출근등록");

      this.$axios
        .post(
          "http://localhost:8090/api/commute/gotoWork",
          {
            com_d8: new Date().toISOString().substr(0, 10),
            prj_no: this.selectedProject.prj_no,
            email: this.$store.getters.getCurrentUser.email,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {});
    },
    openoffWorkDialog(project) {
      this.offworkDialog = true;
      this.selectedProject = project;
    },
    offWork() {
      alert("퇴근등록");

      this.$axios
        .post(
          "http://localhost:8090/api/commute/offWork",
          {
            com_d8: new Date().toISOString().substr(0, 10),
            prj_no: this.selectedProject.prj_no,
            email: this.$store.getters.getCurrentUser.email,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {});
    },
  },
};
</script>

<style scoped>
.ma-4 {
  z-index: 0;
}
.cardText {
  z-index: 3;
}
</style>