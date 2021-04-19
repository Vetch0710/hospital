import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";

// 查询
export function listURP() {
  let list = request({
    url: '/resume/urp/list',
    method: 'get',
  })
  console.log(list);
  return list
}

