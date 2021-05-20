import axios from 'axios';
import { AxiosResponse } from 'axios'

const axiosApi = axios.create({
    baseURL: `http://localhost:8090/api`,
    timeout: 1000,
    headers: { 'Content-Type': 'application/json' }
});

/**
 * 게시글 목록 조회
 * @param {*} params
 */
function getReportListAPI(params) {
    return axios.get('/api/report/list', params)
}

function getSecured() {
    return axios.post('/api/user/login')
}

export {
    getReportListAPI,
    getSecured
}

