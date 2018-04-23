package org.yxyqcy.family.sys.security;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/1
 * Time: 下午2:23
 * description:  验证码异常
 */
public class CaptchaException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super();
    }

    public CaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaException(String message) {
        super(message);
    }

    public CaptchaException(Throwable cause) {
        super(cause);
    }
}
