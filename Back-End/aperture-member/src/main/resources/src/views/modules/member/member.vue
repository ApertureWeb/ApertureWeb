<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('member:member:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('member:member:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="用户id">
      </el-table-column>
      <el-table-column
        prop="username"
        header-align="center"
        align="center"
        label="用户名">
      </el-table-column>
      <el-table-column
        prop="telephone"
        header-align="center"
        align="center"
        label="手机号">
      </el-table-column>
      <el-table-column
        prop="password"
        header-align="center"
        align="center"
        label="密码">
      </el-table-column>
      <el-table-column
        prop="nickname"
        header-align="center"
        align="center"
        label="昵称">
      </el-table-column>
      <el-table-column
        prop="gender"
        header-align="center"
        align="center"
        label="性别[0：男  1：女]">
      </el-table-column>
      <el-table-column
        prop="birthday"
        header-align="center"
        align="center"
        label="生日">
      </el-table-column>
      <el-table-column
        prop="headUrl"
        header-align="center"
        align="center"
        label="头像url">
      </el-table-column>
      <el-table-column
        prop="email"
        header-align="center"
        align="center"
        label="邮箱">
      </el-table-column>
      <el-table-column
        prop="donut"
        header-align="center"
        align="center"
        label="甜甜圈，类比B站硬币">
      </el-table-column>
      <el-table-column
        prop="memberPoint"
        header-align="center"
        align="center"
        label="会员积分">
      </el-table-column>
      <el-table-column
        prop="updateTime"
        header-align="center"
        align="center"
        label="信息修改日期">
      </el-table-column>
      <el-table-column
        prop="description"
        header-align="center"
        align="center"
        label="认证信息/交由宣言">
      </el-table-column>
      <el-table-column
        prop="place"
        header-align="center"
        align="center"
        label="所在地">
      </el-table-column>
      <el-table-column
        prop="isVip"
        header-align="center"
        align="center"
        label="是否开通了会员">
      </el-table-column>
      <el-table-column
        prop="sign"
        header-align="center"
        align="center"
        label="个性签名">
      </el-table-column>
      <el-table-column
        prop="intrest"
        header-align="center"
        align="center"
        label="爱好">
      </el-table-column>
      <el-table-column
        prop="startTime"
        header-align="center"
        align="center"
        label="注册时间">
      </el-table-column>
      <el-table-column
        prop="online"
        header-align="center"
        align="center"
        label="在线时长(分钟)">
      </el-table-column>
      <el-table-column
        prop="status"
        header-align="center"
        align="center"
        label="状态">
      </el-table-column>
      <el-table-column
        prop="gradeLevel"
        header-align="center"
        align="center"
        label="等级">
      </el-table-column>
      <el-table-column
        prop="gradeUid"
        header-align="center"
        align="center"
        label="等级id">
      </el-table-column>
      <el-table-column
        prop="followCount"
        header-align="center"
        align="center"
        label="关注数">
      </el-table-column>
      <el-table-column
        prop="fansCount"
        header-align="center"
        align="center"
        label="粉丝数">
      </el-table-column>
      <el-table-column
        prop="isCertificated"
        header-align="center"
        align="center"
        label="是否实名认证">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './member-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/member/member/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/member/member/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
    }
  }
</script>
