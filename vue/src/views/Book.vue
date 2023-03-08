<template>
  <div class="main-mx-auto">
    <div class="cont11">
      <h1> Manage {{$store.state.user.username}}'s Books!</h1>
      </div>
      <div class="cont55">
      <div class="cont22">
    <add-book/>
    </div>
    <div class="cont33">
    <add-reading-activity/>
    </div>
    </div>
  </div>
</template>

<script>
import AddBook from '../components/AddBook.vue'
import AddReadingActivity from '../components/AddReadingActivity.vue'
export default {
components: 
{AddBook, AddReadingActivity},
}
</script>


<style scoped>
.main-mx-auto {
  display: flex;
  flex-direction: column;
  align-items: center;
  flex-basis: 25%;
  flex-grow: 3;
  height: 100vh;
  background: #52B788;
}
.cont55{
  display: flex;
}

</style>