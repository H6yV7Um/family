package org.yxyqcy.family.common.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with family.
 * User: cy
 * Date: 16/5/23
 * Time: 上午10:54
 * description:  视图访问器
 */
@Controller
@RequestMapping({"/view"})
public class ViewAccessController {
    /**
     * 视图访问  当前只适配二级页面  view/story/time
     *                            view/story/time/time  目前不支持
     * @param module  模块
     * @param page    页面
     * @return
     */
    @RequestMapping({"{module}/{page}"})
    public ModelAndView accessView(@PathVariable("module") String module, @PathVariable("page") String page,
                             @RequestParam(required = false,value = "mf") String flag, ModelAndView model) {
        model.setViewName(""+module + "/" + page);
        if(StringUtils.isNotEmpty(flag))
            model.getModelMap().put("messageFlag",flag);
        return model;
    }
}
