<template>
  <div>
    <!--    @close="Close"-->
    <el-dialog
      v-bind="$attrs"
      v-on="$listeners"
      :visible.sync="dialogFormVisible"
      :title="title"
      :before-close="handleClose"
      :modal-append-to-body="false"
    >
      <el-row :gutter="15">
        <el-form ref="formData" :model="formData" :rules="rules" size="medium" label-width="110px">

                    <el-col :span="8" v-if="this.title=='添加'">
                      <el-form-item label="用户名" prop="name">
                        <el-input v-model="formData.name" placeholder="请输入姓名" show-word-limit clearable
                                  prefix-icon='el-icon-mobile' :style="{width: '100%'}"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8" v-if="this.title=='添加'">
                      <el-form-item label="密码" prop="password">
                        <el-input v-model="formData.password" placeholder="请输入密码" show-word-limit clearable type="password"
                                  prefix-icon='el-icon-mobile' :style="{width: '100%'}"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="真实姓名" prop="realName">
                        <el-input v-model="formData.realName" placeholder="请输入真实姓名" show-word-limit clearable
                                  prefix-icon='el-icon-mobile' :style="{width: '100%'}" :disabled="this.title=='编辑'"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="职称" prop="position">
                        <el-input v-model="formData.position" placeholder="请输入真实姓名" show-word-limit clearable
                                  prefix-icon='el-icon-mobile' :style="{width: '100%'}" :disabled="this.title=='编辑'"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="部门" prop="department">
                        <el-input v-model="formData.department" placeholder="请输入真实姓名" show-word-limit clearable
                                  prefix-icon='el-icon-mobile' :style="{width: '100%'}" :disabled="this.title=='编辑'"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="20">
                      <el-form-item label="专长" prop="specialty">
                        <el-input v-model="formData.specialty" placeholder="专长" show-word-limit clearable
                                  prefix-icon='el-icon-mobile' :style="{width: '100%'}" type="textarea"
                                  :disabled="this.title=='编辑'"></el-input>
                      </el-form-item>
                    </el-col>
          <el-col :span="8">
            <el-form-item label="就诊开始时间" prop="startTime">
              <el-time-select
                placeholder="起始时间"
                v-model="formData.startTime"
                :picker-options="TimeOption"
                :style="{width: '100%'}"
              >
              </el-time-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="就诊结束时间" prop="endTime">
              <el-time-select
                placeholder="结束时间"
                v-model="formData.endTime"
                :picker-options="TimeOption"
                :style="{width: '100%'}"
                :blur="onChange"
              >
              </el-time-select>

            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="最大预约数量" prop="maxNum">
              <el-input v-model.number="formData.maxNum" placeholder="请输入最大预约数量" clearable
                        prefix-icon='el-icon-mobile' :style="{width: '100%'}"></el-input>
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
  import { addADoctor, updateADoctorAInfo } from '@/api/system/doctor'

  export default {
    inheritAttrs: false,
    components: {},
    data() {
      return {
        TimeOption: {
          start: '08:30',
          step: '00:30',
          end: '24:00'
        },
        formData: {
          userId: '',
          doctorId: '',
          name: '',
          password: '111111',
          avatar: '',
          realName: '',
          position: '',
          department: '',
          specialty: '',
          status: '',
          seeTime: '',
          maxNum: '',
          remanNum: '',
          endTime: '08:30',
          startTime: '08:30'
        },
        //表单验证规则
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
          department: [{
            required: true,
            message: '请输入部门',
            trigger: 'blur'
          }],
          position: [{
            required: true,
            message: '请输入职称',
            trigger: 'blur'
          }],
          specialty: [{
            required: true,
            message: '请输入专长',
            trigger: 'blur'
          }],
          startTime: [{
            required: true,
            message: '请输入就诊时间',
            trigger: ' change'
          }],
          endTime: [{
            required: true,
            message: '请输入就诊时间',
            trigger: ' change'
          }],
          maxNum: [{
            required: true,
            message: '请输入最大预约数',
            trigger: 'blur'
          },
            { type: 'number', message: '最大预约数必须为数字值' }
          ]

        },
        //弹窗标题头
        title: '',
        //是否显示弹窗
        dialogFormVisible: false,
        //当前登陆用户角色
        role: '',
        //是否正在加载
        isUpload: false
      }
    },
    computed: {},
    watch: {
      'formData.seeTime': {//深度监听，可监听到对象、数组的变化
        handler(val, oldVal) {
          console.log("~~~~~")
          const seeTime = [...val.split('-')]
          this.formData.startTime = seeTime[0]
          this.formData.endTime = seeTime[1]
        },
        deep: true //true 深度监听
      }
    },
    //获取部门名称集合、部门与项目匹配关系集合、项目与项目经理匹配关系集合、该用户的部门、部门经理、部门名
    props: {},
    created() {
    },
    mounted() {
    },
    methods: {
      onChange() {
        console.log(typeof this.formData.endTime)
      },
      showEdit(row) {
        //清空表单数据
        this.reset()
        if (!row) {
          this.title = '添加'
        } else {
          //给表单及uploadList赋值
          this.title = '编辑'
          this.formData= {...this.formData,...row};
        }
        this.dialogFormVisible = true
      },
      //弹窗关闭之前---确定弹窗是否关闭，即当弹窗非法关闭（鼠标点击弹窗外部，非直接点击弹窗内的取消按钮）时的判断
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            this.dialogFormVisible = false
            this.reset()
            this.$emit('fetch-data')

            done()
          })
          .catch(_ => {
          })
      },
      //关闭弹窗
      Close() {
        this.dialogFormVisible = false
        this.$emit('fetch-data')
        this.reset()
      },
      //重置表单
      reset() {
        this.formData = {
          userId: '',
          doctorId: '',
          name: '',
          password: '111111',
          avatar: '',
          realName: '',
          position: '',
          department: '',
          specialty: '',
          status: '',
          seeTime: '',
          maxNum: '',
          remanNum: '',
          endTime: '',
          startTime: ''

        }
        // this.resetForm('formData')
      },

      //提交表单
      handelConfirm() {
        //验证表单数据
        this.$refs['formData'].validate(valid => {
          if (valid) {
            //发送数据到添加接口
            this.formData.seeTime = this.formData.startTime + '-' + this.formData.endTime
            if (this.title === '添加') {
              addADoctor(this.formData).then(response => {
                if (response.code === 200) {
                  this.msgSuccess('保存成功')
                  //操作成功---向父组件发送消息，刷新列表，关闭弹窗
                  this.$emit('fetch-data')
                  this.dialogFormVisible = false
                } else {
                  this.msgError('保存失败，请检查您的数据及网络连接')
                }
              })
            } else if (this.title === '编辑') {
              //发送数据到编辑接口
              updateADoctorAInfo(this.formData).then(response => {
                if (response.code === 200) {
                  this.msgSuccess('保存成功')
                  this.$emit('fetch-data')
                  this.dialogFormVisible = false
                } else {
                  this.msgError('保存失败，请检查您的数据及网络连接')
                }
              })
              this.dialogFormVisible = false
              this.$emit('fetch-data')
            }
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
