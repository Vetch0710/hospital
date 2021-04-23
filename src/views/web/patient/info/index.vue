<template>
  <div>
    <el-dialog v-bind="$attrs"
               v-on="$listeners"
               :visible.sync="dialogFormVisible"
               :title="title">
      <el-row :gutter="20">
        <el-col :span="10" :xs="24" style="">
          <el-card class="box-card">
<!--            <div slot="header" class="clearfix">-->
<!--              <span>个人信息</span>-->
<!--            </div>-->
            <div>
              <div class="text-center">
                <img v-bind:src="baseUrl+doctorDetailInfo.avatar" style="width: 200px;height: 200px;" class="img-circle"/>
              </div>
              <ul class="list-group list-group-striped">
                <li class="list-group-item">
                  <svg-icon icon-class="email"/>
                  医生姓名
                  <div class="pull-right">{{ doctorDetailInfo.realName }}</div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="tree"/>
                  职称
                  <div class="pull-right">{{ doctorDetailInfo.position }}</div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="peoples"/>
                  科室
                  <div class="pull-right">{{ doctorDetailInfo.department }}</div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="date"/>
                  专长
                  <div class="pull-right">{{ doctorDetailInfo.specialty }}</div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="date"/>
                  就诊时间
                  <div class="pull-right">{{ doctorDetailInfo.seeTime }}</div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="date"/>
                  剩余预约数
                  <div class="pull-right">{{ doctorDetailInfo.remanNum }}</div>
                </li>
              </ul>
            </div>
          </el-card>
        </el-col>

        <el-col :span="10" :xs="24">
          <el-card>
            <div slot="header" class="clearfix">
              <span>预约</span>
            </div>
            日期：
            <el-date-picker
              v-model="startDay"
              align="right"
              type="date"
              placeholder="选择日期"
              :picker-options="pickerOptions"
              format="yyyy 年 MM 月 dd 日"
              value-format="yyyy-MM-dd"
              :style="{width: '100%'}"
            >
            </el-date-picker><br>
            时间：
            <el-time-select
              placeholder="预约时间"
              v-model="startTime"
              :picker-options="TimeOption"
              :style="{width: '100%'}"
              :blur="onChange"
            >
            </el-time-select>
            <el-button type="primary" @click="appoint" style="margin: 30px 0 0 30px;">预约</el-button>

          </el-card>
        </el-col>
      </el-row>


    </el-dialog>
  </div>
</template>

<script>
  import { getDisableTime } from '@/api/system/appoint'

  export default {
    components: {},
    data() {
      return {
        baseUrl: process.env.VUE_APP_BASE_API,
        doctorDetailInfo: {},
        dialogFormVisible: false,
        title:'',
        startTime:'',
        startDay:'',
        TimeOption: {
          start: '08:30',
          step: '00:30',
          end: '24:00'
        },
        pickerOptions: {
          disabledDate(time) {
            // console.log(time)
            return time.getTime() < Date.now() || time.getTime()>Date.now()*1 + 86400 * 7 * 1000;
          },
        }
      }
    },
    watch: {},
    created() {
      // this.doctorDetailInfo=this.$route.query.info
    },
    methods: {
      showEdit(row) {
        //清空表单数据
        this.dialogFormVisible = true
        this.title = '医生信息'
        this.doctorDetailInfo = row

      },
      appoint() {
      }
    }
  }
</script>
