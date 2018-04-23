package org.yxyqcy.family.sys.security;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.yxyqcy.family.sys.security.stateless.StatelessToken;

import java.util.Collection;
import java.util.Map;

/**
 * Created by lcy on 17/3/5.
 */
public class DefaultModularRealm  extends ModularRealmAuthenticator {

    private Map<String, Object> definedRealms;
    /**
     * 多个realm实现
     */
    @Override
    protected AuthenticationInfo doMultiRealmAuthentication(
            Collection<Realm> realms, AuthenticationToken token) {
        return super.doMultiRealmAuthentication(realms, token);
    }


    /**
     * 判断登录类型执行操作
     */
    @Override
    protected AuthenticationInfo doAuthenticate(
            AuthenticationToken authenticationToken)
            throws AuthenticationException {
        this.assertRealmsConfigured();

        Realm realm = null;

        if(authenticationToken instanceof  SystemUsernamePasswordToken)
            realm = (Realm) this.definedRealms.get("systemAuthorizingRealm");
        if(authenticationToken instanceof StatelessToken)
            realm = (Realm) this.definedRealms.get("statelessRealm");
        if (realm == null) {
            return null;
        }
        return this.doSingleRealmAuthentication(realm, authenticationToken);
    }

    public Map<String, Object> getDefinedRealms() {
        return this.definedRealms;
    }

    public void setDefinedRealms(Map<String, Object> definedRealms) {
        this.definedRealms = definedRealms;
    }
}
