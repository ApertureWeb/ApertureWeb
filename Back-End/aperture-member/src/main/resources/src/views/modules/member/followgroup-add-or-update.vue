<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="分组名" prop="name">
      <el-input v-model="dataForm.name" placeholder="分组名"></el-input>
    </el-form-item>
    <el-form-item label="分组的关注数" prop="followCount">
      <el-input v-model="dataForm.followCount" placeholder="分组的关注数"></el-input>
    </el-form-item>
    <el-form-item label="是否为默认分组" prop="isDefault">
      <el-input v-model="dataForm.isDefault" placeholder="是否为默认分组"></el-input>
    </el-form-item>
    <el-form-item label="该分组所属用户id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="该分组所属用户id"></el-input>
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
          followCount: '',
          isDefault: '',
          memberId: ''
        },
        dataRule: {
          name: [
            { required: true, message: '分组名不能为空', trigger: 'blur' }
          ],
          followCount: [
            { required: true, message: '分组的关注数不能为空', trigger: 'blur' }
          ],
          isDefault: [
            { required: true, message: '是否为默认分组不能为空', trigger: 'blur' }
          ],
          memberId: [
            { required: true, message: '该分组所属用户id不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/member/followgroup/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.followGroup.name
                this.dataForm.followCount = data.followGroup.followCount
                this.dataForm.isDefault = data.followGroup.isDefault
                this.dataForm.memberId = data.followGroup.memberId
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
              url: this.$http.adornUrl(`/member/followgroup/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'followCount': this.dataForm.followCount,
                'isDefault': this.dataForm.isDefault,
                'memberId': this.dataForm.memberId
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
