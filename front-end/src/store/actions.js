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



}