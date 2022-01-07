<p align="center">
  <a class="logo" href="https://github.com/qinweizhao/qwz-start">
    <img src="https://cdn.jsdelivr.net/gh/qinweizhao/qwz-start@master/logo.png" height="80" width="45%" alt="Authority">
  </a>
</p>

<p align="center">
👉 <a href="https://www.qinweizhao.com">https://www.qinweizhao.com</a> 👈
</p>

<p align="center">
  <a href="https://github.com/qinweizhao/qwz-start" target="_blank">
    <img src="https://img.shields.io/github/manifest-json/v/qinweizhao/qwz-start" alt="Release"/>
  </a>
</p>


![Alt](https://repobeats.axiom.co/api/embed/a9d167d82b691b1c1e7ad622262698a15b6fc146.svg "Repobeats analytics image")

## 1、简介

calf-fast 是一个前后端分离的权限管理系统，是从零开始搭建的一个项目骨架。

### 1.技术栈

- 前端采用 Vue、Element UI、Axios 。
- 后端采用 Spring Boot、SpringSecurity 、Jwt、Hibernate validatior、MyBatis Plus 。

### 2.仓库结构

```
qwz-start
├─calf-backend  后端源码（已内置前端代码）
│
├─calf-frontend 前端源码
│ 
├─calf-resource 项目资源
```

### 3.实现功能

- 用户管理：用户是系统操作者，该功能主要完成系统用户配置。
- 角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
- 菜单管理：配置系统菜单，操作权限，按钮权限标识等。
- 动态加载权限菜单，多方式轻松权限控制。

## 2、使用

- 启动项目

  1. 用 calf-resource 中的 sql 文件创建数据库。

  2. 更改 calf-back 配置文件中的数据库配置（根据实际情况更改）

  3. 项目地址：http://ip:port/index.html
- 二次开发
  1. 这个需要部署前端。前端在运行时，需要准备一个 nodejs。
  2. 前端项目打开后，需要在项目根目录下，执行 npm install （默认安装比较慢，可以修改为淘宝的源。[下载源修改为淘宝](https://mp.weixin.qq.com/s/HWRYAR16vLE1XFep6_i1tA)）。
  3. npm install 执行成功后，再执行 npm run serve 启动前端项目。
  4. 二次开发完成后，执行 npm run build 前端编译打包。把打包后的文件拷贝到后端。

## 3、演示

<table>
    <tr>
        <td><img src="https://cdn.jsdelivr.net/gh/qinweizhao/qwz-start/calf-resource/img/2021-11-24_142100.png"/></td>
        <td><img src="https://cdn.jsdelivr.net/gh/qinweizhao/qwz-start/calf-resource/img/2021-11-24_142259.png"/></td>
    </tr>
    <tr>
        <td><img src="https://cdn.jsdelivr.net/gh/qinweizhao/qwz-start/calf-resource/img/2021-11-24_142319.png"/></td>
        <td><img src="https://cdn.jsdelivr.net/gh/qinweizhao/qwz-start/calf-resource/img/2021-11-24_142359.png"/></td>
    </tr>
</table>
