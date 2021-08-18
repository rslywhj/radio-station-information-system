import request from '@/utils/request'
// 查询日程管理 - 日程列表
export function listFdjjr() {
  return request({
    url: 'jjrgl/schedule/fdjjrList',
    method: 'get',
  })
}


// 新增日程管理 - 日程
export function addFdjjr(data) {
  return request({
    url: '/jjrgl/schedule/add',
    method: 'post',
    data: data
  })
}

// 修改日程管理 - 日程
export function updateFdjjr(data) {
  return request({
    url: '/jjrgl/schedule/update',
    method: 'post',
    data: data
  })
}

// 删除日程管理 - 日程
export function delFdjjr(id) {
  return request({
    url: '/jjrgl/schedule/delete/' + id,
    method: 'post',
  })
}
// 选择编辑
export function selectEdit(data) {
  return request({
    url: '/member/selectEdit',
    method: 'post',
    data
  })
}
// 选择播音
export function selectBroadcast(data) {
  return request({
    url: '/member/selectBroadcast',
    method: 'post',
    data
  })
}
// 选择稿件
export function selectArticle(data) {
  return request({
    url: '/article/selectAll',
    method: 'post',
    data
  })
}
// 修改日程管理 - 栏目总编（安排定稿）
export function update2(data) {
  return request({
    url: '/jjrgl/schedule/update2',
    method: 'post',
    data: data
  })
}
// 新增日程管理 - 栏目总编（安排写稿）
export function editArrange(data) {
  return request({
    url: '/editArrange/insert',
    method: 'post',
    data: data
  })
}
// 查询日程管理 - 栏目总编（安排写稿）
export function selectArrange(data) {
  return request({
    url: '/editArrange/select',
    method: 'post',
    data: data
  })
}
// 删除日程管理 - 栏目总编（安排写稿）
export function deleteArrange(id) {
  return request({
    url: '/editArrange/delete/'+id,
    method: 'post',
  })
}


export function selectWXEdit(data) {
  return request({
    url: '/editArrange/selectWXEdit',
    method: 'post',
    data
  })
}
//

export function selectBYEdit(data) {
  return request({
    url: '/tutor/selectBYEdit',
    method: 'post',
    data
  })
}
