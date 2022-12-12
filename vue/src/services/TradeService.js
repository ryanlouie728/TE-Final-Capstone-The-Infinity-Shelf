import axios from "axios";
import store from '../store/index.js'
store;

const http = axios.create({
    baseURL: "http://localhost:9000"
});

export default {
    createTrade(trade) {
        return http.post(`/trades/create`, trade)
    }
}