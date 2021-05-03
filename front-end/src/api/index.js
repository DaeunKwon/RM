import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8090',
});

/**
 * 게시글 목록 조회
 * @param {*} params
 */
function getReportListAPI(params) {
    return instance.get('/api/report/list', params)
}

export {
    getReportListAPI,
}