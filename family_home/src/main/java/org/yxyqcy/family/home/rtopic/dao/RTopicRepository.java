package org.yxyqcy.family.home.rtopic.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.home.rtopic.entity.RTopic;
import org.yxyqcy.family.sys.user.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
* Created with licy on 2017-9-29 19:56:42.
*/
@Repository
public interface RTopicRepository extends Mapper<RTopic> {

    /**
    * 根据条件查找
    * @param param
    * @return
    */
    @Select("<script>SELECT " +
            " distinct t.*, ( " +
            " SELECT " +
            "  GROUP_CONCAT(u.name SEPARATOR ',') " +
            "   FROM " +
            "   r_topic_user rt " +
            "   LEFT JOIN sys_user u ON u.business_id = rt.user_id " +
            "   where rt.TOPIC_ID = t.business_id " +
            " ) as users " +

            "<if test=\"isBlogCount !='' and isBlogCount !=null \"> , (SELECT " +
            " count(*) " +
            " FROM " +
            " f_blog b " +
            "LEFT JOIN r_sheet_blog rs ON b.business_id = rs.BLOG_ID " +
            " LEFT JOIN f_timesheet ts ON rs.SHEET_ID = ts.business_id " +
            " WHERE " +
            " b.is_public = '1' " +
            "AND ts.topic_id = t.business_id) as blogCount </if>" +

            " FROM " +
            " r_topic t " +
            " left join r_topic_user rtu on t.business_id = rtu.TOPIC_ID  " +
            " WHERE " +
            " t.DEL_FLAG = '0' " +
            " AND t.FLAG = '1' " +
            "<if test=\"user !='' and user !=null \"> and rtu.USER_ID = #{user} </if>" +
            " </script>"
    )
    @ResultMap(value = "BaseResultMap" )
    List<RTopic> queryRTopics(Map<String, Object> param);

    /**
     * 查询 rtopic    user
     * @param topic
     * @return
     */
    @Select("<script>select s.* "

            + "<if test=\"businessId !='' and businessId !=null \">,"
            + "IF ( " +
            " ( " +
            "  SELECT " +
            "   count(*) " +
            "  FROM " +
            "   r_topic_user rtu " +
            "  WHERE " +
            "   rtu.USER_ID = s.business_id " +
            "  AND rtu.TOPIC_ID=#{businessId}  " +
            " ) > 0, " +
            "  '1', " +
            "  '0' " +
            " ) AS checked "
            + " </if>"

            + "<if test=\"businessId =='' or businessId ==null \">,"
            + " '0' as checked "
            + " </if>"

            + " from sys_user s   where 1=1 "

    		+ " <if test=\"flag !=null and flag != '' \">and FLAG =#{flag} </if>"
            + " <if test=\"delFlag !=null and delFlag != '' \">and del_flag =#{delFlag} </if>"
            + "</script>")
    @ResultMap(value = "userResultMap" )
    List<User> queryUsersByRtopic(RTopic topic);

    /**
     * 删除rtopic Users
     * @param businessId
     * @return
     */
    @Delete("delete from r_topic_user where TOPIC_ID = #{businessId}")
    int deleteRtopicUsers(String businessId);

    /**
     * 批量插入 rtopic users
     * @param rtopicUser
     * @return
     */
    int insertBatchRtopicUser(List<Map> rtopicUser);


    /**
     * update story
     * @param rTopic
     * @return
     */
    @Update("<script>update r_topic " +
            "<trim prefix=\"SET\" suffixOverrides=\",\">" +
            " title = #{title} ," +
            " description = #{description} ," +
            " start_date = #{startDate}, " +
            " end_date = #{endDate}, " +
            " type = #{type}, " +
            //commons
            " <if test=\"createBy !='' and createBy !=null \" > create_by = #{createBy} ,</if>" +
            " <if test=\"updateBy !='' and updateBy !=null \" > update_by = #{updateBy} ,</if>" +
            " <if test=\"createDate !=null \" > create_date = #{createDate} ,</if>" +
            " <if test=\"updateDate !=null \" > update_date = #{updateDate} ,</if>" +
            " <if test=\"flag !='' and flag !=null \" > flag = #{flag} ,</if>" +
            " <if test=\"delFlag !='' and delFlag !=null \" > del_flag = #{delFlag} ,</if>" +
            "</trim>" +
            " where business_id = #{businessId} </script>")
    int updateRtopic(RTopic rTopic);
}

