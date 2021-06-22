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

    // 프로젝트 참여 정보
    getProjectINInfo(state) {
        return state.userINProject;
    },

    // 업무 일지 상세 내용
    getUserReport(state) {
        return state.userReport;
    },

    // 업무 일지 목록
    getReportList(state) {
        return state.userReportList;
    }

}
