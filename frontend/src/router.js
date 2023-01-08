import Vue from 'vue'
import Router from 'vue-router'
import auth from '@/auth'
import Home from '@/views/Home.vue'
import Login from '@/views/Login.vue'
import Logout from '@/views/Logout.vue'
import Register from '@/views/Register.vue'
import Survey from '@/views/Survey.vue'
import Users from '@/views/admin/Users.vue'
import Unauthorized from '@/views/Unauthorized.vue'
import SurveyVersionHistory from '@/views/SurveyVersionHistory.vue'

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
      redirect: '/surveys'
    },
    {
      path: '/surveys',
      name: 'surveys',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/surveys/:surveyId', // : identifies as a parameter in the path
      name: 'survey',
      component: Survey,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/surveys/:surveyId/versionHistory',
      name: 'survey-version-history',
      component: SurveyVersionHistory,
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
        // we want this to be true, and only admin can register new users...
        // TODO: ^^^^^^
        // requiresAdminAuth: true
      }
    },
    {
      path: "/admin/users",
      name: "users",
      component: Users,
      meta: {
        requiresAuth: true,
        requiresAdminAuth: true
      }
    },
    {
      path: "/unauthorized",
      name: "unauthorized",
      component: Unauthorized,
      meta: {
        requiresAuth: false,
        requiresAdminAuth: false
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  const user = auth.getUser();

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && !user) {
    console.log('not a user; redirecting to login');
    next({ name: 'login' });
  } else {

    // Handle Admin Access
    console.log
    if (to.matched.some(x => x.meta.requiresAdminAuth)) {
      if (user.rol.toLowerCase().includes('admin')) {
        console.log('accessible to admin user; going to destination');
        next()
      } else {
        console.log('unauthorized for regular user; going to unauthorized page');
        next({ name: 'unauthorized' })
      }
    } else { // If no admin access required
      // Else let them go to their next destination
      console.log('accessible to regular user; going to destination');
      next();
    }
  }
});

export default router;
