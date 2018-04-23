package org.yxyqcy.family.sys.user.service;




import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.sys.user.entity.User;

import java.util.List;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/16
 * Time: 上午11:16
 * description:
 */
public interface UserService {
    /**
     * 分页查询用户
     * @param pageUtil  分页组件
     * @param user   查询条件
     */
    public List<User> queryUsersByPagination(PageUtil pageUtil, User user);

    /**
     * 插入user
     * @param param  user
     */
    PersistModel persistUser(User param);

    /**
     * 绑定用户角色
     * @param userId    用户id
     * @param role_users    用户角色关联
     * @return
     */
    PersistModel doDistributeRole(String userId, String role_users);

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    User queryUserById(String id);
    
    /**
     * updateByPkSeletive
     * @param param
     * @return
     */
    int updateUser(User param);
    
    /**
     * 根据部门查人
     * @param id
     * @return
     */
    List<User> queryUserByDeptId(String id);
    
    PersistModel removeUser(User u);

    /**
     * 根据流程启动者  查询所属部门的 满足code 的人(id)
     * @param startPersonId  启动者id
     * @param roleCode     code
     * @return
     */
    List<String> queryUsersByPrimaryDeptAndRole(String startPersonId, String roleCode);
    
    /**
     * 查找用户资料
     * @MethodName:queryMyInfo
     * @author: 张雪峰
     * @email: 734126206@qq.com 
     * @date 2016年11月24日 下午1:51:02
     * @version V1.0
     * @return
     */
    User queryMyInfo();

    /**
     * 根据lastpass 验证密码是否正确
     * @param lastPass
     * @return
     */
    User validateUserPass(String lastPass);

    /**
     * 修改用户密码
     * @param param
     * @return
     */
    PersistModel updateUserPass(User param);
}
