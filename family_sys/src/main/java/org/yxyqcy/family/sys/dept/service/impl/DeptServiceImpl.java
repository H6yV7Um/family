package org.yxyqcy.family.sys.dept.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.model.TreeViewModel;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.common.util.IdGen;
import org.yxyqcy.family.sys.dept.dao.DeptRepository;
import org.yxyqcy.family.sys.dept.service.DeptService;
import org.yxyqcy.family.sys.dept.entity.Dept;
import org.yxyqcy.family.sys.util.UserUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lcy on 16/7/30.
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
public class DeptServiceImpl extends BaseService implements DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Override
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
    }
    
    /**
     * 查询部门树，根据role填上和角色绑定的部门
     * @param id
     * @return
     */
	@Override
	public List<TreeViewModel> queryDeptsTreeByRoleId(String id) {
        TreeViewModel treeRoot = null;
        //根节点不能为checked
        List<Dept> deptList = deptRepository.queryRootDeptTree();
        //返回集
        List<TreeViewModel> treeList = new ArrayList<TreeViewModel>();
        for(int i = 0; i < deptList.size();i++){
            treeRoot = conversionDept(deptList.get(i));
            //查询子部门
            queryChildDeptByRoleId(id,treeRoot);
            treeList.add(treeRoot);
        }
        return treeList;
	}
    
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel persistDept(Dept dept) {
        dept.setCommonBusinessId();
        dept.setSeqDate(new Date());
        //增加操作
        UserUtils.setCurrentPersistOperation(dept);
        int line = deptRepository.insertSelective(dept);
        return new PersistModel(line);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel mergeDept(Dept dept) {
        //修改操作
        UserUtils.setCurrentPersistOperation(dept);
        int line = deptRepository.updateByPrimaryKeySelective(dept);
        return new PersistModel(line);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel removeDept(String id) {

        // TODO: 16/12/23 删除子部门 人员的岗位  && 逻辑删除子部门
        Dept dept = deptRepository.selectByPrimaryKey(id);
    	dept.setDelFlag("1");
        UserUtils.setCurrentMergeOperation(dept);
        //int line = deptRepository.logicRemoveDept(id);
        deptRepository.deleteDeptRoleByDeptId(id);
    	deptRepository.deleteDeptRoleUserByDeptId(id);
    	int line=deptRepository.updateByPrimaryKeySelective(dept);
        return new PersistModel(line);
    }

    /**
     * 查询子部门
     * @param treeRoot   父部门
     */
    private void queryChildDept(TreeViewModel treeRoot) {
        List<Dept> deptList = deptRepository.queryDeptTreeByParentId(
                treeRoot.getId());
        List<TreeViewModel> treeView = new ArrayList<TreeViewModel>();
        if(0 == deptList.size())
            return;
        //赋值
        for(Dept dept : deptList) {
            TreeViewModel tree = conversionDept(dept);
            //递归
            queryChildDept(tree);
            treeView.add(tree);
        }
        treeRoot.setNodes(treeView);
    }
    
    /**
     * 查询子部门
     * @param treeRoot   父部门
     */
    private void queryChildDeptByRoleId(String roleId,TreeViewModel treeRoot) {
        List<Dept> deptList = deptRepository.queryDeptTreeByParentIdRoleId(roleId,
                treeRoot.getId());
        List<TreeViewModel> treeView = new ArrayList<TreeViewModel>();
        if(0 == deptList.size())
            return;
        //赋值
        for(Dept dept : deptList) {
            TreeViewModel tree = conversionDept(dept);
            //递归
            queryChildDeptByRoleId(roleId,tree);
            treeView.add(tree);
        }
        treeRoot.setNodes(treeView);
    }

    /**
     * 转换  dept to  treeView
     * @param dept
     * @return
     */
    private TreeViewModel conversionDept(Dept dept) {
        TreeViewModel treeView = new TreeViewModel();
        treeView.setId(dept.getBusinessId());
        treeView.setText(dept.getName());
        treeView.setPid(dept.getParentId());
        treeView.setParentId(dept.getParentId());
        treeView.setSelectable(true);
        treeView.setTags(new String[]{dept.getDeptCode()});
     
        //配置选中
        if(null != dept.getChecked() && !"".equals(dept.getChecked())){
            TreeViewModel.TreeViewStateModel  treeViewStateModel
                    = new TreeViewModel().new TreeViewStateModel();
            if("1".equals(dept.getChecked()))
                treeViewStateModel.setChecked(true);
            else if ("0".equals(dept.getChecked()))
                treeViewStateModel.setChecked(false);
            treeView.setState(treeViewStateModel);
        }
        
        return treeView;
    }




	

	@Override
	public List<Dept> querySelect() {
		return deptRepository.selectAll();
	}
}
