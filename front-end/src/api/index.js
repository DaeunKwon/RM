import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8090',
});

function joinUser(userData) {
    return instance.post('join', userData);
}

export { joinUser };