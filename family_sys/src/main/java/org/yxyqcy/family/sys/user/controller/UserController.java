package org.yxyqcy.family.sys.user.controller;


import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.controller.PaginationController;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.util.FamilyLogger;
import org.yxyqcy.family.sys.user.entity.User;
import org.yxyqcy.family.sys.user.service.UserService;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/16
 * Time: 上午10:28
 * description: user controller
 */
@Controller
@RequestMapping("/user")
public class UserController extends PaginationController<User> {

    static final String USER_MANAGE_PAGE = "redirect:/view/background/user";

    @Resource
    public UserService userServiceImpl;

    /**
     * 分页查询用户
     * @param param
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:user:view")
    @RequestMapping("queryUsers")
    public GridModel queryUsers(User param){
        userServiceImpl.queryUsersByPagination(getPaginationUtility(),param);
        GridModel gridModel = getGridModelResponse();
        FamilyLogger.sysInfo(UserUtils.getUser().getBusinessId(),"查询users");
        return  gridModel;
    }

    /**
     * 添加用户
     * @param param
     * @param model
     * @return
     */

    /**
     * RedirectAttributesModelMap
     * 该类实现了RedirectAttributes接口，提供一个闪存存储方案，使属性能够在重定向时依旧生存而不用嵌入到url
     *
     * 其实最底层仍旧是两种跳转，只不过spring又进行了封装而已，原理是把属性放到session中，在跳到页面后又在session中马上移除对象，所以在刷新一下后这个值就会丢掉。
     */
/*    public String userAdd(HttpSession session, User param, RedirectAttributesModelMap model, BindingResult br){
        // jsr validation
        if(super.hasErrors(br)){
            return getErrorPage();
        }
        PersistModel persistModel = userServiceImpl.persistUser(param);
        if(persistModel.isSuccessBySinglePersist()) {
            super.operate_succes_redirect(model);
            return USER_MANAGE_PAGE;
        }
        else {
            super.operate_failture_redirect(model);
            return USER_MANAGE_PAGE;
        }
    }*/
    @RequiresPermissions("sys:user:view")
    @RequestMapping("adminLog/userAdd")
    @ResponseBody
    public AjaxResponse userAdd(User user, RedirectAttributesModelMap model, BindingResult br,AjaxResponse ar){
        PersistModel persistModel = userServiceImpl.persistUser(user);
        if(persistModel.isSuccessBySinglePersist()) {
        	ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,user.getBusinessId());
        }
        else {
        	ar.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,MessageConstant.MESSAGE_ALERT_ERROR);
        }
        return ar;
    }
    
    /**
     * 删除用户 delFlag=1
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping("adminLog/userDel")
    @ResponseBody
    public AjaxResponse userDel(User param, RedirectAttributesModelMap model, BindingResult br,AjaxResponse ar){
    	param.setDelFlag("1");
    	PersistModel persistModel = userServiceImpl.removeUser(param);
        if(persistModel.isSuccessBySinglePersist())
        	ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,param.getName());
        else
        	ar.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,MessageConstant.MESSAGE_ALERT_ERROR);
        return ar;
    }
    
    
    /**
     * ajax jquery validator  增加用户验证 邮箱和工号是否重复
     * @return  true 合法   false 不合法  不能有其它的输出
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping("queryLoginNameValidate")
    @ResponseBody
    public String queryLoginNameValidate(User u){
    	List<User> userList=userServiceImpl.queryUsersByPagination(getPaginationUtility(),u);
    	if(u.getBusinessId()==null||u.getBusinessId()==""){//add
    		if(userList.size()==0){
    			return "true";
    		}else
    			return "false";
    	}else{//update
    		if(userList.size()<=1){
    			return "true";
    		}else
    			return "false";
    	}
       
    }

    /**
     * 修改密码  原始密码是否正确
     * @param lastPass
     * @return
     *
     * //RequiresAuthentication注解要求在访问或调用被注解的类/实例/方法时，Subject在当前的session中已经被验证。
       //RequiresGuest注解
       //RequiresGuest注解要求当前Subject是一个“访客”，也就是，在访问或调用被注解的类/实例/方法时，他们没有被认证或者在被前一个Session记住。
     */
    @RequiresAuthentication
    @RequestMapping("queryLastPassWdValidate")
    @ResponseBody
    public String queryLastPassWdValidate(String lastPass){
        User user  = userServiceImpl.validateUserPass(lastPass);
        if(null != user)
            return "true";
        else
            return "false";
    }

    /**
     * update user pass
     * @param param
     * @param ar
     * @return
     */
    @RequiresAuthentication
    @RequestMapping("updateUserPass")
    @ResponseBody
    public AjaxResponse updateUserPass(User param , AjaxResponse ar){
        PersistModel persistModel = userServiceImpl.updateUserPass(param);
        if(persistModel.isSuccessBySinglePersist())
            ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,param.getBusinessId());
        else
            ar.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,MessageConstant.MESSAGE_ALERT_ERROR);
        return ar;
    }

    /**
     * to 修改用户页面
     * @param id
     * @param modelAndView
     * @return
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping("toBg/toUserUpdatePage/{id}")
    public ModelAndView toUserUpdatePage(@PathVariable("id") String id, ModelAndView modelAndView){
        modelAndView.getModelMap().addAttribute("userUpdate",userServiceImpl.queryUserById(id));
        modelAndView.setViewName("background/userAdd");
        return modelAndView;
    }
    
    /**
     * to 修改用户
     * @return
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping("adminLog/userUpdate")
    @ResponseBody
    public AjaxResponse userUpdate(HttpSession session, User param, RedirectAttributesModelMap model, BindingResult br,AjaxResponse ar){
    	PersistModel persistModel = new PersistModel(userServiceImpl.updateUser(param));
        if(persistModel.isSuccessBySinglePersist())
        	ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,param.getBusinessId());
        else
        	ar.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,MessageConstant.MESSAGE_ALERT_ERROR);
        return ar;
    }

    /**
     * 权限奉陪
     * @param userId        用户id
     * @param role_users    用户绑定角色
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("sys:role:permission")
    @RequestMapping("adminLog/distributeRole")
    @ResponseBody
    public AjaxResponse distributeRole(@RequestParam("userId") String userId, @RequestParam("role_users")
            String role_users , AjaxResponse ajaxResponse){

        PersistModel persistModel = userServiceImpl.doDistributeRole(userId,role_users);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }
    
    /**
     * 查找用户资料
     * @MethodName:queryMyInfo
     * @author: 张雪峰
     * @email: 734126206@qq.com 
     * @date 2016年11月24日 下午4:57:00
     * @version V1.0
     * @param ar
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:user:view")
    @RequestMapping("queryMyInfo")
    public AjaxResponse queryMyInfo(AjaxResponse ar){ 
		ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,userServiceImpl.queryMyInfo());
        return ar;
    }
}
