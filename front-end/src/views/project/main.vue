<template>
  <div>
    <Header />
    <v-container>
      <br />
      <v-header>프로젝트 목록</v-header>
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
              v-model="model"
              class="pa-4"
              active-class="success"
              show-arrows
            >
              <v-slide-item v-for="project in projectList" :key="project.id">
                <v-card
                  :color="active ? 'undefined' : 'green'"
                  class="ma-4"
                  height="200"
                  width="200"
                >
                  <v-card-title>{{ project.prj_title }}</v-card-title>

                  <v-card-content>
                    <v-card-text>
                      <v-btn
                        color="grey lighten-2"
                        light
                        @click="dialog = true"
                      >
                        출근 </v-btn
                      >&nbsp;

                      <v-dialog v-model="dialog" max-width="290">
                        <v-card>
                          <v-card-title>출근하시겠습니까?</v-card-title>

                          <v-card-actions>
                            <v-spacer></v-spacer>

                            <v-btn
                              color="red darken-1"
                              text
                              @click="dialog = false"
                            >
                              취소
                            </v-btn>

                            <v-btn color="green darken-1" text @click="onWork">
                              확인
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>

                      <v-btn
                        color="grey lighten-2"
                        light
                        @click="dialog2 = true"
                      >
                        업무일지
                      </v-btn>

                      <v-dialog v-model="dialog2" persistent max-width="600px">
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
                                    disabled
                                    outlined
                                    value="Project title"
                                  ></v-text-field>
                                </v-col>
                                <v-col cols="12" sm="6">
                                  <v-text-field
                                    v-model="date"
                                    label="Date"
                                    required
                                    disabled
                                    outlined
                                  >
                                  </v-text-field>
                                </v-col>

                                <v-col
                                  cols="12"
                                  v-for="(input, k) in inputs"
                                  :key="k"
                                >
                                  <v-dialog
                                    ref="dialog3"
                                    v-model="modal2"
                                    :return-value.sync="input.start_time"
                                    persistent
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
                                      v-if="modal2"
                                      v-model="input.start_time"
                                      :id="'start_time' + k"
                                      full-width
                                    >
                                      <v-spacer></v-spacer>
                                      <v-btn
                                        text
                                        color="primary"
                                        @click="modal2 = false"
                                        >Cancel</v-btn
                                      >
                                      <v-btn
                                        text
                                        color="primary"
                                        @click="
                                          $refs.dialog3[k].save(
                                            input.start_time
                                          )
                                        "
                                        >OK</v-btn
                                      >
                                    </v-time-picker>
                                  </v-dialog>

                                  <v-dialog
                                    ref="dialog4"
                                    v-model="modal3"
                                    :return-value.sync="input.end_time"
                                    persistent
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
                                      v-if="modal3"
                                      v-model="input.end_time"
                                      full-width
                                    >
                                      <v-spacer></v-spacer>
                                      <v-btn
                                        text
                                        color="primary"
                                        @click="modal3 = false"
                                        >Cancel</v-btn
                                      >
                                      <v-btn
                                        text
                                        color="primary"
                                        @click="
                                          $refs.dialog4[k].save(input.end_time)
                                        "
                                        >OK</v-btn
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
                            <v-btn
                              color="blue darken-1"
                              text
                              @click="dialog2 = false"
                            >
                              Close
                            </v-btn>
                            <v-btn
                              color="blue darken-1"
                              text
                              @click="dialog2 = false"
                            >
                              Save
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </v-card-text>
                  </v-card-content>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-dialog v-model="dialog3" persistent max-width="600px">
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn icon @click="prjDetail" v-bind="attrs" v-on="on">
                          <v-icon small>info</v-icon>
                        </v-btn>
                      </template>
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
                                  disabled
                                ></v-text-field>
                                <v-text-field
                                  label="진행상황"
                                  required
                                  outlined
                                  disabled
                                ></v-text-field>

                                <v-text-field
                                  label="Legal middle name"
                                  hint="example of helper text only on focus"
                                ></v-text-field>

                                <v-text-field
                                  label="Legal last name*"
                                  hint="example of persistent helper text"
                                  persistent-hint
                                  required
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12">
                                <v-text-field
                                  label="Email*"
                                  required
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12">
                                <v-text-field
                                  label="Password*"
                                  type="password"
                                  required
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12" sm="6">
                                <v-select
                                  :items="['0-17', '18-29', '30-54', '54+']"
                                  label="Age*"
                                  required
                                ></v-select>
                              </v-col>
                              <v-col cols="12" sm="6">
                                <v-autocomplete
                                  :items="[
                                    'Skiing',
                                    'Ice hockey',
                                    'Soccer',
                                    'Basketball',
                                    'Hockey',
                                    'Reading',
                                    'Writing',
                                    'Coding',
                                    'Basejump',
                                  ]"
                                  label="Interests"
                                  multiple
                                ></v-autocomplete>
                              </v-col>
                            </v-row>
                          </v-container>
                          <small>*indicates required field</small>
                        </v-card-text>
                        <v-card-actions>
                          <v-spacer></v-spacer>
                          <v-btn
                            color="blue darken-1"
                            text
                            @click="dialog3 = false"
                            >Close</v-btn
                          >
                          <v-btn
                            color="blue darken-1"
                            text
                            @click="dialog3 = false"
                            >Save</v-btn
                          >
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
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
              v-model="model2"
              class="pa-4"
              active-class="success"
              show-arrows
            >
              <v-slide-item
                v-for="project in doneProjectList"
                :key="project.id"
              >
                <v-card
                  :color="active ? 'undefined' : 'grey lighten-1'"
                  class="ma-4"
                  height="200"
                  width="200"
                  @click="toggle2"
                >
                  <v-card-title>{{ project.prj_title }}</v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-dialog v-model="dialog4" persistent max-width="600px">
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn icon @click="prjDetail" v-bind="attrs" v-on="on">
                          <v-icon small>info</v-icon>
                        </v-btn>
                      </template>

                      <v-card>
                        <v-card-title>
                          <span class="headline">프로젝트 정보</span>
                        </v-card-title>
                        <v-card-text>
                          <v-container>
                            <v-row>
                              <v-col cols="12" sm="6" md="4">
                                <v-text-field
                                  label="Legal first name*"
                                  required
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12" sm="6" md="4">
                                <v-text-field
                                  label="Legal middle name"
                                  hint="example of helper text only on focus"
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12" sm="6" md="4">
                                <v-text-field
                                  label="Legal last name*"
                                  hint="example of persistent helper text"
                                  persistent-hint
                                  required
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12">
                                <v-text-field
                                  label="Email*"
                                  required
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12">
                                <v-text-field
                                  label="Password*"
                                  type="password"
                                  required
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12" sm="6">
                                <v-select
                                  :items="['0-17', '18-29', '30-54', '54+']"
                                  label="Age*"
                                  required
                                ></v-select>
                              </v-col>
                              <v-col cols="12" sm="6">
                                <v-autocomplete
                                  :items="[
                                    'Skiing',
                                    'Ice hockey',
                                    'Soccer',
                                    'Basketball',
                                    'Hockey',
                                    'Reading',
                                    'Writing',
                                    'Coding',
                                    'Basejump',
                                  ]"
                                  label="Interests"
                                  multiple
                                ></v-autocomplete>
                              </v-col>
                            </v-row>
                          </v-container>
                          <small>*indicates required field</small>
                        </v-card-text>
                        <v-card-actions>
                          <v-spacer></v-spacer>
                          <v-btn
                            color="blue darken-1"
                            text
                            @click="dialog4 = false"
                            >Close</v-btn
                          >
                          <v-btn
                            color="blue darken-1"
                            text
                            @click="dialog4 = false"
                            >Save</v-btn
                          >
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
                  </v-card-actions>
                </v-card>
              </v-slide-item>
            </v-slide-group>
          </v-sheet>
        </div>
        <br />
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
  name: "main",
  data() {
    return {
      show: false,
      model: null,
      model2: null,
      dialog: false,
      dialog2: false,
      dialog3: false,
      dialog4: false,
      modal2: false,
      modal3: false,
      projectList: [],
      doneProjectList: [],
      inputs: [{ start_time: null, end_time: null, content: "" }],
      date: "",
    };
  },
  components: {
    Header, //헤더 컴포넌트 추가
    vfooter, //풋터 컴포넌트 추가
  },
  mounted() {
    this.getProjectList();
    this.getDoneProjectList();
    this.getToday();
  },
  methods: {
    prjWrite() {
      this.$router.push("/prjWrite");
    },
    onWork() {
      this.dialog = false;
    },
    getProjectList() {
      http
        .get("/api/project/main")
        .then((res) => {
          this.projectList = res.data;
          console.log(res.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getDoneProjectList() {
      http
        .get("/api/project/main/done")
        .then((res) => {
          this.doneProjectList = res.data;
          console.log(this.doneProjectList);
        })
        .catch((e) => {
          console.log(e);
        });
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
  },
};
</script>

<style>
</style>