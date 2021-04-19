import request from '@/utils/request'
import { praseStrEmpty } from '@/utils/ruoyi'

// 查询用户列表
export function listStaff(query) {
  let list = request({
    url: '/resume/staff/list',
    method: 'get',
    params: query
  })
  console.log(list)
  return list
}

// // 查询用户详细
// export function getStaff(userId) {
//   return request({
//     url: '/system/user/' + praseStrEmpty(userId),
//     method: 'get'
//   })
// }

// 新增用户
export function addStaff(data) {
  return request({
    url: '/resume/staff',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateStaff(data) {
  return request({
    url: '/resume/staff',
    method: 'put',
    data: data
  })
}

// 删除用户
export function delStaff(userId) {
  return request({
    url: '/resume/staff/' + userId,
    method: 'delete'
  })
}

// 上传证件照
export function upload(type) {
  return request({
    url: '/resume/staff/upload/' + type,
    method: 'post'
  })
}

// 上传证件照
export function uploadTemp() {
  return request({
    url: '/resume/staff/upload',
    method: 'post'
  })
}

// 导出用户
export function exportStaff(file, ty) {
  return request({
    url: '/resume/staff/export/'+ty.id+'/'+ty.queryVo,
    method: 'post',
    data:file


  })
}

/*
// 导出用户
export function exportStaff(query) {
  return request({
    url: '/system/user/export',
    method: 'get',
    params: query
  })
}


// 查询用户个人信息
export function getUserProfile() {
  return request({
    url: '/system/user/profile',
    method: 'get'
  })
}

// 修改用户个人信息
export function updateUserProfile(data) {
  return request({
    url: '/system/user/profile',
    method: 'put',
    data: data
  })
}

// 用户密码重置
export function updateUserPwd(oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return request({
    url: '/system/user/profile/updatePwd',
    method: 'put',
    params: data
  })
}

// 用户头像上传
export function uploadAvatar(data) {
  return request({
    url: '/system/user/profile/avatar',
    method: 'post',
    data: data
  })
}

// 下载用户导入模板
export function importTemplate() {
  return request({
    url: '/system/user/importTemplate',
    method: 'get'
  })
}
*/
