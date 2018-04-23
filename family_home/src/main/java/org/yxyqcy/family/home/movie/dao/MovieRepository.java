package org.yxyqcy.family.home.movie.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.home.movie.entity.Movie;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/23
 * Time: 上午10:20
 * description:  movie repository
 */
@Repository
public interface MovieRepository extends Mapper<Movie> {

    /**
     * 查询电影
     * @param movie  param
     * @return
     *
     * @Select("<script>select * from user where <if test=\"username !=null \">username = #{username} </if> <if test=\"phone !=null \">phone = #{phone} </if> <if test=\"usermail !=null \">usermail = #{usermail } </if></script>")
     * @Select  查询  第一列不能为null 否则查不出来  不知道是不是bug  建议id放在第一列
     */
    @Select("<script>select f.*,thea.name as movieTheatreName from f_movie f left join f_movie_theatre thea on f.movie_theatre = thea.business_id and thea.flag = 1 where 1=1 " +
                "<if test=\"createBy !='' and createBy !=null \"> and f.create_by = #{createBy} </if>" +
                "<if test=\"flag !='' and flag !=null \"> and f.flag = #{flag} </if>" +
                "<if test=\"delFlag !='' and delFlag !=null \"> and f.del_flag = #{delFlag} </if>" +
                "<if test=\"name !='' and name !=null \"> " +
                        " and (f.name like concat('%',#{name},'%') or f.name_pinyin like concat('%',#{namePinyin},'%'))" +
                "</if>" +
            "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<Movie> queryMovies(Map movie);

    /**
     * 动aram param
     * @re态修改
     * @pturn
     *
     */
    @Update("<script>update f_movie " +
                "<trim prefix=\"SET\" suffixOverrides=\",\">" +
                    " <if test=\"name !='' and name !=null \" >name = #{name} ,</if>" +
                    " <if test=\"star !='' and star !=null \" >star = #{star} ,</if>" +
                    " <if test=\"price !='' and price !=null \" >price = #{price} ,</if>" +
                    " <if test=\"location !='' and location !=null \" >location = #{location} ,</if>" +
                    " <if test=\"movieTheatre !='' and movieTheatre !=null \" >movie_theatre = #{movieTheatre} ,</if>" +
                    " <if test=\"flyCount !='' and flyCount !=null \" >fly_count = #{flyCount} ,</if>" +
                    " <if test=\"movieInterval !='' and movieInterval !=null \" >movie_interval = #{movieInterval} ,</if>" +
                    " <if test=\"remarks !='' and remarks !=null \" >remarks = #{remarks} ,</if>" +
                    " <if test=\"namePinyin !='' and namePinyin !=null \" >name_pinyin = #{namePinyin} ,</if>" +

                    //commons
                    " <if test=\"createBy !='' and createBy !=null \" > create_by = #{createBy} ,</if>" +
                    " <if test=\"updateBy !='' and updateBy !=null \" > update_by = #{updateBy} ,</if>" +
                    " <if test=\"createDate !=null \" > create_date = #{createDate} ,</if>" +
                    " <if test=\"updateDate !=null \" > update_date = #{updateDate} ,</if>" +
                    " <if test=\"flag !='' and flag !=null \" > flag = #{flag} ,</if>" +
                    " <if test=\"delFlag !='' and delFlag !=null \" > del_flag = #{delFlag} ,</if>" +
                "</trim>" +
            " where business_id = #{businessId} </script>")

    int updateSelective(Movie param);

    /**
     * 根据 businessId 查询
     * @param id
     * @return
     */
    @Select("select *,'' as movieTheatreName from f_movie where business_id = #{id}")
    @ResultMap(value = "BaseResultMap" )
    Movie selectByBusinessId(String id);

    /**
     * 逻辑删除
     * @param movie
     * @return
     */
    @Update("<script>update f_movie " +
            "<trim prefix=\"SET\" suffixOverrides=\",\">" +
            //commons
            " <if test=\"createBy !='' and createBy !=null \" > create_by = #{createBy} ,</if>" +
            " <if test=\"updateBy !='' and updateBy !=null \" > update_by = #{updateBy} ,</if>" +
            " <if test=\"createDate !=null \" > create_date = #{createDate} </if>" +
            " <if test=\"updateDate !=null \" > update_date = #{updateDate} ,</if>" +
            " <if test=\"flag !='' and flag !=null \" > flag = #{flag} ,</if>" +
            " <if test=\"delFlag !='' and delFlag !=null \" > del_flag = #{delFlag} ,</if>" +
            "</trim>" +
            " where business_id = #{businessId} </script>")
    int deleteLogic(Movie movie);
}
