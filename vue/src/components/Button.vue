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

<style >
.buttonStyle {
    padding: 5px 10px;
    border-radius: 9px;
    cursor: pointer;
    font-family: "Bebas Neue", sans-serif;
    background-color: var(--dark-accent);
    color: var(--white);
    text-align: center;
    vertical-align: middle;
   
}

.buttonStyle:hover {
    background-color: var(--light-accent);
}
</style>