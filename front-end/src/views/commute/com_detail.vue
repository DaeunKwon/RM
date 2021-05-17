

<template>
  <div>
    <Header />

    <v-container>
      <v-layout>
        <v-flex></v-flex><v-flex></v-flex>
        <v-flex>
          <h1>출퇴근 관리</h1>
        </v-flex>
        <v-spacer> </v-spacer>
        <v-flex>
          <v-dialog v-model="dialog" persistent max-width="290">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                v-if="dateif"
                class="combtn"
                color="primary"
                v-bind="attrs"
                v-on="on"
                :disabled="test"
              >
                {{ nowdate }}
              </v-btn>
              <v-btn
                v-else
                class="combtn"
                disabled
                color="primary"
                v-bind="attrs"
                v-on="on"
              >
                출근
              </v-btn>
            </template>
            <v-card>
              <v-card-title class="headline"> 출근하시겠습니까? </v-card-title>
              <v-card-text
                >한 번 누르면 출근시간은<br />
                변경할 수 없습니다.</v-card-text
              >
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click="dialog = false">
                  Disagree
                </v-btn>
                <v-btn
                  color="green darken-1"
                  text
                  @click="[(dialog = false), (test = true), (test2 = false)]"
                >
                  Agree
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>

          <v-dialog v-model="dialog2" persistent max-width="290">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                class="combtn"
                :disabled="test2"
                color="primary"
                v-bind="attrs"
                v-on="on"
              >
                퇴근
              </v-btn>
            </template>

            <v-card>
              <v-card-title class="headline"> 퇴근하시겠습니까? </v-card-title>
              <v-card-text
                >한 번 누르면 퇴근시간은<br />
                변경할 수 없습니다.</v-card-text
              >
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click="dialog2 = false">
                  Disagree
                </v-btn>
                <v-btn
                  color="green darken-1"
                  text
                  @click="[(dialog2 = false), (test2 = true)]"
                >
                  Agree
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-flex>
      </v-layout>

      <v-layout fluid grid-list-sm pa-15>
        <v-flex>
          <v-row align="center" justify="center">
            <v-col cols="10" sm="0" md="4">
              <v-menu
                ref="menu"
                v-model="menu"
                :close-on-content-click="false"
                :return-value.sync="date"
                transition="scale-transition"
                offset-y
                min-width="auto"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="date"
                    label="Picker in menu"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker v-model="date" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="menu = false">
                    Cancel
                  </v-btn>
                  <v-btn
                    text
                    color="primary"
                    @click="[$refs.menu.save(date), dateCommand()]"
                  >
                    OK
                  </v-btn>
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-row>
        </v-flex>
      </v-layout>

      <v-layout>
        <v-row align="center" justify="center">
          <v-col cols="2" sm="2"> 현재시간 </v-col>
          <v-col cols="6" sm="4">
            <v-text-field v-model="time" readonly solo> </v-text-field>
          </v-col>
        </v-row>
      </v-layout>

      <v-layout>
        <v-row justify="center">
          <v-col> </v-col>
          <v-col> 근무시간 </v-col>
        </v-row>
        <v-flex>
          <v-progress-linear v-model="knowledge" height="25">
            <strong>{{ Math.ceil(knowledge) }}%</strong>
          </v-progress-linear>
        </v-flex>
      </v-layout>
      <br />

      <v-layout>
        <v-row align="center" justify="center">
          <v-col cols="2" sm="2"> 근무상태 </v-col>
          <v-col cols="6" sm="4">
            <v-text-field value="근무중" solo readonly></v-text-field>
          </v-col>
        </v-row>
      </v-layout>

      <v-layout>
        <v-row align="center" justify="center">
          <template>
            <v-data-table
              :headers="headers"
              :items="desserts"
              hide-default-footer
              class="elevation-1"
            ></v-data-table>
          </template>
        </v-row>
      </v-layout>
      <br />
      <v-layout>
        <v-flex> 이번주 총 근무시간 : 00시간 </v-flex>
      </v-layout>
    </v-container>
    <Footer />
  </div>
</template>


<script>
import Header from "../../views/common/00_header"; //import 헤더 추가
import Footer from "../../views/common/90_footer"; //import 풋터 추가
var nowdate = new Date().toISOString().substr(0, 10);
import Vue from "vue";
new Vue({
  el: "#combtn",
  data: {
    count: 0,
  },
});

export default {
  data: () => ({
    nowdate: nowdate,
    date: nowdate,
    menu: false,
    dialog: false,
    dialog2: false,
    time: "",
    skill: 20,
    knowledge: 33,
    power: 78,
    dateif: true,
    test: false,
    test2: true,
    headers: [
      { text: "sunday", value: "sunday" },
      { text: "monday", value: "monday" },
      { text: "tuesday", value: "tuesday" },
      { text: "wednesday", value: "wednesday" },
      { text: "thursday", value: "thursday" },
      { text: "friday", value: "friday" },
      { text: "saturday", value: "saturday" },
    ],
    desserts: [
      {
        sunday: "0시간",
        monday: "8시간",
        tuesday: "8시간",
        wednesday: "8시간",
        thursday: "8시간",
        friday: "8시간",
        saturday: "0시간",
      },
    ],
  }),

  name: "com_detail",
  components: {
    Header, //헤더 컴포넌트 추가
    Footer, //풋터 컴포넌트 추가
  },
  created() {
    this.getNow();
  },
  methods: {
    com_detail() {
      this.$router.push("/com_detail");
    },

    getNow() {
      const timer = setInterval(() => {
        const today = new Date();
        this.time =
          today.getHours() +
          ":" +
          today.getMinutes() +
          ":" +
          today.getSeconds();
      }, 1000);
    },

    dateCommand() {
      if (this.date == this.nowdate) {
        this.dateif = true;
      } else {
        this.dateif = false;
      }
    },
  },
};
</script>
<style scoped>
.combtn {
  width: 100px;
  height: 500px;
}
.v-text-field__details {
  display: none;
}
.v-progress-linear {
  width: 300px;
}
</style>


