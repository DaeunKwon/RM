import state from "./state";

export default {
    getCurrentUser(state) {
        return state.currentUser;
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