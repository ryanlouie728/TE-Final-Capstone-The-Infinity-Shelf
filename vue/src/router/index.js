import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Collections from '../views/UserProfile.vue'
import CollectionDetails from '../views/CollectionDetails.vue'
import PublicCollections from '../views/PublicCollections.vue'
import ComicDetails from '../views/ComicDetails.vue'
import Trade from '../views/Trade.vue'


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
        requiresAuth: false
      }
    },
    {
      path: '/trade',
      name: 'trade',
      component: Trade,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/collections',
      name: 'collections',
      component: PublicCollections,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/collections/:id',
      name: 'collection-details',
      component: CollectionDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/comics/:id',
      name: 'comic-details',
      component: ComicDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/:username',
      name: 'user-profile',
      component: Collections,
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
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next({name: "login"});
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
