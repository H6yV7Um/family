package org.yxyqcy.family.sys.role.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import org.yxyqcy.family.common.entity.IdEntity;
import org.yxyqcy.family.sys.menu.entity.Menu;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/2
 * Time: 上午9:31
 * description: role
 */
@Entity
@Table(name = "sys_role")
public class Role extends IdEntity<Role> {

    public Role() {
    }

    private static final long serialVersionUID = 1L;

    private String name;
    /**
	 * 获取的用户名
	 */
	@Transient
	private String username;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    private String code;

    @Transient
    private String checked;

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }


    @Transient
    private List<Menu> menuList = Lists.newArrayList(); // 拥有菜单列表

    public Role(String id, String name) {
        this();
        this.businessId = id;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Transient
    @JsonIgnore
    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }


    /**
     * 获取权限字符串列表
     */
    @Transient
    public List<String> getPermissions() {
        List<String> permissions = Lists.newArrayList();
        for (Menu menu : menuList) {
            if (menu.getPermission()!=null && !"".equals(menu.getPermission())){
                permissions.add(menu.getPermission());
            }
        }
        return permissions;
    }
}
