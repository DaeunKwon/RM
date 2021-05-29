<template>
  <div>
    <Header />
    <v-container>
      <br />
      <div>프로젝트 목록</div>
      <div align="right">
        <button type="submit" class="btn btn-warning" @click="prjWrite">
          작성
        </button>
      </div>
      <br />
      <div>
        <div>
          <v-sheet class="mx-auto" elevation="8" max-width="1000"
            ><br />진행중인 프로젝트
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
                <v-card color="green" class="ma-4" height="200" width="200">
                  <v-card-title>{{ project.prj_title }}</v-card-title>

                  <v-card-text>
                    <v-btn
                      color="grey lighten-2"
                      light
                      @click="onworkDialog = true"
                    >
                      출근 </v-btn
                    >&nbsp;

                    <v-btn
                      color="grey lighten-2"
                      light
                      @click="openReportDialog(project)"
                    >
                      업무일지
                    </v-btn>
                  </v-card-text>

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

        <div class="mt-12">
          <v-sheet class="mx-auto" elevation="8" max-width="1000"
            ><br />완료된 프로젝트
            <v-slide-group
              v-model="doneModel"
              class="pa-4"
              active-class="success"
              show-arrows
            >
              <v-slide-item
                v-for="project in doneProjectList"
                :key="project.id"
              >
                <v-card
                  color="grey lighten-1"
                  class="ma-4"
                  height="200"
                  width="200"
                >
                  <v-card-title>{{ project.prj_title }}</v-card-title>
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

        <v-dialog v-model="onworkDialog" max-width="290">
          <v-card>
            <v-card-title>출근하시겠습니까?</v-card-title>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn color="red darken-1" text @click="onworkDialog = false">
                취소
              </v-btn>

              <v-btn color="green darken-1" text @click="onWork"> 확인 </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <v-dialog v-model="reportDialog" persistent max-width="600px">
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
                      :value="selectedProject.prj_title"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field
                      v-model="date"
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
                      :value="this.selectedProjectLeader.email"
                    ></v-text-field>
                    <v-text-field
                      label="참여 시작 날짜"
                      required
                      outlined
                      readonly
                      :value="
                        $moment(this.selectedProjectLeader.prj_in_d8).format(
                          'YYYY-MM-DD'
                        )
                      "
                    ></v-text-field>
                    <v-text-field
                      label="참여 종료 날짜"
                      required
                      outlined
                      readonly
                      :value="
                        $moment(this.selectedProjectLeader.prj_out_d8).format(
                          'YYYY-MM-DD'
                        )
                      "
                    ></v-text-field>
                    <v-text-field
                      label="팀원"
                      required
                      outlined
                      readonly
                    ></v-text-field>
                    <v-text-field
                      label="참여 시작 날짜"
                      required
                      outlined
                      readonly
                    ></v-text-field>
                    <v-text-field
                      label="참여 종료 날짜"
                      required
                      outlined
                      readonly
                    ></v-text-field>
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
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="dialogView = false"
                >닫기</v-btn
              >
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>

      <br /><br />
      <vfooter />
    </v-container>
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
      show: false,
      ingModel: null,
      doneModel: null,
      onworkDialog: false,
      reportDialog: false,
      startDialog: false,
      endDialog: false,
      dialogView: false,
      startModel: false,
      endModel: false,
      projectList: [],
      doneProjectList: [],
      leaderList: [],
      inputs: [{ start_time: null, end_time: null, content: "" }],
      date: "",
      selectedProject: "",
      selectedProjectLeader: "",
    };
  },

  components: {
    Header, //헤더 컴포넌트 추가
    vfooter, //풋터 컴포넌트 추가
  },
  mounted() {
    this.getProjectList;
    this.getDoneProjectList;
    this.getLeaderList;
    this.getToday();
  },
  computed: {
    getProjectList() {
      this.$axios
        .get("/api/project/main")
        .then((res) => {
          this.projectList = res.data;
          console.log(res.data);
          return this.projectList;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getDoneProjectList() {
      this.$axios
        .get("/api/project/main/done")
        .then((res) => {
          this.doneProjectList = res.data;
          console.log(this.doneProjectList);
          return this.getDoneProjectList;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getLeaderList() {
      this.$axios.get("/api/project/in/leader/list").then((res) => {
        this.leaderList = res.data;
        console.log(this.leaderList);
        return this.getLeaderList;
      });
    },
  },
  methods: {
    prjWrite() {
      this.$router.push("/prjWrite");
    },
    onWork() {
      this.onworkDialog = false;
    },
    add(k) {
      this.inputs.push({ start_time: null, end_time: null, content: "" });
    },
    remove(k) {
      this.inputs.splice(k, 1);
    },
    getToday() {
      this.date = this.$moment(new Date()).format("YYYY-MM-DD");
      console.log(this.date);
    },
    openDialogView(project) {
      this.dialogView = true;
      this.selectedProject = project;
      var leaderInfoList = this.leaderList;
      for (var i in leaderInfoList) {
        if (leaderInfoList[i].prj_no == project.prj_no) {
          this.selectedProjectLeader = leaderInfoList[i];
          return this.selectedProjectLeader;
        }
      }
    },
    openReportDialog(project) {
      this.reportDialog = true;
      this.selectedProject = project;
    },
    saveReport() {
      let report = new FormData();
      for (let i = 0; i < this.inputs.length; i++) {
        console.log(this.inputs[i].start_time);
        console.log(this.inputs[i].end_time);
        console.log(this.inputs[i].content);
        report.append("start_time_" + i, this.inputs[i].start_time);
        report.append("end_time_" + i, this.inputs[i].end_time);
        report.append("content_" + i, this.inputs[i].content);
      }
      console.log(report.get("content_1"));
      this.$axios
        .post("/api/report/write", JSON.stringify(report), {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          alert("업무 일지 등록");
          this.reportDialog = false;
        });
    },
  },
};
</script>

<style>
</style>