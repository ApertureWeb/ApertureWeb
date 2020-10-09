<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="等级名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="等级名称"></el-input>
    </el-form-item>
    <el-form-item label="是否为默认等级" prop="isDefaultGrade">
      <el-input v-model="dataForm.isDefaultGrade" placeholder="是否为默认等级"></el-input>
    </el-form-item>
    <el-form-item label="当前等级升级需要的经验" prop="growthValue">
      <el-input v-model="dataForm.growthValue" placeholder="当前等级升级需要的经验"></el-input>
    </el-form-item>
    <el-form-item label="签到增加的经验" prop="signInGrowthValue">
      <el-input v-model="dataForm.signInGrowthValue" placeholder="签到增加的经验"></el-input>
    </el-form-item>
    <el-form-item label="发布作品增加的经验" prop="publishGrowthValue">
      <el-input v-model="dataForm.publishGrowthValue" placeholder="发布作品增加的经验"></el-input>
    </el-form-item>
    <el-form-item label="评论增加的经验" prop="commentGrowthValue">
      <el-input v-model="dataForm.commentGrowthValue" placeholder="评论增加的经验"></el-input>
    </el-form-item>
    <el-form-item label="是否有会员价格优惠特权" prop="isVipDiscount">
      <el-input v-model="dataForm.isVipDiscount" placeholder="是否有会员价格优惠特权"></el-input>
    </el-form-item>
    <el-form-item label="等级描述" prop="description">
      <el-input v-model="dataForm.description" placeholder="等级描述"></el-input>
    </el-form-item>
    <el-form-item label="是否可以开通创建圈子" prop="isCreateCircle">
      <el-input v-model="dataForm.isCreateCircle" placeholder="是否可以开通创建圈子"></el-input>
    </el-form-item>
    <el-form-item label="是否开通了大会员" prop="isVip">
      <el-input v-model="dataForm.isVip" placeholder="是否开通了大会员"></el-input>
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
          isDefaultGrade: '',
          growthValue: '',
          signInGrowthValue: '',
          publishGrowthValue: '',
          commentGrowthValue: '',
          isVipDiscount: '',
          description: '',
          isCreateCircle: '',
          isVip: ''
        },
        dataRule: {
          name: [
            { required: true, message: '等级名称不能为空', trigger: 'blur' }
          ],
          isDefaultGrade: [
            { required: true, message: '是否为默认等级不能为空', trigger: 'blur' }
          ],
          growthValue: [
            { required: true, message: '当前等级升级需要的经验不能为空', trigger: 'blur' }
          ],
          signInGrowthValue: [
            { required: true, message: '签到增加的经验不能为空', trigger: 'blur' }
          ],
          publishGrowthValue: [
            { required: true, message: '发布作品增加的经验不能为空', trigger: 'blur' }
          ],
          commentGrowthValue: [
            { required: true, message: '评论增加的经验不能为空', trigger: 'blur' }
          ],
          isVipDiscount: [
            { required: true, message: '是否有会员价格优惠特权不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '等级描述不能为空', trigger: 'blur' }
          ],
          isCreateCircle: [
            { required: true, message: '是否可以开通创建圈子不能为空', trigger: 'blur' }
          ],
          isVip: [
            { required: true, message: '是否开通了大会员不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/member/membergrade/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.memberGrade.name
                this.dataForm.isDefaultGrade = data.memberGrade.isDefaultGrade
                this.dataForm.growthValue = data.memberGrade.growthValue
                this.dataForm.signInGrowthValue = data.memberGrade.signInGrowthValue
                this.dataForm.publishGrowthValue = data.memberGrade.publishGrowthValue
                this.dataForm.commentGrowthValue = data.memberGrade.commentGrowthValue
                this.dataForm.isVipDiscount = data.memberGrade.isVipDiscount
                this.dataForm.description = data.memberGrade.description
                this.dataForm.isCreateCircle = data.memberGrade.isCreateCircle
                this.dataForm.isVip = data.memberGrade.isVip
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
              url: this.$http.adornUrl(`/member/membergrade/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'isDefaultGrade': this.dataForm.isDefaultGrade,
                'growthValue': this.dataForm.growthValue,
                'signInGrowthValue': this.dataForm.signInGrowthValue,
                'publishGrowthValue': this.dataForm.publishGrowthValue,
                'commentGrowthValue': this.dataForm.commentGrowthValue,
                'isVipDiscount': this.dataForm.isVipDiscount,
                'description': this.dataForm.description,
                'isCreateCircle': this.dataForm.isCreateCircle,
                'isVip': this.dataForm.isVip
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
