<template>
  <div class="app-container">

    <el-row :gutter="20">
      <el-col :span="10" :xs="24" style="margin-left: 30%;">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <div>
            <div class="text-center">
<!--              <userAvatar :avatar=" baseUrl+doctor.avatar"/>-->
              <userAvatar />
            </div>
            <ul class="list-group list-group-striped">
              <li class="list-group-item">
                <svg-icon icon-class="user"/>
                用户名
                <div class="pull-right">{{ doctor.name }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="phone"/>
                密码
<!--                <div class="pull-right">{{ doctor.password }}</div>-->
                <div class="pull-right">{{'******' }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="email"/>
                真实姓名
                <div class="pull-right">{{ doctor.realName }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="tree"/>
                职称
                <div class="pull-right">{{ doctor.position }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="peoples"/>
                科室
                <div class="pull-right">{{ doctor.department }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="date"/>
                专长
                <div class="pull-right">{{ doctor.specialty }}</div>
              </li>
              <li  style="margin-left: 40%;margin-top: 50px;">
                <el-button type="primary" @click="submit">编辑信息</el-button>

              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
    </el-row>

        <edit ref="edit" @fetch-data="getInfo" ></edit>

  </div>
</template>

<script>
  import { getDoctor } from '@/api/system/doctor'
  import Edit from './edit/index'
  import userAvatar from './edit/avatar'

  export default {
    name: 'doctorInfo',
    components: { userAvatar,Edit },
    data() {
      return {
        baseUrl:process.env.VUE_APP_BASE_API+"/",
        doctor: {}
      }
    },
    watch: {},
    created() {
      this.getInfo()
    },
    methods: {
     async getInfo() {
        getDoctor().then(response => {
          this.doctor = response.doctor

        })
      },
      submit() {
        this.$refs['edit'].showEdit(this.doctor)
      }
    }
  }
</script>
