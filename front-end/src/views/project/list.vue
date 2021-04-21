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
            <b-card
              bg-variant="primary"
              text-variant="white"
              header="Primary"
              class="text-center"
            >
              <b-card-text
                >Lorem ipsum dolor sit amet, consectetur adipiscing
                elit.</b-card-text
              >
            </b-card>

            <b-card
              bg-variant="secondary"
              text-variant="white"
              header="Secondary"
              class="text-center"
            >
              <b-card-text
                >Lorem ipsum dolor sit amet, consectetur adipiscing
                elit.</b-card-text
              >
            </b-card>

            <b-card
              bg-variant="success"
              text-variant="white"
              header="Success"
              class="text-center"
            >
              <b-card-text
                >Lorem ipsum dolor sit amet, consectetur adipiscing
                elit.</b-card-text
              >
            </b-card>
          </b-card-group>
        </div>
        <div class="mt-3">
          <b-card-group deck>
            <b-card
              bg-variant="info"
              text-variant="white"
              header="Info"
              class="text-center"
            >
              <b-card-text
                >Lorem ipsum dolor sit amet, consectetur adipiscing
                elit.</b-card-text
              >
            </b-card>

            <b-card
              bg-variant="warning"
              text-variant="white"
              header="Warning"
              class="text-center"
            >
              <b-card-text
                >Lorem ipsum dolor sit amet, consectetur adipiscing
                elit.</b-card-text
              >
            </b-card>

            <b-card
              bg-variant="danger"
              text-variant="white"
              header="Danger"
              class="text-center"
            >
              <b-card-text
                >Lorem ipsum dolor sit amet, consectetur adipiscing
                elit.</b-card-text
              >
            </b-card>
          </b-card-group>
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

export default {
  name: "prjList",
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