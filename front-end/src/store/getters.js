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
    },
    getProjectINInfo(state) {
        return state.userINProject;
    },
    getProjectInfo(state) {
        state => state.userProject;
    },
    getUserReport(state) {
        return state.userReport;
    },
    getReportList(state) {
        return state.userReportList;
    }

}
