<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="收藏夹名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="收藏夹名称"></el-input>
    </el-form-item>
    <el-form-item label="收藏夹里的作品数量" prop="collectionCount">
      <el-input v-model="dataForm.collectionCount" placeholder="收藏夹里的作品数量"></el-input>
    </el-form-item>
    <el-form-item label="用户id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="用户id"></el-input>
    </el-form-item>
    <el-form-item label="收藏夹描述" prop="description">
      <el-input v-model="dataForm.description" placeholder="收藏夹描述"></el-input>
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
          name: '',
          collectionCount: '',
          memberId: '',
          description: ''
        },
        dataRule: {
          name: [
            { required: true, message: '收藏夹名称不能为空', trigger: 'blur' }
          ],
          collectionCount: [
            { required: true, message: '收藏夹里的作品数量不能为空', trigger: 'blur' }
          ],
          memberId: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '收藏夹描述不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/member/favorates/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.favorates.name
                this.dataForm.collectionCount = data.favorates.collectionCount
                this.dataForm.memberId = data.favorates.memberId
                this.dataForm.description = data.favorates.description
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
              url: this.$http.adornUrl(`/member/favorates/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'collectionCount': this.dataForm.collectionCount,
                'memberId': this.dataForm.memberId,
                'description': this.dataForm.description
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
