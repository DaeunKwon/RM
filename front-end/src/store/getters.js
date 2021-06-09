import state from "./state";

export default {
    getCurrentUser(state) {
        return state.currentUser;
    },
    getUserProjectInfo(state) {
        return state.userProjectInfo;
    }
}