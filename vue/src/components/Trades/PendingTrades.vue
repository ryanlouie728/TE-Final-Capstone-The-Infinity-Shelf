<template>
    <div class="pending-trades">
        <h2 v-if="requests.length > 0">Requests</h2>
        <trade-list 
            @refresh="getTrades()"
            v-bind:trades="requests"
        />
        <h2 v-if="pending.length > 0">Pending</h2>
        <trade-list 
            @refresh="getTrades()"
            v-bind:trades="pending"
        />
        
    </div>
</template>

<script>
import TradeService from '../../services/TradeService';
import TradeList from './TradeList.vue';


export default {
  components: { TradeList },
    name: 'pending-trades',
    data() {
        return {
            pending: [],
            requests: []
        }
    }, 
    methods: {
        getTrades() {
            TradeService.getPendingByUserId(this.$store.state.user.id)
            .then(response => {
                if (response.status == 200) {
                    this.pending = response.data.pending
                    this.requests = response.data.requests 
                }
            })
        }
    },
    created() {
        this.getTrades();
    }
}
</script>

<style>
.pending-trades {
    text-align: center;
}


</style>