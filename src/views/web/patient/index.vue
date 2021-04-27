<template>
  <div class="app-container">

    <el-row :gutter="20">
      <!--用户数据-->
      <el-col :xs="24" v-if="status==='0'">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="100px">
          <el-form-item label="医生姓名">
            <el-input
              v-model="queryParams.realName"
              placeholder="请输入医生姓名"
              clearable
              size="small"
              :style="{width: '100%'}"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

          <el-form-item label="职称">
            <el-input
              v-model="queryParams.position"
              placeholder="请输入医生职称"
              clearable
              size="small"
              :style="{width: '100%'}"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="科室">
            <el-input
              v-model="queryParams.department"
              placeholder="请输入医生科室"
              clearable
              size="small"
              :style="{width: '100%'}"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="就诊开始时间">
            <el-time-select
              placeholder="起始时间"
              v-model="queryParams.startTime"
              :picker-options="TimeOption"
              :style="{width: '100%'}"
            >
            </el-time-select>
          </el-form-item>
          <el-form-item label="预约数状态">
            <el-select v-model="queryParams.remanNum" placeholder="预约数状态" clearable
                       :style="{width: '100%'}">
              <el-option v-for="(dict, index) in numOptions" :key="index"
                         :label="dict.key"
                         :value="dict.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
        <ul style="width: 80%;height: 80%;margin-left: 10%;">
          <li v-for="(item, index) in dpInfo" :key="index" style="list-style: none;width: 20%;height: 20%;display: inline-block;">

            <a @click="doctorDetailInfo(item)" style="display: inline;">
              <img v-bind:src="baseUrl+item.avatar" style="width: 85%;height: 85%;" class="img-circle"/>
              <div>
                <span class="doctorDInfo">姓名： {{ item.realName }}</span>
                <span class="doctorDInfo">职称： {{ item.position }}</span>
                <span class="doctorDInfo">科室： {{ item.department }}</span>
              </div>


            </a>

          </li>
        </ul>


        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
          style="margin-right: 30%;margin-top: 60px;"
        />
      </el-col>
      <el-col :xs="24" v-else>
        <div>
          由于您多次未遵守预约时间，您的账号已被冻结到{{banTime}}
        </div>
      </el-col>
    </el-row>
    <Info ref="info" @fetch-data="getList"></Info>
  </div>
</template>

<script>
  import {
    listADoctor
  } from '@/api/system/doctor'
  import {
    getStatus
  } from '@/api/system/appoint'
  import Info from './info/index'

  export default {
    name: 'patient',
    components: { Info},
    data() {
      return {
        status:'1',
        banTime:'',
        baseUrl: process.env.VUE_APP_BASE_API,
        numOptions: [{ 'key': '可预约', value: '>0' }, { 'key': '不可预约', value: '=0' }],
        TimeOption: {
          start: '08:30',
          step: '00:30',
          end: '24:00'
        },
        dpInfo: null,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          realName: '',
          position: '',
          department: '',
          remanNum: '',
          startTime: ''
        },
        total: 0,
        title:'',
      }
    },
    watch: {},
    created() {
      getStatus().then(response => {
        this.status=response.status
        this.banTime=response.banTime
        this.getList()
      })

    },
    methods: {

      doctorDetailInfo(value){
        this.$refs['info'].showEdit(value)
/*        this.$router.push({
          path: '/DInfo/doctorDInfo',
          query: {
           info:value
          }
        })*/
      },
      getList() {
        this.loading = true
        listADoctor(this.queryParams).then(response => {
            console.log(response)
            this.dpInfo = response.rows
            this.total = response.total
            this.loading = false
          }
        )
      }, /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.page = 1
        this.getList()
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.queryParams.pageNum = 1
        this.queryParams.pageSize = 10
        this.queryParams.realName = ''
        this.queryParams.position = ''
        this.queryParams.department = ''
        this.queryParams.remanNum = ''
        this.queryParams.startTime = ''
        this.handleQuery()
      }
    }
  }
</script>
<style>
  .doctorDInfo{
    display: block;
    margin: 5px 30px;
  }
</style>
