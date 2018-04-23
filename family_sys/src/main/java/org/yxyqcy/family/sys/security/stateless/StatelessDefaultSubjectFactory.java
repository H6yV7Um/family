package org.yxyqcy.family.sys.security.stateless;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.stereotype.Component;

/**
 * Created by lcy on 17/3/4.
 */
//@Component  暂时没用,有状态和无状态联合 不能动态设置factory
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        //不创建session
        //通过调用context.setSessionCreationEnabled(false)表示不创建会话；
        //如果之后调用Subject.getSession()将抛出DisabledSessionException异常。
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
