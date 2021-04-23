import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";



// 查询用户详细
export function getDisableTime() {
  return request({
    url: '/web/doctor/info' ,
    method: 'get'
  })
}



// 修改用户
export function updateDoctor(data) {
  return request({
    url: '/web/doctor',
    method: 'put',
    data: data
  })
}

// 用户头像上传
export function uploadAvatar(data) {
  return request({
    url: '/web/doctor/avatar',
    method: 'post',
    data: data
  })
}
