<template>
  <div>
    <Header />
    <v-container
      ><br />
      <v-header>업무 일지 목록</v-header>
      <div align="right">
        <!--test-->
        <v-dialog v-model="dialog5" persistent max-width="600px">
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
                    <v-text-field label="Email*" required></v-text-field>
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
              <v-btn color="blue darken-1" text @click="dialog5 = false"
                >Close</v-btn
              >
              <v-btn color="blue darken-1" text @click="dialog5 = false"
                >Save</v-btn
              >
            </v-card-actions>
          </v-card>
        </v-dialog>

        <v-btn color="grey lighten-2" light @click="dialog2 = true">
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

                  <v-col cols="12" v-for="(input, k) in inputs" :key="k">
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
                        <v-btn text color="primary" @click="modal2 = false"
                          >Cancel</v-btn
                        >
                        <v-btn
                          text
                          color="primary"
                          @click="$refs.dialog3[k].save(input.start_time)"
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
                        <v-btn text color="primary" @click="modal3 = false"
                          >Cancel</v-btn
                        >
                        <v-btn
                          text
                          color="primary"
                          @click="$refs.dialog4[k].save(input.end_time)"
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
                  <!-- </div> -->
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="dialog2 = false">
                Close
              </v-btn>
              <v-btn color="blue darken-1" text @click="dialog2 = false">
                Save
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <!--test-->

        <v-btn color="primary" class="mr-2" @click="$router.push('/prjList')">
          주간
        </v-btn>
        <v-btn color="primary" class="mr-2" @click="monthly"> 월간 </v-btn>
        <v-btn color="primary" @click="daily"> 전체 </v-btn>
      </div>
      <br />
      <v-container fluid>
        <v-row>
          <v-col cols="6" md="3">
            <v-card
              class="pa-2"
              outlined
              tile
              elevation="1"
              height="400"
              width="400"
            >
              개인
              <div>
                <h5>report</h5>
                <h5 v-for="report in reportList" :key="report.id">
                  {{ report.rpt_title }}
                </h5>
              </div>
            </v-card>
          </v-col>
          <v-col cols="12" md="9">
            <v-sheet class="pa-2" outlined tile elevation="1" height="600">
              주간
              <v-slide-group v-model="report" class="pa-4" show-arrows>
                <v-slide-item v-for="day in 5" :key="day">
                  <v-card
                    color="grey lighten-2"
                    class="ma-1"
                    height="500"
                    width="142"
                  >
                  </v-card>
                </v-slide-item>
              </v-slide-group>
            </v-sheet>
          </v-col> </v-row
        ><br />
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
  name: "rptList",
  components: {
    Header, //헤더 컴포넌트 추가
    Footer, //풋터 컴포넌트 추가
  },

  data() {
    return {
      reportList: [],
      dialog2: false,
      dialog3: false,
      dialog4: false,
      dialog5: false,
      date: "",
      modal2: false,
      modal3: false,
      inputs: [{ start_time: null, end_time: null, content: "" }],
    };
  },
  mounted() {
    this.getReportList();
    this.getToday();
  },
  methods: {
    add(k) {
      this.inputs.push({ start_time: null, end_time: null, content: "" });
    },
    remove(k) {
      this.inputs.splice(k, 1);
    },
    // rptList() {
    //   this.$router.push("/rptList");
    // },
    rptWrite() {
      this.$router.push("/rptWrite");
    },
    daily() {
      this.$router.push("/dailyRpt");
    },
    getReportList() {
      http
        .get("/api/report/list")
        .then((res) => {
          this.reportList = res.data;
          console.log(this.reportList);
        })
        .catch((e) => {
          console.log(e);
        });
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