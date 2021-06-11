import router from "../router";

export default {
    setUsername(state, username) {
        state.username = username;
    },

    setCurrentUser: function (state, payload) {
        state.currentUser = payload
    },

    setProject: function (state, payload) {
        state.userProject = payload
    },

    setProjectINinfo: function (state, payload) {
        state.userINProject.push(payload)
    }
}