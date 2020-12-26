<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="当前等级" prop="gradeLevel">
      <el-input v-model="dataForm.gradeLevel" placeholder="当前等级"></el-input>
    </el-form-item>
    <el-form-item label="等级名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="等级名称"></el-input>
    </el-form-item>
    <el-form-item label="等级描述" prop="description">
      <el-input v-model="dataForm.description" placeholder="等级描述"></el-input>
    </el-form-item>
    <el-form-item label="最高等级" prop="topGrade">
      <el-input v-model="dataForm.topGrade" placeholder="最高等级"></el-input>
    </el-form-item>
    <el-form-item label="是否为默认等级" prop="isDefaultGrade">
      <el-input v-model="dataForm.isDefaultGrade" placeholder="是否为默认等级"></el-input>
    </el-form-item>
    <el-form-item label="是否可以开通创建圈子" prop="isCreateCircle">
      <el-input v-model="dataForm.isCreateCircle" placeholder="是否可以开通创建圈子"></el-input>
    </el-form-item>
    <el-form-item label="是否有会员价格优惠特权" prop="isVipDiscount">
      <el-input v-model="dataForm.isVipDiscount" placeholder="是否有会员价格优惠特权"></el-input>
    </el-form-item>
    <el-form-item label="当前等级升级需要的经验" prop="growthValue">
      <el-input v-model="dataForm.growthValue" placeholder="当前等级升级需要的经验"></el-input>
    </el-form-item>
    <el-form-item label="签到增加的经验" prop="signInGrowthValue">
      <el-input v-model="dataForm.signInGrowthValue" placeholder="签到增加的经验"></el-input>
    </el-form-item>
    <el-form-item label="评论增加的经验" prop="commentGrowthValue">
      <el-input v-model="dataForm.commentGrowthValue" placeholder="评论增加的经验"></el-input>
    </el-form-item>
    <el-form-item label="发布作品增加的经验" prop="publishGrowthValue">
      <el-input v-model="dataForm.publishGrowthValue" placeholder="发布作品增加的经验"></el-input>
    </el-form-item>
    <el-form-item label="在线10分钟增加的经验" prop="onlineTenValue">
      <el-input v-model="dataForm.onlineTenValue" placeholder="在线10分钟增加的经验"></el-input>
    </el-form-item>
    <el-form-item label="在线30分钟增加的经验" prop="onlineThirtyValue">
      <el-input v-model="dataForm.onlineThirtyValue" placeholder="在线30分钟增加的经验"></el-input>
    </el-form-item>
    <el-form-item label="在线60分钟增加的经验" prop="onlineSixtyValue">
      <el-input v-model="dataForm.onlineSixtyValue" placeholder="在线60分钟增加的经验"></el-input>
    </el-form-item>
    <el-form-item label="当前等级创建圈子限制次数" prop="createCircleLimit">
      <el-input v-model="dataForm.createCircleLimit" placeholder="当前等级创建圈子限制次数"></el-input>
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
          gradeLevel: '',
          name: '',
          description: '',
          topGrade: '',
          isDefaultGrade: '',
          isCreateCircle: '',
          isVipDiscount: '',
          growthValue: '',
          signInGrowthValue: '',
          commentGrowthValue: '',
          publishGrowthValue: '',
          onlineTenValue: '',
          onlineThirtyValue: '',
          onlineSixtyValue: '',
          createCircleLimit: ''
        },
        dataRule: {
          gradeLevel: [
            { required: true, message: '当前等级不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '等级名称不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '等级描述不能为空', trigger: 'blur' }
          ],
          topGrade: [
            { required: true, message: '最高等级不能为空', trigger: 'blur' }
          ],
          isDefaultGrade: [
            { required: true, message: '是否为默认等级不能为空', trigger: 'blur' }
          ],
          isCreateCircle: [
            { required: true, message: '是否可以开通创建圈子不能为空', trigger: 'blur' }
          ],
          isVipDiscount: [
            { required: true, message: '是否有会员价格优惠特权不能为空', trigger: 'blur' }
          ],
          growthValue: [
            { required: true, message: '当前等级升级需要的经验不能为空', trigger: 'blur' }
          ],
          signInGrowthValue: [
            { required: true, message: '签到增加的经验不能为空', trigger: 'blur' }
          ],
          commentGrowthValue: [
            { required: true, message: '评论增加的经验不能为空', trigger: 'blur' }
          ],
          publishGrowthValue: [
            { required: true, message: '发布作品增加的经验不能为空', trigger: 'blur' }
          ],
          onlineTenValue: [
            { required: true, message: '在线10分钟增加的经验不能为空', trigger: 'blur' }
          ],
          onlineThirtyValue: [
            { required: true, message: '在线30分钟增加的经验不能为空', trigger: 'blur' }
          ],
          onlineSixtyValue: [
            { required: true, message: '在线60分钟增加的经验不能为空', trigger: 'blur' }
          ],
          createCircleLimit: [
            { required: true, message: '当前等级创建圈子限制次数不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`//grade/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.gradeLevel = data.grade.gradeLevel
                this.dataForm.name = data.grade.name
                this.dataForm.description = data.grade.description
                this.dataForm.topGrade = data.grade.topGrade
                this.dataForm.isDefaultGrade = data.grade.isDefaultGrade
                this.dataForm.isCreateCircle = data.grade.isCreateCircle
                this.dataForm.isVipDiscount = data.grade.isVipDiscount
                this.dataForm.growthValue = data.grade.growthValue
                this.dataForm.signInGrowthValue = data.grade.signInGrowthValue
                this.dataForm.commentGrowthValue = data.grade.commentGrowthValue
                this.dataForm.publishGrowthValue = data.grade.publishGrowthValue
                this.dataForm.onlineTenValue = data.grade.onlineTenValue
                this.dataForm.onlineThirtyValue = data.grade.onlineThirtyValue
                this.dataForm.onlineSixtyValue = data.grade.onlineSixtyValue
                this.dataForm.createCircleLimit = data.grade.createCircleLimit
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
              url: this.$http.adornUrl(`//grade/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'gradeLevel': this.dataForm.gradeLevel,
                'name': this.dataForm.name,
                'description': this.dataForm.description,
                'topGrade': this.dataForm.topGrade,
                'isDefaultGrade': this.dataForm.isDefaultGrade,
                'isCreateCircle': this.dataForm.isCreateCircle,
                'isVipDiscount': this.dataForm.isVipDiscount,
                'growthValue': this.dataForm.growthValue,
                'signInGrowthValue': this.dataForm.signInGrowthValue,
                'commentGrowthValue': this.dataForm.commentGrowthValue,
                'publishGrowthValue': this.dataForm.publishGrowthValue,
                'onlineTenValue': this.dataForm.onlineTenValue,
                'onlineThirtyValue': this.dataForm.onlineThirtyValue,
                'onlineSixtyValue': this.dataForm.onlineSixtyValue,
                'createCircleLimit': this.dataForm.createCircleLimit
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
