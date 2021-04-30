<template>
  <div>
    <Header />
    <b-container fluid="md">
      <p>.<span v-html="strHtml"></span></p>
      <h6>업무 일지 상세 내용</h6>
      <p>.<span v-html="strHtml"></span></p>
      <b-form>
        <b-form-group
          label="번호"
          label-for="nested-city"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-input id="nested-city" readonly>${rvo.rpt_no}</b-form-input>
        </b-form-group>

        <b-form-group
          label="프로젝트 번호"
          label-for="nested-state"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-input id="nested-state" readonly>${rvo.prj_no}</b-form-input>
        </b-form-group>

        <b-form-group
          label="작성자"
          label-for="nested-country"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-input id="nested-country" readonly
            >${rvo.rpt_writer}</b-form-input
          >
        </b-form-group>

        <b-form-group
          label="작성일"
          label-for="nested-country"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-input id="nested-country" readonly
            >${rvo.rpt_write_d8}</b-form-input
          >
        </b-form-group>

        <b-form-group
          label="수정자"
          label-for="nested-country"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-input id="nested-country" readonly
            >${rvo.rpt_mod_writer}</b-form-input
          >
        </b-form-group>

        <b-form-group
          label="수정일"
          label-for="nested-country"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-input id="nested-country" readonly
            >${rvo.rpt_mod_d8}</b-form-input
          >
        </b-form-group>

        <b-form-group
          label="내용"
          label-for="nested-country"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-input id="nested-country" readonly
            >${rvo.rpt_content}</b-form-input
          >
        </b-form-group>

        <b-form-group
          label="특이사항"
          label-for="nested-country"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-input id="nested-country" readonly
            >${rvo.rpt_remark}</b-form-input
          >
        </b-form-group>

        <b-button type="button" variant="warning">수정</b-button>
        <b-button variant="info" href="/rptList">목록</b-button>
      </b-form>
    </b-container>
    <Footer />
  </div>
</template>

<script>
import Header from "../../views/common/00_header"; //import 헤더 추가
import Footer from "../../views/common/90_footer"; //import 풋터 추가

export default {
  name: "rptDetail",
  components: {
    Header, //헤더 컴포넌트 추가
    Footer, //풋터 컴포넌트 추가
  },
  methods: {
    prjWrite() {
      this.$router.push("/prjWrite");
    },
  },
};
</script>

<style>
</style>