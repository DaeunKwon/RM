import router from "../router";

export default {
    setCurrentUser: function (state, payload) {
        state.currentUser = payload
    },

    setProject: function (state, payload) {
        state.userProject = payload
    },

    setProjectINinfo: function (state, payload) {
        state.userINProject = payload
    }
}