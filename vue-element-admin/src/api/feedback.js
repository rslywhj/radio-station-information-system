import request from '@/utils/request'

export function insertFeedback(a) {
  return request({
    url: 'feedback/insertFeedback',
    method: 'post',
    params: { 'content': a }
  })
}

export function selectFeedback(data) {
  return request({
    url: 'feedback/selectByStatus',
    method: 'post',
    data
  })
}

export function updateFeedback(data) {
  return request({
    url: '/feedback/updateStatus',
    method: 'post',
    params: { 'id': data }
  })
}

export function selectAll(data) {
  return request({
    url: 'feedback/selectAll',
    method: 'post',
    data
  })
}
