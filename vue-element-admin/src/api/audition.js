import request from '@/utils/request'

export function Audition1(data) {
  return request({
    url: '/audition/voice', // 假地址 自行替换
    method: 'post',
    data
  })
}
