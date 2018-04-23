package org.yxyqcy.family.home.blog.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.yxyqcy.family.common.config.Global;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.controller.FileController;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.model.*;
import org.yxyqcy.family.home.blog.entity.Blog;
import org.yxyqcy.family.home.blog.entity.BlogLabel;
import org.yxyqcy.family.home.blog.service.BlogLabelService;
import org.yxyqcy.family.home.blog.service.BlogService;
import org.yxyqcy.family.home.blog.util.BlogUtil;
import org.yxyqcy.family.sys.user.entity.User;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/24
 * Time: 上午10:14
 * description: 博客  controller
 */
@Controller
@RequestMapping(value={"/blog","/api/blog"})
public class BlogController extends FileController<Blog> {


    @Resource
    private BlogService blogServiceImpl;

    @Resource
    private BlogLabelService blogLabelServiceImpl;

    /**
     * 分页查询博客
     * @param param
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:blog:view")
    @RequestMapping("queryBlogs")
    public GridModel queryBlogs(Blog param){
        //当前登录人
        Subject sub = SecurityUtils.getSubject();
        //没管理权限,或者有管理权限但只看自己的
        if(!sub.isPermitted("home:blog:manage") ||
                (sub.isPermitted("home:blog:manage") && "0".equals(param.getBlogOwn()))) {
            param.setCreateBy(UserUtils.getUser().getBusinessId());
            param.setIsPublic(null);
        }
        else//有管理权限的,能看所有public的
            param.setIsPublic(UserUtils.getUser().getBusinessId());
        blogServiceImpl.queryBlogsByPagination(getPaginationUtility(),param);
        GridModel gridModel = getGridModelResponse();
        /*分辨所属*/
        if(sub.isPermitted("home:blog:manage") && "1".equals(param.getBlogOwn())) {
            List<Blog> blogList = ((CommonPageGridModel) gridModel).getRows();
            BlogUtil.setOwnsBlogs(blogList,UserUtils.getUser().getBusinessId());
        }
        return  gridModel;
    }

    /**
     * 查询 my 博客
     * @param param
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:blog:view")
    @RequestMapping("querySheetBlogs")
    public GridModel querySheetBlogs(Blog param){
        //当前登录人
        param.setIsPublic(null);
        param.setCreateBy(UserUtils.getUser().getBusinessId());
        blogServiceImpl.queryBlogsByPagination(getPaginationUtility(),param);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }


    /**
     * 查询 my 博客
     * @param param
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:blog:view")
    @RequestMapping("querySheetBlogsBySheet")
    public GridModel querySheetBlogsBySheet(Blog param,CommonPageGridModel gridModel){
        List<Blog> list = blogServiceImpl.queryBlogsBySheetByPagination(getPaginationUtility(), param);
        gridModel.setRows(list);
        gridModel.setTotal(list.size());
        return  gridModel;
    }
    /**
     * 分页查询博客 anon
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("queryBlogsAnon")
    public GridModel queryBlogsAnon(Blog param){
        //当前登录人
        //param.setIsTop(Blog.isTJ);
        param.setIsPublic(null);
        param.setPublicFlag(Blog.isPub);
        /*日程 null*/
        param.setSheetId(null);
        blogServiceImpl.queryBlogsByPagination(getPaginationUtility(),param);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }


    /**
     * 添加博客
     * @param param
     * @return
     */
    @RequiresPermissions("home:blog:add")
    @RequestMapping("blogAdd")
    @ResponseBody
    //这种方式是有一个约定的，就是BindingResult必须紧随@valid之后。  否则验证之前 hasErrors 就会报400
    public AjaxResponse blogAdd(HttpSession session, @Valid Blog param, BindingResult br, AjaxResponse ajaxResponse){
        // jsr validation
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse,MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = blogServiceImpl.persistBlog(param);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,param);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return ajaxResponse;

    }

    /**
     * 保存 minder json
      * @param param
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("home:blog:add")
    @RequestMapping("blogAddMinder")
    @ResponseBody
    public AjaxResponse blogAddMinder(Blog param,AjaxResponse ajaxResponse){
        Blog blog = blogServiceImpl.queryBlogById(param.getBusinessId());
        if(null == blog)
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_DATA_ERROR,null);
        /*非本人 权限不足*/
        else if(!blog.getCreateBy().equals(UserUtils.getUser().getBusinessId()))
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_AUTHOR_INVALID,null);
        else{
            PersistModel persistModel = blogServiceImpl.mergeBlog(param,blog,true);
            if(persistModel.isSuccessBySinglePersist()) {
                ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,param);
            }
            else {
                ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
            }
        }
        return ajaxResponse;

    }
    /**
     * query by id
     * @param id
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("home:blog:view")
    @RequestMapping("queryOne/{id}")
    @ResponseBody
    public AjaxResponse queryOne(@PathVariable("id") String id, AjaxResponse ajaxResponse){
        Blog blog = blogServiceImpl.queryBlogById(id);
        if(null == blog)
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_DATA_ERROR,null);
        else
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,blog);
        return ajaxResponse;
    }

    /**
     * query by id
     * @param id
     * @param ajaxResponse
     * @return
     */
    //@RequiresPermissions("home:blog:view")   //不加权限 可以预览
    @RequestMapping("queryOneMinderInfo/{id}")
    @ResponseBody
    public AjaxResponse queryOneMinder(@PathVariable("id") String id, AjaxResponse ajaxResponse){
        /*只能预览自己的或public的*/
        Blog blog = blogServiceImpl.browseBlogInToc(id);
        if(null == blog)
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_DATA_ERROR,null);
        else
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,blog);
        return ajaxResponse;
    }

    /**
     * 修改博客
     * @param param
     * @return
     */
    @RequiresPermissions("home:blog:update")
    @RequestMapping("blogUpdate")
    @ResponseBody
    //这种方式是有一个约定的，就是BindingResult必须紧随@valid之后。  否则验证之前 hasErrors 就会报400
    public AjaxResponse blogUpdate(HttpSession session, @Valid Blog param, BindingResult br, AjaxResponse ajaxResponse){
        // jsr validation
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse,MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = blogServiceImpl.mergeBlog(param,null,false);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,param);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return ajaxResponse;

    }
    /**
     * delete 逻辑博客
     * @param id
     * @return
     */
    @RequiresPermissions("home:blog:delete")
    @RequestMapping("blogDelete/{id}")
    @ResponseBody
    public AjaxResponse blogDelete(@PathVariable("id") String id, AjaxResponse ajaxResponse){
        //当前登录人
        Subject sub = SecurityUtils.getSubject();
        PersistModel persistModel = null;
        //有管理权限
        if(sub.isPermitted("home:blog:manage")){
            persistModel = blogServiceImpl.removeBlog(id);
        }else{
            Blog blog = blogServiceImpl.queryBlogById(id);
            //不是本人
            if(null == blog || !blog.getCreateBy().equals(UserUtils.getUser().getBusinessId())) {
                ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_AUTHOR_INVALID, null);
                return ajaxResponse;
            }
            persistModel = blogServiceImpl.removeBlog(id);
        }
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * 推荐 逻辑博客
     * @param blog
     * @return
     */
    @RequiresPermissions("home:blog:manage")
    @RequestMapping("topBlog")
    @ResponseBody
    public AjaxResponse topBlog(Blog blog, AjaxResponse ajaxResponse){
        //当前登录人
        PersistModel persistModel = blogServiceImpl.topBlog(blog);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * 推荐 逻辑博客
     * @param blog
     * @return
     */
    @RequiresPermissions("home:blog:view")
    @RequestMapping("publicBlog")
    @ResponseBody
    public AjaxResponse publicBlog(Blog blog, AjaxResponse ajaxResponse){
        PersistModel persistModel = blogServiceImpl.publicBlog(blog);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * to 修改博客页面
     * @param id
     * @param modelAndView
     * @return
     */
    @RequiresPermissions("home:blog:update")
    @RequestMapping("toHg/toBlogUpdatePage/{id}")
    public ModelAndView toBlogUpdatePage(@PathVariable("id") String id, ModelAndView modelAndView){
        Blog blog = blogServiceImpl.browseBlogInToc(id);
        modelAndView.getModelMap().addAttribute("blogUpdate",blog);
        modelAndView.setViewName("homeBack/blogAdd");
        return modelAndView;
    }

    /**
     * 到mind 修改页面
     * @param id
     * @param modelAndView
     * @return
     */
    //不加权限 可以预览
    @RequestMapping("toHg/toMindUpdatePage/{id}")
    public ModelAndView toMindUpdatePage(@PathVariable("id") String id, ModelAndView modelAndView){
        Subject sub = SecurityUtils.getSubject();
        Blog blog = blogServiceImpl.browseBlogInToc(id);
        modelAndView.addObject("blog",blog);
        modelAndView.setViewName("mind/mind");
        User user = UserUtils.getUser();
        //未登录,且不是public
        if((null == user || StringUtils.isBlank(user.getBusinessId()) &&
                Blog.isNotPub.equals(blog.getIsPublic()))){
            modelAndView.setViewName("redirect:home/blog");
        }
        //已经登录
        else if(null != user && !StringUtils.isBlank(user.getBusinessId()) &&
                blog.getCreateBy().equals(user.getBusinessId()) ){
            modelAndView.addObject("owns",1);
        }
        //;.getBusinessId()
        return modelAndView;
    }


    /**
     * 浏览blog toc
     * @param id
     * @param modelAndView
     * @return
     */
    @RequestMapping("browseBlog/{id}")
    public ModelAndView browseBlogInToc(@PathVariable String id,ModelAndView modelAndView){
        Subject sub = SecurityUtils.getSubject();
        Blog blog = blogServiceImpl.browseBlogInToc(id);
        User user = UserUtils.getUser();
        //未登录,且不是public
        if((null == user || StringUtils.isBlank(user.getBusinessId()) &&
                Blog.isNotPub.equals(blog.getIsPublic()))){
            modelAndView.setViewName("redirect:/view/home/blog");
        }
        else{
            modelAndView.setViewName("blog/blogTemp");
            modelAndView.addObject("blog", blog);
        }
        return  modelAndView;
    }

    /**
     * 统计分类
     * @param ajaxResponse
     * @return
     */
    @ResponseBody
    @RequestMapping("staticClassify")
    public AjaxResponse staticClassify(AjaxResponse ajaxResponse){
        List<Map> list = blogServiceImpl.staticBlogCountByClassify();
        ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,list);
        return ajaxResponse;
    }


    /**
     * md editor   上传图片
     * @param file
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:blog:view")
    @RequestMapping("updatePicture")  //editormd-image-file
    public MdResponse updatePicture(@RequestParam(value = "editormd-image-file")MultipartFile file){
        String finalName = super.globalCommonFileUpload(file, "UUID", Global.getConfig("base_upload_location")
                + Global.getConfig("md_upload_location"));
        return new MdResponse(1,"上传成功",
                Global.getConfig("virtualServer")+Global.getConfig("md_upload_location")
        + finalName);
    }

    /**
     * mind file upload
     * @param file
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:blog:view")
    @RequestMapping("updateMindPicture")  //editormd-image-file
    public MindResponse updateMindPicture(@RequestParam(value = "upload_file")MultipartFile file){
        String finalName = super.globalCommonFileUpload(file, "UUID", Global.getConfig("base_upload_location")
                + Global.getConfig("mind_upload_location"));
        return new MindResponse(0,"上传成功",
                Global.getConfig("virtualServerAddress")+
                Global.getConfig("virtualServer")+Global.getConfig("mind_upload_location")
                + finalName);
    }


    /**
     * 查询blogLabel 数量
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("home:blog:view")
    @RequestMapping("queryBlogLabelCount")
    @ResponseBody
    public AjaxResponse queryBlogLabelCount(BlogLabel label, AjaxResponse ajaxResponse){
        return ajaxResponse;
    }

    /**
     * query labels
     * @param blogLabel
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("home:blog:view")
    @RequestMapping("queryBlogLabels")
    @ResponseBody
    public AjaxResponse queryBlogLabels(BlogLabel blogLabel,AjaxResponse ajaxResponse){
        //当前登录人
        blogLabel.setCreateBy(UserUtils.getUser().getBusinessId());
        List<BlogLabel> result = blogLabelServiceImpl.queryBlogLabels(blogLabel);
        ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,result);
        return ajaxResponse;
    }

    /**
     * 增加 blogLabel
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("home:blog:add")
    @RequestMapping("blogLabelAdd")
    @ResponseBody
    public AjaxResponse blogLabelAdd(BlogLabel label, AjaxResponse ajaxResponse){
        PersistModel persistModel = blogLabelServiceImpl.persistBlogLabel(label);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,label);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * update label title
     * @param label
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("home:blog:update")
    @RequestMapping("blogLabelUpdate")
    @ResponseBody
    public AjaxResponse blogLabelUpdate(BlogLabel label, AjaxResponse ajaxResponse){
        PersistModel persistModel = blogLabelServiceImpl.mergeBlogLabel(label);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,label);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * 删除 blogLabel
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("home:blog:delete")
    @RequestMapping("blogLabelDelete/{id}")
    @ResponseBody
    public AjaxResponse blogLabelDelete(@PathVariable("id") String id, AjaxResponse ajaxResponse){
        PersistModel persistModel = blogLabelServiceImpl.removeBlogLabel(id);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(
                    (persistModel.getInfluenceReason() == null || "".equals(persistModel.getInfluenceReason()))
                            ? MessageConstant.MESSAGE_ALERT_ERROR : persistModel.getInfluenceReason(),null);
        return ajaxResponse;
    }
}
