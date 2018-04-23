package org.yxyqcy.family.sys.security;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/1
 * Time: 下午2:22
 * description: 用户名 密码  验证码 token
 */
public class SystemUsernamePasswordToken extends UsernamePasswordToken {


    private static final long serialVersionUID = 2807368199681133289L;
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public SystemUsernamePasswordToken() {
        super();
    }

    public SystemUsernamePasswordToken(String username, char[] password,
                                 boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

}
