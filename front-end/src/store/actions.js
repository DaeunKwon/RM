//import axios from 'axios';
import axios from 'axios';
import UserService from '../service/UserService'

export default {
    login({ commit }, user) {
        return UserService.login(user).then(
            user => {
                commit('loginSuccess', user);
                return Promise.resolve(user);
            },
            error => {
                commit('loginFailure');
                return Promise.reject(error);
            }
        );
    },
    logout({ commit }) {
        UserService.logout();
        commit('logout');
    },
    join({ commit }, user) {
        return UserService.join(user).then(
            response => {
                commit('joinSuccess');
                return Promise.resolve(response.data);
            },
            error => {
                commit('joinFailure');
                return Promise.reject(error);
            }
        );
    },
    get_user_list({ commit }, { }) {
        return axios.get('/api/user/list').then(data => {
            if (data.status === 200) {
                return {
                    "userList": data.data.data.userList
                }
            }
        })
    }
}