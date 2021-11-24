(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0a798d1a"],{"266e":function(e,t,l){},"50e1":function(e,t,l){"use strict";l("266e")},"7ad5":function(e,t,l){"use strict";l.r(t);var o=function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",[l("el-row",{attrs:{gutter:20}},[l("el-col",{attrs:{span:24,xs:24}},[l("el-form",{staticClass:"search",attrs:{inline:!0}},[l("el-form-item",[l("el-input",{attrs:{placeholder:"角色名",clearable:""},model:{value:e.searchForm.roleName,callback:function(t){e.$set(e.searchForm,"roleName",t)},expression:"searchForm.roleName"}})],1),l("el-form-item",[l("el-button",{on:{click:e.getRoleList}},[e._v("搜索")])],1),l("el-form-item",[l("el-button",{attrs:{type:"primary"},on:{click:e.handleAdd}},[e._v("新增")])],1),l("el-form-item",[l("el-popconfirm",{attrs:{title:"这是确定批量删除吗？"},on:{confirm:function(t){return e.delHandle(null)}}},[l("el-button",{attrs:{slot:"reference",type:"danger",disabled:e.delBtlStatu},slot:"reference"},[e._v("批量删除")])],1)],1)],1),l("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.tableData},on:{"selection-change":e.handleSelectionChange}},[l("el-table-column",{attrs:{type:"selection",width:"50",align:"center"}}),l("el-table-column",{key:"roleId",attrs:{label:"角色编号",align:"center",prop:"roleId"}}),l("el-table-column",{key:"roleName",attrs:{label:"角色名称",align:"center",prop:"roleName"}}),l("el-table-column",{key:"roleKey",attrs:{label:"角色标识",align:"center",prop:"roleKey","show-overflow-tooltip":!0}}),l("el-table-column",{key:"status",attrs:{label:"状态",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return 1!==t.row.roleId?[l("el-switch",{attrs:{"active-value":"0","inactive-value":"1"},on:{change:function(l){return e.handleStatusChange(t.row)}},model:{value:t.row.status,callback:function(l){e.$set(t.row,"status",l)},expression:"scope.row.status"}})]:void 0}}],null,!0)}),l("el-table-column",{attrs:{label:"操作",align:"center",width:"260","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return 1!==t.row.roleId?[l("el-button",{attrs:{size:"mini",type:"primary",icon:"el-icon-edit"},on:{click:function(l){return e.handleUpdate(t.row.roleId)}}},[e._v("修改")]),l("el-button",{attrs:{size:"mini",type:"primary",icon:"el-icon-delete"},on:{click:function(l){return e.delHandle(t.row.roleId)}}},[e._v("删除")])]:void 0}}],null,!0)})],1),l("el-pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total > 0"}],attrs:{total:e.total,"current-page":e.current,"page-size":e.size},on:{pagination:function(t){return e.getRoleList()}}}),l("el-dialog",{attrs:{visible:e.dialogVisible,"append-to-body":"",width:"550px"},on:{"update:visible":function(t){e.dialogVisible=t}}},[l("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"80px"}},[l("el-row",[l("el-col",{attrs:{span:12}},[l("el-form-item",{attrs:{label:"名称",prop:"roleName"}},[l("el-input",{attrs:{placeholder:"请输入角色名称",maxlength:"30"},model:{value:e.form.roleName,callback:function(t){e.$set(e.form,"roleName",t)},expression:"form.roleName"}})],1)],1),l("el-col",{attrs:{span:12}},[l("el-form-item",{attrs:{label:"标识",prop:"roleKey"}},[l("el-input",{attrs:{placeholder:"请输入角色标识",maxlength:"30"},model:{value:e.form.roleKey,callback:function(t){e.$set(e.form,"roleKey",t)},expression:"form.roleKey"}})],1)],1),l("el-col",{attrs:{span:12}},[l("el-form-item",{attrs:{label:"角色"}},[l("el-select",{attrs:{multiple:"",placeholder:"请选择"},model:{value:e.form.menuIds,callback:function(t){e.$set(e.form,"menuIds",t)},expression:"form.menuIds"}},e._l(e.menuOptions,(function(e){return l("el-option",{key:e.menuId,attrs:{label:e.menuName,value:e.menuId,disabled:1==e.status}})})),1)],1)],1)],1)],1),l("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[l("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("确 定")]),l("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)],1)],1)],1)],1)},a=[],s=(l("159b"),{name:"User",data:function(){return{loading:!0,size:5,current:1,searchForm:{},total:0,tableData:[],form:{status:"0"},delBtlStatu:!0,dialogVisible:!1,multipleSelection:[],menuOptions:[],rules:{roleName:[{required:!0,message:"角色名称不能为空",trigger:"blur"}],roleKey:[{required:!0,message:"角色标识不能为空",trigger:"blur"}]}}},created:function(){this.getRoleList()},methods:{getRoleList:function(){var e=this;this.$axios.get("/sys/role/page",{params:{username:this.searchForm.roleName,current:this.current,size:this.size}}).then((function(t){e.tableData=t.data.data.records,e.size=t.data.data.size,e.current=t.data.data.current,e.total=t.data.data.total,e.loading=!1}))},handleUpdate:function(e){var t=this;this.$axios.get("/sys/role/info/"+e).then((function(e){t.form=e.data.data,t.form.menuIds=e.data.data.menuIds,t.dialogVisible=!0,t.$axios.get("/sys/menu/list").then((function(e){console.log(e),t.menuOptions=e.data.data}))}))},handleAdd:function(){var e=this;this.form={},this.dialogVisible=!0,this.$axios.get("/sys/menu/list").then((function(t){console.log(t),e.menuOptions=t.data.data}))},submitForm:function(){var e=this;void 0!=this.form.roleId?this.$axios.post("/sys/role/update",this.form).then((function(t){e.dialogVisible=!1,e.$message({showClose:!0,message:"修改成功",type:"success",onClose:function(){e.getRoleList()}})})):this.$axios.post("/sys/role/save",this.form).then((function(t){e.dialogVisible=!1,e.$message({showClose:!0,message:"新增成功",type:"success",onClose:function(){e.getRoleList()}})}))},cancel:function(){this.dialogVisible=!1,this.form={}},handleSelectionChange:function(e){console.log("勾选"),console.log(e),this.multipleSelection=e,this.delBtlStatu=0==e.length},delHandle:function(e){var t=this,l=[];e?l.push(e):this.multipleSelection.forEach((function(e){l.push(e.roleId)})),console.log("勾选ids"),console.log(l),this.$axios.post("/sys/role/delete",l).then((function(e){t.$message({showClose:!0,message:"恭喜你，操作成功",type:"success",onClose:function(){t.getRoleList()}})}))}}}),n=s,i=(l("50e1"),l("2877")),r=Object(i["a"])(n,o,a,!1,null,"28dee63f",null);t["default"]=r.exports}}]);
//# sourceMappingURL=chunk-0a798d1a.01fc2c0a.js.map