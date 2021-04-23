<template>
  <div>
    <Header />
    <b-container fluid="md">
      <p>.<span v-html="strHtml"></span></p>
      <h6>프로젝트 작성</h6>
      <p>.<span v-html="strHtml"></span></p>
      <b-form v-if="show">
        <b-form-group id="input-group-1" label="프로젝트명" label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="project.prj_title"
            placeholder="프로젝트명"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group label="진행 상태" v-slot="{ ariaDescribedby }">
          <b-form-radio-group
            id="radio-group-2"
            v-model="project.cond"
            :aria-describedby="ariaDescribedby"
            name="radio-sub-component"
          >
            <b-form-radio value="진행 예정">진행 예정</b-form-radio>
            <b-form-radio value="진행중">진행중</b-form-radio>
            <b-form-radio value="진행 완료">진행 완료</b-form-radio>
          </b-form-radio-group>
        </b-form-group>

        <b-form-group id="input-group-1" label="시작 날짜" label-for="input-1">
          <b-form-datepicker
            id="input-1"
            v-model="project.start_d8"
            placeholder="시작 날짜"
            required
          ></b-form-datepicker>
        </b-form-group>

        <b-form-group id="input-group-1" label="종료 날짜" label-for="input-1">
          <b-form-datepicker
            id="input-1"
            v-model="project.end_d8"
            placeholder="종료 날짜"
            required
          ></b-form-datepicker>
        </b-form-group>

        <b-form-group id="input-group-1" label="팀장" label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="leader.email"
            placeholder="팀장"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="input-group-1"
          label="참여 시작 날짜"
          label-for="input-1"
        >
          <b-form-datepicker
            id="input-1"
            v-model="leader.prj_in_d8"
            placeholder="참여 시작 날짜"
            required
          ></b-form-datepicker>
        </b-form-group>

        <b-form-group
          id="input-group-1"
          label="참여 종료 날짜"
          label-for="input-1"
        >
          <b-form-datepicker
            id="input-1"
            v-model="leader.prj_out_d8"
            placeholder="참여 종료 날짜"
            required
          ></b-form-datepicker>
        </b-form-group>

        <b-form-group id="input-group-1" label="팀원" label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="follower.email"
            placeholder="팀원"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="input-group-1"
          label="참여 시작 날짜"
          label-for="input-1"
        >
          <b-form-datepicker
            id="input-1"
            v-model="follower.prj_in_d8"
            placeholder="참여 시작 날짜"
            required
          ></b-form-datepicker>
        </b-form-group>

        <b-form-group id="input-group-1" label="종료 날짜" label-for="input-1">
          <b-form-datepicker
            id="input-1"
            v-model="follower.prj_out_d8"
            placeholder="참여 종료 날짜"
            required
          ></b-form-datepicker>
        </b-form-group>

        <b-form-group id="input-group-1" label="내용" label-for="input-1">
          <b-form-textarea
            id="input-1"
            v-model="project.prj_content"
            placeholder="프로젝트 내용"
            rows="8"
            required
          ></b-form-textarea>
        </b-form-group>

        <b-form-group id="input-group-1" label="특이사항" label-for="input-1">
          <b-form-textarea
            id="input-1"
            v-model="project.prj_remark"
            placeholder="특이사항"
            rows="4"
            required
          ></b-form-textarea>
        </b-form-group>
        <p>.<span v-html="strHtml"></span></p>

        <b-button type="button" variant="primary" @click="prjWrite"
          >저장</b-button
        >
        <b-button type="button" variant="danger" @click="reset">취소</b-button>
        <b-button variant="info" href="/prjList">목록</b-button>
      </b-form>
      <p>.<span v-html="strHtml"></span></p>
      <p>.<span v-html="strHtml"></span></p>
      <Footer />
    </b-container>
  </div>
</template>

<script>
import Header from "../../views/common/00_header"; //import 헤더 추가
import Footer from "../../views/common/90_footer"; //import 풋터 추가

export default {
  name: "prjWrite",
  components: {
    Header, //헤더 컴포넌트 추가
    Footer, //풋터 컴포넌트 추가
  },
  data() {
    return {
      project: {
        prj_title: "",
        cond: "",
        start_d8: "",
        end_d8: "",
        prj_content: "",
        prj_remark: "",
      },
      leader: {
        email: "",
        prj_in_d8: "",
        prj_out_d8: "",
      },
      follower: {
        email: "",
        prj_in_d8: "",
        prj_out_d8: "",
      },
      show: true,
    };
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
        .post("/prjWrite", JSON.stringify(project), {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          console.log(res);
          alert("프로젝트 등록 성공");
          this.$router.push("/prjList");
        });
    },
    reset(event) {
      event.preventDefault();
      // Reset our form values
      this.project.prj_title = "";
      this.project.cond = "";
      this.project.start_d8 = "";
      this.project.end_d8 = "";
      this.project.prj_content = "";
      this.project.prj_remark = "";
      this.leader.email = "";
      this.leader.prj_in_d8 = "";
      this.leader.prj_out_d8 = "";
      this.follower.email = "";
      this.follower.prj_in_d8 = "";
      this.follower.prj_out_d8 = "";
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>

<style>
</style>