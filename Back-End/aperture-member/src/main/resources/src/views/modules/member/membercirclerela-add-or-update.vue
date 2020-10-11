<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="会员id" prop="memebrId">
      <el-input v-model="dataForm.memebrId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="圈子id" prop="circleId">
      <el-input v-model="dataForm.circleId" placeholder="圈子id"></el-input>
    </el-form-item>
    <el-form-item label="会员昵称" prop="memberNickname">
      <el-input v-model="dataForm.memberNickname" placeholder="会员昵称"></el-input>
    </el-form-item>
    <el-form-item label="圈子名" prop="circleName">
      <el-input v-model="dataForm.circleName" placeholder="圈子名"></el-input>
    </el-form-item>
    <el-form-item label="在圈子的职位" prop="position">
      <el-input v-model="dataForm.position" placeholder="在圈子的职位"></el-input>
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
          memebrId: '',
          circleId: '',
          memberNickname: '',
          circleName: '',
          position: ''
        },
        dataRule: {
          memebrId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          circleId: [
            { required: true, message: '圈子id不能为空', trigger: 'blur' }
          ],
          memberNickname: [
            { required: true, message: '会员昵称不能为空', trigger: 'blur' }
          ],
          circleName: [
            { required: true, message: '圈子名不能为空', trigger: 'blur' }
          ],
          position: [
            { required: true, message: '在圈子的职位不能为空', trigger: 'blur' }
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
                this.dataForm.memebrId = data.memberCircleRela.memebrId
                this.dataForm.circleId = data.memberCircleRela.circleId
                this.dataForm.memberNickname = data.memberCircleRela.memberNickname
                this.dataForm.circleName = data.memberCircleRela.circleName
                this.dataForm.position = data.memberCircleRela.position
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
                'memebrId': this.dataForm.memebrId,
                'circleId': this.dataForm.circleId,
                'memberNickname': this.dataForm.memberNickname,
                'circleName': this.dataForm.circleName,
                'position': this.dataForm.position
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
