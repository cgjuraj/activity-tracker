<template>
  <div class="main-mx-auto">
    <!-- {{books}}
    {{activity}} -->
    <a
      id="showForm1"
      href="#"
      v-if="showForm1 === false"
      v-on:click.prevent="showForm1 = true"
    >
      Add Reading Activity
    </a>

    <form
      id="reading-form"
      v-on:submit.prevent="addNewReadingActivity"
      v-if="showForm1 === true"
    >
      <div class="form-element">
        <label for="title">
Book Title: </label>
        <select id="title" name="userBooks" v-model="activity.title">
          <option  v-for="book in books" v-bind:key="book.id">{{ book.title }}</option>
          </select>
      </div>
      <div class="form-element">
        <label for="reader">
Activity for: </label>
        <select id="reader" name="reader" required v-model="activity.user_id">
          <option v-for="member in familyList" v-bind:key="member.family_id" :value="member.user_id">{{member.username}}  </option>
          </select>
      </div>
      <div class="form-element">
        <label for="time_read">Time Read: </label>
        <input id="time_read" type="text" v-model="activity.time_read" />
      </div>
      <div class="form-element">
        <label for="book_format">Book Format: </label>
        <input id="book_format" type="text" v-model="activity.book_format" />
      </div>
      <div class="form-element">
        <label for="notes">Notes: </label>
        <input id="notes" type="text" v-model="activity.notes" />
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
  name: "add-reading",
  props: ["user"],
  data() {
    return {
      showForm1: false,
      activity: {
        title: "",
        user_id: "",
        time_read: "",
        book_format: "",
        notes: "",
      },
      familyList: [],
      books: [],
      };
  },
  created() {
    this.getBooksByUserId();
    this.getFamilyList();


    },
  methods: {
    getFamilyList() {
      familyService.displayFamily().then((response) =>{
        this.familyList = response.data;
        })
    },
    getBooksByUserId() {
      familyService.getBookByUser().then((response) =>{
        this.books = response.data;
    })
    },
    resetForm() {
      this.showForm1 = false;
      this.activity = {
        title: "",
        time_read: "",
        book_format: "",
        notes: "",
      };
    },
    addNewReadingActivity() {
      familyService.addReadingActivity(this.activity).then((response) => {
        if (response.status == 200) {
          alert("Success");
          this.activity = response.data;
          this.$store.commit("ADD_ACTIVITY", this.activity);
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
#showForm1 {
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