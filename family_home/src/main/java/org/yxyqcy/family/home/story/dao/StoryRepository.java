package org.yxyqcy.family.home.story.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.home.story.entity.Story;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/23
 * Time: 上午10:20
 * description:  story repository
 */
@Repository
public interface StoryRepository extends Mapper<Story> {

    /**
     * 查询   story
     * @param param
     * @return
     */
    @Select("<script>select f.* from l_story f where 1=1 " +

            "<if test=\"title !=null and title !='' \"> and f.title like concat('%',#{title},'%') </if>" +

            "<if test=\"createBy !='' and createBy !=null \"> and f.create_by = #{createBy} </if>" +
            "<if test=\"flag !='' and flag !=null \"> and f.flag = #{flag} </if>" +
            "<if test=\"delFlag !='' and delFlag !=null \"> and f.del_flag = #{delFlag} </if>" +
            "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<Story> queryStories(Map param);

    /**
     * 逻辑删故事
     * @param story
     * @return
     */
    @Update("<script>update l_story " +
            "<trim prefix=\"SET\" suffixOverrides=\",\">" +
            //commons
            " <if test=\"createBy !='' and createBy !=null \" > create_by = #{createBy} ,</if>" +
            " <if test=\"updateBy !='' and updateBy !=null \" > update_by = #{updateBy} ,</if>" +
            " <if test=\"createDate !=null \" > create_date = #{createDate} ,</if>" +
            " <if test=\"updateDate !=null \" > update_date = #{updateDate} ,</if>" +
            " <if test=\"flag !='' and flag !=null \" > flag = #{flag} ,</if>" +
            " <if test=\"delFlag !='' and delFlag !=null \" > del_flag = #{delFlag} ,</if>" +
            "</trim>" +
            " where business_id = #{businessId} </script>")
    int deleteLogic(Story story);

    /**
     * update story
     * @param story
     * @return
     */
    @Update("<script>update l_story " +
            "<trim prefix=\"SET\" suffixOverrides=\",\">" +
            " title = #{title} ," +
            " description = #{description} ," +

            //commons
            " <if test=\"createBy !='' and createBy !=null \" > create_by = #{createBy} ,</if>" +
            " <if test=\"updateBy !='' and updateBy !=null \" > update_by = #{updateBy} ,</if>" +
            " <if test=\"createDate !=null \" > create_date = #{createDate} ,</if>" +
            " <if test=\"updateDate !=null \" > update_date = #{updateDate} ,</if>" +
            " <if test=\"flag !='' and flag !=null \" > flag = #{flag} ,</if>" +
            " <if test=\"delFlag !='' and delFlag !=null \" > del_flag = #{delFlag} ,</if>" +
            "</trim>" +
            " where business_id = #{businessId} </script>")
    int updateStory(Story story);
}
