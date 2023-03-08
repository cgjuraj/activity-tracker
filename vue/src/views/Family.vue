<template>
  <div class="main-mx-auto">
      <!-- <h1>Welcome {{$store.state.user.username}}</h1> -->
      
      <h1 class="route">
      <router-link id="removeformat" v-bind:to="{ name: 'register' }">
              Add your child
            </router-link>
            <manage-family/>
            </h1>
  </div>
</template>

<script>
import ManageFamily from '../components/ManageFamily.vue'

export default {
components:{ ManageFamily },

}
</script>

<style>
.main-mx-auto{
  background-color: #D8F3DC;
}

.route{
  text-decoration: none;
}
#removeformat {
  text-decoration: none;
  color: black;
  font-size: 20px;
}
</style>