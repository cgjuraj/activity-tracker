<template>
  
  <div class="main mx-auto">
    <div class="list">
      <h1>Book List</h1>
      <book-list/>
      </div>
  </div>
</template>

<script>
import BookList from '../components/BookList.vue'
export default {
components: {BookList},
}
</script>

<style scoped>
.list {
  height: 100vh;
margin: 0 auto;
    width: 1080px;
}
</style>