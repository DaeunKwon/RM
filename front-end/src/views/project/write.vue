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
            v-model="project.leader"
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
            v-model="project.lead_prj_in_d8"
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
            v-model="project.lead_prj_out_d8"
            placeholder="참여 종료 날짜"
            required
          ></b-form-datepicker>
        </b-form-group>

        <b-form-group id="input-group-1" label="팀원" label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="project.follower"
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
            v-model="project.follow_prj_in_d8"
            placeholder="참여 시작 날짜"
            required
          ></b-form-datepicker>
        </b-form-group>

        <b-form-group id="input-group-1" label="종료 날짜" label-for="input-1">
          <b-form-datepicker
            id="input-1"
            v-model="project.follow_prj_out_d8"
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

        <b-button type="button" variant="primary" @click="prjWritePOST"
          >저장</b-button
        >
        <b-button type="button" variant="danger">취소</b-button>
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
        prj_leader: "",
        lead_prj_in_d8: "",
        lead_prj_out_d8: "",
        prj_follower: "",
        follow_prj_in_d8: "",
        follow_prj_out_d8: "",
        prj_content: "",
        prj_remark: "",
      },
      show: true,
    };
  },
  methods: {
    prjWritePOST() {
      this.$axios.POST(
        "/joinPOST",
        JSON.stringify({
          prj_title: this.project.prj_title,
          cond: this.project.cond,
          start_d8: this.project.start_d8,
          end_d8: this.project.end_d8,
          prj_leader: this.project.prj_leader,
          lead_prj_in_d8: this.project.lead_prj_in_d8,
          lead_prj_out_d8: this.project.lead_prj_out_d8,
          prj_follower: this.project.prj_follower,
          follow_prj_in_d8: this.project.follow_prj_in_d8,
          follow_prj_out_d8: this.project.follow_prj_out_d8,
          prj_content: this.project.prj_content,
          prj_remark: this.project.prj_remark,
        }),
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
    },
  },
};
</script>

<style>
</style>