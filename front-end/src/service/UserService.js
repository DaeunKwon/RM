import axios from 'axios';

const API_URL = 'http://localhost:8090/api/user/';

class UserService {
    login(user) {
        console.log(user)
        return axios
            .post('http://localhost:8090/' + 'login', {
                email: user.email,
                password: user.password
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user');
    }

    join(user) {
        return axios.post(API_URL + 'join', {
            name: user.name,
            email: user.email,
            password: user.password
        });
    }

    // update(id, data) {

    //     return http.put(`/users/${id}`, data);

    // }
}

export default new UserService();
