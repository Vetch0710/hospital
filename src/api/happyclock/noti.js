import request from '@/utils/request'

// 查询需要提醒的PT列表
export function listInfo(query) {
  return request({
    // url: '/happyclock/info/list',
    url: '/happyclock/info/listRemind',
    method: 'get',
    params: query
  })
}

// 更新确认状态
export function setSureState(data) {
  return request({
    url: '/happyclock/info/',
    method: 'put',
    data: data
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