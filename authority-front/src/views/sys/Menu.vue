<template>
  <div>
    <el-row :gutter="20">
      <!--用户数据-->
      <el-col :span="24" :xs="24">
        <el-form class="search" :inline="true">
          <el-form-item>
            <el-button type="primary" @click="handleAdd">新增</el-button>
          </el-form-item>
        </el-form>
        <el-table
          v-loading="loading"
          :data="tableData"
          style="width: 100%; margin-bottom: 20px"
          row-key="menuId"
          default-expand-all
          :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
        >
          <el-table-column
            label="菜单名称"
            align="center"
            key="menuName"
            prop="menuName"
          >
          </el-table-column>
          <el-table-column label="菜单类型" align="center" prop="menuType">
            <template slot-scope="scope">
              <el-tag size="small" v-if="scope.row.menuType === '0'"
                >目录</el-tag
              >
              <el-tag
                size="small"
                v-else-if="scope.row.menuType === '1'"
                type="success"
                >菜单</el-tag
              >
              <el-tag
                size="small"
                v-else-if="scope.row.menuType === '2'"
                type="info"
                >按钮</el-tag
              >
            </template>
          </el-table-column>
          <el-table-column
            label="路由地址"
            align="center"
            key="path"
            prop="path"
          >
          </el-table-column>
          <el-table-column label="图标" align="center" key="icon" prop="icon">
          </el-table-column>
          <el-table-column
            label="组件路径"
            align="center"
            key="component"
            prop="component"
          >
          </el-table-column>
          <el-table-column
            label="权限标识"
            align="center"
            key="permission"
            prop="permission"
          >
          </el-table-column>
          <el-table-column label="状态" align="center" key="status">
            <template slot-scope="scope">
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
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="primary"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row.menuId)"
                >修改</el-button
              >
              <el-button
                size="mini"
                type="primary"
                icon="el-icon-delete"
                @click="delHandle(scope.row.menuId)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>

        <!-- 添加或修改参数配置对话框 -->
        <el-dialog :visible.sync="dialogVisible" append-to-body width="650px">
          <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-row>
              <el-col :span="12">
                <el-form-item label="名称" prop="menuName">
                  <el-input
                    v-model="form.menuName"
                    placeholder="请输入菜单名称"
                    maxlength="30"
                  />
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="标识" prop="menuType">
                  <el-input
                    v-model="form.permission"
                    placeholder="请输入权限标识"
                    maxlength="30"
                  />
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="路由" prop="path">
                  <el-input
                    v-model="form.path"
                    placeholder="请输入路由地址"
                    maxlength="30"
                  />
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="组件" prop="component">
                  <el-input
                    v-model="form.component"
                    placeholder="请输入组件路径"
                    maxlength="30"
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
                <el-form-item label="类型">
                  <el-radio-group v-model="form.menuType">
                    <el-radio :label="'M'">目录</el-radio>
                    <el-radio :label="'C'">菜单</el-radio>
                    <el-radio :label="'F'">按钮</el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="上级菜单" prop="parentId">
                  <el-select
                    v-model="form.parentId"
                    placeholder="请选择上级菜单"
                  >
                    <template v-for="item in tableData">
                      <el-option
                        :key="item.menuId"
                        :label="item.menuName"
                        :value="item.menuId"
                      ></el-option>
                      <template v-for="child in item.children">
                        <el-option :label="child.menuName" :value="child.menuId" :key="child.menuId">
                          <span>{{ "- " + child.menuName }}</span>
                        </el-option>
                      </template>
                    </template>
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
      searchForm: {},
      tableData: [],
      form: { status: "0" },
      delBtlStatu: true,
      dialogVisible: false,
      multipleSelection: [],
      menuOptions: [],
      tableData: [],
      // 表单校验
      rules: {
        menuName: [
          { required: true, message: "菜单名称不能为空", trigger: "blur" },
        ],
        menuKey: [
          { required: true, message: "菜单标识不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getMenuList();
  },
  methods: {
    getMenuList() {
      this.$axios.get("/sys/menu/list").then((res) => {
        this.tableData = res.data.data;
        this.loading = false;
      });
    },
    handleUpdate(id) {
      this.$axios.get("/sys/menu/info/" + id).then((res) => {
        this.form = res.data.data;
        this.form.menuIds = res.data.data.menuIds;
        this.dialogVisible = true;
        this.$axios.get("/sys/menu/list").then((res) => {
          console.log(res);
          this.menuOptions = res.data.data;
        });
      });
    },
    handleAdd() {
      this.form = {};
      this.dialogVisible = true;
      this.$axios.get("/sys/menu/list").then((res) => {
        console.log(res);
        this.menuOptions = res.data.data;
      });
    },
    submitForm() {
      if (this.form.menuId != undefined) {
        this.$axios.post("/sys/menu/update", this.form).then((res) => {
          this.dialogVisible = false;
          this.$message({
            showClose: true,
            message: "修改成功",
            type: "success",
            onClose: () => {
              this.getMenuList();
            },
          });
        });
      } else {
        this.$axios.post("/sys/menu/save", this.form).then((res) => {
          this.dialogVisible = false;
          this.$message({
            showClose: true,
            message: "新增成功",
            type: "success",
            onClose: () => {
              this.getMenuList();
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
      this.$axios.post("/sys/menu/delete/"+id).then((res) => {
        this.$message({
          showClose: true,
          message: "恭喜你，操作成功",
          type: "success",
          onClose: () => {
            this.getMenuList();
          },
        });
      });
    }
  },
};
</script>

<style scoped>
.search {
  float: left;
}
</style>