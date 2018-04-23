package org.yxyqcy.family.common.security;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lcy on 16/8/31.
 */
public class FamilySimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    //扩展ajax权限不足
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {


        String viewName = this.determineViewName(ex, request);
        if(viewName != null) {
            Integer statusCode = this.determineStatusCode(request, viewName);
            if(statusCode != null) {
                this.applyStatusCodeIfPossible(request, response, statusCode.intValue());
            }
            //ajax 权限不足
            if(WebUtil.isAjaxRequest(request)){
                AjaxResponse ajax = new AjaxResponse();
                ajax.setErrorMessage(ex.getMessage(),null);
                try {
                    response.setContentType("text/html; utf-8");
                    response.getWriter().print(JSONObject.toJSONString(ajax,
                            SerializerFeature.WriteMapNullValue));
                }catch (Exception e){
                    logger.error(e.getMessage());
                }
                return null;
            }
            else
                return this.getModelAndView(viewName, ex, request);
        } else {
            return null;
        }

    }
}
