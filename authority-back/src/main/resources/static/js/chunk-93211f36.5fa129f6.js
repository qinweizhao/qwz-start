(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-93211f36"],{"38eb":function(e,t,a){"use strict";a("eeac")},e3c9:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:24,xs:24}},[a("el-form",{staticClass:"search",attrs:{inline:!0}},[a("el-form-item",[e.hasAuthority("sys:menu:save")?a("el-button",{attrs:{type:"primary"},on:{click:e.handleAdd}},[e._v("新增")]):e._e()],1)],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticStyle:{width:"100%","margin-bottom":"20px"},attrs:{data:e.tableData,"row-key":"menuId","default-expand-all":"","tree-props":{children:"children",hasChildren:"hasChildren"}}},[a("el-table-column",{key:"menuName",attrs:{label:"菜单名称",align:"center",prop:"menuName"}}),a("el-table-column",{attrs:{label:"菜单类型",align:"center",prop:"menuType"},scopedSlots:e._u([{key:"default",fn:function(t){return["0"===t.row.menuType?a("el-tag",{attrs:{size:"small"}},[e._v("目录")]):"1"===t.row.menuType?a("el-tag",{attrs:{size:"small",type:"success"}},[e._v("菜单")]):"2"===t.row.menuType?a("el-tag",{attrs:{size:"small",type:"info"}},[e._v("按钮")]):e._e()]}}])}),a("el-table-column",{key:"path",attrs:{label:"路由地址",align:"center",prop:"path"}}),a("el-table-column",{key:"icon",attrs:{label:"图标",align:"center",prop:"icon"}}),a("el-table-column",{key:"component",attrs:{label:"组件路径",align:"center",prop:"component"}}),a("el-table-column",{key:"permission",attrs:{label:"权限标识",align:"center",prop:"permission"}}),a("el-table-column",{key:"status",attrs:{label:"状态",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return["0"===t.row.status?a("el-tag",{attrs:{size:"small",type:"success"}},[e._v("正常")]):"1"===t.row.status?a("el-tag",{attrs:{size:"small",type:"danger"}},[e._v("禁用")]):e._e()]}}])}),a("el-table-column",{attrs:{label:"操作",align:"center",width:"260","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[e.hasAuthority("sys:menu:update")?a("el-button",{attrs:{size:"mini",type:"primary",icon:"el-icon-edit"},on:{click:function(a){return e.handleUpdate(t.row.menuId)}}},[e._v("修改")]):e._e(),e.hasAuthority("sys:menu:delete")?a("el-button",{attrs:{size:"mini",type:"primary",icon:"el-icon-delete"},on:{click:function(a){return e.delHandle(t.row.menuId)}}},[e._v("删除")]):e._e()]}}])})],1),a("el-dialog",{attrs:{visible:e.dialogVisible,"append-to-body":"",width:"650px"},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"80px"}},[a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"名称",prop:"menuName"}},[a("el-input",{attrs:{placeholder:"请输入菜单名称",maxlength:"30"},model:{value:e.form.menuName,callback:function(t){e.$set(e.form,"menuName",t)},expression:"form.menuName"}})],1)],1),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"标识",prop:"menuType"}},[a("el-input",{attrs:{placeholder:"请输入权限标识",maxlength:"30"},model:{value:e.form.permission,callback:function(t){e.$set(e.form,"permission",t)},expression:"form.permission"}})],1)],1),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"路由",prop:"path"}},[a("el-input",{attrs:{placeholder:"请输入路由地址",maxlength:"30"},model:{value:e.form.path,callback:function(t){e.$set(e.form,"path",t)},expression:"form.path"}})],1)],1),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"组件",prop:"component"}},[a("el-input",{attrs:{placeholder:"请输入组件路径",maxlength:"30"},model:{value:e.form.component,callback:function(t){e.$set(e.form,"component",t)},expression:"form.component"}})],1)],1),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"状态"}},[a("el-radio-group",{model:{value:e.form.status,callback:function(t){e.$set(e.form,"status",t)},expression:"form.status"}},[a("el-radio",{attrs:{label:"0"}},[e._v("启用")]),a("el-radio",{attrs:{label:"1"}},[e._v("禁用")])],1)],1)],1),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"类型"}},[a("el-radio-group",{model:{value:e.form.menuType,callback:function(t){e.$set(e.form,"menuType",t)},expression:"form.menuType"}},[a("el-radio",{attrs:{label:"M"}},[e._v("目录")]),a("el-radio",{attrs:{label:"C"}},[e._v("菜单")]),a("el-radio",{attrs:{label:"F"}},[e._v("按钮")])],1)],1)],1),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"上级菜单",prop:"parentId"}},[a("el-select",{attrs:{placeholder:"请选择上级菜单"},model:{value:e.form.parentId,callback:function(t){e.$set(e.form,"parentId",t)},expression:"form.parentId"}},[e._l(e.tableData,(function(t){return[a("el-option",{key:t.menuId,attrs:{label:t.menuName,value:t.menuId}}),e._l(t.children,(function(t){return[a("el-option",{key:t.menuId,attrs:{label:t.menuName,value:t.menuId}},[a("span",[e._v(e._s("- "+t.menuName))])])]}))]}))],2)],1)],1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("确 定")]),a("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)],1)],1)],1)],1)},n=[];function s(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}var o={name:"User",data:function(){var e;return e={loading:!0,searchForm:{},tableData:[],form:{status:"0"},delBtlStatu:!0,dialogVisible:!1,multipleSelection:[],menuOptions:[]},s(e,"tableData",[]),s(e,"rules",{menuName:[{required:!0,message:"菜单名称不能为空",trigger:"blur"}],menuKey:[{required:!0,message:"菜单标识不能为空",trigger:"blur"}]}),e},created:function(){this.getMenuList()},methods:{getMenuList:function(){var e=this;this.$axios.get("/sys/menu/list").then((function(t){e.tableData=t.data.data,e.loading=!1}))},handleUpdate:function(e){var t=this;this.$axios.get("/sys/menu/info/"+e).then((function(e){t.form=e.data.data,t.form.menuIds=e.data.data.menuIds,t.dialogVisible=!0,t.$axios.get("/sys/menu/list").then((function(e){console.log(e),t.menuOptions=e.data.data}))}))},handleAdd:function(){var e=this;this.form={},this.dialogVisible=!0,this.$axios.get("/sys/menu/list").then((function(t){console.log(t),e.menuOptions=t.data.data}))},submitForm:function(){var e=this;void 0!=this.form.menuId?this.$axios.post("/sys/menu/update",this.form).then((function(t){e.dialogVisible=!1,e.$message({showClose:!0,message:"修改成功",type:"success",onClose:function(){e.getMenuList()}})})):this.$axios.post("/sys/menu/save",this.form).then((function(t){e.dialogVisible=!1,e.$message({showClose:!0,message:"新增成功",type:"success",onClose:function(){e.getMenuList()}})}))},cancel:function(){this.dialogVisible=!1,this.form={}},handleSelectionChange:function(e){console.log("勾选"),console.log(e),this.multipleSelection=e,this.delBtlStatu=0==e.length},delHandle:function(e){var t=this;this.$axios.post("/sys/menu/delete/"+e).then((function(e){t.$message({showClose:!0,message:"恭喜你，操作成功",type:"success",onClose:function(){t.getMenuList()}})}))}}},r=o,i=(a("38eb"),a("2877")),u=Object(i["a"])(r,l,n,!1,null,"7517bd0a",null);t["default"]=u.exports},eeac:function(e,t,a){}}]);
//# sourceMappingURL=chunk-93211f36.5fa129f6.js.map