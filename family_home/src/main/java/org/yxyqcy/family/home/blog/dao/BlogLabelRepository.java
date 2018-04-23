package org.yxyqcy.family.home.blog.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.home.blog.entity.BlogLabel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/2/15.
 */
@Repository
public interface BlogLabelRepository extends Mapper<BlogLabel> {


    /**
     * 查询博客标签
     * @param   movie
     * @return
     *
     */
    @Select("<script>select f.*,(select count(b.business_id) from f_blog b where " +
            " b.blog_label_id = f.business_id and b.flag = '1' and b.del_flag = '0' ) as count_blog  " +
            " from f_blog_label f where 1=1 " +


            "<if test=\"createBy !='' and createBy !=null \"> and f.create_by = #{createBy} </if>" +
            "<if test=\"flag !='' and flag !=null \"> and f.flag = #{flag} </if>" +
            "<if test=\"delFlag !='' and delFlag !=null \"> and f.del_flag = #{delFlag} </if>" +
            "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<BlogLabel> queryBlogLabels(Map movie);

    /**
     * update blogLabel
     * @param label
     * @return
     */
    @Update("<script>update f_blog_label " +
            "<trim prefix=\"SET\" suffixOverrides=\",\">" +
            " title = #{title} ," +
            " <if test=\"seqDate !=null \" > seq_date = #{seqDate} ,</if>" +
            //commons
            " <if test=\"createBy !='' and createBy !=null \" > create_by = #{createBy} ,</if>" +
            " <if test=\"updateBy !='' and updateBy !=null \" > update_by = #{updateBy} ,</if>" +
            " <if test=\"createDate !=null \" > create_date = #{createDate} ,</if>" +
            " <if test=\"updateDate !=null \" > update_date = #{updateDate} ,</if>" +
            " <if test=\"flag !='' and flag !=null \" > flag = #{flag} ,</if>" +
            " <if test=\"delFlag !='' and delFlag !=null \" > del_flag = #{delFlag} ,</if>" +
            "</trim>" +
            " where business_id = #{businessId} </script>")
    int mergeBlogLabel(BlogLabel label);




}
