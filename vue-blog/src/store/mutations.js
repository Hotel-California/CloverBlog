import * as types from './mutation-types'
const mutations = {
  [types.SET_USER] (state, user) {
    state.user = user
  },
  [types.SET_LOGIN] (state, login) {
    state.login = login
  },
  [types.SET_BLOGS] (state, blogs) {
    state.blogs = blogs
  },
  [types.SET_CATEGORY] (state, category) {
    if (state.category === category) state.category = ''
    else state.category = category
  },
  [types.SET_TAGS] (state, tagsOn) {
    state.tagsOn = tagsOn
  }
}

export default mutations
