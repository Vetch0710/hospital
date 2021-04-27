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
                <img v-bind:src="baseUrl+doctorDetailInfo.avatar" style="width: 200px;height: 200px;"
                     class="img-circle"/>
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
                <li class="list-group-item" v-if="doctorDetailInfo.seeTime">
                  <svg-icon icon-class="date"/>
                  就诊时间
                  <div class="pull-right">{{ doctorDetailInfo.seeTime }}</div>
                </li>
                <li class="list-group-item" v-if="doctorDetailInfo.seeTime">
                  <svg-icon icon-class="date"/>
                  剩余预约数
                  <div class="pull-right">{{ doctorDetailInfo.remanNum }}</div>
                </li>
              </ul>
            </div>
          </el-card>
        </el-col>

        <el-col :span="10" :xs="24">
          <el-card v-if="doctorDetailInfo.seeTime&&doctorDetailInfo.remanNum!==0">
            <div slot="header" class="clearfix">
              <span>预约</span>
            </div>
            日期：
            <el-date-picker
              v-model="selectDay"
              align="right"
              type="date"
              placeholder="选择日期"
              :picker-options="pickerOptions"
              format="yyyy 年 MM 月 dd 日"
              value-format="yyyy-MM-dd"
              :style="{width: '100%'}"
            >
            </el-date-picker>
            <br>
            时间：<span style="color: red;font-size: 9px;">请先选择日期,再选择时间</span>
            <el-select v-model="selectTime" placeholder="预约数状态" clearable
                       :style="{width: '100%'}">
              <el-option v-for="(dict, index) in activeTimes" :key="index"
                         :label="dict"
                         :value="dict"></el-option>
            </el-select>
            <el-button type="primary" @click="appoint" style="margin: 30px 0 0 30px;" :disabled="!this.selectTime||!this.selectDay">预约</el-button>

          </el-card>
          <el-card v-else>

            <span>当前医生无法预约</span>


          </el-card>
        </el-col>
      </el-row>


    </el-dialog>
  </div>
</template>

<script>
  import { getActiveTime ,addAppoint } from '@/api/system/appoint'

  export default {
    components: {},
    data() {
      return {
        baseUrl: process.env.VUE_APP_BASE_API,
        doctorDetailInfo: {},
        dialogFormVisible: false,
        title: '',
        selectTime: '',
        selectDay: '',
        activeTimes: '',
        TimeOption: {
          start: '08:30',
          step: '00:30',
          end: '24:00'
        },
        pickerOptions: {
          disabledDate(time) {
            // console.log(time)
            return time.getTime() < Date.now() || time.getTime() > Date.now() * 1 + 86400 * 7 * 1000
          }
        }
      }
    },
    watch: {
      selectDay() {
        if (this.selectDay) {
          console.log(111111111111111)
          this.getActiveTime()
        }else {
          this.activeTimes=''
          this.selectTime=''
        }
      }
    },
    created() {
      // this.doctorDetailInfo=this.$route.query.info
    },
    methods: {
      showEdit(row) {
        //清空表单数据
        this.dialogFormVisible = true
        this.title = '医生信息'
        this.doctorDetailInfo = row
        this.selectTime = ''
        this.selectDay = ''
      },
      getActiveTime() {
        getActiveTime(this.doctorDetailInfo.doctorId, this.selectDay).then(response => {
          this.activeTimes = response.time
        })
      },
      appoint() {
        addAppoint({
          "appointTime": this.selectDay+'-'+this.selectTime,
          "messageId": this.doctorDetailInfo.messageId
        }).then(response => {
          if (response.code === 200) {
            this.msgSuccess('预约成功')
            this.$emit('fetch-data')
          } else {
            this.msgError('预约失败，请查看当前预约时间是否可预约')
          }
          this.dialogFormVisible = false

        })
      }
    }
  }
</script>
