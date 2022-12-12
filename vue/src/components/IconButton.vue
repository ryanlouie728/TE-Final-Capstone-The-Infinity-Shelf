<template>
    <div v-on="$listeners"  class="icon-button buttonStyle">
        <i class="material-icons">{{iconName}}</i>
        <p>{{buttonText}}</p>
    </div>
</template>

<script>

export default {
    name: 'icon-button',
    props: ['iconName', 'buttonText']
}
</script>

<style>
.icon-button {
    user-select: none;
    display: flex;
    box-sizing: border-box;
    /* background-color: transparent; */
    background-color: var(--main-background);
    color: var(--dark-accent);
    height: 35px;
}

.icon-button:hover {
    background-color: var(--light-accent);
}


.icon-button > i {
    margin: 0px;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 10px;
    margin-left: 5px;
}

.icon-button > p {
    margin: 0px;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1rem;
}
</style>