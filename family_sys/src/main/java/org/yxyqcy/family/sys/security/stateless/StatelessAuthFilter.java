package org.yxyqcy.family.sys.security.stateless;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.constant.SysConstants;
import org.yxyqcy.family.common.message.AjaxResponse;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lcy on 17/3/4.
 *
 * 无状态 auth filter
 */
@Component
public class StatelessAuthFilter extends AccessControlFilter {

    private Logger logger = LoggerFactory.getLogger(StatelessAuthFilter.class);


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        //1、客户端生成的消息摘要
        String clientDigest = request.getParameter(SysConstants.PARAM_DIGEST);
        //2、客户端传入的用户身份
        String username = request.getParameter(SysConstants.PARAM_USERNAME);
        //3、客户端参数列表顺序
        String paramList = request.getParameter(SysConstants.PARAM_LIST);
        //无身份信息
        if(null == username || "".equals(username.trim())){
            return false;
        }
        //3、客户端请求的参数列表
        Map<String, String[]> params =
                new LinkedHashMap<>(request.getParameterMap());
        params.remove(SysConstants.PARAM_DIGEST);
        params.remove(SysConstants.PARAM_LIST);
        //params.forEach((key,value)-> logger.info(key+"-"+ Arrays.toString(value)));
        //4、生成无状态Token
        StatelessToken token = new StatelessToken(username, params, clientDigest);
        token.setOriginDigest(params,paramList);
        try {
            //5、委托给Realm进行登录
            Subject subject = getSubject(request, response);
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            this.printAjaxReponse(httpServletResponse, false,MessageConstant.MESSAGE_ALERT_AUTHENTICATION_FAIL,AjaxResponse.ErrorAjaxCode.ERROR_CODE_NOAUTHENTICATION);
            return false;
        }
        return true;
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
