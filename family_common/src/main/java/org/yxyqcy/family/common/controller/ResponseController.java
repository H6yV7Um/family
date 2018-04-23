package org.yxyqcy.family.common.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.yxyqcy.family.common.constant.MessageConstant;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/22
 * Time: 上午8:57
 * description:  响应 controller
 */
public class ResponseController extends  ValidatedController{

    /**
     * 操作成功
     */
    protected  static String MESS_TRUE = "?messageFlag=1";

    /**
     * 操作失败
     */
    protected  static String MESS_FLASE = "?messageFlag=0";

    /**
     * 操作成功 重定向  闪存
     * @param model
     */
    protected  void operate_succes_redirect(RedirectAttributesModelMap model){
        model.addFlashAttribute("message","操作成功");
        model.addFlashAttribute("messageFlag","1");
    }


    /**
     * 操作失败 重定向  闪存
     * @param model
     */
    protected  void operate_failture_redirect(RedirectAttributesModelMap model){
        model.addFlashAttribute("message", MessageConstant.MESSAGE_ALERT_ERROR);
        model.addFlashAttribute("messageFlag","0");
    }
}
