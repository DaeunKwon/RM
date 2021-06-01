import axios from 'axios';

// function requestLogin(user) {
//     let form = new FormData();
//     form.append('username', user.email);
//     form.append('password', user.password);
//     form.append("grant_type", "password");
//     const requestData = {
//         url: "http://localhost:8090",
//         method: "POST",
//         auth: {
//             username: process.env.VUE_APP_CLIENTID,
//             password: process.env.VUE_APP_CLIENTSECRET,
//         },
//         data: form
//     };
//     return axios(requestData);
// }

const resourceHost = "http://localhost:8090"

export default {
    LOGIN({ commit }, { email, password }) {
        return axios
            .post(`${resourceHost}/auth/login`, { email, password })
            .then(({ data }) => commit("LOGIN", data))
    },
    LOGOUT({ commit }) {
        axios.defaults.headers.common['Authorization'] = undefined
        commit("LOGOUT")
    },
    // join({ commit }, user) {
    //     return UserService.join(user).then(
    //         response => {
    //             commit('joinSuccess');
    //             return Promise.resolve(response.data);
    //         },
    //         error => {
    //             commit('joinFailure');
    //             return Promise.reject(error);
    //         }
    //     );
    // },
    // get_user_list({ commit }, { }) {
    //     return axios.get('/api/user/list').then(data => {
    //         if (data.status === 200) {
    //             return {
    //                 "resultList": data.data.data.resultList
    //             }
    //         }
    //     })
    // },
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