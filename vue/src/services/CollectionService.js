import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
});

export default {
    listByUserId(id) {
        return http.get(`/collections/simple/${id}`)
    },
    listPublic() {
        return http.get(`/collections`)
    },
    createCollection(collection) {
        return http.post('/collections', collection);
    }
}