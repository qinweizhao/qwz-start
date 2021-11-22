<template>
  <div>
    <el-row :gutter="20">
      <!--用户数据-->
      <el-col :span="24" :xs="24">
        <el-form class="search" :inline="true">
          <el-form-item>
            <el-input
              v-model="searchForm.username"
              placeholder="用户名"
              clearable
            >
            </el-input>
          </el-form-item>

          <el-form-item>
            <el-button @click="getUserList">搜索</el-button>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="handleAdd">新增</el-button>
          </el-form-item>
          <el-form-item>
            <el-popconfirm
              title="这是确定批量删除吗？"
              @confirm="delHandle(null)"
            >
              <el-button type="danger" slot="reference" :disabled="delBtlStatu"
                >批量删除</el-button
              >
            </el-popconfirm>
          </el-form-item>
        </el-form>

        <el-table
          v-loading="loading"
          :data="tableData"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column
            label="用户编号"
            align="center"
            key="userId"
            prop="userId"
          />
          <el-table-column
            label="头像"
            align="center"
            key="phonenumber"
            prop="phonenumber"
            width="130"
          >
            <template slot-scope="scope">
              <el-avatar size="small" :src="scope.row.avatar"></el-avatar>
            </template>
          </el-table-column>
          <el-table-column
            label="用户名称"
            align="center"
            key="username"
            prop="username"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            label="用户昵称"
            align="center"
            key="nickName"
            prop="nickName"
            :show-overflow-tooltip="true"
          />

          <el-table-column label="状态" align="center" key="status">
            <template slot-scope="scope" v-if="scope.row.userId !== 1">
              <el-switch
                v-model="scope.row.status"
                active-value="0"
                inactive-value="1"
                @change="handleStatusChange(scope.row)"
              ></el-switch>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            align="center"
            width="260"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope" v-if="scope.row.userId !== 1">
              <el-button
                size="mini"
                type="primary"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row.userId)"
                >修改</el-button
              >
              <el-button
                size="mini"
                type="primary"
                icon="el-icon-delete"
                @click="delHandle(scope.row.userId)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>

        <el-pagination
          v-show="total > 0"
          :total="total"
          :current-page="current"
          :page-size="size"
          @pagination="getUserList()"
        />

        <!-- 添加或修改参数配置对话框 -->
        <el-dialog :visible.sync="dialogVisible" append-to-body width="550px">
          <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-row>
              <!-- <el-col :span="24">
                <template>
                  <el-avatar size="large" :src="editForm.avatar"></el-avatar>
                </template>
              </el-col> -->
              <el-col :span="12">
                <el-form-item label="账号" prop="username">
                  <el-input
                    v-model="form.username"
                    placeholder="请输入账号"
                    maxlength="30"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="昵称" prop="nickName">
                  <el-input
                    v-model="form.nickName"
                    placeholder="请输入昵称"
                    maxlength="30"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item
                  v-if="form.userId == undefined"
                  label="密码"
                  prop="password"
                >
                  <el-input
                    v-model="form.password"
                    placeholder="请输入密码"
                    type="password"
                    maxlength="20"
                    show-password
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="状态">
                  <el-radio-group v-model="form.status">
                    <el-radio :label="'0'">启用</el-radio>
                    <el-radio :label="'1'">禁用</el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="角色">
                  <el-select
                    v-model="form.roleIds"
                    multiple
                    placeholder="请选择"
                  >
                    <el-option
                      v-for="item in roleOptions"
                      :key="item.roleId"
                      :label="item.roleName"
                      :value="item.roleId"
                      :disabled="item.status == 1"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      // 遮罩层
      loading: true,
      size: 5,
      current: 1,
      searchForm: {},
      total: 0,
      tableData: [],
      form: { status: "0" },
      delBtlStatu: true,
      dialogVisible: false,
      multipleSelection: [],
      roleOptions: [],
      // 表单校验
      rules: {
        username: [
          { required: true, message: "用户名称不能为空", trigger: "blur" },
          {
            min: 2,
            max: 20,
            message: "用户名称长度必须介于 2 和 20 之间",
            trigger: "blur",
          },
        ],
        nickName: [
          { required: true, message: "用户昵称不能为空", trigger: "blur" },
        ],
        password: [
          { required: true, message: "用户密码不能为空", trigger: "blur" },
          {
            min: 5,
            max: 20,
            message: "用户密码长度必须介于 5 和 20 之间",
            trigger: "blur",
          },
        ],
        status: [
          { required: true, message: "用户昵称不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getUserList();
  },
  methods: {
    getUserList() {
      this.$axios
        .get("/sys/user/page", {
          params: {
            username: this.searchForm.username,
            current: this.current,
            size: this.size,
          },
        })
        .then((res) => {
          this.tableData = res.data.data.records;
          this.size = res.data.data.size;
          this.current = res.data.data.current;
          this.total = res.data.data.total;
          this.loading = false;
        });
    },
    handleUpdate(id) {
      this.$axios.get("/sys/user/info/" + id).then((res) => {
        this.form = res.data.data;
        this.form.roleIds = res.data.data.roleIds;
        this.dialogVisible = true;
        this.$axios.get("/sys/role/list").then((res) => {
          console.log(res);
          this.roleOptions = res.data.data;
        });
      });
    },
    handleAdd() {
      this.form = {};
      this.dialogVisible = true;
      this.$axios.get("/sys/role/list").then((res) => {
        console.log(res);
        this.roleOptions = res.data.data;
      });
    },
    submitForm() {
      if (this.form.userId != undefined) {
        this.$axios.post("/sys/user/update", this.form).then((res) => {
          this.dialogVisible = false;
          this.$message({
            showClose: true,
            message: "修改成功",
            type: "success",
            onClose: () => {
              this.getUserList();
            },
          });
        });
      } else {
        this.$axios.post("/sys/user/save", this.form).then((res) => {
          this.dialogVisible = false;
          this.$message({
            showClose: true,
            message: "新增成功",
            type: "success",
            onClose: () => {
              this.getUserList();
            },
          });
        });
      }
    },
    cancel() {
      this.dialogVisible = false;
      this.form = {};
    },
    handleSelectionChange(val) {
      console.log("勾选");
      console.log(val);
      this.multipleSelection = val;
      this.delBtlStatu = val.length == 0;
    },
    delHandle(id) {
      var ids = [];

      if (id) {
        ids.push(id);
      } else {
        this.multipleSelection.forEach((row) => {
          ids.push(row.userId);
        });
      }
      console.log("勾选ids");
      console.log(ids);

      this.$axios.post("/sys/user/delete", ids).then((res) => {
        this.$message({
          showClose: true,
          message: "恭喜你，操作成功",
          type: "success",
          onClose: () => {
            this.getUserList();
          },
        });
      });
    },
  },
};
</script>

<style scoped>
.search {
  float: left;
}
</style>