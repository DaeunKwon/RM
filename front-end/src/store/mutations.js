import router from "../router";

export default {
    setUsername(state, username) {
        state.username = username;
    },

    setCurrentUser: function (state, payload) {
        state.currentUser = payload
    },

    setComdate: (state, payload) => {
        state.comDate = payload
    },

    setWeekdata: (state, payload) => {
        state.week = payload
    }

}