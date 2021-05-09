<template>
  <div>
    <Header />
    <b-container fluid="md">
      <p>.<span v-html="strHtml"></span></p>
      <h6>프로젝트 목록</h6>
      <div align="right">
        <button type="submit" class="btn btn-warning" @click="prjWrite">
          작성
        </button>
      </div>
      <p>.<span v-html="strHtml"></span></p>
      <div>
        <div>
          <b-card-group deck>
            <b-card class="text-center" title="Project title">
              <b-card-text>Project abstract</b-card-text>
              <b-button href="/prjDetail" variant="primary">Detail</b-button>
            </b-card>

            <b-card class="text-center" title="Project title">
              <b-card-text>Project abstract</b-card-text>
              <b-button href="#" variant="primary">Detail</b-button>
            </b-card>

            <b-card class="text-center" title="Project title">
              <b-card-text>Project abstract</b-card-text>
              <b-button href="#" variant="primary">Detail</b-button>
            </b-card>
          </b-card-group>
        </div>
        <div class="mt-3">
          <b-card-group deck>
            <b-card class="text-center" title="Project title">
              <b-card-text>Project abstract</b-card-text>
              <b-button href="#" variant="primary">Detail</b-button>
            </b-card>

            <b-card class="text-center" title="Project title">
              <b-card-text>Project abstract</b-card-text>
              <b-button href="#" variant="primary">Detail</b-button>
            </b-card>

            <b-card class="text-center" title="Project title">
              <b-card-text>Project abstract</b-card-text>
              <b-button href="#" variant="primary">Detail</b-button>
            </b-card>
          </b-card-group>
        </div>
      </div>

      <p>.<span v-html="strHtml"></span></p>
      <p>.<span v-html="strHtml"></span></p>
      <vfooter />
    </b-container>
  </div>
</template>

<script>
import Header from "../../views/common/00_header"; //import 헤더 추가
import vfooter from "@/views/common/90_footer"; //import 풋터 추가

export default {
  name: "prjList",
  components: {
    Header, //헤더 컴포넌트 추가
    vfooter, //풋터 컴포넌트 추가
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