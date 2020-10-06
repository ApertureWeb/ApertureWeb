<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="观看目标的id" prop="targetId">
      <el-input v-model="dataForm.targetId" placeholder="观看目标的id"></el-input>
    </el-form-item>
    <el-form-item label="观看目标的名称" prop="targetName">
      <el-input v-model="dataForm.targetName" placeholder="观看目标的名称"></el-input>
    </el-form-item>
    <el-form-item label="目标的类型" prop="targetType">
      <el-input v-model="dataForm.targetType" placeholder="目标的类型"></el-input>
    </el-form-item>
    <el-form-item label="观看到哪个时间点" prop="watchWhere">
      <el-input v-model="dataForm.watchWhere" placeholder="观看到哪个时间点"></el-input>
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
          targetId: '',
          targetName: '',
          targetType: '',
          watchWhere: ''
        },
        dataRule: {
          targetId: [
            { required: true, message: '观看目标的id不能为空', trigger: 'blur' }
          ],
          targetName: [
            { required: true, message: '观看目标的名称不能为空', trigger: 'blur' }
          ],
          targetType: [
            { required: true, message: '目标的类型不能为空', trigger: 'blur' }
          ],
          watchWhere: [
            { required: true, message: '观看到哪个时间点不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/member/memberwatchhistory/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.targetId = data.memberWatchHistory.targetId
                this.dataForm.targetName = data.memberWatchHistory.targetName
                this.dataForm.targetType = data.memberWatchHistory.targetType
                this.dataForm.watchWhere = data.memberWatchHistory.watchWhere
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
              url: this.$http.adornUrl(`/member/memberwatchhistory/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'targetId': this.dataForm.targetId,
                'targetName': this.dataForm.targetName,
                'targetType': this.dataForm.targetType,
                'watchWhere': this.dataForm.watchWhere
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
