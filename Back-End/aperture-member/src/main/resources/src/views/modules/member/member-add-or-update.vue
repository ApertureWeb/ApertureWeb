<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="dataForm.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="手机号" prop="telephone">
      <el-input v-model="dataForm.telephone" placeholder="手机号"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="昵称" prop="nickname">
      <el-input v-model="dataForm.nickname" placeholder="昵称"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="gender">
      <el-input v-model="dataForm.gender" placeholder="性别"></el-input>
    </el-form-item>
    <el-form-item label="生日" prop="birthday">
      <el-input v-model="dataForm.birthday" placeholder="生日"></el-input>
    </el-form-item>
    <el-form-item label="头像" prop="header">
      <el-input v-model="dataForm.header" placeholder="头像"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="甜甜圈，类比B站硬币" prop="doughnut">
      <el-input v-model="dataForm.doughnut" placeholder="甜甜圈，类比B站硬币"></el-input>
    </el-form-item>
    <el-form-item label="注册时间" prop="startTime">
      <el-input v-model="dataForm.startTime" placeholder="注册时间"></el-input>
    </el-form-item>
    <el-form-item label="会员积分" prop="integration">
      <el-input v-model="dataForm.integration" placeholder="会员积分"></el-input>
    </el-form-item>
    <el-form-item label="信息修改日期" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="信息修改日期"></el-input>
    </el-form-item>
    <el-form-item label="在线时长(分钟)" prop="online">
      <el-input v-model="dataForm.online" placeholder="在线时长(分钟)"></el-input>
    </el-form-item>
    <el-form-item label="认证用户为认证信息 普通用户为交友宣言" prop="description">
      <el-input v-model="dataForm.description" placeholder="认证用户为认证信息 普通用户为交友宣言"></el-input>
    </el-form-item>
    <el-form-item label="所在地" prop="place">
      <el-input v-model="dataForm.place" placeholder="所在地"></el-input>
    </el-form-item>
    <el-form-item label="个性签名" prop="sign">
      <el-input v-model="dataForm.sign" placeholder="个性签名"></el-input>
    </el-form-item>
    <el-form-item label="爱好" prop="intrest">
      <el-input v-model="dataForm.intrest" placeholder="爱好"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="等级id" prop="gradeUid">
      <el-input v-model="dataForm.gradeUid" placeholder="等级id"></el-input>
    </el-form-item>
    <el-form-item label="用户所在圈子的标签id" prop="signUid">
      <el-input v-model="dataForm.signUid" placeholder="用户所在圈子的标签id"></el-input>
    </el-form-item>
    <el-form-item label="关注数" prop="followCount">
      <el-input v-model="dataForm.followCount" placeholder="关注数"></el-input>
    </el-form-item>
    <el-form-item label="粉丝数" prop="fansCount">
      <el-input v-model="dataForm.fansCount" placeholder="粉丝数"></el-input>
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
          telephone: '',
          password: '',
          nickname: '',
          gender: '',
          birthday: '',
          header: '',
          email: '',
          doughnut: '',
          startTime: '',
          integration: '',
          updateTime: '',
          online: '',
          description: '',
          place: '',
          sign: '',
          intrest: '',
          status: '',
          gradeUid: '',
          signUid: '',
          followCount: '',
          fansCount: ''
        },
        dataRule: {
          username: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          telephone: [
            { required: true, message: '手机号不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          nickname: [
            { required: true, message: '昵称不能为空', trigger: 'blur' }
          ],
          gender: [
            { required: true, message: '性别不能为空', trigger: 'blur' }
          ],
          birthday: [
            { required: true, message: '生日不能为空', trigger: 'blur' }
          ],
          header: [
            { required: true, message: '头像不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          doughnut: [
            { required: true, message: '甜甜圈，类比B站硬币不能为空', trigger: 'blur' }
          ],
          startTime: [
            { required: true, message: '注册时间不能为空', trigger: 'blur' }
          ],
          integration: [
            { required: true, message: '会员积分不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '信息修改日期不能为空', trigger: 'blur' }
          ],
          online: [
            { required: true, message: '在线时长(分钟)不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '认证用户为认证信息 普通用户为交友宣言不能为空', trigger: 'blur' }
          ],
          place: [
            { required: true, message: '所在地不能为空', trigger: 'blur' }
          ],
          sign: [
            { required: true, message: '个性签名不能为空', trigger: 'blur' }
          ],
          intrest: [
            { required: true, message: '爱好不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          gradeUid: [
            { required: true, message: '等级id不能为空', trigger: 'blur' }
          ],
          signUid: [
            { required: true, message: '用户所在圈子的标签id不能为空', trigger: 'blur' }
          ],
          followCount: [
            { required: true, message: '关注数不能为空', trigger: 'blur' }
          ],
          fansCount: [
            { required: true, message: '粉丝数不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/member/member/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.username = data.member.username
                this.dataForm.telephone = data.member.telephone
                this.dataForm.password = data.member.password
                this.dataForm.nickname = data.member.nickname
                this.dataForm.gender = data.member.gender
                this.dataForm.birthday = data.member.birthday
                this.dataForm.header = data.member.header
                this.dataForm.email = data.member.email
                this.dataForm.doughnut = data.member.doughnut
                this.dataForm.startTime = data.member.startTime
                this.dataForm.integration = data.member.integration
                this.dataForm.updateTime = data.member.updateTime
                this.dataForm.online = data.member.online
                this.dataForm.description = data.member.description
                this.dataForm.place = data.member.place
                this.dataForm.sign = data.member.sign
                this.dataForm.intrest = data.member.intrest
                this.dataForm.status = data.member.status
                this.dataForm.gradeUid = data.member.gradeUid
                this.dataForm.signUid = data.member.signUid
                this.dataForm.followCount = data.member.followCount
                this.dataForm.fansCount = data.member.fansCount
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
              url: this.$http.adornUrl(`/member/member/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'username': this.dataForm.username,
                'telephone': this.dataForm.telephone,
                'password': this.dataForm.password,
                'nickname': this.dataForm.nickname,
                'gender': this.dataForm.gender,
                'birthday': this.dataForm.birthday,
                'header': this.dataForm.header,
                'email': this.dataForm.email,
                'doughnut': this.dataForm.doughnut,
                'startTime': this.dataForm.startTime,
                'integration': this.dataForm.integration,
                'updateTime': this.dataForm.updateTime,
                'online': this.dataForm.online,
                'description': this.dataForm.description,
                'place': this.dataForm.place,
                'sign': this.dataForm.sign,
                'intrest': this.dataForm.intrest,
                'status': this.dataForm.status,
                'gradeUid': this.dataForm.gradeUid,
                'signUid': this.dataForm.signUid,
                'followCount': this.dataForm.followCount,
                'fansCount': this.dataForm.fansCount
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
