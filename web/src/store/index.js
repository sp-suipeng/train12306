import { createStore } from 'vuex'
// 全局变量  不支持刷新，一重新加载就没了
const MEMBER = "MEMBER"
export default createStore({
  state: {
    member:window.SessionStorage.get(MEMBER) || {}
  },
  getters: {
  },
  mutations: {
    setMember(state, _member) {
      state.member = _member
      // 在浏览器打开情况下ok
      window.SessionStorage.set(MEMBER, _member);
    }
  },
  actions: {
  },
  modules: {
  }
})
