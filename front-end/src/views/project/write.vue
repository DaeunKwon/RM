<template>
  <div>
    <Header />
    <v-container>
      <v-flex md6 offset-md3>
        <br />
        <h5>프로젝트 등록</h5>
        <br />
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-if="$route.params.project"
            v-model="$route.params.project.prj_title"
            label="프로젝트명"
            required
            outlined
          ></v-text-field>
          <v-text-field
            v-else
            v-model="title"
            label="프로젝트명"
            required
            outlined
          ></v-text-field>

          <v-select
            v-if="$route.params.project"
            v-model="$route.params.project.cond"
            :items="cond_items"
            label="진행상황"
            required
            outlined
          ></v-select>
          <v-select
            v-else
            :items="cond_items"
            v-model="cond"
            label="진행상황"
            required
            outlined
          ></v-select>

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
                    v-if="$route.params.project"
                    :value="
                      $moment($route.params.project.start_d8).format(
                        'YYYY-MM-DD'
                      )
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
                    v-if="$route.params.project"
                    :value="
                      $moment($route.params.project.end_d8).format('YYYY-MM-DD')
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
            v-if="$route.params.project"
            v-model="$route.params.leader.email"
            outlined
            :hint="`${leader.name}, ${leader.email}`"
            :items="userList"
            item-text="name"
            item-value="email"
            label="팀장"
            required
            persistent-hint
            return-object
          ></v-select>
          <v-select
            v-else
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
                    v-if="$route.params.leader"
                    :value="
                      $moment($route.params.leader.prj_in_d8).format(
                        'YYYY-MM-DD'
                      )
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
                    v-if="$route.params.leader"
                    :value="
                      $moment($route.params.leader.prj_out_d8).format(
                        'YYYY-MM-DD'
                      )
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
              </v-select
            ></v-col>
            <v-col cols="12">
              <v-dialog
                ref="dialog"
                v-model="follow_in_d8"
                :return-value.sync="input.in_date"
                width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="input.in_date"
                    :id="'in_date' + k"
                    label="참여 시작 날짜"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                    outlined
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-if="follow_in_d8"
                  v-model="input.in_date"
                  :id="'in_date' + k"
                  full-width
                >
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="follow_in_d8 = false"
                    >Cancel</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.dialog[k].save(input.in_date)"
                    >OK</v-btn
                  >
                </v-date-picker>
              </v-dialog>

              <v-dialog
                ref="dialog2"
                v-model="follow_out_d8"
                :return-value.sync="input.out_date"
                width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="input.out_date"
                    :id="'out_date' + k"
                    label="참여 종료 날짜"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                    outlined
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-if="follow_out_d8"
                  v-model="input.out_date"
                  full-width
                >
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="follow_out_d8 = false"
                    >Cancel</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.dialog2[k].save(input.out_date)"
                    >OK</v-btn
                  >
                </v-date-picker>
              </v-dialog>

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

          <br /><br /><br />

          <v-textarea
            v-if="$route.params.project"
            v-model="$route.params.project.prj_content"
            outlined
            label="프로젝트 내용"
            required
          ></v-textarea>
          <v-textarea
            v-else
            outlined
            v-model="content"
            label="프로젝트 내용"
            required
          ></v-textarea>

          <v-textarea
            v-if="$route.params.project"
            v-model="$route.params.project.prj_remark"
            outlined
            label="특이사항"
            required
          ></v-textarea>
          <v-textarea
            v-else
            outlined
            v-model="remark"
            label="특이사항"
            required
          ></v-textarea>

          <br />
          <v-btn color="primary" class="mr-4" @click="prjWrite"> 저장 </v-btn>
          <v-btn color="success" @click="main"> 목록 </v-btn>
        </v-form>
      </v-flex>
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
  data: () => ({
    title: "",
    cond: "",
    start_date: "",
    end_date: "",
    content: "",
    remark: "",
    leader: { name: "", email: "" },
    lead_in_date: "",
    lead_out_date: "",
  }),
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
      inputs: [
        { follower: { name: "", email: "" }, in_date: null, out_date: null },
      ],
      follow_in_d8: false,
      follow_out_d8: false,
      content: "",
      remark: "",
      show: true,
      //start_date: new Date().toISOString().substr(0, 10),
    };
  },
  mounted() {
    this.getUserList;
  },
  computed: {
    getUserList() {
      this.$axios
        .get("/api/user/list")
        .then((res) => {
          this.userList = res.data;
          console.log(this.userList);
          return this.userList;
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  methods: {
    formatDate() {},
    prjWrite() {
      console.log(this.$store.getters.getCurrentUser);
      const project = new FormData();
      project.append("title", this.title);
      project.append("cond", this.cond);
      project.append("start_date", this.start_date);
      project.append("end_date", this.end_date);
      project.append("writer", this.$store.getters.getCurrentUser.email);
      project.append("mod_writer", this.$store.getters.getCurrentUser.email);
      project.append("content", this.content);
      project.append("remark", this.remark);

      this.$axios.post("/api/project/write", project).then((res) => {
        const leader = new FormData();
        leader.append("email", this.leader.email);
        leader.append("prj_in_d8", this.lead_in_date);
        leader.append("prj_out_d8", this.lead_out_date);
        leader.append("prj_no", res.data);

        this.$axios
          .post("/api/project/in/leader", leader)
          .then(console.log("팀장 넣기 성공")(this.$router.push("/main")))
          .catch((err) => {
            console.log(err);
          });

        // const follower = new FormData();
        // for (let i = 0; i < this.inputs.length; i++) {
        //   console.log(this.inputs[i].follower.email);
        //   console.log(this.inputs[i].in_date);
        //   console.log(this.inputs[i].out_date);
        //   follower.append("email_" + i, this.inputs[i].follower.email);
        //   follower.append("prj_in_d8_" + i, this.inputs[i].in_date);
        //   follower.append("prj_out_d8_" + i, this.inputs[i].out_date);
        // }
        // console.log(follower);
        // this.$axios.post("api/project/in/follower", follower);
      });
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
  },
};
</script>

<style>
</style>