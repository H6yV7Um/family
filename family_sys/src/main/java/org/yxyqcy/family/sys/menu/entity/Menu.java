package org.yxyqcy.family.sys.menu.entity;

import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/2
 * Time: 上午9:39
 * description: menu
 */
@Entity
@Table(name = "sys_menu")
public class Menu extends IdEntity<Menu> {
    private static final long serialVersionUID = 1L;
    @Transient
    private Menu parent;	// 父级菜单
    private String parentIds; // 所有父级编号
    private String name; 	// 名称
    private String href; 	// 链接
    private String target; 	// 目标（ mainFrame、_blank、_self、_parent、_top）
    private String icon; 	// 图标
    private Integer sort; 	// 排序
    private String isShow; 	// 是否在菜单中显示（1：显示；0：不显示）
    private String isActiviti; 	// 是否同步到工作流（1：同步；0：不同步）
    private String permission; // 权限标识
    private String parentId;   //parent id
    private String code;       //菜单编号
    private String modelId;     //model ID  in sys_model

    public String getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(String caseCount) {
        this.caseCount = caseCount;
    }

    private String caseCount;     //层级

    public String getChecked() {
        return checked;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    @Transient
    private String checked;     // 根据权限 是否被选中

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Menu(){
        super();
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getIsActiviti() {
        return isActiviti;
    }

    public void setIsActiviti(String isActiviti) {
        this.isActiviti = isActiviti;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
