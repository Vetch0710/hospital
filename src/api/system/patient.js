import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";



// 查询用户详细
export function getPatient() {
  return request({
    url: '/web/patient/list' ,
    method: 'get'
  })
}

// 新增用户
export function addPatient(data) {
  return request({
    url: '/web/patient/register',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updatePatient(data) {
  return request({
    url: '/web/patient',
    method: 'put',
    data: data
  })
}




// // 下载用户导入模板
// export function importTemplate() {
//   return request({
//     url: '/system/user/importTemplate',
//     method: 'get'
//   })
// }
