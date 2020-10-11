<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="收藏名" prop="name">
      <el-input v-model="dataForm.name" placeholder="收藏名"></el-input>
    </el-form-item>
    <el-form-item label="收藏数量" prop="collectionCount">
      <el-input v-model="dataForm.collectionCount" placeholder="收藏数量"></el-input>
    </el-form-item>
    <el-form-item label="收藏日期" prop="collectionDate">
      <el-input v-model="dataForm.collectionDate" placeholder="收藏日期"></el-input>
    </el-form-item>
    <el-form-item label="播放量" prop="watchCount">
      <el-input v-model="dataForm.watchCount" placeholder="播放量"></el-input>
    </el-form-item>
    <el-form-item label="收藏目标的id" prop="targetId">
      <el-input v-model="dataForm.targetId" placeholder="收藏目标的id"></el-input>
    </el-form-item>
    <el-form-item label="收藏夹id" prop="favoratesId">
      <el-input v-model="dataForm.favoratesId" placeholder="收藏夹id"></el-input>
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
          collectionDate: '',
          watchCount: '',
          targetId: '',
          favoratesId: ''
        },
        dataRule: {
          name: [
            { required: true, message: '收藏名不能为空', trigger: 'blur' }
          ],
          collectionCount: [
            { required: true, message: '收藏数量不能为空', trigger: 'blur' }
          ],
          collectionDate: [
            { required: true, message: '收藏日期不能为空', trigger: 'blur' }
          ],
          watchCount: [
            { required: true, message: '播放量不能为空', trigger: 'blur' }
          ],
          targetId: [
            { required: true, message: '收藏目标的id不能为空', trigger: 'blur' }
          ],
          favoratesId: [
            { required: true, message: '收藏夹id不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/member/collcetions/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.collcetions.name
                this.dataForm.collectionCount = data.collcetions.collectionCount
                this.dataForm.collectionDate = data.collcetions.collectionDate
                this.dataForm.watchCount = data.collcetions.watchCount
                this.dataForm.targetId = data.collcetions.targetId
                this.dataForm.favoratesId = data.collcetions.favoratesId
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
              url: this.$http.adornUrl(`/member/collcetions/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'collectionCount': this.dataForm.collectionCount,
                'collectionDate': this.dataForm.collectionDate,
                'watchCount': this.dataForm.watchCount,
                'targetId': this.dataForm.targetId,
                'favoratesId': this.dataForm.favoratesId
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
