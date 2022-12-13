<template>
  <div class="trade">
    <div class="tab-bar">
        <icon-button 
            class="tab-bar-option"
            v-bind:class="{ 'tab-bar-option-selected': tab == 'create'}"
            v-on:click.prevent="tab='create'"
            :buttonText="'Create'"
            :iconName="'add_circle'"
        />
        <icon-button 
            class="tab-bar-option"
            v-bind:class="{ 'tab-bar-option-selected': tab == 'pending'}"
            v-on:click.prevent="tab='pending'"
            :buttonText="'Pending'"
            :iconName="'pending'"
        />
        <icon-button 
            class="tab-bar-option"
            v-bind:class="{ 'tab-bar-option-selected': tab == 'history'}"
            v-on:click.prevent="tab='history'"
            :buttonText="'History'"
            :iconName="'history'"
        />
    </div>
    <div class="tab-content" v-if="tab == 'create'">
        <create-trade />
    </div>
    <div class="tab-content" v-if="tab == 'pending'">
        <pending-trades />
    </div>
    <div class="tab-content" v-if="tab == 'history'">
        <trade-history />
    </div>
  </div>
</template>

<script>
import CreateTrade from '../components/Trades/CreateTrade.vue'
import IconButton from '../components/IconButton.vue';
import PendingTrades from '../components/Trades/PendingTrades.vue'
import TradeHistory from '../components/Trades/TradeHistory.vue';

export default {
    components: {CreateTrade, IconButton, PendingTrades, TradeHistory},
    name: 'trade',
    data() {
        return {
            tab: 'create'
        }

    },
    methods: {
    },
    created() {
    }
}
</script>

<style scoped>
.tab-content {
    width: 100%;
    max-height: calc(100% - 35px);
    border-radius: 10px;
    box-sizing: border-box;
    padding: 5px;
}


.tab-bar {
    border-top: solid 2px var(--medium-accent);
    position: absolute;
    top: 210px;
    left: 10px;
    width: 185px;
    height: 35px;
    display: flex;
    flex-direction: column;
    background-color: transparent;
}

.tab-bar-option {
    display: flex;
    align-items: center;
    justify-content: flex-start;
    box-sizing: border-box;
    border-radius: 5px;
    width: 100%;
    height: 35px;
    background-color: transparent;
    cursor: pointer;
    padding-left: 15px;
    animation: fade-in 0.1s linear;
    transition: all 0.1s ease;
}
.tab-bar-option:hover {
    background-color: var(--light-accent);
    color: var(--white);
}

.tab-bar-option > i {
    margin-left: 0px;
}

.tab-bar-option-selected {
    background-color: var(--medium-accent);
    color: var(--white);
}

.tab-bar-option-selected:hover {
    background-color: var(--medium-accent);
}



</style>