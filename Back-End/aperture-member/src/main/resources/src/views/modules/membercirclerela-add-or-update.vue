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
    <el-form-item label="职位:0圈友、1管理员、2圈主" prop="position">
      <el-input v-model="dataForm.position" placeholder="职位:0圈友、1管理员、2圈主"></el-input>
    </el-form-item>
    <el-form-item label="圈子名" prop="ciecleName">
      <el-input v-model="dataForm.ciecleName" placeholder="圈子名"></el-input>
    </el-form-item>
    <el-form-item label="用户在圈子的等级" prop="gradeLevel">
      <el-input v-model="dataForm.gradeLevel" placeholder="用户在圈子的等级"></el-input>
    </el-form-item>
    <el-form-item label="当前所在圈子等级经验值" prop="gradeValue">
      <el-input v-model="dataForm.gradeValue" placeholder="当前所在圈子等级经验值"></el-input>
    </el-form-item>
    <el-form-item label="当前用户所在圈子等级id" prop="gradeId">
      <el-input v-model="dataForm.gradeId" placeholder="当前用户所在圈子等级id"></el-input>
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
          position: '',
          ciecleName: '',
          gradeLevel: '',
          gradeValue: '',
          gradeId: ''
        },
        dataRule: {
          memebrId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          circleId: [
            { required: true, message: '圈子id不能为空', trigger: 'blur' }
          ],
          position: [
            { required: true, message: '职位:0圈友、1管理员、2圈主不能为空', trigger: 'blur' }
          ],
          ciecleName: [
            { required: true, message: '圈子名不能为空', trigger: 'blur' }
          ],
          gradeLevel: [
            { required: true, message: '用户在圈子的等级不能为空', trigger: 'blur' }
          ],
          gradeValue: [
            { required: true, message: '当前所在圈子等级经验值不能为空', trigger: 'blur' }
          ],
          gradeId: [
            { required: true, message: '当前用户所在圈子等级id不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`//membercirclerela/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memebrId = data.memberCircleRela.memebrId
                this.dataForm.circleId = data.memberCircleRela.circleId
                this.dataForm.position = data.memberCircleRela.position
                this.dataForm.ciecleName = data.memberCircleRela.ciecleName
                this.dataForm.gradeLevel = data.memberCircleRela.gradeLevel
                this.dataForm.gradeValue = data.memberCircleRela.gradeValue
                this.dataForm.gradeId = data.memberCircleRela.gradeId
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
              url: this.$http.adornUrl(`//membercirclerela/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'memebrId': this.dataForm.memebrId,
                'circleId': this.dataForm.circleId,
                'position': this.dataForm.position,
                'ciecleName': this.dataForm.ciecleName,
                'gradeLevel': this.dataForm.gradeLevel,
                'gradeValue': this.dataForm.gradeValue,
                'gradeId': this.dataForm.gradeId
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
