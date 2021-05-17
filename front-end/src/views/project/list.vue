<template>
  <div>
    <Header />
    <v-container>
      <br />
      <v-header>프로젝트 목록</v-header>
      <div align="right">
        <button type="submit" class="btn btn-warning" @click="prjWrite">
          작성
        </button>
      </div>
      <br />
      <div>
        <div>
          <v-sheet class="mx-auto" elevation="8" max-width="1000">
            <v-slide-group
              v-model="model"
              class="pa-4"
              active-class="success"
              show-arrows
            >
              <v-slide-item v-for="n in 5" :key="n">
                <v-card
                  :color="active ? 'undefined' : 'green'"
                  class="ma-4"
                  height="200"
                  width="200"
                >
                  <v-card-title>Project title</v-card-title>

                  <v-card-content>
                    <v-card-text>
                      <v-btn
                        color="grey lighten-2"
                        light
                        @click="dialog = true"
                      >
                        출근 </v-btn
                      >&nbsp;

                      <v-dialog v-model="dialog" max-width="290">
                        <v-card>
                          <v-card-title>출근하시겠습니까?</v-card-title>

                          <v-card-actions>
                            <v-spacer></v-spacer>

                            <v-btn
                              color="red darken-1"
                              text
                              @click="dialog = false"
                            >
                              취소
                            </v-btn>

                            <v-btn color="green darken-1" text @click="onWork">
                              확인
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>

                      <v-btn
                        color="grey lighten-2"
                        light
                        @click="dialog2 = true"
                      >
                        업무일지
                      </v-btn>

                      <v-dialog v-model="dialog2" persistent max-width="600px">
                        <v-card>
                          <v-card-title>
                            <span class="headline">업무 일지 작성</span>
                          </v-card-title>
                          <v-card-text>
                            <v-container>
                              <v-row>
                                <v-col cols="12" sm="6">
                                  <v-text-field
                                    label="Project title"
                                    required
                                    disabled
                                    outlined
                                    value="Project title"
                                  ></v-text-field>
                                </v-col>
                                <v-col cols="12" sm="6">
                                  <v-text-field
                                    label="Date"
                                    required
                                    disabled
                                    outlined
                                    value="YYYY-MM-DD"
                                  >
                                  </v-text-field>
                                </v-col>
                                <v-col cols="12" sm="6">
                                  <v-text-field
                                    label="Timeline"
                                    required
                                    outlined
                                  ></v-text-field>
                                </v-col>

                                <v-col cols="12" sm="6">
                                  <v-textarea
                                    label="Content"
                                    required
                                    outlined
                                  ></v-textarea>
                                </v-col>
                              </v-row>
                              <v-btn
                                class="sm-2"
                                fab
                                dark
                                small
                                color="indigo"
                                @click="add()"
                              >
                                <v-icon dark>mdi-plus</v-icon>
                              </v-btn>
                            </v-container>
                          </v-card-text>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn
                              color="blue darken-1"
                              text
                              @click="dialog2 = false"
                            >
                              Close
                            </v-btn>
                            <v-btn
                              color="blue darken-1"
                              text
                              @click="dialog2 = false"
                            >
                              Save
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </v-card-text>
                  </v-card-content>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-dialog v-model="dialog" persistent max-width="600px">
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn icon @click="prjDetail" v-bind="attrs" v-on="on">
                          <v-icon small>info</v-icon>
                        </v-btn>
                      </template>
                      <v-card>
                        <v-card-title>
                          <span class="headline">프로젝트 정보</span>
                        </v-card-title>
                        <v-card-text>
                          <v-container>
                            <v-row>
                              <v-col cols="12" sm="6" md="4">
                                <v-text-field
                                  label="Legal first name*"
                                  required
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12" sm="6" md="4">
                                <v-text-field
                                  label="Legal middle name"
                                  hint="example of helper text only on focus"
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12" sm="6" md="4">
                                <v-text-field
                                  label="Legal last name*"
                                  hint="example of persistent helper text"
                                  persistent-hint
                                  required
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12">
                                <v-text-field
                                  label="Email*"
                                  required
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12">
                                <v-text-field
                                  label="Password*"
                                  type="password"
                                  required
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12" sm="6">
                                <v-select
                                  :items="['0-17', '18-29', '30-54', '54+']"
                                  label="Age*"
                                  required
                                ></v-select>
                              </v-col>
                              <v-col cols="12" sm="6">
                                <v-autocomplete
                                  :items="[
                                    'Skiing',
                                    'Ice hockey',
                                    'Soccer',
                                    'Basketball',
                                    'Hockey',
                                    'Reading',
                                    'Writing',
                                    'Coding',
                                    'Basejump',
                                  ]"
                                  label="Interests"
                                  multiple
                                ></v-autocomplete>
                              </v-col>
                            </v-row>
                          </v-container>
                          <small>*indicates required field</small>
                        </v-card-text>
                        <v-card-actions>
                          <v-spacer></v-spacer>
                          <v-btn
                            color="blue darken-1"
                            text
                            @click="dialog = false"
                            >Close</v-btn
                          >
                          <v-btn
                            color="blue darken-1"
                            text
                            @click="dialog = false"
                            >Save</v-btn
                          >
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
                  </v-card-actions>
                </v-card>
              </v-slide-item>
            </v-slide-group>
          </v-sheet>
        </div>
        <div class="mt-12">
          <v-sheet class="mx-auto" elevation="8" max-width="1000">
            <v-slide-group
              v-model="model2"
              class="pa-4"
              active-class="success"
              show-arrows
            >
              <v-slide-item
                v-for="nn in 5"
                :key="nn"
                v-slot="{ active, toggle2 }"
              >
                <v-card
                  :color="active ? undefined : 'grey lighten-1'"
                  class="ma-4"
                  height="200"
                  width="200"
                  @click="toggle2"
                >
                  <v-card-title>Project title</v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn icon @click="prjDetail">
                      <v-icon small>info</v-icon>
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-slide-item>
            </v-slide-group>
          </v-sheet>
        </div>
        <br />
      </div>

      <br /><br />
      <vfooter />
    </v-container>
  </div>
</template>

<script>
import Header from "../../views/common/00_header"; //import 헤더 추가
import vfooter from "@/views/common/90_footer"; //import 풋터 추가

export default {
  name: "prjList",
  data: () => ({
    show: false,
    model: null,
    model2: null,
    dialog: false,
    dialog2: false,
  }),
  components: {
    Header, //헤더 컴포넌트 추가
    vfooter, //풋터 컴포넌트 추가
  },
  methods: {
    prjWrite() {
      this.$router.push("/prjWrite");
    },
    onWork() {
      this.dialog = false;
    },
  },
};
</script>

<style>
</style>