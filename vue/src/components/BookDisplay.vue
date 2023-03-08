<template>
<div class="main">
  <div class="books">
      <h1> Title: {{ book.title }}</h1>
      <h2> Author: {{ book.author }}</h2>
      <p> ISBN: {{ book.isbn }}</p>
  </div>
  </div>
</template>

<script>
export default {
name: "book-display",
props: ["book"],
}
</script>

<style>

.books {
  background-color: #fafafa;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  border: 1px solid #ccc;
  border-radius: 6px;
  padding: 5px;
  box-sizing: border-box;
  margin: 10px 0;
  color: #bbb;

}
.books h1 {
   color: #2ab675
}
.books h2 {
  color: #333;
}
</style>