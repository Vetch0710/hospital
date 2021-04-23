<template>
  <div>
    <!--    @close="Close"-->
    <el-dialog v-bind="$attrs"
               v-on="$listeners"
               :visible.sync="dialogFormVisible"
               :title="title">
      <el-row :gutter="15">
        <el-form ref="formData" :model="doctor" :rules="rules" size="medium" label-width="94px">
          <el-col :span="13">
            <el-form-item label="用户名" prop="name">
              <el-input v-model="doctor.name" placeholder="用户名" show-word-limit clearable
                        prefix-icon='el-icon-mobile' :style="{width: '100%'}" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="登陆密码" prop="password">
              <el-input v-model="doctor.password" placeholder="登陆密码" show-word-limit clearable
                        prefix-icon='el-icon-mobile' :style="{width: '100%'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="真实姓名" prop="realName">
              <el-input v-model="doctor.realName" placeholder="真实姓名" show-word-limit clearable
                        prefix-icon='el-icon-mobile' :style="{width: '100%'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="职称" prop="position">
              <el-input v-model="doctor.position" placeholder="职称" show-word-limit clearable
                        prefix-icon='el-icon-mobile' :style="{width: '100%'}" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="科室" prop="department">
              <el-input v-model="doctor.department" placeholder="科室" show-word-limit clearable
                        prefix-icon='el-icon-mobile' :style="{width: '100%'}" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="20">
            <el-form-item label="专长" prop="specialty">
              <el-input v-model="doctor.specialty" placeholder="专长" show-word-limit clearable
                        prefix-icon='el-icon-mobile' :style="{width: '100%'}" type="textarea"></el-input>
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
  import { updateDoctor } from '@/api/system/doctor'

  export default {
    inheritAttrs: false,
    components: {},
    data() {
      return {
        dialogFormVisible: false,
        title: '',
        doctor: {},
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
          realName: [{
            required: true,
            message: '请输入真实姓名',
            trigger: 'blur'
          }],
          position: [{
            required: true,
            message: '请输入职称',
            trigger: 'blur'
          }],
          department: [{
            required: true,
            message: '请输入部门',
            trigger: 'blur'
          }],
          specialty: [{
            required: true,
            message: '请输入专长',
            trigger: 'blur'
          }]
        }

      }
    },
    computed: {},
    watch: {},
    props: {},
    created() {
    },
    mounted() {
    },
    methods: {
      showInfo(row) {
        //清空表单数据
        this.dialogFormVisible = true
        this.reset()

        this.title = '编辑'
        this.doctor = row

      },
      //关闭弹窗
      Close() {
        this.dialogFormVisible = false
        this.reset()
      },
      //重置表单
      reset() {
        this.doctor = {
          userId: '',
          doctorId:'',
          name: '',
          password: '',
          avatar: '',
          realName: '',
          position: '',
          specialty: '',
          department: '',
          status: ''

        }
        this.resetForm('formData')
      },

      handelConfirm() {
        //验证表单数据
        this.$refs['formData'].validate(valid => {
          if (valid) {
            //发送数据到添加接口

            //发送数据到编辑接口
            updateDoctor(this.doctor).then(response => {
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

  .clearPic {
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
