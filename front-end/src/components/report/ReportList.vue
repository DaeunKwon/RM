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
        <!-- <div>
          <b-table hover :items="items"></b-table>
        </div> -->
        <v-row>
          <v-col>
            <v-data-table
              class="elevation-1"
              @click:row="onClickRow"
              :headers="headers"
              :items="document"
              :options.sync="options"
            >
              <template slot="items" slot-scope="props">
                <td>{{ props.item.rpt_no }}</td>
                <td>{{ props.item.rpt_title }}</td>
                <td>{{ props.item.prj_title }}</td>
                <td>{{ props.item.rpt_writer }}</td>
              </template>
            </v-data-table>
          </v-col>
        </v-row>
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
import { getReportListAPI } from "@/api/index";

export default {
  name: "rptList",
  components: {
    Header, //헤더 컴포넌트 추가
    Footer, //풋터 컴포넌트 추가
  },
  data() {
    return {
        headers:[
            { text: '번호', align='center', value: 'rpt_no'},
            { text: '제목', align='center', value: 'rpt_title'},
            { text: '프로젝트명', align='center', value: 'prj_title'},
            { text: '작성자', align='center', value: 'rpt_writer'},
        ],
        documents: [],
        options:{
            multiSort: true,
				sortBy: [],
				sortDesc: [],
				page: 1,
				itemsPerPage: 10,
        }
    //   items: [
    //     {
    //       번호: 1,
    //       제목: "2021년 4월 22일 업무 일지",
    //       프로젝트명: "프로젝트 1",
    //       작성자: "권다은",
    //     },
    //   ],
    };
  },
  mounted(){
      this.getReportList()
  },
  watch: {
		options: {
			handler() {
				this.getBoardList()
			},
			deep: true,
		},
	},
  methods: {
    rptWrite() {
      this.$router.push("/rptWrite");
    },
    getBoardList(){
        const vm = this
        return new Promise((resolve, reject) => {
            const
        })
    }
  },
};
</script>
