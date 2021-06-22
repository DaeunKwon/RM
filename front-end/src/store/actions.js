import axios from 'axios';

const resourceHost = "http://localhost:8090"

export default {
    // 현재 로그인 한 유저 가져오기
    initCurrentUser: async function (context) {
        await axios.get('/api/user/info', { email, password })
            .then(res => {
                context.commit('setCurrentUser', res.data)
            }).catch(error => {
                context.commit('setCurrentUser', 'none')
            })
    },

    // 업무 일지 상세 내용 가져오기
    getReportAll: async function (context) {
        await axios.get('/api/report/detail/getAll').then(res => {
            context.commit('setReport', res.data)
        }).catch(err => {
            context.commit('setReport', 'none')
        })
    },

    getReportADMIN: async function (context) {
        await axios.get('/api/report/detail/getADMIN' + prj_no
        ).then(res => {
            context.commit('setReport', res.data)
        }).catch(err => {
            context.commit('setReport', 'none')
        })
    },

    getReportUSER: async function (context, { prj_in_no }) {
        await axios.get('/api/report/detail/getUSER', prj_in_no).then(res => {
            context.commit('setReport', res.data)
        }).catch(err => {
            context.commit('setReport', 'none')
        })
    },

    // 업무 일지 목록 가져오기
    getReportListAll: async function (context) {
        await axios.get('/api/report/getAll').then(res => {
            context.commit('setReportList', res.data)
        }).catch(err => {
            context.commit('setReportList', 'none')
        })
    },

    getReportListADMIN: async function (context) {
        await axios.get('/api/report/getADMIN' + prj_no
        ).then(res => {
            context.commit('setReportList', res.data)
        }).catch(err => {
            context.commit('setReportList', 'none')
        })
    },

    getReportListUSER: async function (context, { prj_in_no }) {
        await axios.get('/api/report/getUSER', prj_in_no).then(res => {
            context.commit('setReportList', res.data)
        }).catch(err => {
            context.commit('setReportList', 'none')
        })
    },
}
