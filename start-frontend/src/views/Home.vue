<template>
  <el-container>
    <el-aside width="200px">
      <SideMenu />
    </el-aside>
    <el-container>
      <el-header style="height: 55px">
        <Strong>Authority</Strong>

        <div class="header-avatar block">
          <el-avatar
            :src="userInfo.avatar"
            class="el-avatar"
            size="medium"
          ></el-avatar>
          <el-dropdown>
            <span class="el-dropdown-link">
              {{ userInfo.username }}
              <i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item :underline="false">
                <router-link :to="{ name: 'UserCenter' }">个人中心</router-link>
              </el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-container>
        <el-main>
          <Tabs />
          <div style="margin: 0 15px">
            <router-view />
          </div>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
import SideMenu from "./inc/SideMenu.vue";
import Tabs from "./inc/Tabs.vue";
export default {
  name: "Home",
  data() {
    return {
      userInfo: {
        id: "",
        username: "",
        avatar: "",
      },
    };
  },
  components: {
    SideMenu,
    Tabs,
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    getUserInfo() {
      this.$axios.get("/sys/user/info").then((res) => {
        console.log("userInfo:" + res.data.data);
        this.userInfo = res.data.data;
      });
    },
    logout() {
      this.$axios.post("/logout").then((res) => {
        localStorage.clear();
        sessionStorage.clear();

        this.$store.commit("resetState");

        this.$router.push("/login");
      });
    },
  },
};
</script>

<style>
.el-dropdown-link {
  cursor: pointer;
}

.header-avatar {
  float: right;
  width: 210px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.el-container {
  padding: 0;
  margin: 0;
  height: 100%;
}

.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
}

body > .el-container {
  margin-bottom: 40px;
}

a {
  text-decoration: none;
}
</style>