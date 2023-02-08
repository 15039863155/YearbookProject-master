// 系统所有的异步请求接口
import service from "../util/interceptor";
import {getToken} from "../util/auth";

export function sendSms(cellphone) {
  return service({
    url: '/sms/' + cellphone,
    method: 'get'
  })
}

// 进行通讯录信息注册
export function registerYearbook(data) {
  return service({
    url: '/register',
    method: 'post',
    data
  })
}

export function loginYearbook(data) {
  return service({
    url: "/login",
    method: 'post',
    data
  })
}

export function getInfo() {
  return service({
    url: '/info/' + getToken(),
    method: 'get'
  })
}

export function queryPage(pageNum, pageSize) {
  return service({
    url: '/page/' + pageNum + "/" + pageSize,
    method: 'post'
  })
}

export function queryById(id) {
  return service({
    url: '/id/' + id,
    method: 'get'
  })
}
