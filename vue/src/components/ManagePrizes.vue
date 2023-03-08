<template>
<div class="main mx-auto">
    <a
      id="showForm"
      href="#"
      v-if="showForm === false"
      v-on:click.prevent="showForm = true"
    >
      Add a Prize
    </a>
    
    <form
      id="book-form"
      v-on:submit.prevent="addPrizeToFamily"
      v-if="showForm === true"
    >
      <div class="form-element">
        <label for="prize_name">Prize: </label>
        <input id="prize_name" type="text" v-model="newPrize.prize_name" />
      </div>
      <div class="form-element">
        <label for="prize_description">Description of Prize: </label>
        <input id="prize_description" type="text" v-model="newPrize.prize_description" />
      </div>
      <div class="form-element">
        <label for="milestone">Minutes to Milestone: </label>
        <input id="milestone" type="text" v-model="newPrize.milestone" />
      </div>
      <div class="form-element">
        <label for="start_date">Start Date: </label>
        <input id="start_date" type="date" v-model="newPrize.start_date" />
      </div>
      <div class="form-element">
        <label for="end_date">End Date: </label>
        <input id="end_date" type="date" v-model="newPrize.end_date" />
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
import FamilyReadingService from '../services/FamilyReadingService';

export default {
    name: "manage-prizes",
    data() {
    return {
      showForm: false,
      newPrize: {
        prize_name: "",
        prize_description: "",
        milestone: "",
        start_date: "",
        end_date: "",
      },
    }
    },
    methods: {
        resetForm() {
            this.showForm = false;
            this.newPrize = {
                prize_name: "",
                prize_description: "",
                milestone: "",
                start_date: "",
                end_date: "",
            }
        },
        addPrizeToFamily() {
            FamilyReadingService.addPrizeToFamily(this.newPrize).then((response) => {
                if(response.status == 201) {
                    alert("Success");
                    this.newPrize = response.data;
                    this.$store.commit("ADD_PRIZE", this.newPrize);
                    this.resetForm();
                }
                else{
                    alert("unsuccessful");
                }
            }) .catch((response) => {
                console.log(response.status);
            });

        },
    }   
      
}
</script>

<style>
#book-form{

  display:block;
  margin: 0 auto;
}
</style>