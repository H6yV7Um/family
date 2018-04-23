package org.yxyqcy.family.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yxyqcy.family.common.config.Global;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

/**
 * Created by lcy on 16/8/31.
 */
@Controller
@RequestMapping({"/global"})
public class GlobalController {

    protected Logger logger = LoggerFactory.getLogger(getClass());



    /**
     * 无认证 请求类型判断
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping("/noAuthentication")
    public String authenticationException(ModelMap modelMap, HttpServletRequest request) {

        if(WebUtil.isAjaxRequest(request)){
            return "redirect:/global/asynNoAuthentication";
        }else{
            return "redirect:/global/synNoAuthentication";
        }

    }
    /**
     * 异步请求 认证
     * @param modelMap
     * @param request
     * @param response
     */
    @RequestMapping("/asynNoAuthentication")
    public void asynNoAuthentication(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        AjaxResponse ajax = new AjaxResponse();
        ajax.setErrorMessage(MessageConstant.MESSAGE_ALERT_NOAUTHENTICATION,AjaxResponse.ErrorAjaxCode.ERROR_CODE_NOAUTHENTICATION);
        PrintWriter out  = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            out.print(JSONObject.toJSONString(ajax,
                    SerializerFeature.WriteMapNullValue));
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            out.flush();
            out.close();
        }
    }

    /**
     * 同步请求 认证
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping("/synNoAuthentication")
    public String synNoAuthentication(ModelMap modelMap, HttpServletRequest request) {
        return Global.getConfig("login_page");
    }



    /**
     * 无权限  请求类型判断
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping("/noAuthorization")
    public String authorizationException(ModelMap modelMap, HttpServletRequest request) {

        if(WebUtil.isAjaxRequest(request)){
            return "redirect:/global/asynNoAuthorization";
        }else{
            return "redirect:/global/synNoAuthorization";
        }

    }
    /**
     * 异步请求 权限
     * @param modelMap
     * @param request
     * @param response
     */
    @RequestMapping("/asynNoAuthorization")
    public void asynNoAuthorization(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        AjaxResponse ajax = new AjaxResponse();
        ajax.setErrorMessage(MessageConstant.MESSAGE_ALERT_NOAUTHORIZATION,AjaxResponse.ErrorAjaxCode.ERROR_CODE_NOAUTHORIZATION);
        PrintWriter out  = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            out.print(JSONObject.toJSONString(ajax,
                    SerializerFeature.WriteMapNullValue));
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            out.flush();
            out.close();
        }
    }

    /**
     * 同步请求 权限
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping("/synNoAuthorization")
    public String synNoAuthorization(ModelMap modelMap, HttpServletRequest request) {
        return Global.getConfig("no_authorize_page");
    }
}
