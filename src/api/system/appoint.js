import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";



// 获取医生可预约时间
export function getActiveTime(doctorId,selectDay) {
  return request({
    url: '/web/appoint/appointTime/'+doctorId+'/'+selectDay ,
    method: 'get'
  })
}



// 预约
export function addAppoint(data) {
  return request({
    url: '/web/appoint',
    method: 'post',
    data: data
  })
}
////////////////////////////////////////////
// 获取用户状态
export function getStatus() {
  return request({
    url: '/web/patient/status' ,
    method: 'get'
  })
}

