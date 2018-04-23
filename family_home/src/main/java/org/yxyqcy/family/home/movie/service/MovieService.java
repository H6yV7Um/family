package org.yxyqcy.family.home.movie.service;


import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.home.movie.entity.Movie;

import java.util.List;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/23
 * Time: 上午10:23
 * description:  电影 service
 */
public interface MovieService {
    /**
     * 分页查询电影
     * @param pageUtil  分页组件
     * @param movie   查询条件
     */
    public List<Movie> queryMoviesByPagination(PageUtil pageUtil, Movie movie);

    /**
     * 插入 电影
     * @param param  movie
     */
    PersistModel persistMovie(Movie param);

    /**
     * 修改 电影
     * @param param
     */
    PersistModel mergeMovie(Movie param);
    /**
     * 删除 电影
     * @param businessId
     */
    PersistModel removeMovie(String businessId);

    /**
     * 根据id查询movie
     * @param id
     * @return
     */
    Movie queryMovieById(String id);
}
