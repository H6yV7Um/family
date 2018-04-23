package org.yxyqcy.family.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.sys.dept.entity.DeptRole;
import org.yxyqcy.family.sys.user.entity.User;
import org.yxyqcy.family.sys.security.SystemAuthorizingRealm;
import org.yxyqcy.family.sys.service.SystemService;
import org.yxyqcy.family.sys.user.dao.UserRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/2
 * Time: 上午10:11
 * description:  sys 实现
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
public class SystemServiceImpl extends BaseService implements SystemService {


    public static final int SALT_SIZE = 8;
    private static final long serialVersionUID = 8414828990342185240L;

    @Autowired
    private SystemAuthorizingRealm systemRealm;

    @Resource
    private SystemService systemServiceImpl;

    @Resource
    private UserRepository userRepository;

    @Override
    public User getUserByLoginName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }

	@Override
	public List<DeptRole> getDeptRoleByLoginName(String loginName) {
		return userRepository.findDeptRoleByLoginName(loginName);
	}
}

