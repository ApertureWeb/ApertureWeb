<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="用户id"></el-input>
    </el-form-item>
    <el-form-item label="圈子id" prop="circleId">
      <el-input v-model="dataForm.circleId" placeholder="圈子id"></el-input>
    </el-form-item>
    <el-form-item label="在对应圈子里的位置" prop="position">
      <el-input v-model="dataForm.position" placeholder="在对应圈子里的位置"></el-input>
    </el-form-item>
    <el-form-item label="创建圈子审核状态(1：通过  0：不通过)" prop="status">
      <el-input v-model="dataForm.status" placeholder="创建圈子审核状态(1：通过  0：不通过)"></el-input>
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
          circleId: '',
          position: '',
          status: ''
        },
        dataRule: {
          memberId: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
          ],
          circleId: [
            { required: true, message: '圈子id不能为空', trigger: 'blur' }
          ],
          position: [
            { required: true, message: '在对应圈子里的位置不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '创建圈子审核状态(1：通过  0：不通过)不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/member/membercirclerela/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memberId = data.memberCircleRela.memberId
                this.dataForm.circleId = data.memberCircleRela.circleId
                this.dataForm.position = data.memberCircleRela.position
                this.dataForm.status = data.memberCircleRela.status
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
              url: this.$http.adornUrl(`/member/membercirclerela/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'memberId': this.dataForm.memberId,
                'circleId': this.dataForm.circleId,
                'position': this.dataForm.position,
                'status': this.dataForm.status
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
