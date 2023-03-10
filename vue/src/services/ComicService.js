import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
});

export default {

    list(){
        return http.get('/comics/simple');
    },
    listSimpleByCollectionId(collectionId) {
        return http.get(`/comics/simple/${collectionId}`);
    },
    addComic(comic) {
        return http.post('/comics', comic);
    },
    listSimpleByTitle(title) {
        return http.get(`/comics?title=${title}`);
    },
    getComicById(id) {
        return http.get(`/comics/${id}`);
    },
    getComicsByUserIdNotInTrade(userId) {
        return http.get(`/comics/user/${userId}`)
    }
}