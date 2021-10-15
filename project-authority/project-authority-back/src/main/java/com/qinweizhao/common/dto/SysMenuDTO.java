package com.qinweizhao.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinweizhao
 * @since 2021/10/15
 */
public class SysMenuDTO {

        private Long menuId;

        private String menuName;

        private String icon;

        private String path;

        private String component;

        private List<SysMenuDTO> children = new ArrayList<>();


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public List<SysMenuDTO> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenuDTO> children) {
        this.children = children;
    }
}
