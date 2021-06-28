import router from "../router";
import state from "./state";

export default {
    setCurrentUser: function (state, payload) {
        state.currentUser = payload
    },

    setComdate: (state, payload) => {
        state.comDate = payload
    },

    setWeekdata: (state, payload) => {
        state.week = payload
    },

    setProjectINinfo: function (state, payload) {
        state.userINProject = payload
    },

    // 업무 일지 상세 내용
    setReport: function (state, payload) {
        state.userReport = payload
    },

    // 업무 일지 목록
    setReportList: function (state, payload) {
        state.userReportList = payload
    },

    setUpdatingProject: function (state, payload) {
        state.updatingProject = payload
    },

    setUpdatingLeader: function (state, payload) {
        state.updatingLeader = payload
    },

    setUpdatingFollower: function (state, payload) {
        state.updatingFollower = payload
    },
}