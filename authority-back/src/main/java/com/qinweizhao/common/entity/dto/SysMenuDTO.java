package com.qinweizhao.common.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author qinweizhao
 * @since 2021/10/15
 */
@Data
@EqualsAndHashCode
public class SysMenuDTO {

    private Long menuId;

    private String menuName;

    private String icon;

    private String path;

    private Long parentId;

    private String component;

    private List<SysMenuDTO> children;
}
