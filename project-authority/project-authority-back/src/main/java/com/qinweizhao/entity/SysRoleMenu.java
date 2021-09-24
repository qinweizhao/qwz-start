package com.qinweizhao.entity;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
public class SysRoleMenu {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "SysRoleMenu{" +
                "roleId=" + roleId +
                ", menuId=" + menuId +
                "}";
    }
}
