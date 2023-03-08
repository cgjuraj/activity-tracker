import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    books:[],
    families: [],
    familyMembers:[],
    familyId : 0,
    activities: [],
    prizes: []
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_BOOKS(state, data){
      state.books = data;
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    ADD_BOOK(state, book){
      state.books.push(book);
    },
    ADD_ACTIVITY(state, activity){
      state.activities.push(activity);
    },
    ADD_FAMILY(state, family){
      state.families.push(family);
    },
    ADD_FAMILY_MEMBER(state, user){
      state.familyMembers.push(user);
    },
    SET_FAMILY_ID(state, id){
      state.familyId =id;
    },
    ADD_PRIZE(state, prize){
      state.prizes.push(prize);
    }
  }
})
