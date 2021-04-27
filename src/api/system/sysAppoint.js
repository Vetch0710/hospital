import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";



// 获取医生预约安排
export function getDoctor() {
  return request({
    url: '/sys/appoint/doctor' ,
    method: 'get'
  })
}



// 获取患者预约
export function getPatient() {
  return request({
    url: '/sys/appoint/patient',
    method: 'get'
  })
}

// 获取患者预约
export function deleteAppoint(row) {
  return request({
    url: '/sys/appoint',
    method: 'delete',
    data:row
  })
}

// 保存预约
export function downloadAppoint(row) {
  return request({
    url: '/sys/appoint/download',
    method: 'post',
    data:row
  })
}
