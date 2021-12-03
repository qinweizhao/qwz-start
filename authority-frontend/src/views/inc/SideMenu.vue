<template>
  <el-menu
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
  >
    <router-link to="/index" @click="selectMenu({name: 'Index', title: '首页'})">
      <el-menu-item index="Index">
        <template>
          <i class="el-icon-s-home"></i>
          <span slot="title">首页</span>
        </template>
      </el-menu-item>
    </router-link>

    <el-submenu
        default-active="Index"
        :index="menu.menuName"
        v-for="menu in menuList"
        :key="menu.menuId"
    >
      <template slot="title">
        <i :class="menu.icon"></i> <span>{{ menu.menuName }}</span>
      </template>
      <el-menu-item-group>
        <router-link :to="item.path" v-for="item in menu.children" :key="item.name">
          <el-menu-item :index="item.menuName">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.menuName }}</span>
            </template>
          </el-menu-item>
        </router-link>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "SideMenu",
  data(){
    return{

    }
  },
  computed:{
    menuList:{
      get(){
        return this.$store.state.menus.menuList
      }
    }
  },
  methods:{
    selectMenu(item){
      this.$store.commit("addTab", item)
    }
  }
}      
</script>

<style scoped>
.el-menu-vertical-demo {
  height: 100%;
}

a {
  text-decoration: none;
}
</style>