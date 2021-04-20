<template>
  <div>
    <!--    @close="Close"-->
    <el-dialog v-bind="$attrs"
               v-on="$listeners"
               :visible.sync="dialogFormVisible"
               :title="title">
      <el-row :gutter="15">
        <el-form ref="formData" :model="patient" :rules="rules" size="medium" label-width="94px">
          <el-col :span="9">
            <el-form-item label="用户名" prop="name">
              <el-input v-model="patient.name" placeholder="用户名" show-word-limit clearable
                        prefix-icon='el-icon-mobile' :style="{width: '100%'}" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="登陆密码" prop="password">
              <el-input v-model="patient.password" placeholder="用户名" show-word-limit clearable
                        prefix-icon='el-icon-mobile' :style="{width: '100%'}" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">

          </el-col>
          <el-col :span="9">
            <el-form-item label="身份证" prop="idCard">
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
      </el-row>
      <div slot="footer">
        <el-button @click="Close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import { updatePatient } from '@/api/system/patient'


  export default {
    inheritAttrs: false,
    components: {},
    data() {
      return {
        dialogFormVisible:false,
        title: '',
        patient:{},
        rules: {
          name: [{
            required: true,
            message: '请输入用户名',
            trigger: 'blur'
          }],
          password: [{
            required: true,
            message: '请输入密码',
            trigger: 'blur'
          }],
        },

      }
    },
    computed: {},
    watch: {},
    props:{
    },
    created() {
    },
    mounted() {
    },
    methods: {
      showEdit(row) {
        //清空表单数据
        this.dialogFormVisible=true
        console.log(this.patient)
        this.reset()

          this.title = '编辑'
          this.patient = row

      },
      //关闭弹窗
      Close() {
        this.dialogFormVisible = false
        this.reset()
      },
      //重置表单
      reset() {
        this.patient = {
          userId: '',
          name: '',
          password: '',
          idCard: '',
          status: '',
          banTime: '',

        }
        this.resetForm('formData')
      },

      handelConfirm() {
        //验证表单数据
        this.$refs['formData'].validate(valid => {
          if (valid) {
            //发送数据到添加接口

              //发送数据到编辑接口
            updatePatient(this.patient).then(response => {
                if (response.code === 200) {
                  this.msgSuccess('保存成功')
                  this.$emit('fetch-data')
                  this.dialogFormVisible = false
                } else {
                  this.msgError('保存失败，请检查您的数据及网络连接')
                }
              })
            }

        })
      }
    }
  }

</script>
<style>
  .el-upload__tip {
    line-height: 1.2;
  }

  /*当上传文件超过1时，隐藏upload上传标签*/
  .receipt-images-wrap .hide .el-upload--picture-card {
    display: none;
  }
  .clearPic{
    position: absolute;
    top: 0;
    left: 35%;
    /*right: 5%;*/
    z-index: 999;
    text-align: center;
    vertical-align: center;
    /*transform: translate(50%, -50%);*/
    /*width: 180px;*/
    /*height: 180px;*/
    /*border-radius: 50%;*/
    /*box-shadow: 0 0 4px #ccc;*/
    /*overflow: hidden;*/
  }

</style>
