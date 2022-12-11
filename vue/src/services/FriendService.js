import axios from "axios";
import store from '../store/index.js'
store;

const http = axios.create({
    baseURL: "http://localhost:9000"
});



export default {
    getFriendPageByUserId(userId) {
        return http.get(`/friends/${userId}`)
    },
    createFriendRequest(friendId) {
        return http.post(`/friends/request/${friendId}`)
    },
    acceptRequest(requestId) {
        return http.post(`/friends/accept/${requestId}`)
    },
    rejectRequest(requestId) {
        return http.put(`/friends/reject/${requestId}`)
    },
    cancelRequest(requestId) {
        return http.put(`/friends/cancel/${requestId}`)
    }
}