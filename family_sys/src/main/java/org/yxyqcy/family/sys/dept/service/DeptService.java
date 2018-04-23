package org.yxyqcy.family.sys.dept.service;



import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.model.TreeViewModel;
import org.yxyqcy.family.sys.dept.entity.Dept;

import java.util.List;

/**
 * Created by lcy on 16/7/30.
 */
public interface DeptService {
    /**
     * 查询 dept 根据树
     * @return
     */
    public List<TreeViewModel> queryDeptTree();
    
    /**
     * 查询部门树，根据role填上和角色绑定的部门
     * @param id
     * @return
     */
    public List<TreeViewModel> queryDeptsTreeByRoleId(String id);
    


    /**
     * 保存部门
     * @param dept
     * @return
     */
    PersistModel persistDept(Dept dept);

    /**
     * 修改部门
     * @param dept
     * @return
     */
    PersistModel mergeDept(Dept dept);

    /**
     * 删除部门
     * @param id
     * @return
     */
    PersistModel removeDept(String id);
    
    /**
     * 查询所有部门
     * @MethodName:querySelect
     * @author: 张雪峰
     * @email: 734126206@qq.com 
     * @date 2016年11月24日 下午4:46:34
     * @version V1.0
     * @return
     */
    public List<Dept> querySelect();
    
}
