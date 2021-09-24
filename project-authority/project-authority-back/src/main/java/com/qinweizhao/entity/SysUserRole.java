package com.qinweizhao.entity;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author qinweizhao
 * @since 2021-09-25
 */
public class SysUserRole {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                "}";
    }
}
