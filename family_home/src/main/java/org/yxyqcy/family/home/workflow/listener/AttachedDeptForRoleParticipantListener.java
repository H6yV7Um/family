package org.yxyqcy.family.home.workflow.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.el.FixedValue;
import org.springframework.stereotype.Component;
import org.yxyqcy.family.common.component.SpringContextHolder;
import org.yxyqcy.family.sys.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 17/6/9.
 *
 *
 * 相同部门,附属角色
 */
@Component
public class AttachedDeptForRoleParticipantListener implements TaskListener {

    private static final long serialVersionUID = 581172303915887097L;

    //注入角色
    private FixedValue roleName;

    public FixedValue getRoleName() {
        return roleName;
    }

    public void setRoleName(FixedValue roleName) {
        this.roleName = roleName;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        UserService userService = SpringContextHolder.getBean("userServiceImpl");
        //List<String> users = userService.queryUsersByPrimaryDeptAndRole(startPersonId,
        //       (String)roleCode.getValue(null));
        //候选组
        List<String> candidateGroup = new ArrayList<>();
        delegateTask.addCandidateGroups(candidateGroup);
    }
}
