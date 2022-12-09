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
    },
    addComicToCollection(collectionId, comicId) {
        return http.post(`/collections/${collectionId}/${comicId}`)
    },
    removeComicFromCollection(collectionId, comicId) {
        return http.delete(`/collections/${collectionId}/${comicId}`)
    },
    getCollectionById(collectionId) {
        return http.get(`/collections/${collectionId}`)
    },
    updateCollection(collection) {
        return http.put('/collections', collection);
    },
    deleteCollection(collectionId){
        return http.delete(`collections/${collectionId}`);
    }
}