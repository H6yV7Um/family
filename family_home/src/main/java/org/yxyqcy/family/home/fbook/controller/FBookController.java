package org.yxyqcy.family.home.fbook.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.yxyqcy.family.common.model.CommonPageGridModel;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.util.DateUtil;
import org.yxyqcy.family.home.fbook.entity.FBook;
import org.yxyqcy.family.home.fbook.service.FBookService;
import org.yxyqcy.family.home.fbook.util.BookUtil;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* Created by yxyqcy on 2017-11-13 23:12:26.
 *
 *
 *
 *   #book page  $book_upload_location /userid/bookid/page1.pdf...
     #book image $book_upload_location /userid/bookid.png
     #book original $book_upload_location /userid/bookid.pdf
*/
@Controller
@RequestMapping("/book")
public class FBookController extends FileController<FBook> {
    @Autowired
    private FBookService fBookServiceImpl;

    /**
    * 插入FBook
    * @param fBook
    * @param ajaxResponse
    * @return
    */
    @RequiresPermissions("home:book:add")
    @RequestMapping("/bookAdd")
    @ResponseBody
    public AjaxResponse add(@Valid FBook fBook, BindingResult br, AjaxResponse ajaxResponse) {
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse,MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = fBookServiceImpl.persistFbook(fBook);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,fBook);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return ajaxResponse;
    }

    /**
    * 逻辑删除FBook
    * @param id
    * @param ar
    * @return
    */
    @RequestMapping("/logicRemove")
    @ResponseBody
    public AjaxResponse logicRemove(String id,AjaxResponse ar) {
        PersistModel result = fBookServiceImpl.removeFBook(id);
        ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,result);
        return ar;
    }

    /**
    * 根据主键唯一查找
    * @param businessId
    * @param ar
    * @return
    */
    @RequestMapping("/one")
    @ResponseBody
    public AjaxResponse queryOne(String businessId,AjaxResponse ar) {
        ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,fBookServiceImpl.selectOne(businessId));
        return ar;
    }


    /**
    * 根据条件分页查询
    * @param param
    * @param ar
    * @return
    */
    @RequiresPermissions("home:book:view")
    @RequestMapping("/queryFBooksByPagination")
    @ResponseBody
    public GridModel queryFBooksByPagination(FBook param,AjaxResponse ar) {
        //当前登录人
        Subject sub = SecurityUtils.getSubject();
        //没管理权限,或者有管理权限但只看自己的
        if(!sub.isPermitted("home:book:manage") ||
                (sub.isPermitted("home:book:manage") && "0".equals(param.getBookOwn()))) {
            param.setCreateBy(UserUtils.getUser().getBusinessId());
            param.setIsPublic(null);
        }
        else//有管理权限的,能看所有public的
            param.setIsPublic(UserUtils.getUser().getBusinessId());
        fBookServiceImpl.queryFBooksByPagination(getPaginationUtility(),param);
        GridModel gridModel = getGridModelResponse();
        /*分辨所属*/
        if(sub.isPermitted("home:book:manage") && "1".equals(param.getBookOwn())) {
            List<FBook> booksList = ((CommonPageGridModel) gridModel).getRows();
            BookUtil.setOwnsFbooks(booksList,UserUtils.getUser().getBusinessId());
        }
        return gridModel;
    }



    /**
     * story image 上传
     * @param file
     * @param response
     * @return
     */
    @RequiresPermissions("home:book:view")
    @RequestMapping("uploadBook")
    public String uploadImage(@RequestParam(value = "Filedata", required = false) MultipartFile file,HttpServletResponse response){
        String tempPath =
                Global.getConfig("temp_upload_location") + File.separator + DateUtil.getSimepleDate("yyyy-MM-dd",new Date());
        super.transformCommonFile(file,tempPath,"UUID");
        return null;
    }

    /**
     * 浏览 book
     * @param id
     * @param modelAndView
     * @return
     */
    @RequestMapping("browseBook/{id}")
    public ModelAndView browseBook(@PathVariable String id, ModelAndView modelAndView){
        List<String> pageUrls = fBookServiceImpl.browseBookById(id);
        modelAndView.setViewName("book/bookPreview");
        modelAndView.addObject("book", pageUrls);
        return  modelAndView;
    }

    /**
     * 分页查询book anon
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("queryBooksAnon")
    public GridModel queryBooksAnon(FBook param){
        //当前登录人
        param.setIsPublic(null);
        param.setPublicFlag(FBook.isPub);
        fBookServiceImpl.queryFBooksByPagination(getPaginationUtility(),param);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }


    /**
     * 统计分类
     * @param ajaxResponse
     * @return
     */
    @ResponseBody
    @RequestMapping("staticClassify")
    public AjaxResponse staticClassify(AjaxResponse ajaxResponse){
        List<Map> list = fBookServiceImpl.staticBookCountByClassify();
        ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,list);
        return ajaxResponse;
    }
    /**
     * delete 逻辑博客
     * @param id
     * @return
     */
    @RequiresPermissions("home:book:delete")
    @RequestMapping("bookDelete/{id}")
    @ResponseBody
    public AjaxResponse bookDelete(@PathVariable("id") String id, AjaxResponse ajaxResponse){
        //当前登录人
        Subject sub = SecurityUtils.getSubject();
        PersistModel persistModel = null;
        //有管理权限
        if(sub.isPermitted("home:book:manage")){
            persistModel = fBookServiceImpl.removeFBook(id);
        }else{
            FBook book = fBookServiceImpl.queryFBookById(id);
            //不是本人
            if(null == book || !book.getCreateBy().equals(UserUtils.getUser().getBusinessId())) {
                ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_AUTHOR_INVALID, null);
                return ajaxResponse;
            }
            persistModel = fBookServiceImpl.removeFBook(id);
        }
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * 公开 book
     * @param book
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("home:book:view")
    @RequestMapping("publicBook")
    @ResponseBody
    public AjaxResponse publicBook(FBook book, AjaxResponse ajaxResponse){
        PersistModel persistModel = fBookServiceImpl.publicBook(book);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * 推荐 书籍
     * @param book
     * @return
     */
    @RequiresPermissions("home:book:manage")
    @RequestMapping("topBook")
    @ResponseBody
    public AjaxResponse topBook(FBook book, AjaxResponse ajaxResponse){
        //当前登录人
        PersistModel persistModel = fBookServiceImpl.topBlog(book);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    //queryCodeValidate 查看code 合法
    /**
     * @return  true 合法   false 不合法  不能有其它的输出
     */
    @RequiresPermissions("home:book:view")
    @RequestMapping("queryCodeValidate")
    @ResponseBody
    public String queryCodeValidate(FBook book){
        book.setIsPublic(null);
        List<FBook> bookList = fBookServiceImpl.queryFBooks(book);
        if(StringUtils.isBlank(book.getBusinessId())){//add
            if(bookList.size()==0){
                return "true";
            }else
                return "false";
        }else{//update
            if(bookList.size()<=1){
                return "true";
            }else
                return "false";
        }

    }
}
