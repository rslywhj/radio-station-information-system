import request from "@/utils/request";

export function Add(data) {
  return request({
    url: '/tutor/add',
    method: 'post',
    data
  })
}
