import router from "../router";

export default {
    setUsername(state, username) {
        state.username = username;
    },

    setCurrentUser: function (state, payload) {
        state.currentUser = payload
    },

    setUserProjectInfo: function (state, payload) {
        state.userProjectInfo = payload
    }
}