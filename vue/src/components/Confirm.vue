<template>
  <div class="confirm">
    <p>{{message}}</p>
    <div class="confirm-buttons">
        <button v-on:click.prevent="confirm()" class="confirm-button">Confirm</button>
        <button v-on:click.prevent="$emit('cancel')" class="confirm-button">Cancel</button>
    </div>
  </div>
</template>

<script>
export default {
    name: 'confirm',
    props: ['message', 'function', 'arguments'],
    methods: {
        confirm() {
            this.function(...this.arguments);
        }
    }
}
</script>

<style>
.confirm {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    font-size: 1.5rem;
    width: 300px;
    height: fit-content;
    border-radius: 10px;
    border: solid 2px var(--dark-accent);
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: var(--light-accent);
    position: fixed;
}

.confirm-buttons {
    margin-bottom: 10px;
    display: flex;
    width: 100%;
    justify-content: center;
    align-items: center;
}

.confirm-button:first-child {
    margin-right: 2px;
}

.confirm-button {
    border: none;
    border-radius: 3px;
    height: 20px;
    width: 30%;
    background-color: var(--medium-accent);
    color: var(--white);
    font-weight: bolder;
    cursor: pointer;
}

.confirm-button:hover {
    background-color: var(--dark-accent);
}

</style>