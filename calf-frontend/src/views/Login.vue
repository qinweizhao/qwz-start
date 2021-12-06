<template>
  <div class="login">
    <el-form
        class="loginForm"
        :model="loginForm"
        :rules="rules"
        ref="loginForm"
        label-width="80px"
    >
      <h3 class="title" style="height: 40px">Authority</h3>
      <el-form-item label="账号" prop="username">
        <el-input v-model="loginForm.username" style="width: 230px"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="loginForm.password" type="password" style="width: 230px"></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input
            v-model="loginForm.captcha"
            style="width: 140px; float: left"
        ></el-input>
        <el-image :src="captchaImg" class="captchaImg" @click="getCaptcha"></el-image>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('loginForm')"
        >登录
        </el-button
        >
        <el-button @click="resetForm('loginForm')">重置</el-button>
      </el-form-item>
    </el-form>
    <!--  底部  -->
    <div class="loginFooter">
      <span>Copyright © 2021 qinweizhao All Rights Reserved.</span>
    </div>
  </div>
</template>
<script>

export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: "admin",
        password: "123456",
        captcha: "",
      },
      captchaImg: '',
      rules: {
        username: [{required: true, message: "请输入账号", trigger: "blur"}],
        password: [{required: true, message: "请输入密码", trigger: "blur"}],
        captcha: [
          {required: true, message: "请输入验证码", trigger: "blur"},
          {min: 5, max: 5, message: "长度为5个字符", trigger: "blur"},
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
              .post("/login", this.loginForm)
              .then((res) => {
                const jwt = res.data.data;
                // // 将 jwt 存储到应用 store 中
                this.$store.commit('SET_TOKEN', jwt);
                this.$router.push("/");
              })
              .catch(() => {
                this.getCaptcha();
                console.log("error submit!!!");
              });
        } else {
          this.getCaptcha();
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    getCaptcha() {
      this.$axios.get("sys/user/captcha").then(res => {
        console.log(res)
        this.captchaImg = res.data.data;
      });
    }
  },
  created() {
    this.getCaptcha();
  }
};
</script>

<style scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
  height: 100%;
}

.title {
  text-align: center;
}

.loginForm {
  border-radius: 6px;
  background: #ffffff;
  width: 350px;
  padding: 25px 25px 5px 25px;
}

.captchaImg {
  float: left;
  width: 88px;
  height: 40px;
  margin-left: 5px;
  border-radius: 4px;
}

.loginFooter {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-size: 12px;
  letter-spacing: 1px;
}
</style>