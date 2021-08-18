import request from '@/utils/request'

export function getRoutes() {
  return request({
    url: '/vue-element-admin/routes',
    method: 'get'
  })
}

export function updateMember(id, data) {
  return request({
    url: `/member/update/${id}`,
    method: 'put',
    data
  })
}


export function delMember(id) {
  return request({
    url: '/member/delete/' + id,
    method: 'post',
  })
}

export function findAll(query) {
  return request({
    url: '/member/list',
    method: 'post',
    params:query
  })
}
