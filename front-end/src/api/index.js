import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8090/',
});

/**
 * 회원가입
 * @param {*} params
 */
function join(params) {
    return instance.get('/user/join', params)
}

/**
 * 로그인
 * @param {*} params
 */
function login(params) {
    return instance.get('/user/login', params)
}

/**
 * 프로젝트 목록 조회
 * @param {*} params
 */
function getProjectListAPI(params) {
    return instance.get('/project/list', params)
}

/**
 * 프로젝트 상세 조회
 * @param {*} params
 */
function getProjectDetailAPI(params) {
    return instance.get('/project/detail', params)
}

/**
 * 프로젝트 작성
 * @param {*} params
 */
function insertProjectAPI(params) {
    return instance.get('/project/insert', params)
}

/**
 * 프로젝트 수정
 * @param {*} params
 */
function updateProjectAPI(params) {
    return instance.get('/project/update', params)
}

/**
 * 프로젝트 삭제
 * @param {*} params
 */
function deleteProjectAPI(params) {
    return instance.get('/project/delete', params)
}

/**
 * 업무 일지 목록 조회
 * @param {*} params
 */
function getReportListAPI(params) {
    return instance.get('/report/list', params)
}

/**
 * 업무 일지 상세 조회
 * @param {*} params
 */
function getReportDetailAPI(params) {
    return instance.get('/report/detail', params)
}

/**
 * 업무 일지 작성
 * @param {*} params
 */
function insertReportAPI(params) {
    return instance.get('/report/insert', params)
}

/**
 * 업무 일지 수정
 * @param {*} params
 */
function updateReportAPI(params) {
    return instance.get('/report/update', params)
}

/**
 * 업무 일지 삭제
 * @param {*} params
 */
function deleteReportAPI(params) {
    return instance.get('/report/delete', params)
}


export {
    join,
    login,
    getProjectListAPI,
    getProjectDetailAPI,
    insertProjectAPI,
    updateProjectAPI,
    deleteProjectAPI,
    getReportListAPI,
    getReportDetailAPI,
    insertReportAPI,
    updateReportAPI,
    deleteReportAPI
};