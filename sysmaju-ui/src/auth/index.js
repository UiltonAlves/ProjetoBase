
import { LocalStorage } from 'quasar'
import service from './service'

class Auth {
  constructor () {
    this.Session = LocalStorage
  }

  logout () {
    this.Session.remove('access_token')
    this.Session.remove('user')
  }

  isAuthenticated () {
    return !!this.Session.has('access_token')
  }

  login (params) {
    return new Promise((resolve, reject) => {
      service.attemptLogin(params)
        .then(async (data) => {
          let user = this.storeSession(data)
          resolve(user)
        })
        .catch(error => {
          console.log('AUTH Authentication error: ', error)
          reject(error)
        })
    })
  }

  async loadUser () {
    return new Promise((resolve, reject) => {
      if (!this.Session.has('access_token')) {
        return resolve(null)
      }
      service
        .currentUser()
        .then(resolve)
        .catch(reject)
    })
  }

  getAuthHeader () {
    if (this.Session.has('access_token')) {
      let accessToken = this.getItem('access_token')
      return `Bearer ${accessToken}`
    }
    return null
  }

  getUser () {
    if (this.Session.has('user')) {
      return this.getItem('user')
    }
    return null
  }

  addAuthHeaders () {
    let header = this.getAuthHeader()
    service.addAuthorizationHeader(header)
  }

  getItem (key) {
    return this.Session.get.item(key)
  }

  setItem (key, value) {
    this.Session.set(key, value)
  }

  async storeSession (data) {
    this.setItem('access_token', data.token)
    this.addAuthHeaders()
    let user = await this.loadUser()
    this.setItem('user', user)
    return user
  }
}

export default Auth
