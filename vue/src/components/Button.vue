<template>
  <button v-on="$listeners" class="buttonStyle"> {{ buttonText }} </button>
</template>

<script>
export default {
    props: {
        buttonText: {
            type: String,
            default: () => "A Useless Button",
        }
    },
}
</script>

<style scoped>
.buttonStyle {
    padding: 5px 10px;
    border-radius: 9px;
    cursor: pointer;
    font-family: "Bebas Neue", sans-serif;
    background-color: var(--dark-accent);
    color: var(--white);
    cursor: pointer
}

.buttonStyle:hover {
    background-color: var(--light-accent);
}
</style>