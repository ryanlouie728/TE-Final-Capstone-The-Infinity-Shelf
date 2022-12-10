import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
});

export default {
    getFriendPageByUserId(userId) {
        return http.get(`/friends/${userId}`)
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