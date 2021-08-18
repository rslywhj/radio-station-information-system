import request from '@/utils/request'

export function article(data) {
  return request({
    url: '/article/selectAll',
    method: 'post',
    data
  })
}

export function seeArticles(data) {
  return request({
    url: '/article/selectByUsername',
    method: 'post',
    data
  })
}

export function allArticles(data) {
  return request({
    url: '/article/selectAllByGroup',
    method: 'post',
    data
  })
}

export function updateStatusZ(data) {
  return request({
    url: '/article/updateStatusZ',
    method: 'post',
    params: { 'title': data }
  })
}

export function noUpdateStatusZ(status, title) {
  return request({
    url: '/article/noUpdateStatusZ',
    method: 'post',
    params: {
      'status': status,
      'title': title
    }
  })
}

export function submitArticle(data) {
  return request({
    url: '/article/insert',
    method: 'post',
    data:data,
    headers: { // 上传文件数据格式
      'Content-Type': 'multipart/form-data;boundary = ' + new Date().getTime() // 文件上传时的格式
    },
    /*contentType: false,
    processData: false,*/
    dataType: "json",
  })
}

export function findArticle(dateTime) {
  return request({
    url: '/article/select',
    method: 'post',
    params: {
      'date': dateTime
    }
  })
}

export function selectAllF(data) {
  return request({
    url: '/article/selectAllF',
    method: 'post',
    data
  })
}
export function updateStatusT(data) {
  return request({
    url: '/article/updateStatusT',
    method: 'post',
    params: { 'title': data }
  })
}

export function selectAllT(data) {
  return request({
    url: '/article/selectAllT',
    method: 'post',
    params: { 'title': data }
  })
}
export function noUpdateStatusT(status, title) {
  return request({
    url: '/article/noUpdateStatusT',
    method: 'post',
    params: {
      'status': status,
      'title': title
    }
  })
}

export function selectByEdit(data) {
  return request({
    url: '/article/selectByEdit',
    method: 'post',
    data
  })
}
