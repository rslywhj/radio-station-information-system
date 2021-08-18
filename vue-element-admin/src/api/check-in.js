import request from '@/utils/request'

export function check_In(data) {
  return request({
    url: '/check/in',
    method: 'post',
    data
  })
}
export function selectPersonCheck(data) {
  return request({
    url: '/check/selectPersonCheck',
    method: 'post',
    data
  })
}

export function selectGroup(data) {
  return request({
    url: '/check/selectGroup',
    method: 'post',
    data
  })
}
export function selectAllGroup(data) {
  return request({
    url: '/check/selectAllGroup',
    method: 'post',
    data
  })
}
