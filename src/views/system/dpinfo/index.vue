<template>
  <div class="app-container">

    <el-row :gutter="20">
      <!--用户数据-->
      <el-col :xs="24">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="90px">
          <el-form-item label="医生姓名">
            <el-input
              v-model="queryParams.realName"
              placeholder="请输入医生姓名"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

          <el-form-item label="医生编号" prop="doctorId">
            <el-input
              v-model="queryParams.doctorId"
              placeholder="请输入医生编号"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['sys:dpinfo:add']"
            >新增
            </el-button>
          </el-col>
        </el-row>

        <el-table v-loading="loading" :data="dpInfo">
          <el-table-column label="医生编号" align="center" prop="doctorId"/>
          <el-table-column label="医生姓名" align="center" prop="realName" :show-overflow-tooltip="true"/>
          <el-table-column label="职称" align="center" prop="position" :show-overflow-tooltip="true"/>
          <el-table-column label="科室" align="center" prop="department" :show-overflow-tooltip="true"/>
          <el-table-column label="就诊时间" align="center" prop="seeTime" :show-overflow-tooltip="true"/>
          <el-table-column label="最大预约数量" align="center" prop="maxNum" :show-overflow-tooltip="true"/>
          <el-table-column label="剩余预约数" align="center" prop="remanNum" :show-overflow-tooltip="true"/>

          <el-table-column
            label="操作"
            align="center"
            width="180"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['sys:dpinfo:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['sys:dpinfo:remove']"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </el-col>
    </el-row>
        <edit ref="edit" @fetch-data="getList" ></edit>
  </div>
</template>

<script>
  import {
    listADoctor, delADoctor, updateDoctorAInfo
  } from '@/api/system/doctor'
import Edit from './edit'
  export default {
    name: 'dpInfo',
    components: {Edit},
    data() {
      return {
        dpInfo: null,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 遮罩层
        loading: true,
        // 总条数
        total: 0,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          realName:'',
          doctorId:'',
        },
        rule:{
          doctorId: [
            { type: 'number', message: '医生编号必须为数字值'}
            ]
        }

      }
    },
    watch: {},
    created() {
      this.getList()
    },
    methods: {
      /** 查询用户列表 */
      getList() {
        console.log(111111111111)
        this.loading = true
        listADoctor(this.queryParams).then(response => {
            console.log(response)
            this.dpInfo = response.rows
            this.total = response.total
            this.loading = false
          }
        )
      },

      /** 新增按钮操作 */
      handleAdd() {
        this.$refs['edit'].showEdit()
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        // if (row.seeTime){
        //   const seeTime = [...row.seeTime.split('-')]
        //   row.startTime = seeTime[0]
        //   row.endTime = seeTime[1]
        // }
        this.$refs['edit'].showEdit(row)
      },
      //删除数据
      handleDelete(row) {

        this.$confirm('是否确认删除用户编号为"' + row.userId + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function() {
          return delADoctor(row)
        }).then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        }).catch(function() {
        })
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.page = 1
        this.getList()
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.queryParams.pageNum=1;
        this.queryParams.pageSize=10;
        this.queryParams.realName='';
        this.queryParams.doctorId='';
        this.handleQuery()
      }
    }
  }
</script>
