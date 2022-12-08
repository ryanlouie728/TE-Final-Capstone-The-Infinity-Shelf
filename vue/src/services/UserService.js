import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
});

export default {
    getProfileById(id) {
        return http.get(`/users/${id}`);
    },
    getByUsername(username) {
        return http.get(`/users?username=${username}`);
    }
}