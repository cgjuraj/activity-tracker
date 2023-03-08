<template>
  <div class="home">
    <div class="div-one">
      <div class="container-one">
        <img src="../assets//read-logo-3-01.png" alt="Read" />
      </div>

      <div class="container-two">
        <div class="welcome">
          <h1>Welcome, {{ $store.state.user.username }}!</h1>
          <img id="books" src="../assets/book.webp" alt="" />
        </div>
      </div>
      <div class="container-three">
        <!-- <div class="prompt">What would you like to do?</div> -->

        
      </div>
    </div>
    <div class="div-two">
      <h1>What would you like to do?</h1>
      <div class="infographic-container">
        <div class="infographic">
          <router-link v-bind:to="{ name: 'manageBooks' }">
            Your Books
          </router-link>
        </div>
        <div class="infographic">
          <router-link v-bind:to="{ name: 'addBook' }">
            Manage Books
          </router-link>
        </div>
      </div>
      <div class="infographic-container">
        <div class="infographic">
          <router-link v-bind:to="{ name: 'manageFamily' }">
            Manage Family
          </router-link>
        </div>
        <div class="infographic">
          <router-link v-bind:to="{ name: 'addPrizes' }">
            Manage Prizes
          </router-link>
        </div>
      </div>
    </div>

    <!-- <div class="div-three">
      <div class="spouse ">
        <h3>Family Members</h3>
        <img class="circular-image" src="../assets/bookworm.jpg" alt="" />
      </div>
      <div class="child ">
        <img class="circular-image" src="../assets/baby.jpg" alt="" />
      </div>
      <div class="child ">
        <img class="circular-image" src="../assets/funnyboy.jpg" alt="" />
      </div>
    </div> -->
  </div>
</template>

<script>
//import Register from './Register.vue';
export default {
  //components: { Register },
  name: "home",
};
</script>

<style scoped>
#books {
  margin-left: 3em;
}

.home {
  height: 100%;
 
  display: flex;
  flex-basis: 25%;
  flex-direction: row;
  justify-content: space-between;
  box-shadow: inset 0 0 10px rgb(192, 189, 189);
  border-radius: 5px;
}

.home:first-child{
   background-color: rgb(241, 238, 238);
}

.circular-image {
  border: solid 1px  #ccc;
  box-shadow: inset 0 0 10px rgb(192, 189, 189);
  margin: 20px 0;
  display: block;
}
.div-one {
  height: 100vh;
  display: flex;
  align-content: center;
  flex-direction: column;
  justify-content: top;
  box-shadow: inset 0 0 10px rgb(192, 189, 189);
  padding-top: .5em;
  border-radius: 5px;
  padding: 1em;
}

h1 {
  padding-top: 0;
  margin-left: 0.5em;
  color: #035149;
  font-size: 25px;
}

h3 {
  font-family: "Open Sans", sans-serif;
  font-size: 15px;
  color: #035149;
}

.div-two {
  display: flex;
  flex-direction: column;
  flex-grow: 2;
  flex-wrap: wrap;
  justify-content: space-around;
  border-radius: 5px;
  padding: 3em;
  padding-top: 1em;
}
.infographic-container, .infographic{
   display: flex;
    justify-content: center;
    align-items: center;
}

.infographic {
 
  border: 0px;
  border-radius: 20px;
  box-shadow: inset 0 0 15px rgb(160, 158, 158);
  width: 250px;
  height: 200px;
  background-color: #2ab675;
  color: rgb(241, 238, 238);
  margin: 0 50px;
}
.div-three {
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  padding: 2em;
}
.container-two, .container-three {
  text-align: center;
}

.container-one {
  padding: 0.5em 2em 0em 1.5em;
  margin-top: 5%;
}
.container-three {
  display: flex;
  flex-direction: column;
  padding-top: 2em;
}

.div-three img {
  height: 8rem;
  border-radius: 50%;
}

.welcome img {
  height: 8rem;
  border-radius: 50%;
}

.prompt {
  font-size: 16px;
  font-weight: bold;
  width: 100%;
}

.choice,
.choice a {
  color: rgb(65, 65, 158);
  font-weight: bold;
  list-style: none;
  text-decoration: none;
  margin-top: 10px;
}
a {
  color: white;
  text-decoration: none;
}
</style>
