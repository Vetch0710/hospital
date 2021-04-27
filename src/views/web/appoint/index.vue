<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--用户数据-->
      <el-col :xs="24">

        <el-table v-loading="loading" :data="appointInfo">
<!--          <el-table-column label="医生编号" align="center" prop="doctorId"/>-->
          <el-table-column label="预约时间" align="center" prop="time" :show-overflow-tooltip="true"/>
          <el-table-column label="就诊时间" align="center" prop="seeTime" :show-overflow-tooltip="true"/>
          <el-table-column label="医生姓名" align="center" prop="realName" :show-overflow-tooltip="true"/>
          <el-table-column label="职称" align="center" prop="position" :show-overflow-tooltip="true"/>
          <el-table-column label="科室" align="center" prop="department" :show-overflow-tooltip="true"/>
          <el-table-column label="患者姓名" align="center" prop="name" :show-overflow-tooltip="true"/>
          <el-table-column label="就诊状态" align="center" prop="status" :show-overflow-tooltip="true">
            <template slot-scope="scope">
              {{scope.row.status==='0'?'未完成':'已完成'}}
            </template>
          </el-table-column>

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
                @click="handleDownload(scope.row)"
              >下载预约信息
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
              >撤销预约
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


  </div>
</template>

<script>
  import {
    getDoctor, getPatient,deleteAppoint,downloadAppoint
  } from '@/api/system/sysAppoint'

export default {
  name: "appoint",
  components: { },
  data() {
    return {
      loading: true,
      appointInfo:[]  ,
      total: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
    };
  },
  watch: {

  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true
      getPatient(this.queryParams).then(response => {
          console.log(response)
          this.appointInfo = response.rows
          this.total = response.total
          this.loading = false
        }
      )
    },
    /** 修改按钮操作 */
    handleDownload(row) {
     return  downloadAppoint(row).then(response=>{
       if (response.msg !== 'error') {
         this.download(response.msg)
       }else {
         this.$message.error('下载失败')
       }
      }).then(response=>{
      })
    },
    //删除数据
    handleDelete(row) {

      this.$confirm('是否确认撤销预约?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return deleteAppoint(row)
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function() {
      })
    },
  }
};
</script>
