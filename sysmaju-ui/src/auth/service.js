
import axios from 'axios'
import _ from 'lodash'

const API_URL = process.env.API_URL

export default {

  async currentUser () {
    try {
      let response = await axios.get(`${API_URL}/login`)
      return response.data
    } catch (error) {
      return _.get(error, 'response.data')
    }
  },

  async attemptLogin (credentials) {
    try {
      let response = await axios.post(`${API_URL}/login`, credentials)
      return new Promise(resolve => resolve(response.data || {}))
    } catch (error) {
      return new Promise((resolve, reject) => reject(error))
    }
  },

  addAuthorizationHeader (header) {
    axios.defaults.headers.common['Authorization'] = header
  }
}
