// 导航守护
import router from './router';
import {getToken} from "./util/auth";

// 设置不需要进行校验的路由地址
const whitelist = ['/register', '/login']

// 当进行路由跳转时，需要检查是否完成登录认证
router.beforeEach(async (to, from, next) => {
  // 获得当前路由跳转连接地址信息
  let path = to.path
  // 获得存储在 localStorage 中的 Token 信息
  let hasToken = getToken();
  // 判断能够得到该 Token 信息
  if (hasToken) {
    // 获得登录成功后所绑定的 Token 信息
    // 判断此时该用户的路由连接是否为：/login 或者是 /register
    if (path == '/login' || path == '/register') {
      // 说明该用户已经登录，但是需要跳转到登录或者是注册页面，则重定向到首页面
      router.push('/')
      return router
    } else {
      // 说明用户已经登录，而且没有跳转到登录或者是注册，则可以继续进行原来的路由跳转
      next()
    }
  } else {
    // 未获得当前登录后所绑定信息，因此该用户未进行登录
    // 判断是否跳转到登录或者是注册路由地址
    if (path == '/login' || path == '/register') {
      // 未登录，路由跳转到登录或者是注册，则可以继续进行
      next()
    } else {
      // 未登录跳转到需要认证的路由，则定向到登录路由
      router.push('/login')
      return router
    }
  }
})
