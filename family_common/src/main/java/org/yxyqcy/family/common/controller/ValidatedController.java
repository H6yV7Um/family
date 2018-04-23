package org.yxyqcy.family.common.controller;

import org.springframework.validation.BindingResult;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.message.ResponseMessage;
import org.yxyqcy.family.common.util.PropertiesLoader;
import org.yxyqcy.family.common.validate.ValidatedResult;

import java.util.Map;

/**
 * @descript validated controller
 * @author cy
 * Created by cy on 16/5/20.
 */

public class ValidatedController extends  HttpServletController {
    protected static final String VALIDMESSAGESKEY = "validerrors";

    private static  String  ERROR_PAGE = null;

    public  String getErrorPage(){
        if (null  == ERROR_PAGE)
        {
            PropertiesLoader propertiesLoader = new PropertiesLoader("properties/auto.properties");
            ERROR_PAGE = propertiesLoader.getProperty("errorPagePath","forward:/view/error/500");
        }else {
            ERROR_PAGE = "forward:/view/error/500";
        }
        return ERROR_PAGE;
    }


    public ValidatedController() {
    }

    public boolean hasErrors(BindingResult br) {
        ValidatedResult vr = new ValidatedResult();
        vr.dealBindingResult(br);
        this.request.setAttribute("validerrors", vr);
        return vr.hasErrors().booleanValue();
    }

    public Map<String, String[]> getErrorsMap() {
        ValidatedResult vr = (ValidatedResult)this.request.getAttribute("validerrors");
        return vr.getErrorsMap();
    }

    public ValidatedResult getValidatedResult() {
        ValidatedResult vr = (ValidatedResult)this.request.getAttribute("validerrors");
        return vr;
    }

    public ResponseMessage getValidatedMessage() {
        ValidatedResult vr = (ValidatedResult)this.request.getAttribute("validerrors");
        return vr.getValidatedMessage();
    }

    protected void setBindError(BindingResult br, AjaxResponse ajaxResponse,String message){
        ValidatedResult vr = new ValidatedResult();
        vr.dealBindingResult(br);
        ajaxResponse.setErrorMessage(message,vr);
    }
}
