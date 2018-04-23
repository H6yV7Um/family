package org.yxyqcy.family.sys.user.service.impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.common.util.IdGen;
import org.yxyqcy.family.common.util.SystemUtil;
import org.yxyqcy.family.sys.user.entity.User;
import org.yxyqcy.family.sys.user.dao.UserRepository;
import org.yxyqcy.family.sys.user.service.UserService;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/16
 * Time: 上午11:22
 * description:
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseService implements UserService {

    private static final long serialVersionUID = 2143099358865691109L;
    @Resource
    public UserRepository userRepository;

    @Override
    public List<User> queryUsersByPagination(PageUtil pageUtil, User user) {
        return userRepository.queryUsers(user);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel persistUser(User user) {
        user.setCommonBusinessId();
        UserUtils.setCurrentPersistOperation(user);
        user.setLoginName(user.getEmail());//登录名设为邮箱
        user.setPassword(SystemUtil.entryptPassword(user.getPassword()));
        int line = userRepository.insertSelective(user);
        return new PersistModel(line);
    }
    
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel doDistributeRole(String userId, String role_users) {
        //删除原始角色
        int line = userRepository.deleteUserRoles(userId);
        //插入新的用户角色
        if(null != role_users && !"".equals(role_users.trim())){
            String[] roles = role_users.split(",");
            List<Map> userRole = new ArrayList<Map>();
            Map map = null;
            for (int i = 0 ; i < roles.length ; i++){
                map = new HashMap();
                map.put("id", IdGen.uuid());
                map.put("userId",userId);
                map.put("deptRoleId",roles[i]);
                userRole.add(map);
            }
            line = userRepository.insertBatchUserRole(userRole);
        }
        return new PersistModel(line);
    }

    @Override
    public User queryUserById(String id) {

        return userRepository.selectByPrimaryKey(id);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
	@Override
	public int updateUser(User user) {
        UserUtils.setCurrentMergeOperation(user);
		return userRepository.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<User> queryUserByDeptId(String id) {
		// TODO Auto-generated method stub
		return userRepository.queryUsersByDeptid(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
	@Override
	public PersistModel removeUser(User user) {
        user.setDelFlag("1");
		UserUtils.setCurrentMergeOperation(user);
		int line = userRepository.updateByPrimaryKeySelective(user);
		userRepository.deleteDeptRoleUserByUserId(user.getBusinessId());
		return new PersistModel(line);
	}

    @Override
    public List<String> queryUsersByPrimaryDeptAndRole(String startPersonId, String roleCode) {  	
        return userRepository.queryOwnDeptUserId(startPersonId,roleCode);
    }

	@Override
	public User queryMyInfo() {
		// TODO Auto-generated method stub
		return userRepository.selectUserByPrimaryKey(UserUtils.getUser().getBusinessId());
	}

    @Override
    public User validateUserPass(String lastPass) {
        User user = userRepository.selectByPrimaryKey(UserUtils.getUser().getBusinessId());
        //验证密码
        if(null == lastPass)
            return null;
        if(SystemUtil.validatePassword(lastPass,user.getPassword()))
            return user;
        else
            return null;
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel updateUserPass(User param) {
        param.setBusinessId(UserUtils.getUser().getBusinessId());
        if(null == param.getPassword())
            return new PersistModel(0);
        UserUtils.setCurrentMergeOperation(param);
        param.setPassword(SystemUtil.entryptPassword(param.getPassword()));
        int line = userRepository.updateUserPass(param);
        return new PersistModel(line);
    }
}
