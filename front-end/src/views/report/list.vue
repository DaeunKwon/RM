<template>
  <div>
    <Header />
    <b-container fluid="md">
      <p>.<span v-html="strHtml"></span></p>
      <h6>업무 일지 목록</h6>
      <div align="right">
        <button type="submit" class="btn btn-warning" @click="rptWrite">
          작성
        </button>
      </div>
      <p>.<span v-html="strHtml"></span></p>
      <div>
        <div>
          <b-table hover :items="items"></b-table>
        </div>
      </div>
      <p>.<span v-html="strHtml"></span></p>
      <p>.<span v-html="strHtml"></span></p>
      <Footer />
    </b-container>
  </div>
</template>

<script>
import Header from "../../views/common/00_header"; //import 헤더 추가
import Footer from "../../views/common/90_footer"; //import 풋터 추가

// var reportVO ${list};

export default {
  name: "rptList",
  components: {
    Header, //헤더 컴포넌트 추가
    Footer, //풋터 컴포넌트 추가
  },

  data() {
    return {
      items: [
        {
          번호: 1,
          제목: "2021년 4월 22일 업무 일지",
          프로젝트명: "프로젝트 1",
          작성자: "권다은",
        },
      ],
    };
  },
  methods: {
    rptWrite() {
      this.$router.push("/rptWrite");
    },
  },
};
</script>

<style>
</style>