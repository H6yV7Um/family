package org.yxyqcy.family.sys.security;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.constant.SysConstants;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.util.SystemUtil;
import org.yxyqcy.family.common.util.WebUtil;
import org.yxyqcy.family.sys.cache.CacheUtil;
import org.yxyqcy.family.sys.controller.LoginController;
import org.yxyqcy.family.sys.security.stateless.StatelessToken;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/1
 * Time: 下午2:24
 * description: 表单验证 过滤器
 */
@Component
public class SystemFormAuthenticationFilter extends FormAuthenticationFilter {
    public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";

    private static final Logger log = LoggerFactory.getLogger(SystemFormAuthenticationFilter.class);

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        if (password==null){
            password = "";
        }
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        String captcha = getCaptcha(request);
        return new SystemUsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha);
    }

    /**
     * 主要是针对登入成功的处理方法。对于请求头是AJAX的之间返回JSON字符串。
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //remove 验证码
        httpServletRequest.getSession().removeAttribute("isValidateCodeLogin");
        //设备登录  /adminLogin = authc 所以 不能进入无状态filter
        if(WebUtil.isMobileDevice(httpServletRequest)){
            this.printAjaxReponse(httpServletResponse,true,MessageConstant.MESSAGE_ALERT_LOGIN_SUCCESS,UserUtils.getUser());
        }
        //ajax
        else if(WebUtil.isAjaxRequest(httpServletRequest)){
            this.printAjaxReponse(httpServletResponse,true,MessageConstant.MESSAGE_ALERT_LOGIN_SUCCESS,UserUtils.getUser());
            /*login 验证码 清空*/
            boolean loginResult = LoginController.isValidateCodeLogin(UserUtils.getUser().getLoginName(), false, true);
            httpServletRequest.getSession().setAttribute("isValidateCodeLogin",false);
            /*login 验证码 清空 end*/
        }else{
            issueSuccessRedirect(request, response);
        }
        //we handled the success redirect directly, prevent the chain from continuing:
        return false;
    }
    /**
     * 主要是针对登入失败的处理方法。对于请求头是AJAX的之间返回JSON字符串。
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtil.get("loginFailMap");
        SystemUsernamePasswordToken authToken = (SystemUsernamePasswordToken) token;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        //设备登录  /adminLogin = authc 所以 不能进入无状态filter
        if(WebUtil.isMobileDevice(httpServletRequest)){
            try {
                String message = e.getClass().getSimpleName();
                if ("IncorrectCredentialsException".equals(message)) {
                    this.printAjaxReponse(httpServletResponse,false,"密码错误",loginFailMap.get(authToken.getUsername()));
                } else if ("UnknownAccountException".equals(message)) {
                    this.printAjaxReponse(httpServletResponse,false,"账号不存在",null);
                } else if ("LockedAccountException".equals(message)) {
                    this.printAjaxReponse(httpServletResponse,false,"账号被锁定",null);
                } else if ("CaptchaException".equals(message)) {
                    this.printAjaxReponse(httpServletResponse,false,"验证码错误",loginFailMap.get(authToken.getUsername()));
                } else if("AuthenticationException".equals(message)) {
                    //动态设置realm时 出现的异常
                    this.printAjaxReponse(httpServletResponse,false,e.getCause().getMessage(),loginFailMap.get(authToken.getUsername()));
                }else{
                    this.printAjaxReponse(httpServletResponse,false,"未知错误",null);
                }
                //return false
                //不能跳转  response.writer.print 信息后  如果继续到跳转
                //@RequestMapping(value = "adminLogin", method = RequestMethod.POST)  则会报错
                return false;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        //ajax
        else if(WebUtil.isAjaxRequest(httpServletRequest)){
            try {
                String message = e.getClass().getSimpleName();
                if ("IncorrectCredentialsException".equals(message)) {
                    /*login 验证码*/
                    boolean loginResult = LoginController.isValidateCodeLogin(authToken.getUsername(), true, false);
                    httpServletRequest.getSession().setAttribute("isValidateCodeLogin",loginResult);
                    /*login 验证码 end*/
                    this.printAjaxReponse(httpServletResponse,false,"密码错误",loginFailMap.get(authToken.getUsername()));
                } else if ("UnknownAccountException".equals(message)) {
                    this.printAjaxReponse(httpServletResponse,false,"账号不存在",null);
                } else if ("LockedAccountException".equals(message)) {
                    this.printAjaxReponse(httpServletResponse,false,"账号被锁定",null);
                } else if ("CaptchaException".equals(message)) {
                    /*login 验证码*/
                    boolean loginResult = LoginController.isValidateCodeLogin(authToken.getUsername(), true, false);
                    httpServletRequest.getSession().setAttribute("isValidateCodeLogin",loginResult);
                    /*login 验证码 end*/
                    this.printAjaxReponse(httpServletResponse,false,"验证码错误",loginFailMap.get(authToken.getUsername()));
                } else if("AuthenticationException".equals(message)) {
                    //动态设置realm时 出现的异常
                    this.printAjaxReponse(httpServletResponse,false,e.getCause().getMessage(),loginFailMap.get(authToken.getUsername()));
                }else{
                    this.printAjaxReponse(httpServletResponse,false,"未知错误",null);
                }
                //return false
                //不能跳转  response.writer.print 信息后  如果继续到跳转
                //@RequestMapping(value = "adminLogin", method = RequestMethod.POST)  则会报错
                return false;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }else{
            setFailureAttribute(request, e);
            //login failed, let request continue back to the login page:
        }
        return true;
    }


    /*@Override
        无权权限  ajax 扩展

        <!--权限不足-->
				<prop key="org.apache.shiro.authz.UnauthorizedException">redirect:/global/noAuthorization</prop>
        1.目前采取  get post  区分ajax请求 进行处理
        2.也可以采取重写  onAccessDenied 进行扩展
    **/

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        Subject subject = getSubject(request, response);
        //login  get请求
        if (isLoginRequest(request, response)) {
            //login  post请求
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            /**
             * ajax 请求 判断 return false;
             */
            if(subject.getPrincipal() == null && WebUtil.isAjaxRequest(httpRequest)) {
                this.printAjaxReponse(httpResponse,false,MessageConstant.MESSAGE_ALERT_NOAUTHENTICATION,AjaxResponse.ErrorAjaxCode.ERROR_CODE_NOAUTHENTICATION);
                return false;
            }else if(subject.getPrincipal() != null && WebUtil.isAjaxRequest(httpRequest)) {
                this.printAjaxReponse(httpResponse,false,MessageConstant.MESSAGE_ALERT_NOAUTHORIZATION,AjaxResponse.ErrorAjaxCode.ERROR_CODE_NOAUTHORIZATION);
                return false;
            }
            /*ajax 请求 判断 end*/

            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }


    /**
     * print ajax response
     * @param response
     * @return
     * @throws Exception
     */
    private void printAjaxReponse(HttpServletResponse response,boolean success,String message,Object result) throws Exception{
        /**
         * ajax 请求 判断 return false;
         */
        AjaxResponse ajax = new AjaxResponse();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        out = response.getWriter();
        if(success)
            ajax.setSuccessMessage(message,result);
        else
            ajax.setErrorMessage(message,result);
        out.print(JSONObject.toJSONString(ajax,
                SerializerFeature.WriteMapNullValue));
        out.flush();
        out.close();
    }

}
