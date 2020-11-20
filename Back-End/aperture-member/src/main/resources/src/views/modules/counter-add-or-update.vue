<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="每个收藏夹的收藏数" prop="collection">
      <el-input v-model="dataForm.collection" placeholder="每个收藏夹的收藏数"></el-input>
    </el-form-item>
    <el-form-item label="关注组中的关注数" prop="follow">
      <el-input v-model="dataForm.follow" placeholder="关注组中的关注数"></el-input>
    </el-form-item>
    <el-form-item label="粉丝数" prop="fans">
      <el-input v-model="dataForm.fans" placeholder="粉丝数"></el-input>
    </el-form-item>
    <el-form-item label="获赞数" prop="like">
      <el-input v-model="dataForm.like" placeholder="获赞数"></el-input>
    </el-form-item>
    <el-form-item label="收藏夹数" prop="favorates">
      <el-input v-model="dataForm.favorates" placeholder="收藏夹数"></el-input>
    </el-form-item>
    <el-form-item label="视频播放数" prop="play">
      <el-input v-model="dataForm.play" placeholder="视频播放数"></el-input>
    </el-form-item>
    <el-form-item label="阅读数" prop="read">
      <el-input v-model="dataForm.read" placeholder="阅读数"></el-input>
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
          collection: '',
          follow: '',
          fans: '',
          like: '',
          favorates: '',
          play: '',
          read: ''
        },
        dataRule: {
          collection: [
            { required: true, message: '每个收藏夹的收藏数不能为空', trigger: 'blur' }
          ],
          follow: [
            { required: true, message: '关注组中的关注数不能为空', trigger: 'blur' }
          ],
          fans: [
            { required: true, message: '粉丝数不能为空', trigger: 'blur' }
          ],
          like: [
            { required: true, message: '获赞数不能为空', trigger: 'blur' }
          ],
          favorates: [
            { required: true, message: '收藏夹数不能为空', trigger: 'blur' }
          ],
          play: [
            { required: true, message: '视频播放数不能为空', trigger: 'blur' }
          ],
          read: [
            { required: true, message: '阅读数不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`//counter/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.collection = data.counter.collection
                this.dataForm.follow = data.counter.follow
                this.dataForm.fans = data.counter.fans
                this.dataForm.like = data.counter.like
                this.dataForm.favorates = data.counter.favorates
                this.dataForm.play = data.counter.play
                this.dataForm.read = data.counter.read
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
              url: this.$http.adornUrl(`//counter/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'collection': this.dataForm.collection,
                'follow': this.dataForm.follow,
                'fans': this.dataForm.fans,
                'like': this.dataForm.like,
                'favorates': this.dataForm.favorates,
                'play': this.dataForm.play,
                'read': this.dataForm.read
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
