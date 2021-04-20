<template>
  <div class="register">

    <el-form ref="registerForm" :model="registerForm" :rules="registerRules" class="register-form" >
      <h3 class="registerTitle">用户注册</h3>
      <div style="height: 45px;">
        <router-link to="/login" >
          <h3 class="goBackToLogin">已有账号？立即登陆</h3>
        </router-link>
      </div>

<!--      <el-col :span="23">-->
      <el-form-item label="用户名" prop="username" label-width="80px">
        <el-input v-model="registerForm.username" type="text" auto-complete="off" placeholder="账号" :style="{width: '100%'}">
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
<!--      </el-col>-->
<!--      <el-col :span="23">-->
      <el-form-item label="密码" prop="password" label-width="80px">
        <el-input
          v-model="registerForm.password"
          type="password"
          auto-complete="off"
          placeholder="密码"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon"/>
        </el-input>
      </el-form-item>
<!--      </el-col>-->
<!--      <el-col :span="23">-->
      <el-form-item label="身份证" prop="idCard" label-width="80px">
        <el-input
          v-model="registerForm.idCard"
          type="text"
          auto-complete="off"
          placeholder="身份证"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon"/>
        </el-input>
      </el-form-item>
<!--      </el-col>-->
<!--      <el-col :span="20">-->
      <el-form-item prop="code" label="验证码" label-width="80px">
        <el-input
          v-model="registerForm.code"
          auto-complete="off"
          placeholder="验证码"
          style="width: 55%"
          @keyup.enter.native="handleRegister"
        >
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon"/>
        </el-input>
        <div class="register-code">
          <img :src="codeUrl" @click="getCode"/>
        </div>
      </el-form-item>
<!--      </el-col>-->
      <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width:100%;margin-top: 20px;"
          @click.native.prevent="handleRegister"
        >
          <span v-if="!loading">注册</span>
          <span v-else>注 册 中...</span>
        </el-button>
      </el-form-item>
    </el-form>
    <!--  底部  -->
    <!--    <div class="el-login-footer">-->
    <!--      <span>Copyright © 2018-2019 ruoyi.vip All Rights Reserved.</span>-->
    <!--    </div>-->
  </div>
</template>

<script>
  import { getCodeImg } from '@/api/login'
  import { addPatient } from '@/api/system/patient'


  export default {
    name: 'register',
    data() {
      return {

        codeUrl: '',
        cookiePassword: '',
        registerForm: {
          username: '',
          password: '',
          idCard:'',
          code: '',
          uuid: ''
        },
        registerRules: {
          username: [
            { required: true, trigger: 'blur', message: '用户名不能为空' }
          ],
          password: [
            { required: true, trigger: 'blur', message: '密码不能为空' }
          ],
          idCard: [
            { required: true, trigger: 'blur', message: '身份证不能为空' }
          ],
          code: [{ required: true, trigger: 'blur', message: '验证码不能为空' }]
        },
        loading: false,
        redirect: undefined
      }
    },
    watch: {

    },
    created() {
      this.getCode()
    },
    methods: {
      getCode() {
        getCodeImg().then(res => {
          this.codeUrl = 'data:image/gif;base64,' + res.img
          this.registerForm.uuid = res.uuid
        })
      },
      handleRegister() {
        console.log(this.registerForm)
        this.$refs.registerForm.validate(valid => {
          if (valid) {
            this.loading = true
            addPatient(this.registerForm)
              .then(response => {
                if (response.code === 200) {
                  this.msgSuccess('注册成功')
                  this.$router.push("/login")
                } else {
                  this.msgError('注册失败，请检查您的数据及网络连接')
                }
              })
              .catch(() => {
                this.loading = false
                this.getCode()
              })
          }
        })
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss">
  .registerTitle {
    margin: 0px auto 30px auto;
    text-align: center;
    color: #707070;
    font-size: 20px;
  }

  .goBackToLogin {
    margin: 0px auto 30px auto;
    text-align: right;
    color: #e43422;
    font-size: 13px;
    float: right;
  }
  .goBackToLogin:hover{
        color: blue;
      }

  .register {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background-image: url("../assets/image/login-background.jpg");
    background-size: cover;
  }

  .title {
    margin: 0px auto 30px auto;
    text-align: center;
    color: #707070;
  }

  .register-form {
    border-radius: 6px;
    background: #ffffff;
    width: 400px;
    padding: 25px 25px 5px 25px;

    .el-input {
      height: 38px;

      input {
        height: 38px;
      }
    }

    .input-icon {
      height: 39px;
      width: 14px;
      margin-left: 2px;
    }
  }

  .register-tip {
    font-size: 13px;
    text-align: center;
    color: #bfbfbf;
  }

  .register-code {
    width: 33%;
    height: 38px;
    float: right;
    margin-right: 20px;
    img {
      cursor: pointer;
      vertical-align: middle;
    }
  }

  .el-register-footer {
    height: 40px;
    line-height: 40px;
    position: fixed;
    bottom: 0;
    width: 100%;
    text-align: center;
    color: #fff;
    font-family: Arial;
    font-size: 12px;
    letter-spacing: 1px;
  }
</style>
