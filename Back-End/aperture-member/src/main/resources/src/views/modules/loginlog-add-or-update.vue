<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="用户id"></el-input>
    </el-form-item>
    <el-form-item label="用户登录时的ip" prop="ip">
      <el-input v-model="dataForm.ip" placeholder="用户登录时的ip"></el-input>
    </el-form-item>
    <el-form-item label="用户登录时所在的城市" prop="city">
      <el-input v-model="dataForm.city" placeholder="用户登录时所在的城市"></el-input>
    </el-form-item>
    <el-form-item label="登录类型[1-web，2-app]" prop="loginType">
      <el-input v-model="dataForm.loginType" placeholder="登录类型[1-web，2-app]"></el-input>
    </el-form-item>
    <el-form-item label="在线时长(分钟)" prop="onlineTime">
      <el-input v-model="dataForm.onlineTime" placeholder="在线时长(分钟)"></el-input>
    </el-form-item>
    <el-form-item label="注册时间" prop="registTime">
      <el-input v-model="dataForm.registTime" placeholder="注册时间"></el-input>
    </el-form-item>
    <el-form-item label="0：已下线  1：上线中" prop="loginStatus">
      <el-input v-model="dataForm.loginStatus" placeholder="0：已下线  1：上线中"></el-input>
    </el-form-item>
    <el-form-item label="封号时长，单位：分钟" prop="banTime">
      <el-input v-model="dataForm.banTime" placeholder="封号时长，单位：分钟"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          memberId: '',
          ip: '',
          city: '',
          loginType: '',
          onlineTime: '',
          registTime: '',
          loginStatus: '',
          banTime: ''
        },
        dataRule: {
          memberId: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
          ],
          ip: [
            { required: true, message: '用户登录时的ip不能为空', trigger: 'blur' }
          ],
          city: [
            { required: true, message: '用户登录时所在的城市不能为空', trigger: 'blur' }
          ],
          loginType: [
            { required: true, message: '登录类型[1-web，2-app]不能为空', trigger: 'blur' }
          ],
          onlineTime: [
            { required: true, message: '在线时长(分钟)不能为空', trigger: 'blur' }
          ],
          registTime: [
            { required: true, message: '注册时间不能为空', trigger: 'blur' }
          ],
          loginStatus: [
            { required: true, message: '0：已下线  1：上线中不能为空', trigger: 'blur' }
          ],
          banTime: [
            { required: true, message: '封号时长，单位：分钟不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`//loginlog/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memberId = data.loginLog.memberId
                this.dataForm.ip = data.loginLog.ip
                this.dataForm.city = data.loginLog.city
                this.dataForm.loginType = data.loginLog.loginType
                this.dataForm.onlineTime = data.loginLog.onlineTime
                this.dataForm.registTime = data.loginLog.registTime
                this.dataForm.loginStatus = data.loginLog.loginStatus
                this.dataForm.banTime = data.loginLog.banTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`//loginlog/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'memberId': this.dataForm.memberId,
                'ip': this.dataForm.ip,
                'city': this.dataForm.city,
                'loginType': this.dataForm.loginType,
                'onlineTime': this.dataForm.onlineTime,
                'registTime': this.dataForm.registTime,
                'loginStatus': this.dataForm.loginStatus,
                'banTime': this.dataForm.banTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
