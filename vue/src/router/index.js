import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Book from '../views/Book.vue'
import Books from '../views/Books.vue'
import Family from '../views/Family.vue'
import AddReadingActivity from '../components/AddReadingActivity.vue'
import Prizes from '../views/Prizes.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/addBook",
      name: "addBook",
      component: Book,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/addActivity",
      name: "addActivity",
      component: AddReadingActivity,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/book/list",
      name: "manageBooks",
      component: Books,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/manageFamily",
      name: "manageFamily",
      component: Family,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/displayUsers",
      name: "displayUsers",
      component: Family,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/familyList/:familyId",
      name: "familyId",
      component: Family,
      meta: {
        requiresAuth: true
      } 
    },
    {
      path: "/addFamilyPrize",
      name: "addPrizes",
      component: Prizes,
      meta: {
        requiresAuth: true
      }
    }

  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
