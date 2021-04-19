import request from '@/utils/request'

// 查询PT管理-信息维护列表
export function listInfo(query) {
  return request({
    url: '/happyclock/info/statisticalList',
    method: 'get',
    params: query
  })
}

// 查询PT管理-信息维护详细
export function getInfo(patientsId) {
  return request({
    url: '/happyclock/info/' + patientsId,
    method: 'get'
  })
}

// 新增PT管理-信息维护
export function addInfo(data) {
  return request({
    url: '/happyclock/info',
    method: 'post',
    data: data
  })
}

// 修改PT管理-信息维护
export function updateInfo(data) {
  return request({
    url: '/happyclock/info',
    method: 'put',
    data: data
  })
}

// 删除PT管理-信息维护
export function delInfo(patientsId) {
  return request({
    url: '/happyclock/info/' + patientsId,
    method: 'delete'
  })
}

// 导出PT管理-信息维护
export function exportInfo(query) {
  return request({
    url: '/happyclock/info/export',
    method: 'get',
    params: query
  })
}