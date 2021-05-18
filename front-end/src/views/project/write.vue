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
            label="Title"
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
                ref="start_d8"
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
                    >Cancel</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.start_d8.save(start_date)"
                    >OK</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
            <v-spacer></v-spacer>
            <v-col cols="12" lg="6">
              <v-menu
                ref="end_d8"
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
                    >Cancel</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.end_d8.save(end_date)"
                    >OK</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-row>

          <v-select
            outlined
            v-model="lead_email"
            :rules="nameRules"
            label="팀장"
            required
          >
            <option v-for="user in userList" :key="user.id">
              {{ user.name }}
            </option></v-select
          >

          <v-row>
            <v-col cols="12" lg="6">
              <v-menu
                ref="lead_in_d8"
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
                    >Cancel</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.lead_in_d8.save(lead_in_date)"
                    >OK</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
            <v-spacer></v-spacer>
            <v-col cols="12" lg="6">
              <v-menu
                ref="lead_out_d8"
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
                    >Cancel</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.lead_out_d8.save(lead_out_date)"
                    >OK</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-row>
          <v-select
            outlined
            v-model="follow_email"
            :rules="nameRules"
            label="팀원"
            required
          >
            <option v-for="user in userList" :key="user.id">
              {{ user.name }}
            </option></v-select
          >

          <v-row>
            <v-col cols="12" lg="6">
              <v-menu
                ref="follow_in_d8"
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
                    >Cancel</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.follow_in_d8.save(follow_in_date)"
                    >OK</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
            <v-spacer></v-spacer>
            <v-col cols="12" lg="6">
              <v-menu
                ref="follow_out_d8"
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
                    >Cancel</v-btn
                  >
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.follow_out_d8.save(follow_out_date)"
                    >OK</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-row>
          <v-btn
            class="sm-2 mr-4"
            fab
            dark
            small
            color="indigo"
            @click="remove"
          >
            <v-icon dark>mdi-minus</v-icon>
          </v-btn>
          <v-btn class="sm-2" fab dark small color="indigo" @click="add()">
            <v-icon dark>mdi-plus</v-icon>
          </v-btn>
          <br />
          <div id="app2">
            <li v-for="item in buttons" :key="item"></li>
            <br />
          </div>
          <br />

          <v-textarea
            outlined
            v-model="content"
            :rules="nameRules"
            label="Content"
            required
          ></v-textarea>

          <v-textarea
            outlined
            v-model="remark"
            :rules="nameRules"
            label="Remark"
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
      buttons: [],
      cond: null,
      cond_items: ["예정", "진행중", "완료"],
      userList: [],
      //start_date: new Date().toISOString().substr(0, 10),
      start_date: "",
      end_date: "",
      lead_in_date: "",
      lead_out_date: "",
      start_d8: false,
      end_d8: false,
      lead_in_d8: false,
      lead_out_d8: false,
      project: {
        title: "",
        cond: "",
        start_d8: "",
        end_d8: "",
        content: "",
        remark: "",
      },
      leader: {
        lead_email: "",
        lead_prj_in_d8: "",
        lead_prj_out_d8: "",
      },
      follower: {
        follow_email: "",
        follow_prj_in_d8: "",
        follow_prj_out_d8: "",
      },
      show: true,
    };
  },

  mounted() {
    this.getUserList();
  },
  methods: {
    prjWrite() {
      alert(JSON.stringify(this.project));
      var leader = {
        email: this.leader.email,
        prj_in_d8: this.leader.prj_in_d8,
        prj_out_d8: this.leader.prj_out_d8,
      };
      var follower = {
        email: this.follower.email,
        prj_in_d8: this.follower.prj_in_d8,
        prj_out_d8: this.follower.prj_out_d8,
      };
      var project = {
        prj_title: this.project.prj_title,
        cond: this.project.cond,
        start_d8: this.project.start_d8,
        end_d8: this.project.end_d8,
        prj_content: this.project.prj_content,
        prj_remark: this.project.prj_remark,
        leader: leader,
        follower: follower,
      };

      this.$axios
        .post("/api/project/write", JSON.stringify(project), {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          console.log(res);
          alert("프로젝트 등록 성공");
          this.$router.push("/main");
        });
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
    add() {
      this.buttons.push("addMember");
    },
    remove() {
      this.buttons.push("removeBtn");
    },
  },
};
</script>

<style>
</style>