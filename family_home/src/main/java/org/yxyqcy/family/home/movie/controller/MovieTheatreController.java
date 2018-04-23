package org.yxyqcy.family.home.movie.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yxyqcy.family.common.controller.PaginationController;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.home.movie.entity.MovieTheatre;
import org.yxyqcy.family.home.movie.service.MovieTheatreService;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lcy on 17/2/3.
 */
@Controller
@RequestMapping("/movieTheatre")
public class MovieTheatreController extends PaginationController<MovieTheatre> {

    @Resource
    private MovieTheatreService movieTheatreServiceImpl;

    /**
     * 查询电影院
     * @param movieTheatre
     * @param ajaxResponse
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:movie:view")
    @RequestMapping("queryMovieTheatres")
    public AjaxResponse queryMovieTheatres(MovieTheatre movieTheatre,AjaxResponse ajaxResponse){
        List<MovieTheatre> result = movieTheatreServiceImpl.queryMovieTheatres(movieTheatre);
        ajaxResponse.setSuccessMessage("查询成功",result);
        return  ajaxResponse;
    }
}
