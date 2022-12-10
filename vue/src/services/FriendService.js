import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
});

export default {
    getFriendPageByUserId(userId) {
        return http.get(`/friends/${userId}`)
    }
}