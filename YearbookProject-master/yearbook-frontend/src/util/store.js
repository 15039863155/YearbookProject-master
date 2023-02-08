const token_key = 'yearbook_token'

const store = {
  // 全局状态
  state: () => {

  },
  actions: {
    // 根据登录获得的 token 信息，存储到 LocalStorage 中
    setToken(token) {
      // 将获得的 token 存储到 localStorage 中
      localStorage.setItem(token_key, token)
    }
  }
}
