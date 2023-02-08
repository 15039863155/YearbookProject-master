// 用于设置保存和设定值 Token 信息的函数
const TOKEN_KEY = 'yearbook_token'

// 设置 Token 信息
export function setToken(token) {
  // 将所获得的 Token 信息存储到 LocalStore 中的对应位置
  localStorage.setItem(TOKEN_KEY, token)
}

// 获得 Token 信息
export function getToken() {
  // 通过 localStorage 获得已经设置好的 Token 信息
  return localStorage.getItem(TOKEN_KEY);
}
