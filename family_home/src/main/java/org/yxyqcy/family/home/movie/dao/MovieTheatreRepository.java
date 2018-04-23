package org.yxyqcy.family.home.movie.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.home.movie.entity.MovieTheatre;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/2/3.
 */
@Repository
public interface MovieTheatreRepository extends Mapper<MovieTheatre> {
    /**
     * 查询电影院
     * @param param
     * @return
     */
    @Select("<script>select * from f_movie_theatre where 1=1 " +
            "<if test=\"flag !='' and flag !=null \"> and flag = #{flag} </if>" +
            "<if test=\"delFlag !='' and delFlag !=null \"> and del_flag = #{delFlag} </if>" +
            " order by order_time" +
            "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<MovieTheatre> queryMovieTheatres(Map<String, Object> param);
}
