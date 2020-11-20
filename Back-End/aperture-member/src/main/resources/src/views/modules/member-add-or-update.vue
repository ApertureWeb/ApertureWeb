<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="dataForm.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="手机号" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="手机号"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="昵称" prop="nickname">
      <el-input v-model="dataForm.nickname" placeholder="昵称"></el-input>
    </el-form-item>
    <el-form-item label="性别[0：男  1：女]" prop="gender">
      <el-input v-model="dataForm.gender" placeholder="性别[0：男  1：女]"></el-input>
    </el-form-item>
    <el-form-item label="生日" prop="birthday">
      <el-input v-model="dataForm.birthday" placeholder="生日"></el-input>
    </el-form-item>
    <el-form-item label="头像url" prop="headUrl">
      <el-input v-model="dataForm.headUrl" placeholder="头像url"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="甜甜圈，类比B站硬币" prop="donut">
      <el-input v-model="dataForm.donut" placeholder="甜甜圈，类比B站硬币"></el-input>
    </el-form-item>
    <el-form-item label="会员积分" prop="memberPoint">
      <el-input v-model="dataForm.memberPoint" placeholder="会员积分"></el-input>
    </el-form-item>
    <el-form-item label="信息修改日期" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="信息修改日期"></el-input>
    </el-form-item>
    <el-form-item label="认证信息/交由宣言" prop="description">
      <el-input v-model="dataForm.description" placeholder="认证信息/交由宣言"></el-input>
    </el-form-item>
    <el-form-item label="所在地" prop="place">
      <el-input v-model="dataForm.place" placeholder="所在地"></el-input>
    </el-form-item>
    <el-form-item label="是否开通了会员" prop="isVip">
      <el-input v-model="dataForm.isVip" placeholder="是否开通了会员"></el-input>
    </el-form-item>
    <el-form-item label="个性签名" prop="sign">
      <el-input v-model="dataForm.sign" placeholder="个性签名"></el-input>
    </el-form-item>
    <el-form-item label="爱好" prop="interest">
      <el-input v-model="dataForm.interest" placeholder="爱好"></el-input>
    </el-form-item>
    <el-form-item label="等级" prop="gradeLevel">
      <el-input v-model="dataForm.gradeLevel" placeholder="等级"></el-input>
    </el-form-item>
    <el-form-item label="等级id" prop="gradeId">
      <el-input v-model="dataForm.gradeId" placeholder="等级id"></el-input>
    </el-form-item>
    <el-form-item label="是否实名认证" prop="isCertificated">
      <el-input v-model="dataForm.isCertificated" placeholder="是否实名认证"></el-input>
    </el-form-item>
    <el-form-item label="-2：封号中  -1：已注销  0：正常" prop="memberStatus">
      <el-input v-model="dataForm.memberStatus" placeholder="-2：封号中  -1：已注销  0：正常"></el-input>
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
          username: '',
          mobile: '',
          password: '',
          nickname: '',
          gender: '',
          birthday: '',
          headUrl: '',
          email: '',
          donut: '',
          memberPoint: '',
          updateTime: '',
          description: '',
          place: '',
          isVip: '',
          sign: '',
          interest: '',
          gradeLevel: '',
          gradeId: '',
          isCertificated: '',
          memberStatus: ''
        },
        dataRule: {
          username: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '手机号不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          nickname: [
            { required: true, message: '昵称不能为空', trigger: 'blur' }
          ],
          gender: [
            { required: true, message: '性别[0：男  1：女]不能为空', trigger: 'blur' }
          ],
          birthday: [
            { required: true, message: '生日不能为空', trigger: 'blur' }
          ],
          headUrl: [
            { required: true, message: '头像url不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          donut: [
            { required: true, message: '甜甜圈，类比B站硬币不能为空', trigger: 'blur' }
          ],
          memberPoint: [
            { required: true, message: '会员积分不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '信息修改日期不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '认证信息/交由宣言不能为空', trigger: 'blur' }
          ],
          place: [
            { required: true, message: '所在地不能为空', trigger: 'blur' }
          ],
          isVip: [
            { required: true, message: '是否开通了会员不能为空', trigger: 'blur' }
          ],
          sign: [
            { required: true, message: '个性签名不能为空', trigger: 'blur' }
          ],
          interest: [
            { required: true, message: '爱好不能为空', trigger: 'blur' }
          ],
          gradeLevel: [
            { required: true, message: '等级不能为空', trigger: 'blur' }
          ],
          gradeId: [
            { required: true, message: '等级id不能为空', trigger: 'blur' }
          ],
          isCertificated: [
            { required: true, message: '是否实名认证不能为空', trigger: 'blur' }
          ],
          memberStatus: [
            { required: true, message: '-2：封号中  -1：已注销  0：正常不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`//member/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.username = data.member.username
                this.dataForm.mobile = data.member.mobile
                this.dataForm.password = data.member.password
                this.dataForm.nickname = data.member.nickname
                this.dataForm.gender = data.member.gender
                this.dataForm.birthday = data.member.birthday
                this.dataForm.headUrl = data.member.headUrl
                this.dataForm.email = data.member.email
                this.dataForm.donut = data.member.donut
                this.dataForm.memberPoint = data.member.memberPoint
                this.dataForm.updateTime = data.member.updateTime
                this.dataForm.description = data.member.description
                this.dataForm.place = data.member.place
                this.dataForm.isVip = data.member.isVip
                this.dataForm.sign = data.member.sign
                this.dataForm.interest = data.member.interest
                this.dataForm.gradeLevel = data.member.gradeLevel
                this.dataForm.gradeId = data.member.gradeId
                this.dataForm.isCertificated = data.member.isCertificated
                this.dataForm.memberStatus = data.member.memberStatus
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
              url: this.$http.adornUrl(`//member/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'username': this.dataForm.username,
                'mobile': this.dataForm.mobile,
                'password': this.dataForm.password,
                'nickname': this.dataForm.nickname,
                'gender': this.dataForm.gender,
                'birthday': this.dataForm.birthday,
                'headUrl': this.dataForm.headUrl,
                'email': this.dataForm.email,
                'donut': this.dataForm.donut,
                'memberPoint': this.dataForm.memberPoint,
                'updateTime': this.dataForm.updateTime,
                'description': this.dataForm.description,
                'place': this.dataForm.place,
                'isVip': this.dataForm.isVip,
                'sign': this.dataForm.sign,
                'interest': this.dataForm.interest,
                'gradeLevel': this.dataForm.gradeLevel,
                'gradeId': this.dataForm.gradeId,
                'isCertificated': this.dataForm.isCertificated,
                'memberStatus': this.dataForm.memberStatus
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
