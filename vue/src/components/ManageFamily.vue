<template>
   <div class="main-mx-auto">
     <div class="cont">
       <register />
<div class="cont1"> 
  
    <a
      id="showForm"
      href="#"
      v-if="showForm1 === false"
      v-on:click.prevent="showForm1 = true"
    >
    <h3>
      Add a Family
      </h3>
    </a>
    
    <form
      id="book-form"
      v-on:submit.prevent="addNewFamily"
      v-if="showForm1 === true"
    >
      <div class="form-element">
        <label for="fm">Family Name: </label>
        <input id="fm" type="text" v-model="family.family_name" />
      </div>
      <div id="button-row">
        <input id="buttons" type="submit" value="Save" />
        <input
          id="buttons"
          type="button"
          value="Cancel"
          v-on:click="resetForm1"
        />
      </div>
    </form>
    </div>

   
<div class="cont2">

        <a href="#" v-on:click.prevent="showForm3 = !showForm3"
          ><h3>Add Family Members</h3></a
        >
        <div id="familySearchBar" v-show="showForm3">
          <input
            type="text"
            id="search"
            v-model="searchWord"
            placeholder="enter username"
          />
          <button btn v-on:click.prevent="search">Search</button>
          <button btn v-on:click="resetForm3">Clear</button><br /><br />
          <div class="user_list">
            <li
              id="userlist"
              v-for="(user, index) in filterList"
              v-bind:key="index"
            >
              <div id="userInList">{{ user.username }}</div>
              <div id="userButton">
                <button btn v-on:click="addFamilyMember(user)">
                  Add Family Member
                </button>
              </div>
            </li>
          </div>
        </div>
      
      </div>
      </div>
<div class="cont3">
  <h2>User List</h2>
      <user-display class="user_list"
        v-for="user in userList"
        v-bind:key="user.id"
        v-bind:user="user"
      />
</div>
    </div>
  
</template>

<script>
import familyReadingService from "../services/FamilyReadingService";
import UserDisplay from "./UserDisplay.vue";
export default {
  components: { UserDisplay },
  name: "manage-family",
  data() {
    return {
     
      familyMembers: [],
      user: {
        username: "",
        familyId: this.familyId,
      },
      showForm1: false,
      showForm2: false,
      showForm3: false,
      family: {
        family_name: "",
      },
      family_id: 0,
      userList: [],
      filterList: [],
      searchWord: "",
    };
  },
  created() {
    // familyReadingService.displayFamily().then((response) => {
    //   this.userList = response.data;
    //   console.log("this is where my family is");
    // });

    familyReadingService.displayUsers().then((response) => {
      console.log(response.data);
      this.userList = response.data;
    });
  },
  methods: {
    resetForm1() {
      this.showForm1= false;
      this.family = {
        family_name: "",
      };
    },
    resetForm2() {
      this.showForm2 = false;
      this.family = {
        family_name: "",
      };
    },
    resetForm3() {
      this.showForm3 = false;
      this.searchWord = "";
      this.filterList = [];
    },
    addNewFamily() {
      familyReadingService.addFamily(this.family).then((response) => {
        console.log(this.family)
        if (response.status == 201) {
          alert("Success");
          this.family_id = response.data;
          console.log(this.family_id);
          this.$store.commit("SET_FAMILY_ID", this.family_id);
          this.resetForm2();
          this.$router.push("/manageFamily");
          //this is where you can call another API call to retrieve brand new list of books
          //route back to home page
        } else {
          alert("unsuccessful");
        }
      });
    },
    search() {
      return (this.filterList = this.userList.filter((user) => {
        return user.username
          .toLowerCase()
          .includes(this.searchWord.toLowerCase());
      }));
    },
    clear() {
      return (this.filterList = this.userList);
    },
    addFamilyMember(user) {
     
   
     
      familyReadingService.addUserToFamily(parseInt(this.$store.state.user.family_id), parseInt(user.id));
      this.resetForm3();
    },

  },
};
</script>

<style scoped>
.main-mx-auto{
display: flex;
flex-direction: row;
justify-content:space-between;
}
h1 {
  padding-top: 0em;
}
.cont{
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}
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
a {
  text-decoration:none ;
  color: black;
  font-size: 20px;

}

</style>