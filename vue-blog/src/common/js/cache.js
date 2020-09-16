import storage from 'good-storage'

const USER = '__user__'
const TOKEN = '__token__'
const AUTOLOGIN = '__auto__'

export function saveUser (user) {
  let temp = storage.get(USER, [])
  if (!temp.includes(user)) temp.push(user)
  storage.set(USER, temp)
}

export function loadUser () {
  return storage.get(USER, [])
}

export function saveToken (token) {
  storage.set(TOKEN, token)
}

export function loadToken () {
  if (!storage.has(TOKEN)) return ''
  return storage.get(TOKEN, String)
}

export function saveAuto (auto) {
  storage.set(AUTOLOGIN, auto)
}

export function loadAuto () {
  if (!storage.has(AUTOLOGIN)) return false
  return storage.get(AUTOLOGIN, Boolean)
}
