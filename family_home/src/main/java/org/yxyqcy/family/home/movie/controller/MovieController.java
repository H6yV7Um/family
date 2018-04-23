package org.yxyqcy.family.home.movie.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.yxyqcy.family.common.config.Global;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.controller.PaginationController;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.validate.ValidatedResult;
import org.yxyqcy.family.home.movie.entity.Movie;
import org.yxyqcy.family.home.movie.service.MovieService;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/23
 * Time: 上午10:36
 * description: 电影 controller
 */
@Controller
@RequestMapping(value={"/movie","/api/movie"})
public class MovieController extends PaginationController<Movie> {

    @Resource
    private MovieService movieServiceImpl;


    /**
     * 分页查询电影
     * @param param
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:movie:view")
    @RequestMapping("queryMovies")
    public GridModel queryMovies(Movie param){
        //当前登录人
        param.setCreateBy(UserUtils.getUser().getBusinessId());
        movieServiceImpl.queryMoviesByPagination(getPaginationUtility(),param);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }
    /**
     * 分页查询电影 anno
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("queryMoviesAnon")
    public GridModel queryMoviesAnonymous(Movie param){
        //管理员
        param.setCreateBy(Global.getConfig("adminBusinessId"));
        movieServiceImpl.queryMoviesByPagination(getPaginationUtility(),param);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }

    /**
     * 添加电影
     * @param movie
     * @param
     * @return
     */
    @RequiresPermissions("home:movie:add")
    @RequestMapping("movieAdd")
    @ResponseBody
    public AjaxResponse movieAdd(@Valid Movie movie, BindingResult br, AjaxResponse ajaxResponse){
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse, MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = movieServiceImpl.persistMovie(movie);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,movie);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return  ajaxResponse;
    }
    /**
     * to 修改电影页面
     * @param id
     * @param modelAndView
     * @return
     */
    @RequiresPermissions("home:movie:update")
    @RequestMapping("toHg/toMovieUpdatePage/{id}")
    public ModelAndView toMovieUpdatePage(@PathVariable("id") String id, ModelAndView modelAndView){
        modelAndView.getModelMap().addAttribute("movieUpdate",movieServiceImpl.queryMovieById(id));
        modelAndView.setViewName("homeBack/movieAdd");
        return modelAndView;
    }
    /**
     * 修改电影
     * @param movie
     * @param
     * @return
     */
    @RequiresPermissions("home:movie:update")
    @RequestMapping("movieUpdate")
    @ResponseBody
    public AjaxResponse movieUpdate(@Valid Movie movie, BindingResult br, AjaxResponse ajaxResponse){
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse,MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = movieServiceImpl.mergeMovie(movie);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,movie);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return  ajaxResponse;
    }
    /**
     * delete 电影
     * @param id
     * @return
     */
    @RequiresPermissions("home:movie:delete")
    @RequestMapping("movieDelete/{id}")
    @ResponseBody
    public AjaxResponse movieDelete(@PathVariable("id") String id, AjaxResponse ajaxResponse){
        PersistModel persistModel = movieServiceImpl.removeMovie(id);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }
}
