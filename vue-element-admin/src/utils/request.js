import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'
import qs from 'qs'

// create an axios instance
// axios创建实例对象
const service = axios.create({
  // 设置baseRUL
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // 允许axios请求带上cookie
  withCredentials: true, // send cookies when cross-domain requests
  /* 设置请求超时时间*/
  timeout: 30000, // request timeout
  // 请求转换成 form-data请求数据格式
  // 请求转换成 form-data请求数据格式
  // `transformRequest` 允许在向服务器发送前，修改请求数据
  // 只能用在 'PUT', 'POST' 和 'PATCH' 这几个请求方法
  // transformRequest: data => qs.stringify(data)
  transformRequest: (data, headers) => {
    console.log('headers' + JSON.stringify(headers['Content-Type']))
    if (headers['Content-Type']) {
      if ((headers['Content-Type']).indexOf('multipart/form-data') > -1) { // 上传文件处理
        return data
      } else if ((headers['Content-Type']).indexOf('application/json') > -1) { // 请求json数据格式处理
        return JSON.stringify(data)
      } else { // 其他都是按照 form-data 数据格式处理
        return qs.stringify(data)
      }
    } else { // headers['Content-Type'] 不存在时，按照 form-data 数据格式处理
      return qs.stringify(data)
    }
  }
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent

    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      // config.headers['X-Token'] = getToken()
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    console.log('response=' + JSON.stringify(response))
    console.log('response.data=' + JSON.stringify(response.data))
    const res = response.data

    // if the custom code is not 20000, it is judged as an error.
    if (res.state > 0) {
      Message({
        message: res.msg || 'Error',
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(new Error(res.msg || 'Error'))
    } else if (res.state === -1) { // 登录过期
      // to re-login
      MessageBox.confirm('登录已经过期，请重新登录', '登录过期', {
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        store.dispatch('user/resetToken').then(() => {
          location.reload()
        })
      })
      return Promise.reject(new Error(res.msg || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
