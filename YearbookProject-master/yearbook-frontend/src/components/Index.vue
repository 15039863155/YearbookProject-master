<template>
  <div id="app">
    <el-container>
      <el-header>广告位招租：13060374742</el-header>
      <el-main>
        <el-row>
          <el-col :span="24">
            <h1>此处是导航</h1>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-table
              :data="tableData"
              border
              style="width: 100%">
              <el-table-column
                type="index"
                label="序号"
                width="50"
                :index="indexMethod">
              </el-table-column>
              <el-table-column
                prop="name"
                label="姓名"
                width="120">
              </el-table-column>
              <el-table-column
                prop="cellphone"
                label="手机号码"
                width="180">
              </el-table-column>
              <el-table-column
                prop="gender"
                label="性别"
                width="50">
              </el-table-column>
              <el-table-column
                prop="birthday"
                label="生日"
                width="180">
              </el-table-column>
              <el-table-column
                prop="email"
                label="电子邮件"
                width="250">
              </el-table-column>
              <el-table-column
                prop="address"
                label="地址">
              </el-table-column>
              <el-table-column
                fixed="right"
                label="操作"
                width="100">
                <template slot-scope="scope">
                  <!-- scope.row 为该行数据 -->
                  <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-pagination
              background
              layout="prev, pager, next"
              :total="totalPage">
            </el-pagination>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import {getInfo, queryPage} from "../api/request";
import router from "../router";

export default {
  name: "Index",
  beforeCreate() {
    // 在渲染当前 Vue 组件前，根据 Token 查询用户信息
    getInfo().then((res) => {
      // 通过响应获得当前登录用户信息
      console.log(res.data)
      this.yearbookVO = res.data
    })
  },
  created() {
    // 等到整个 VUE 对象渲染完毕后，进行加载分页列表
    queryPage(this.pageNum, this.pageSize).then((res) => {
      // 获取分页列表
      this.tableData = res.data.list
      // 设置最大值
      this.totalPage = res.data.totalPage
    }).catch();
  },
  data: function () {
    return {
      tableData: [],
      pageNum: 1,
      pageSize: 10,
      totalPage: 0
    }
  },
  methods: {
    indexMethod(index) {
      return index;
    },
    getByPage(pageNum, pageSize) {
      queryPage(pageNum, pageSize).then((res) => {
        console.log(res)
      }).catch()
    },
    handleClick(row) {
      // row 将会该行所对应的所有数据，那么根据该数据获得该用户的主键
      // 根据用户主键进行路由跳转
      router.push('/detail/' + row.id)
    }
  }
}
</script>

<style scoped>
</style>
