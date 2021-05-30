import state from "./state";

export default {
    isLoggedIn: state => state.loginSuccess,
    hasLoginErrored: state => state.loginError,
    getUserName: state => state.userName,
    getUserPass: state => state.userPass,
    getCurrentUser: state => state.currentUser,
}