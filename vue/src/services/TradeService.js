import axios from "axios";
import store from '../store/index.js'
store;

const http = axios.create({
    baseURL: "http://localhost:9000"
});

export default {
    createTrade(trade) {
        return http.post(`/trades/create`, trade)
    },
    getPendingByUserId(userId) {
        return http.get(`/trades/pending/${userId}`)
    },
    acceptTrade(tradeId) {
        return http.put(`/trades/accept/${tradeId}`)
    },
    rejectTrade(tradeId) {
        return http.put(`/trades/reject/${tradeId}`)
    },
    cancelTrade(tradeId) {
        return http.put(`/trades/cancel/${tradeId}`)
    },
    getTradeHistoryByUserId(userId) {
        return http.get(`/trades/history/${userId}`)
    }
}