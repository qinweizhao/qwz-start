<template>
  <div>
    <el-row :gutter="20">
      <!--用户数据-->
      <el-col :span="24" :xs="24">
        <el-form class="search" :inline="true">
          <el-form-item>
            <el-input
              v-model="searchForm.roleName"
              placeholder="角色名"
              clearable
            >
            </el-input>
          </el-form-item>

          <el-form-item>
            <el-button @click="getRoleList">搜索</el-button>
          </el-form-item>

          <el-form-item>
            <el-button
              type="primary"
              @click="handleAdd"
              v-if="hasAuthority('sys:role:save')"
              >新增</el-button
            >
          </el-form-item>
          <el-form-item>
            <el-popconfirm
              title="这是确定批量删除吗？"
              @confirm="delHandle(null)"
            >
              <el-button
                type="danger"
                slot="reference"
                :disabled="delBtlStatu"
                v-if="hasAuthority('sys:role:delete')"
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
            label="角色编号"
            align="center"
            key="roleId"
            prop="roleId"
          />
          <el-table-column
            label="角色名称"
            align="center"
            key="roleName"
            prop="roleName"
          >
          </el-table-column>
          <el-table-column
            label="角色标识"
            align="center"
            key="roleKey"
            prop="roleKey"
            :show-overflow-tooltip="true"
          />
          <el-table-column label="状态" align="center" key="status">
            <template slot-scope="scope">
              <el-tag
                size="small"
                v-if="scope.row.status === '0'"
                type="success"
                >正常</el-tag
              >
              <el-tag
                size="small"
                v-else-if="scope.row.status === '1'"
                type="danger"
                >禁用</el-tag
              >
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
                @click="handleUpdate(scope.row.roleId)"
                v-if="hasAuthority('sys:role:update')"
                >修改</el-button
              >
              <el-button
                size="mini"
                type="primary"
                icon="el-icon-delete"
                @click="delHandle(scope.row.roleId)"
                v-if="hasAuthority('sys:role:delete')"
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
          @pagination="getRoleList()"
        />

        <!-- 添加或修改参数配置对话框 -->
        <el-dialog :visible.sync="dialogVisible" append-to-body width="550px">
          <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-row>
              <el-col :span="12">
                <el-form-item label="名称" prop="roleName">
                  <el-input
                    v-model="form.roleName"
                    placeholder="请输入角色名称"
                    maxlength="30"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="标识" prop="roleKey">
                  <el-input
                    v-model="form.roleKey"
                    placeholder="请输入角色标识"
                    maxlength="30"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="角色">
                  <el-select
                    v-model="form.menuIds"
                    multiple
                    placeholder="请选择"
                  >
                    <el-option
                      v-for="item in menuOptions"
                      :key="item.menuId"
                      :label="item.menuName"
                      :value="item.menuId"
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
      menuOptions: [],
      // 表单校验
      rules: {
        roleName: [
          { required: true, message: "角色名称不能为空", trigger: "blur" },
        ],
        roleKey: [
          { required: true, message: "角色标识不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getRoleList();
  },
  methods: {
    getRoleList() {
      this.$axios
        .get("/sys/role/page", {
          params: {
            username: this.searchForm.roleName,
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
      this.$axios.get("/sys/role/info/" + id).then((res) => {
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
      if (this.form.roleId != undefined) {
        this.$axios.post("/sys/role/update", this.form).then((res) => {
          this.dialogVisible = false;
          this.$message({
            showClose: true,
            message: "修改成功",
            type: "success",
            onClose: () => {
              this.getRoleList();
            },
          });
        });
      } else {
        this.$axios.post("/sys/role/save", this.form).then((res) => {
          this.dialogVisible = false;
          this.$message({
            showClose: true,
            message: "新增成功",
            type: "success",
            onClose: () => {
              this.getRoleList();
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
          ids.push(row.roleId);
        });
      }
      console.log("勾选ids");
      console.log(ids);

      this.$axios.post("/sys/role/delete", ids).then((res) => {
        this.$message({
          showClose: true,
          message: "恭喜你，操作成功",
          type: "success",
          onClose: () => {
            this.getRoleList();
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