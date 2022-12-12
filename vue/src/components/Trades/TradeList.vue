<template>
  <div class="trade-list">
    <div v-for="trade in trades" v-bind:key="trade.tradeId">
        <trade-card 
            v-bind:trade="trade"
            @refresh="$emit('refresh')"
            v-bind:showButtons="showButtons"
        />
    </div>
  </div>
</template>

<script>
import TradeCard from './TradeCard.vue'




export default {
  components: {TradeCard  },
    name: 'trade-list',
    props: ['trades', 'showButtons'],
    data() {
        return {
            showComics: false,
        }
    },
    methods: {

    },
    created() {
        
    }
}
</script>

<style>

</style>