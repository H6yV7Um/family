package org.yxyqcy.family.home.user.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yxyqcy.family.common.controller.PaginationController;
import org.yxyqcy.family.common.model.CommonPageGridModel;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.home.rtopic.entity.RTopic;
import org.yxyqcy.family.home.rtopic.service.RTopicService;
import org.yxyqcy.family.sys.user.entity.User;

import java.util.List;

/**
* Created by licy on 2017-9-29 19:56:42.
*/
@Controller
@RequestMapping("/homeUser")
public class HomeUserController extends PaginationController<User> {
    @Autowired
    private RTopicService rTopicServiceImpl;


}
