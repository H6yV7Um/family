package org.yxyqcy.family.home.story.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.home.story.entity.StoryFragment;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/23
 * Time: 上午10:20
 * description:  StoryFragment repository
 */
@Repository
public interface StoryFragmentRepository extends Mapper<StoryFragment> {

    @Select("<script>select f.* from l_story_fragment f where 1=1 " +
            " and f.story_id = #{storyId} " +
            "<if test=\"title !=null and title !='' \"> and f.title like concat('%',#{title},'%') </if>" +
            "<if test=\"createBy !='' and createBy !=null \"> and f.create_by = #{createBy} </if>" +
            "<if test=\"flag !='' and flag !=null \"> and f.flag = #{flag} </if>" +
            "<if test=\"delFlag !='' and delFlag !=null \"> and f.del_flag = #{delFlag} </if>" +
            "<if test=\"orderBy !='' and orderBy !=null \"> order by ${orderBy} </if>" +
            "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<StoryFragment> queryStoryFragments(Map param);

    /**
     * update storyFragment
     * @param storyFragment
     * @return
     */
    @Update("<script>update l_story_fragment " +
            "<trim prefix=\"SET\" suffixOverrides=\",\">" +
            " title = #{title} ," +
            " story_date = #{storyDate} ," +
            " is_year = #{isYear} ," +
            " image_first = #{imageFirst} ," +
            " content_first = #{contentFirst} ," +
            " class_first = #{classFirst} ," +
            " image_second = #{imageSecond} ," +
            " content_second = #{contentSecond} ," +
            " class_second = #{classSecond} ," +
            //commons
            " <if test=\"createBy !='' and createBy !=null \" > create_by = #{createBy} ,</if>" +
            " <if test=\"updateBy !='' and updateBy !=null \" > update_by = #{updateBy} ,</if>" +
            " <if test=\"createDate !=null \" > create_date = #{createDate} ,</if>" +
            " <if test=\"updateDate !=null \" > update_date = #{updateDate} ,</if>" +
            " <if test=\"flag !='' and flag !=null \" > flag = #{flag} ,</if>" +
            " <if test=\"delFlag !='' and delFlag !=null \" > del_flag = #{delFlag} ,</if>" +
            "</trim>" +
            " where business_id = #{businessId} </script>")
    int updateFragment(StoryFragment storyFragment);

    /**
     * deletlogic 逻辑删除
     * @param storyFragment
     * @return
     */
    @Update("<script>update l_story_fragment " +
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
    int deleteLogic(StoryFragment storyFragment);


}
