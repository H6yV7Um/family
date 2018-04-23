package org.yxyqcy.family.sys.role.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.common.util.IdGen;
import org.yxyqcy.family.sys.dept.entity.DeptRole;
import org.yxyqcy.family.sys.role.entity.Role;
import org.yxyqcy.family.sys.role.dao.RoleRepository;
import org.yxyqcy.family.sys.role.service.RoleService;
import org.yxyqcy.family.sys.util.UserUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 16/7/30.
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
public class RoleServiceImpl extends BaseService implements RoleService {

    private static final long serialVersionUID = 2992065094209068977L;
    @Autowired
    private RoleRepository roleRepository;

/*    @Override
    public List<TreeViewModel> queryDeptTree() {
        TreeViewModel treeRoot = null;
        List<Dept> deptList = deptRepository.queryRootDeptTree();
        //返回集
        List<TreeViewModel> treeList = new ArrayList<TreeViewModel>();
        for(int i = 0; i < deptList.size();i++){
            treeRoot = conversionDept(deptList.get(i));
            //查询子部门
            queryChildDept(treeRoot);
            treeList.add(treeRoot);
        }
        return treeList;
    }*/



    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel persistRole(Role role) {
    	role.setCommonBusinessId();
        //增加操作
        UserUtils.setCurrentPersistOperation(role);
        int line = roleRepository.insertSelective(role);
        return new PersistModel(line);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel mergeRole(Role role) {
        //修改操作
        UserUtils.setCurrentMergeOperation(role);
        int line = roleRepository.updateByPrimaryKeySelective(role);
        return new PersistModel(line);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel removeRole(String id) {
        //删除该角色所有的信息，包括dept_role  dept_role_user
    	int line;
        line = roleRepository.deleteDeptRoleUserByroleId(id);
        line = roleRepository.deleteDeptRoleByRoleId(id);
        line = roleRepository.deleteRoleMenuByRoleId(id);

        Role role = roleRepository.selectByPrimaryKey(id);
        role.setDelFlag("1");
        UserUtils.setCurrentMergeOperation(role);
        //int line = deptRepository.logicRemoveDept(id);
    	line = roleRepository.updateByPrimaryKeySelective(role);
        return new PersistModel(line);
    }

    @Override
    public List<Role> queryRolesByPagination(PageUtil<Role> paginationUtility, Role role) {
        return roleRepository.queryRoles(role);
    }
    @Override
    public List<DeptRole> queryUserRolesByPagination(PageUtil<Role> paginationUtility, String userId, Role role) {
        return roleRepository.queryUserRolesByPagination(userId,role);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel doAuthorization(String roleId, String role_menus) {
        //删除原始权限
        int line = roleRepository.deleteRoleMenus(roleId);
        //批量插入新的权限
        if(null != role_menus && !"".equals(role_menus.trim())){
            String[] menus = role_menus.split(",");
            List<Map> menuRole = new ArrayList<Map>();
            Map map = null;
            for (int i = 0 ; i < menus.length ; i++){
                map = new HashMap();
                map.put("roleId",roleId);
                map.put("menuId",menus[i]);
                map.put("id", IdGen.uuid());
                menuRole.add(map);
            }
            line=roleRepository.insertBatchRoleMenu(menuRole);
        }
        return new PersistModel(line);
    }
    
    @Override
    public List<Role> queryDeptRolesByPagination(PageUtil<Role> paginationUtility, String deptId, Role role) {
        return roleRepository.queryDeptRolesByPagination(deptId,role);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel doAuthorizationDept(String roleId, String role_depts) {
        //删除原始权限
        int line = roleRepository.deleteRoleDepts(roleId);
        //批量插入新的权限
        if(null != role_depts && !"".equals(role_depts.trim())){
            String[] depts = role_depts.split(",");
            List<Map> deptRole = new ArrayList<Map>();
            Map map = null;
            for (int i = 0 ; i < depts.length ; i++){
                map = new HashMap();
                map.put("roleId",roleId);
                map.put("deptId",depts[i]);
                map.put("id", IdGen.uuid());
                deptRole.add(map);
            }
            line = roleRepository.insertBatchRoleDept(deptRole);
        }
        return new PersistModel(line);
    }

	@Override
	public Role queryRoleById(String id) {
	       return roleRepository.selectByPrimaryKey(id);
	}

	@Override
	public List<Role> queryRolesSelect(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.queryRolesSelect(role);
	}




}
