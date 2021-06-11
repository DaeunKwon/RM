import state from "./state";

export default {
    getCurrentUser(state) {
        return state.currentUser;
    },
    getProjectINInfo(state) {
        state => state.userINProject;
    },
    getProjectInfo(state) {
        state => state.userProject;
    }
}