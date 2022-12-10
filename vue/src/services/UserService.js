import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
});

export default {
    getProfileById(id) {
        return http.get(`/users/${id}`);
    },
    getIdByUsername(username) {
        return http.get(`/users/id/${username}`)
    },
    getByUsername(username) {
        return http.get(`/users?username=${username}`);
    }
}