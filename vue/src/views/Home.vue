<template>
  <div class="home">
    <div id="left-pane">
      <h2>Aggregate Stats</h2>
    </div>
  </div>
</template>

<script>
export default {
  name: "home",
};
</script>

<style scoped>
.home {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
}

#left-pane {
  width: 66%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: flex-start;
}
</style>
