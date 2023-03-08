import axios from 'axios';

export default {

  addBook(book) {
    return axios.post('/addBook', book)
  },
  addReadingActivity(activity) {
    return axios.post('/addActivity', activity)
  },
  getBooks() {
    return axios.get('/books/list')
  },
  displayFamily() {
    return axios.get('/family/list')
  },
  addFamily(family) {
    return axios.post('/addFamily', family)
  },

  displayUsers() {
    return axios.get('/displayUsers')
  },
  addUserToFamily(familyId, userId) {
    console.log('ADD USER TO FAMILY');

    return axios.put(`/family/${familyId}/${userId}`)
  },
  getBookByUser() {
    return axios.get(`/books/userList`)
  },
  addPrizeToFamily(newPrize) {
    return axios.post(`/addFamilyPrize`, newPrize)
  }

 
}
