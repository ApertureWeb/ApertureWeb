<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="收藏名" prop="name">
      <el-input v-model="dataForm.name" placeholder="收藏名"></el-input>
    </el-form-item>
    <el-form-item label="收藏日期" prop="collectionDate">
      <el-input v-model="dataForm.collectionDate" placeholder="收藏日期"></el-input>
    </el-form-item>
    <el-form-item label="目标作者名字" prop="authorName">
      <el-input v-model="dataForm.authorName" placeholder="目标作者名字"></el-input>
    </el-form-item>
    <el-form-item label="收藏目标的id" prop="targetId">
      <el-input v-model="dataForm.targetId" placeholder="收藏目标的id"></el-input>
    </el-form-item>
    <el-form-item label="所属收藏夹id" prop="favoratesId">
      <el-input v-model="dataForm.favoratesId" placeholder="所属收藏夹id"></el-input>
    </el-form-item>
    <el-form-item label="当前用户id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="当前用户id"></el-input>
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
          collectionDate: '',
          authorName: '',
          targetId: '',
          favoratesId: '',
          memberId: ''
        },
        dataRule: {
          name: [
            { required: true, message: '收藏名不能为空', trigger: 'blur' }
          ],
          collectionDate: [
            { required: true, message: '收藏日期不能为空', trigger: 'blur' }
          ],
          authorName: [
            { required: true, message: '目标作者名字不能为空', trigger: 'blur' }
          ],
          targetId: [
            { required: true, message: '收藏目标的id不能为空', trigger: 'blur' }
          ],
          favoratesId: [
            { required: true, message: '所属收藏夹id不能为空', trigger: 'blur' }
          ],
          memberId: [
            { required: true, message: '当前用户id不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`//collection/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.collection.name
                this.dataForm.collectionDate = data.collection.collectionDate
                this.dataForm.authorName = data.collection.authorName
                this.dataForm.targetId = data.collection.targetId
                this.dataForm.favoratesId = data.collection.favoratesId
                this.dataForm.memberId = data.collection.memberId
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
              url: this.$http.adornUrl(`//collection/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'collectionDate': this.dataForm.collectionDate,
                'authorName': this.dataForm.authorName,
                'targetId': this.dataForm.targetId,
                'favoratesId': this.dataForm.favoratesId,
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
