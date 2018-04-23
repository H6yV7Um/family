package org.yxyqcy.family.common.component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.yxyqcy.family.common.config.Global;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lcy on 16/8/31.
 */
@ControllerAdvice
public class FamilyExceptionHandler implements HandlerExceptionResolver {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    /*权限不足*/
    private static final String UNAUTHORIZED_EX = "org.apache.shiro.authz.UnauthorizedException";
    /*无认证*/
    private static final String AUTHORIZATION_EX = "org.apache.shiro.authz.AuthorizationException";

    public FamilyExceptionHandler() {
    }

    /**
     * 全局异常处理
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        try {
            logger.error(e.getMessage());
            //如果是ajax请求
            if (WebUtil.isAjaxRequest(httpServletRequest)) {
                //设置返回信息
                /*
                   *  已经在spring-mvc 配置中 拦截, 应该不会进入到异常处理器
                   * */
                AjaxResponse ajax = new AjaxResponse();
                if(UNAUTHORIZED_EX.equalsIgnoreCase(e.getClass().getName())){
                    ajax.setErrorMessage("您没有访问此请求的权限!",AjaxResponse.ErrorAjaxCode.ERROR_CODE_NOAUTHORIZATION);
                }
                else if(AUTHORIZATION_EX.equalsIgnoreCase(e.getClass().getName())){
                    ajax.setErrorMessage("您没有授权,请重新授权!",AjaxResponse.ErrorAjaxCode.ERROR_CODE_NOAUTHORIZATION);
                }

                else
                    ajax.setErrorMessage(e.getMessage(),null);
                httpServletResponse.getWriter().print(JSONObject.toJSONString(ajax,
                        SerializerFeature.WriteMapNullValue));
            }else{
                //不是ajax请求 跳转到错误页面
                return new ModelAndView("forward:/" + Global.getConfig("no_authorize_page")).addObject("ex",
                        e.getClass().getName());
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }

        return null;
    }
}
