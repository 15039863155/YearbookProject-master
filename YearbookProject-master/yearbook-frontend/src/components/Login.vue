<template>
  <div id="app">
    <el-form ref="loginForm" :model="loginVO" :rules="rules" :inline="true">
      <div class="container">
        <el-row>
          <el-col style="text-align: center;">
            <h1>个人通讯录信息平台</h1>
          </el-col>
        </el-row>
        <el-row style="margin-bottom: 60px;">
          <el-col style="text-align: center;">
            <span>【用户登录】</span>
          </el-col>
        </el-row>
        <el-row class="el-row">
          <el-col :span="24">
            <el-form-item label="手机号码：" label-width="100px" prop="cellphone">
              <el-input v-model="loginVO.cellphone" class="input-form" placeholder="请填写您的手机号码"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="登录密码：" label-width="100px" prop="password">
              <el-input v-model="loginVO.password" type="password" class="input-form" placeholder="请填写您的登录密码"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="16">
            <el-link type="info" @click="toRegister">还没有账号？去注册...</el-link>
          </el-col>
          <el-col :span="8" style="text-align: right; padding-right: 10px;">
            <el-button type="primary" icon="el-icon-user" :loading="isLogin" @click="login('loginForm')">登录</el-button>
          </el-col>
        </el-row>
      </div>
    </el-form>
  </div>
</template>

<script>
import router from "../router";
import {loginYearbook} from "../api/request";
import {setToken} from "../util/auth";

export default {
  name: "Login",
  data: function () {
    // 定义手机号码校验规则
    const validateCellphone = (rule, value, callback) => {
      // 定义手机号码正则表达式
      const cellphoneregex = /^1[0-9]{10}$/
      // 匹配用户所给定信息
      if (cellphoneregex.test(value)) {
        callback();
      } else {
        callback(new Error('请填写正确格式的手机号码'));
      }
    }
    // 定义登录密码校验规则
    const validatePassword = (rule, value, callback) => {
      // 设置登录密码校验正则表达式
      const passwordRegex = /^[A-Za-z0-9]{6,20}$/
      // 校验登录密码
      if (passwordRegex.test(value)) {
        // 校验通过
        callback()
      } else {
        callback(new Error('请填写正确的登录密码，可由 6 ~ 20 位大写、小写字母或数字组成'))
      }
    }
    return {
      isLogin: false,
      loginVO: {
        cellphone: "",
        password: ""
      },
      rules: {
        cellphone: [
          {required: true, trigger: 'blur', validator: validateCellphone}
        ],
        password: [
          {required: true, trigger: 'blur', validator: validatePassword}
        ]
      }
    }
  },
  methods: {
    toRegister: function () {
      // 进行路由跳转到注册页面
      router.push('/register');
    },
    login: function (loginForm) {
      // 判断此时所有的校验均已通过
      this.$refs[loginForm].validate((valid) => {
        // 判断此时校验是否通过
        if (valid) {
          // 如果校验通过，则进行用户登录
          // 将登录按钮变为不可以点
          this.isLogin = true
          // 获得用户所提交的登录信息，进行异步登录
          loginYearbook(this.loginVO).then((res) => {
            // 登录成功
            // 获得用户所提交的 Token 信息
            const token = res.data;
            setToken(token)
            // 页面跳转到都页面
            this.$message.success("登录成功")
            router.push('/index')
          }).catch((error) => {
            // 登录失败
            // 将登录按钮重置为可点击状态
            this.isLogin = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.container {
  width: 510px;
  margin: 80px auto;
}

.el-row {
  width: 100%;
}

.input-form {
  width: 400px;
}
</style>
