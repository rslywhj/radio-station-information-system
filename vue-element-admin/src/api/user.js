import request from '@/utils/request'

export function login(data) {
  console.log('===========' + data)
  return request({
    url: '/member/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/member/info',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/member/logout',
    method: 'post'
  })
}

export function getMemberName() {
  return request({
    url: '/member/getMemberName',
    method: 'post'
  })
}

