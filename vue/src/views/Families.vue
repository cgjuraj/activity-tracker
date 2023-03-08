<template>
  
  <div class="main mx-auto">
    <div class="list">
      <h1>Family List</h1>
      <family-list/>
      </div>
  </div>
</template>

<script>
import FamilyList from '../components/FamilyList.vue'
export default {
components: {FamilyList},
}
</script>

<style scoped>
.list {
  height: 100vh;
  margin: 0 auto;
    width: 1080px;
}
</style>