<template>
  <div class="app-container">
    <el-row :gutter="20">

      <el-col :span="18" :xs="24">
        <el-card >
          <div slot="header" class="clearfix">
            <span>个人中心</span>

          </div>
          <el-tabs v-model="activeTab">
            <el-tab-pane label="基本资料" name="userinfo" style="position: relative">
              <el-button type="primary" @click="submit" style="right: 5%;position: absolute;top: 0;width: 150px;">编辑资料</el-button>

              <el-form ref="formData" :model="patient"  size="medium" label-width="94px">
                <el-col :span="9">
                  <el-form-item label="用户名" >
                    <el-input v-model="patient.name" placeholder="用户名" show-word-limit clearable
                              prefix-icon='el-icon-mobile' :style="{width: '100%'}" disabled></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="7">
                  <el-form-item label="登陆密码" >
                    <el-input v-model="patient.password" placeholder="用户名" show-word-limit clearable type="password"
                              prefix-icon='el-icon-mobile' :style="{width: '100%'}" disabled></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">

                </el-col>
                <el-col :span="9">
                  <el-form-item label="身份证">
                    <el-input v-model="patient.idCard" placeholder="用户名" show-word-limit clearable
                              prefix-icon='el-icon-mobile' :style="{width: '100%'}" disabled></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="7">
                  <el-form-item label="用户状态">

                    <el-input value="正常" maxlength="50" disabled v-if="patient.status==='0'"/>
                    <el-input value="冻结" maxlength="50" disabled v-if="patient.status==='1'"/>
                  </el-form-item>

                </el-col>
                <el-col :span="7" v-if="patient.status==='1'">
                  <el-form-item label=" 解封时间">
                    <el-input v-model="patient.banTime" maxlength="50" disabled/>
                  </el-form-item>
                </el-col>

              </el-form>

            </el-tab-pane>

          </el-tabs>

        </el-card>
      </el-col>

    </el-row>

    <edit ref="edit" @fetch-data="getUser" ></edit>
  </div>
</template>

<script>

  import { getPatient } from '@/api/system/patient'
  import Edit from './edit/index'
  export default {
    name: 'patientInfo',
    components: {Edit},
    data() {
      return {
        patient: {},

        activeTab: 'userinfo'
      }
    },
    created() {
      this.getUser()
    },
    methods: {
      getUser() {
        getPatient().then(response => {
          this.patient = response.patient

        })
      },
      submit() {
        this.$refs['edit'].showEdit(this.patient)
      }
    }
  }
</script>
