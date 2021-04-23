<template>
  <div>
    <Header />
    <b-container fluid="md">
      <p>.<span v-html="strHtml"></span></p>
      <h6>업무 일지 작성</h6>
      <p>.<span v-html="strHtml"></span></p>
      <b-form v-if="show">
        <b-form-group id="input-group-1" label="제목" label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="report.rpt_title"
            placeholder="제목"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-1" label="프로젝트" label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="report.prj_title"
            placeholder="프로젝트명"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-1" label="내용" label-for="input-1">
          <b-form-textarea
            id="input-1"
            v-model="report.rpt_content"
            placeholder="업무 일지 내용"
            rows="8"
            required
          ></b-form-textarea>
        </b-form-group>

        <b-form-group id="input-group-1" label="특이사항" label-for="input-1">
          <b-form-textarea
            id="input-1"
            v-model="report.rpt_remark"
            placeholder="특이사항"
            rows="4"
            required
          ></b-form-textarea>
        </b-form-group>
        <p>.<span v-html="strHtml"></span></p>

        <b-button type="button" variant="primary" @click="rptWrite"
          >저장</b-button
        >
        <b-button type="button" variant="danger" @click="reset">취소</b-button>
        <b-button variant="info" href="/rptList">목록</b-button>
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
  name: "rptWrite",
  components: {
    Header, //헤더 컴포넌트 추가
    Footer, //풋터 컴포넌트 추가
  },
  data() {
    return {
      report: {
        rpt_title: "",
        prj_title: "",
        rpt_content: "",
        rpt_remark: "",
      },
      show: true,
    };
  },
  methods: {
    rptWrite() {
      alert(JSON.stringify(this.report));
      this.$axios
        .post(
          "/rptWrite",
          JSON.stringify({
            rpt_title: this.report.rpt_title,
            prj_title: this.report.prj_title,
            rpt_content: this.report.rpt_content,
            rpt_remark: this.report.rpt_remark,
          }),
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {
          console.log(res);
          alert("업무 일지 등록 성공");
          this.$router.push("/rptList");
        });
    },
    reset(event) {
      event.preventDefault();
      // Reset our form values
      this.report.rpt_title = "";
      this.report.prj_title = "";
      this.report.rpt_content = "";
      this.report.rpt_remark = "";
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