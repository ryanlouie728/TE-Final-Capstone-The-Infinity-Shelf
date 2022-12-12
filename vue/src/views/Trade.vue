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

    </div>
  </div>
</template>

<script>
import CreateTrade from '../components/Trades/CreateTrade.vue'
import IconButton from '../components/IconButton.vue';
import PendingTrades from '../components/Trades/PendingTrades.vue'

export default {
    components: {CreateTrade, IconButton, PendingTrades},
    name: 'trade',
    data() {
        return {
            tab: 'create'
        }

    },
    methods: {
    },
    created() {
    },
    mounted() {
    }
}
</script>

<style>
.tab-content {
    width: 100%;
    height: 99%;
    max-height: calc(100% - 35px);
    /* background-color: var(--light-accent); */
    border-radius: 10px;
    box-sizing: border-box;
    padding: 5px;
    overflow-y: auto;
}


.tab-bar {
    width: 100%;
    height: 35px;
    display: flex;
    background-color: transparent;
}

.tab-bar-option {
    display: flex;
    align-items: center;
    justify-content: flex-start;
    margin-right: 5px;
    margin-bottom: 5px;
    box-sizing: border-box;
    border-radius: 5px;
    width: 125px;
    height: 30px;
    background-color: var(--light-accent);
    cursor: pointer;
}
.tab-bar-option:hover {
    background-color: var(--dark-accent);
    color: var(--white);
}

.tab-bar-option > i {
    margin-left: 0px;
}

.tab-bar-option-selected {
    background-color: var(--dark-accent);
    color: var(--white);
}


</style>