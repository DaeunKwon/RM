<template>
  <div>
    <Header />
    <v-container>
      <br />
      <h6>프로젝트 목록</h6>
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
              <v-slide-item v-for="n in 8" :key="n" v-slot="{ active, toggle }">
                <v-card
                  :color="active ? undefined : 'green'"
                  class="ma-4"
                  height="200"
                  width="200"
                  @click="toggle"
                >
                  <v-card-title>Project title</v-card-title>

                  <v-card-content>
                    <v-card-text>
                      <v-chip-group
                        v-model="selection"
                        active-class="deep-purple accent-4 white--text"
                        column
                      >
                        <v-chip>출근</v-chip>

                        <!-- <v-chip>업무일지</v-chip> -->

                        <v-chip>상세보기</v-chip>

                        <v-chip>
                          <v-dialog
                            v-model="dialog"
                            persistent
                            max-width="600px"
                          >
                            <template v-slot:activator="{ on, attrs }">
                              <v-btn
                                color="grey lighten-1"
                                v-bind="attrs"
                                v-on="on"
                              >
                                업무일지
                              </v-btn>
                            </template>
                            <v-card>
                              <v-card-title>
                                <span class="headline">업무 일지 작성</span>
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
                                        :items="[
                                          '0-17',
                                          '18-29',
                                          '30-54',
                                          '54+',
                                        ]"
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
                                >
                                  Close
                                </v-btn>
                                <v-btn
                                  color="blue darken-1"
                                  text
                                  @click="dialog = false"
                                >
                                  Save
                                </v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-dialog>
                        </v-chip>
                      </v-chip-group>
                    </v-card-text>
                  </v-card-content>
                </v-card>
              </v-slide-item>
            </v-slide-group>
          </v-sheet>
        </div>
        <div class="mt-12">
          <v-sheet class="mx-auto" elevation="8" max-width="1000">
            <v-slide-group
              v-model="model"
              class="pa-4"
              active-class="success"
              show-arrows
            >
              <v-slide-item v-for="n in 8" :key="n" v-slot="{ active, toggle }">
                <v-card
                  :color="active ? undefined : 'grey lighten-1'"
                  class="ma-4"
                  height="200"
                  width="200"
                  @click="toggle"
                >
                  <v-card-title>Project title</v-card-title>
                  <v-card-content>
                    <v-card-text>
                      <v-chip-group
                        v-model="selection"
                        active-class="deep-purple accent-4 white--text"
                        column
                      >
                        <v-chip>상세보기</v-chip>
                      </v-chip-group>
                    </v-card-text>
                  </v-card-content>
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
    model: null,
    dialog: false,
  }),
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