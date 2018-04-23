package org.yxyqcy.family.sys.service;


import org.yxyqcy.family.sys.dept.entity.DeptRole;
import org.yxyqcy.family.sys.user.entity.User;

import java.util.List;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/2
 * Time: 上午10:08
 * description: sys service
 */
public interface SystemService {
    /**
     * 根据登录名称  查询用户
     * @param loginName
     * @return
     */
    public User getUserByLoginName(String loginName);
    
    /**
     * 根据用户名查询部门和职位
     * @MethodName:getDeptRoleByLoginName
     * @author: 张雪峰
     * @email: 734126206@qq.com 
     * @date 2016年11月14日 下午4:17:22
     * @version V1.0
     * @param loginName
     * @return
     */
    public List<DeptRole> getDeptRoleByLoginName(String loginName);
}
