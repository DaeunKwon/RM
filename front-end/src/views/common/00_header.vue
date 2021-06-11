<template>
  <div>
    <v-app-bar>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <v-toolbar-title type="button" @click="main"
        >Report Management</v-toolbar-title
      >

      <v-spacer></v-spacer>

      <v-btn text @click="rptList"> 업무일지 </v-btn>

      <v-btn text> 근태관리 </v-btn>

      <v-subheader> {{ $store.getters.getCurrentUser.name }} 님</v-subheader>

      <v-menu
        :close-on-click="closeOnClick"
        :close-on-content-click="closeOnContentClick"
        :offset-y="offsetY"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn icon v-bind="attrs" v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item
            v-for="(item, index) in items"
            :key="index"
            @click="menuActionClick(item.action)"
          >
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
  </div>
</template>
 
<script>
export default {
  data: () => ({
    items: [
      { title: "UserInfo", action: "userInfo" },
      { title: "Logout", action: "logout" },
    ],
    closeOnClick: true,
    closeOnContentClick: true,
    offsetY: true,
  }),
  methods: {
    menuActionClick(action) {
      if (action === "userInfo") {
        alert("UserInfo");
      } else if (action === "logout") {
        this.$axios.post("http://localhost:8090/logout").then((res) => {
          alert("logout");
          //로그아웃시 프로젝트 참여 정보 초기화
          //this.$store.commit("setProjectINinfo", null);
          this.$router.push("/");
        });
      }
    },
    rptList() {
      this.$router.push("/rptList");
    },
    main() {
      this.$router.push("/main");
    },
  },
};
</script>
 
<style scoped>
</style>
