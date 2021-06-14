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
    // UserProjectInfo: async function (context) {
    //     await axios.get('/api/project/main').then(res => {
    //         context.commit('setUserProjectInfo', res.data)
    //     }).catch(e => {
    //         context.commit('setUserProjectInfo', 'none')
    //     })
    // }

}