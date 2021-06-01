import router from "../router";

// const setTokenInLocalStorage = (tokenInfo) => {
//     localStorage.setItem("access_token", tokenInfo.access_token);
//     localStorage.setItem("refresh_token", tokenInfo.refresh_token);
// };

// const deleteTokenInLocalStorage = () => {
//     localStorage.removeItem("access_token");
//     localStorage.removeItem("refresh_token")
// };

// const setAccessTokenInHeader = (accessToken) => {
//     axios.defaults.headers.common['Authorization'] = accessToken;
// };

// const deleteAccessTokenInHeader = () => {
//     axios.defaults.headers.common['Authorization'] = null;
// };


export default {
    setUsername(state, username) {
        state.username = username;
    },

    // loginSuccess(state, user) {
    //     state.status.loggedIn = true;
    //     state.user = user;
    // },
    // loginFailure(state) {
    //     state.status.loggedIn = false;
    //     state.user = null;
    // },
    // logout(state) {
    //     state.status.loggedIn = false;
    //     state.user = null;
    // },
    // registerSuccess(state) {
    //     state.status.loggedIn = false;
    // },
    // registerFailure(state) {
    //     state.status.loggedIn = false;
    // },
    setCurrentUser: function (state, payload) {
        state.currentUser = payload
    },

    LOGIN(state, { accessToken }) {
        state.accessToken = accessToken
        localStorage.accessToken = accessToken
    },
    LOGOUT(state) {
        state.accessToken = null
        delete localStorage.accessToken
    },
    // login(state, responseTokenInfo) {
    //     setTokenInLocalStorage(responseTokenInfo);
    //     setAccessTokenInHeader(responseTokenInfo.access_token);
    //     state.access_token = localStorage.getItem(key = "access_token");
    //     state.loadingState = false;
    // }

}