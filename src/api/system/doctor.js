import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";

// 查询用户列表
export function listADoctor(query) {
  let list = request({
    url: '/sys/ADoctor/list',
    method: 'get',
    params: query
  })
  console.log(list)
  return list
}
// 删除用户
export function delADoctor(data) {
  return request({
    url: '/sys/ADoctor/',
    method: 'delete',
    data: data
  })
}

// 修改用户
export function updateADoctorAInfo(data) {
  return request({
    url: '/sys/ADoctor',
    method: 'put',
    data: data
  })
}

// 新增用户
export function addADoctor(data) {
  return request({
    url: '/sys/ADoctor',
    method: 'post',
    data: data
  })
}

/////////////////////////////////////////////////////////////

// 查询用户详细
export function getDoctor() {
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
