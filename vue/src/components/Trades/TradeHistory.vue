<template>
    <div class="trade-history">
        <h2 v-if="accepted.length > 0">Accepted</h2>
        <trade-list 
            v-bind:trades="accepted"
        />
        <h2 v-if="rejected.length > 0">Rejected</h2>
        <trade-list 
            v-bind:trades="rejected"
        />
        <h2 v-if="cancelled.length > 0">Cancelled</h2>
        <trade-list 
            v-bind:trades="cancelled"
        />
    </div>
</template>

<script>
import TradeService from '../../services/TradeService'
import TradeList from './TradeList.vue'


export default {
  components: { TradeList },
    name: 'trade-history',
    data() {
        return {
            trades: [],
            accepted: [],
            rejected: [],
            cancelled: []
        }
    },
    methods: {
        getTrades() {
            TradeService.getTradeHistoryByUserId(this.$store.state.user.id)
            .then(response => {
                if (response.status == 200) {
                    this.sortTrades(response.data)
                }
            })
        },
        sortTrades(trades) {
            this.accepted = []
            this.rejected = []
            this.cancelled = []
            for (let trade of trades) {
                if (trade.status == 'accepted') {
                    this.accepted.push(trade)
                } else if (trade.status == 'rejected') {
                    this.rejected.push(trade)
                } else {
                    this.cancelled.push(trade)
                }
            }
        }
    },
    created() {
        this.getTrades();
    }
}
</script>

<style>

</style>