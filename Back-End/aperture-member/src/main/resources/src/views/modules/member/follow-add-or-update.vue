<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="被关注的用户id" prop="followedId">
      <el-input v-model="dataForm.followedId" placeholder="被关注的用户id"></el-input>
    </el-form-item>
    <el-form-item label="被关注的用户昵称" prop="followedNickname">
      <el-input v-model="dataForm.followedNickname" placeholder="被关注的用户昵称"></el-input>
    </el-form-item>
    <el-form-item label="所属分组id" prop="groupId">
      <el-input v-model="dataForm.groupId" placeholder="所属分组id"></el-input>
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
          followedId: '',
          followedNickname: '',
          groupId: ''
        },
        dataRule: {
          followedId: [
            { required: true, message: '被关注的用户id不能为空', trigger: 'blur' }
          ],
          followedNickname: [
            { required: true, message: '被关注的用户昵称不能为空', trigger: 'blur' }
          ],
          groupId: [
            { required: true, message: '所属分组id不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/member/follow/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.followedId = data.follow.followedId
                this.dataForm.followedNickname = data.follow.followedNickname
                this.dataForm.groupId = data.follow.groupId
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
              url: this.$http.adornUrl(`/member/follow/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'followedId': this.dataForm.followedId,
                'followedNickname': this.dataForm.followedNickname,
                'groupId': this.dataForm.groupId
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
