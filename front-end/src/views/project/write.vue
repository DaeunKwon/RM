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
            v-model="prj_title"
            :rules="nameRules"
            label="프로젝트명"
            required
            outlined
          ></v-text-field>

          <v-select
            :items="cond_items"
            v-model="cond"
            :rules="nameRules"
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
            outlined
            v-model="lead_email"
            :hint="`${lead_email.name}, ${lead_email.email}`"
            :items="userList"
            item-text="name"
            item-value="email"
            :rules="nameRules"
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
          <!-- <v-select
            outlined
            v-model="follow_email"
            :hint="`${follow_email.name}, ${follow_email.email}`"
            :items="userList"
            item-text="name"
            item-value="email"
            :rules="nameRules"
            label="팀원"
            required
            persistent-hint
            return-object
          >
          </v-select
          ><br /> -->

          <!-- <v-row>
            <v-col cols="12" lg="6">
              <v-menu
                ref="menu5"
                v-model="follow_in_d8"
                :close-on-content-click="false"
                :return-value.sync="follow_in_date"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="follow_in_date"
                    label="참여 시작 날짜"
                    v-bind="attrs"
                    v-on="on"
                    outlined
                  ></v-text-field>
                </template>
                <v-date-picker v-model="follow_in_date" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="follow_in_d8 = false"
                    >취소</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.menu5.save(follow_in_date)"
                    >저장</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
            <v-spacer></v-spacer>
            <v-col cols="12" lg="6">
              <v-menu
                ref="menu6"
                v-model="follow_out_d8"
                :close-on-content-click="false"
                :return-value.sync="lead_out_date"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="follow_out_date"
                    label="참여 종료 날짜"
                    v-bind="attrs"
                    v-on="on"
                    outlined
                  ></v-text-field>
                </template>
                <v-date-picker v-model="follow_out_date" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="follow_out_d8 = false"
                    >취소</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.menu6.save(follow_out_date)"
                    >저장</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-row> -->

          <!--test-->
          <v-row v-for="(input, k) in inputs" :key="k">
            <v-col cols="12">
              <v-select
                outlined
                v-model="follower"
                :hint="`${follower.name}, ${follower.email}`"
                :items="userList"
                item-text="name"
                item-value="email"
                :rules="nameRules"
                label="팀원"
                required
                persistent-hint
                return-object
              >
              </v-select
            ></v-col>
            <v-col cols="12">
              <v-menu
                ref="menu7"
                v-model="modal"
                :close-on-content-click="false"
                :return-value.sync="input.in_date"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="input.in_date"
                    :id="'in_date' + k"
                    label="참여 시작 날짜"
                    v-bind="attrs"
                    v-on="on"
                    outlined
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="input.in_date"
                  :id="'in_date' + k"
                  no-title
                  scrollable
                  width="467"
                >
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="modal = false"
                    >취소</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.menu7[k].save(input.in_date)"
                    >저장</v-btn
                  >
                </v-date-picker>
              </v-menu>
              <v-spacer></v-spacer>
              <v-menu
                ref="menu8"
                v-model="modal2"
                :close-on-content-click="false"
                :return-value.sync="input.out_date"
                transition="scale-transition"
                offset-y
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="input.out_date"
                    :id="'out_date' + k"
                    label="참여 종료 날짜"
                    v-bind="attrs"
                    v-on="on"
                    outlined
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="input.out_date"
                  :id="'out_date' + k"
                  no-title
                  scrollable
                  width="467"
                >
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="modal2 = false"
                    >취소</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.menu8[k].save(input.out_date)"
                    >저장</v-btn
                  >
                </v-date-picker>
              </v-menu>

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

          <!--test-->

          <!-- <v-btn
            class="sm-2 mr-4"
            fab
            dark
            small
            color="indigo"
            @click="remove"
          >
            <v-icon dark>mdi-minus</v-icon>
          </v-btn>
          <v-btn class="sm-2" fab dark small color="indigo" @click="add">
            <v-icon dark>mdi-plus</v-icon>
          </v-btn> -->

          <br /><br /><br />

          <v-textarea
            outlined
            v-model="content"
            :rules="nameRules"
            label="프로젝트 내용"
            required
          ></v-textarea>

          <v-textarea
            outlined
            v-model="remark"
            :rules="nameRules"
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
  data() {
    return {
      start_d8: false,
      end_d8: false,
      prj_title: "",
      lead_in_d8: false,
      lead_out_d8: false,
      modal: false,
      modal2: false,
      inputs: [{ in_date: null, out_date: null }],
      lead_email: { name: "", email: "" },
      follower: { name: "", email: "" },
      lead_in_date: "",
      lead_out_date: "",
      buttons: [],
      cond: null,
      cond_items: ["예정", "진행중", "완료"],
      userList: [],
      //start_date: new Date().toISOString().substr(0, 10),
      start_date: "",
      end_date: "",
      menu1: false,
      menu2: false,
      menu3: false,
      menu4: false,
      menu5: false,
      menu6: false,
      remark: "",
      content: "",

      show: true,
    };
  },

  mounted() {
    this.getUserList();
  },
  methods: {
    prjWrite() {
      // alert(JSON.stringify(this.project));
      // var leader = {
      //   email: this.leader.email,
      //   prj_in_d8: this.leader.prj_in_d8,
      //   prj_out_d8: this.leader.prj_out_d8,
      // };
      // var follower = {
      //   email: this.follower.email,
      //   prj_in_d8: this.follower.prj_in_d8,
      //   prj_out_d8: this.follower.prj_out_d8,
      // };
      // var project = {
      //   prj_title: this.project.prj_title,
      //   cond: this.project.cond,
      //   start_d8: this.project.start_d8,
      //   end_d8: this.project.end_d8,
      //   prj_content: this.project.prj_content,
      //   prj_remark: this.project.prj_remark,
      //   leader: leader,
      //   follower: follower,
      // };
      // this.$axios
      //   .post("/api/project/write", JSON.stringify(project), {
      //     headers: {
      //       "Content-Type": "application/json",
      //     },
      //   })
      //   .then((res) => {
      //     console.log(res);
      //     alert("프로젝트 등록 성공");
      //     this.$router.push("/main");
      //   });
    },
    main() {
      this.$router.push("/main");
    },
    getUserList() {
      http
        .get("/api/user/list")
        .then((res) => {
          this.userList = res.data;
          console.log(this.userList);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    add(k) {
      this.inputs.push({ in_date: null, out_date: null });
    },
    remove(k) {
      this.inputs.splice(k, 1);
    },
  },
};
</script>

<style>
</style>