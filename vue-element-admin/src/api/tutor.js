import request from '@/utils/request'

export function Tutor(data) {
  return request({
    url: '/tutor/list',
    method: 'post',
    data
  })
}

