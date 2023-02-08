<template>
  <div id="app">
    <el-form ref="registerForm" :model="registerVO" :rules="rules"  :inline="true">
      <div class="container">
        <el-row>
          <el-col style="text-align: center;">
            <h1>个人通讯录信息平台</h1>
          </el-col>
        </el-row>
        <el-row style="margin-bottom: 60px;">
          <el-col style="text-align: center;">
            <span>【新用户注册】</span>
          </el-col>
        </el-row>
        <el-row class="el-row">
          <el-col :span="24">
            <el-form-item label="姓名：" prop="name" label-width="100px">
              <el-input v-model="registerVO.name" class="input-form" placeholder="请填写您的姓名"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row class="el-row">
          <el-col :span="24">
            <el-form-item label="手机号码：" prop="cellphone" label-width="100px">
              <el-input v-model="registerVO.cellphone" class="input-form" placeholder="请填写您的手机号码"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item label="验证码：" label-width="100px" prop="sms">
              <el-input v-model="registerVO.sms" placeholder="请填写您的短信验证码"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-button type="primary" :loading="isLoading" @click="sendSms">{{btnMsg}}</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="登录密码：" label-width="100px" prop="password">
              <el-input v-model="registerVO.password" type="password" class="input-form" placeholder="请填写您的登录密码"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="密码确认：" label-width="100px" prop="confirmPassword">
              <el-input v-model="registerVO.confirmpassword" type="password" class="input-form" placeholder="请再次确认您的登录密码"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="16">
            <el-link type="info" @click="toLogin">已经有账号？去登录...</el-link>
          </el-col>
          <el-col :span="8" style="text-align: right; padding-right: 10px;">
            <el-button type="primary" icon="el-icon-user" :loading="isRegister" @click="registerYearbook('registerForm')">注册</el-button>
          </el-col>
        </el-row>
      </div>
    </el-form>
  </div>
</template>

<script>
import {registerYearbook, sendSms} from "../api/request";
import router from "../router";

export default {
  name: "Register",
  data: function() {
    const validateName = (rule, value, callback) => {
      // 设置姓名校验正则表达式
      const nameRegex = /^[\u4e00-\u9fa5A-Za-z]{2,}$/
      if (nameRegex.test(value)) {
        callback()
      } else {
        callback(new Error('请填写正确的姓名，至少可由两位汉字、大写、小写字母组成'))
      }
    }
    const validateCellphone = (rule, value, callback) => {
      // 进行手机号码校验
      // 设置手机号码正则表达式
      const cellphoneRegex = /^1[0-9]{10}$/
      // 使用正则表达式进行校验
      if (cellphoneRegex.test(value)) {
        // 正则校验通过则手机号码格式填写正确
        callback()
      } else {
        // 校验失败，手机号码格式填写错误
        callback(new Error("请填写正确的手机号码"))
      }
    }
    // 密码校验规则
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
    // 密码确认校验规则
    const validateConfirmPassword = (rule, value, callback) => {
      // 比较两个密码是否相同
      if (this.registerVO.password == this.registerVO.confirmpassword) {
        // 两个密码相同
        callback()
      } else {
        callback(new Error('两次填写的密码不一致'))
      }
    }

    return {
      btnMsg: '获得验证码',
      isLoading: false,
      isRegister: false,
      registerVO: {
        name: "",
        cellphone: "",
        sms: "",
        password: "",
        confirmpassword: ""
      },
      rules: {
        name: [
          {required: true, trigger: 'blur', validator: validateName}
        ],
        cellphone: [
          {required: true, trigger: 'blur', validator: validateCellphone}
        ],
        sms: [
          {required: true, message: '请填写短信验证码', trigger: 'blur'}
        ],
        password: [
          {required: true, trigger: 'blur', validator: validatePassword}
        ],
        confirmPassword: [
          {required: true, trigger: 'blur', validator: validateConfirmPassword}
        ]
      }
    }
  },
  methods: {
    sendSms: function() {
      // 校验能够获得已经填写的手机号码
      // 设置手机号码正则表达式
      let cellphoneRegex = /^1[0-9]{10}$/
      if (cellphoneRegex.test(this.registerVO.cellphone)) {
        // 手机号码已填，符合对应的手机号码规则，可以正常发送短信
        // 将该按钮变为不可点击
        this.isLoading = true
        // 倒计时 60s
        this.btnMsg = '60s 后再次发送'

        // 进行异步短信发送
        sendSms(this.registerVO.cellphone).then((result) => {
          if (result.code == 20000) {
            // 产生成功的提示框
            this.$message.success(result.message)
          } else {
            this.$message.error(result.message)
          }
        });

        let count = 60;
        let countdown = setInterval(() => {
          count--
          this.btnMsg = count + "s 后再次发送"
        }, 1000);
        // 当 count 计时完毕，取消定时器
        setTimeout(() => {
          clearInterval(countdown)
          this.btnMsg = '获得验证码'
          this.isLoading = false
        }, 10000)
      } else {
        // 手机号码错误，提示用户
        this.$message.error('请填写正确的手机号码');
      }
    },
    registerYearbook: function (registerForm) {

      // 进行用户注册
      // 判断此时所有的校验均已通过
      this.$refs[registerForm].validate((valid) => {
        if (valid) {
          // 所有校验通过，可以进行异步注册，获得 Token 信息
          this.isRegister = true
          registerYearbook(this.registerVO).then((result) => {
            if (result.code == '20000') {
              // 注册成功
              this.$message.success(result.message)
              // 定向到登录页面
              router.push('/login')
            } else {
              this.isRegister = false
              this.$message.error(result.message)
            }
          })
        }
      })
    },
    toLogin: function () {
      // 路由跳转到登录界面
      router.push("/login")
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
