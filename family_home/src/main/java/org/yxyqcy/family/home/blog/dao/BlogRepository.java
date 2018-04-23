package org.yxyqcy.family.home.blog.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.home.blog.entity.Blog;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/24
 * Time: 上午10:02
 * description:  blogRepository
 */
@Repository
public interface BlogRepository extends Mapper<Blog> {

    /**
     * 分页查询博客
     * @param   movie
     * @return
     *
     */
    @Select("<script>select f.*,s.LOGIN_NAME as blogUserName,s.name as blogUseRealName,'' checked from f_blog f left join sys_user s on f.create_by = s.business_id  where 1=1 " +

            "<if test=\"classify !=null and classify !='' \"> and f.classify = #{classify} </if>" +

            "<if test=\"blogLabelId !=null and blogLabelId !='' \"> and f.blog_label_id = #{blogLabelId} </if>" +

            "<if test=\"title !=null and title !='' \"> and f.title like concat('%',#{title},'%') </if>" +

            "<if test=\"createBy !='' and createBy !=null \"> and f.create_by = #{createBy} </if>" +

            "<if test=\"isTop !='' and isTop !=null \"> and f.is_top = #{isTop} </if>" +

            "<if test=\"flag !='' and flag !=null \"> and f.flag = #{flag} </if>" +

            "<if test=\"delFlag !='' and delFlag !=null \"> and f.del_flag = #{delFlag} </if>" +

            "<if test=\"isPublic !='' and isPublic !=null \"> and  ((f.create_by = #{isPublic}) or (f.create_by != #{isPublic} and f.is_public = '1'))</if>" +

            "<if test=\"publicFlag !='' and publicFlag !=null \"> and  f.is_public = #{publicFlag} </if>" +

            "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<Blog> queryBlogs(Map movie);


    /**
     * 分页关联查询 sheet 博客
     * @param   movie
     * @return
     *
     *
     * bug   mysql if  与 mybatis if 同时使用,会报错,但是程序可以查出结果
     *       暂不使用<script></script>
     */
    @Select("SELECT  " +
            " f.*,"
            + "IF ( " +
            " ( " +
            "  SELECT " +
            "   count(*) " +
            "  FROM " +
            "   r_sheet_blog rsb " +
            "  WHERE " +
            "   rsb.BLOG_ID=f.business_id " +
            "  AND rsb.SHEET_ID=#{sheetId}  " +
            " ) > 0, " +
            "  '1', " +
            "  '0' " +
            " ) AS checked "
            + "  from f_blog f where 1=1 " +
            " and f.create_by = #{createBy}" +
            " and f.flag = #{flag} " +
            " and f.del_flag = #{delFlag} " )
    @ResultMap(value = "BaseResultMap" )
    List<Blog> querySheetBlogs(Map movie);


    /**
     * 查看 日程对应的  blog
     * @param param
     * @return
     */
    @Select("<script>select f.*,'' checked from f_blog f " +
            " left join r_sheet_blog rsb on rsb.BLOG_ID=f.business_id where 1=1 " +

            " AND rsb.SHEET_ID=#{sheetId} and f.is_public = '1' " +

            "<if test=\"title !=null and title !='' \"> and f.title like concat('%',#{title},'%') </if>" +

            "<if test=\"createBy !='' and createBy !=null \"> and f.create_by = #{createBy} </if>" +

            "<if test=\"isTop !='' and isTop !=null \"> and f.is_top = #{isTop} </if>" +

            "<if test=\"flag !='' and flag !=null \"> and f.flag = #{flag} </if>" +

            "<if test=\"delFlag !='' and delFlag !=null \"> and f.del_flag = #{delFlag} </if>" +
            "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<Blog> queryBlogsBySheet(Map param);



    /**
     * 根据id 查询博客
     * @param id
     * @return
     */
    @Select("select * from f_blog where business_id = #{id}")
    @ResultMap(value = "BaseResultMap" )
    Blog queryBlogById(@Param("id") String id);

    /**
     * 查询总数根据labelId
     * @param businessId
     * @return
     */
    @Select("<script>select count(*) from f_blog where 1=1 and flag = '1' and del_flag = '0'" +

            "<if test=\"blogLabelId !=null and blogLabelId !='' \"> and blog_label_id = #{blogLabelId} </if>" +

            "</script>")
    @ResultType(Integer.class)
    Integer queryCountByLabelId(@Param("blogLabelId") String businessId);

    /**
     * 逻辑删除
     * @param blog
     * @return
     */
    @Update("<script>update f_blog " +
            "<trim prefix=\"SET\" suffixOverrides=\",\">" +
            //commons
            " <if test=\"updateBy !='' and updateBy !=null \" > update_by = #{updateBy} ,</if>" +
            " <if test=\"updateDate !=null \" > update_date = #{updateDate} ,</if>" +
            " <if test=\"flag !='' and flag !=null \" > flag = #{flag} ,</if>" +
            " <if test=\"delFlag !='' and delFlag !=null \" > del_flag = #{delFlag} ,</if>" +
            "</trim>" +
            " where business_id = #{businessId} </script>")
    int deleteLogic(Blog blog);

    /**
     * 修改
     * @param blog
     * @return
     */
    @Update("<script>update f_blog " +
            "<trim prefix=\"SET\" suffixOverrides=\",\">" +
            " bkeys = #{bkeys}, "+
            " title = #{title}, " +
            " blog_label_id = #{blogLabelId}, " +
            " classify = #{classify}, " +
            " <if test=\"seqDate !=null \" >seq_date = #{seqDate} ,</if>" +
            " <if test=\"blogMd !=null and blogMd !=''  \" > blog_md = #{blogMd} ,</if>" +
            " <if test=\"blogHtml !=null and blogHtml !='' \" > blog_html = #{blogHtml} ,</if>" +
            " <if test=\"bdesc !=null and bdesc !='' \" > bdesc = #{bdesc} ,</if>" +
            //commons
            " <if test=\"createBy !=null and createBy !='' \" > create_by = #{createBy} ,</if>" +
            " <if test=\"updateBy !=null and updateBy !='' \" > update_by = #{updateBy} ,</if>" +
            " <if test=\"createDate !=null \" > create_date = #{createDate} ,</if>" +
            " <if test=\"updateDate !=null \" > update_date = #{updateDate} ,</if>" +
            " <if test=\"flag !=null and flag !=''  \" > flag = #{flag} ,</if>" +
            " <if test=\"delFlag !=null and delFlag !='' \" > del_flag = #{delFlag} ,</if>" +
            "</trim>" +
            " where business_id = #{businessId} </script>")
    int updateSelective(Blog blog);

    /**
     * update blog
     * @param blog
     * @return
     */
    @Update("<script>update f_blog " +
            "<trim prefix=\"SET\" suffixOverrides=\",\">" +

            " <if test=\"isTop !=null and isTop !='' \" > is_top = #{isTop} ,</if>" +
            " <if test=\"isPublic !=null and isPublic !='' \" > is_public = #{isPublic} ,</if>" +
            //commons
            " <if test=\"createBy !=null and createBy !='' \" > create_by = #{createBy} ,</if>" +
            " <if test=\"updateBy !=null and updateBy !='' \" > update_by = #{updateBy} ,</if>" +
            " <if test=\"createDate !=null \" > create_date = #{createDate} ,</if>" +
            " <if test=\"updateDate !=null \" > update_date = #{updateDate} ,</if>" +
            " <if test=\"flag !=null and flag !=''  \" > flag = #{flag} ,</if>" +
            " <if test=\"delFlag !=null and delFlag !='' \" > del_flag = #{delFlag} ,</if>" +
            "</trim>" +
            " where business_id = #{businessId} </script>")
    int updateBlogState(Blog blog);

    /**
     * 统计
     * @return
     */
    @Select("<script>select classify,count(*) as count from f_blog where DEL_FLAG = '0' and is_public = '1'" +
            " group by classify </script>")
    @ResultType(Map.class)
    List<Map> queryStaticBlogCountByClassify();

    /**
     * 根据 rtopic 查看
     * @param param
     * @return
     */
    @Select("<script>select f.*,s.LOGIN_NAME as blogUserName,s.name as blogUseRealName,'' checked from f_blog f " +

            " left join sys_user s on f.create_by = s.business_id  " +

            " left join r_sheet_blog rs ON f.business_id = rs.BLOG_ID " +

            " LEFT JOIN f_timesheet ts ON rs.SHEET_ID = ts.business_id " +

            " where 1=1 and ts.topic_id = #{rtopic} " +

            "<if test=\"classify !=null and classify !='' \"> and f.classify = #{classify} </if>" +

            "<if test=\"blogLabelId !=null and blogLabelId !='' \"> and f.blog_label_id = #{blogLabelId} </if>" +

            "<if test=\"title !=null and title !='' \"> and f.title like concat('%',#{title},'%') </if>" +

            "<if test=\"createBy !='' and createBy !=null \"> and f.create_by = #{createBy} </if>" +

            "<if test=\"isTop !='' and isTop !=null \"> and f.is_top = #{isTop} </if>" +

            "<if test=\"flag !='' and flag !=null \"> and f.flag = #{flag} </if>" +

            "<if test=\"delFlag !='' and delFlag !=null \"> and f.del_flag = #{delFlag} </if>" +

            "<if test=\"isPublic !='' and isPublic !=null \"> and  ((f.create_by = #{isPublic}) or (f.create_by != #{isPublic} and f.is_public = '1'))</if>" +

            "<if test=\"publicFlag !='' and publicFlag !=null \"> and  f.is_public = #{publicFlag} </if>" +

            "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<Blog> queryBlogsByRtopic(Map param);
}
