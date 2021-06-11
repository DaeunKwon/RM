import router from "../router";

export default {
    setCurrentUser: function (state, payload) {
        state.currentUser = payload
    },

    setProject: function (state, payload) {
        state.userProject = payload
    },

    setProjectINinfo: function (state, payload) {
        //새로고침 할때마다 push -> 초기화 필요
        state.userINProject.push(payload)
    }
}