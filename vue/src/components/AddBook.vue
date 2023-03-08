<template>
  <div class="main mx-auto">
    <a
      id="showForm"
      href="#"
      v-if="showForm === false"
      v-on:click.prevent="showForm = true"
    >
      Add a Book
    </a>

    <form
      id="book-form"
      v-on:submit.prevent="addNewBook"
      v-if="showForm === true"
    >
      <div class="form-element">
        <label for="title">Title: </label>
        <input id="title" type="text" v-model="newBook.title" />
      </div>
      <div class="form-element">
        <label for="author">Author: </label>
        <input id="author" type="text" v-model="newBook.author" />
      </div>
      <div class="form-element">
        <label for="isbn">ISBN: </label>
        <input id="ISBN" type="text" v-model="newBook.isbn" />
      </div>
      <div id="button-row">
        <input id="buttons" type="submit" value="Save" />
        <input
          id="buttons"
          type="button"
          value="Cancel"
          v-on:click="resetForm"
        />
      </div>
    </form>
  </div>
</template>

<script>
import familyService from "../services/FamilyReadingService";
export default {
  name: "add-book",
  data() {
    return {
      showForm: false,
      newBook: {
        title: "",
        author: "",
        isbn: "",
      },
    };
  },
  created() {},
  methods: {
    resetForm() {
      this.showForm = false;
      this.newBook = {
        title: "",
        author: "",
        isbn: "",
      };
    },
    addNewBook() {
      familyService.addBook(this.newBook).then((response) => {
        if (response.status == 200) {
          alert("Success");
          this.newBook = response.data;
          this.$store.commit("ADD_BOOK", this.newBook);
          this.resetForm();
          //this is where you can call another API call to retrieve brand new list of books
          //route back to home page
        } else {
          alert("unsuccessful");
        }
      });
    },
  },
};
</script>

<style scoped>
.form-element {
  padding-left: 4em;
}
#showForm {
  text-decoration: none;
  padding-left: 2em;
}
#buttons {
  margin-right: 10px;
}
#button-row {
  padding-left: 8em;
}
#title,
#author,
#ISBN {
  margin-left: 20px;
}
</style>