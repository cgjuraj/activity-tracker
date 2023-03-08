<template>
  
  <div class="main mx-auto">
<h2>Your books are: </h2>
  <book-display
    v-for="book in books"
    v-bind:key="book.title"
    v-bind:book="book"
  />

  </div>
</template>

<script>
import familyReadingService from '../services/FamilyReadingService'
import BookDisplay from './BookDisplay.vue';
export default {
  components: { BookDisplay },
name: 'book-list', 
// props: ["book"],
data(){
    return{
        books: [
          
        ],
    };
},
created(){
    familyReadingService.getBooks().then((response) =>{
        this.books = response.data;
        console.log(this.books);
    })
}

}
</script>

<style>

</style>