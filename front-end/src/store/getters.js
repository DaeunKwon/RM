import state from "./state";

export default {
    getCurrentUser(state) {
        return state.currentUser;
    },

    getDate: (state) => {
        return state.comDate;
    },

    getMonth: (state) => {
        var Month = state.comDate.toISOString().substr(0, 7);
        return Month;
    }

}
