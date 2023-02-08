import Vue from 'vue'
import Router from 'vue-router'
import Login from "@/components/Login"
import Register from "@/components/Register";
import Index from "../components";
import Detail from "../components/Detail";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: Login,
      component: Login
    }, {
      path: '/register',
      name: Register,
      component: Register
    }, {
      path: '/index',
      name: Index,
      component: Index
    }, {
      path: '/detail/:id',
      name: Detail,
      component: Detail
    }
  ]
})
