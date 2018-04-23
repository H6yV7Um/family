package org.yxyqcy.family.home.timesheet.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.home.timesheet.entity.Timesheet;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/2/6.
 */
@Repository
public interface TimesheetRepository  extends Mapper<Timesheet> {
    /**
     * 查询 timeSheet
     * @param param 条件
     * @return
     */
    @Select("<script>select f.*,user.name as CREATE_USER " +

            "<if test=\"topicId !='' and topicId !=null \">," +
            " (SELECT " +
            " count(*) " +
            " FROM " +
            " f_blog b " +
            " INNER JOIN r_sheet_blog rsb ON b.business_id = rsb.BLOG_ID " +
            " where rsb.SHEET_ID = f.business_id and b.is_public = '1' and b.del_flag = '0' ) as blogCount  </if>" +

            " from f_timesheet f left join sys_user user on user.BUSINESS_ID = f.CREATE_BY where 1=1 " +

            "<if test=\"start !=null \"> and f.start_date &gt;= #{start} </if>" +
            "<if test=\"end !=null \"> and (f.end_date &lt;= #{end}  or f.end_date is null)</if>" +

            "<if test=\"createUser !=null and createUser !='' \"> and user.name like concat('%',#{createUser},'%') </if>" +

            "<if test=\"createBy !='' and createBy !=null \"> and f.create_by = #{createBy} </if>" +
            "<if test=\"flag !='' and flag !=null \"> and f.flag = #{flag} </if>" +
            "<if test=\"delFlag !='' and delFlag !=null \"> and f.del_flag = #{delFlag} </if>" +

            "<if test=\"topicId !='' and topicId !=null \"> and f.topic_id = #{topicId}  </if>" +

            "<if test=\"type !='' and type !=null \"> and f.type = #{type}  </if>" +

            "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<Timesheet> queryTimeSheets(Map param);



    /**
     * 查询 timeSheet 根据人员排序
     * @param param 条件
     * @return
     */
    @Select("<script>select f.*,user.name as CREATE_USER from f_timesheet f left join sys_user user on user.BUSINESS_ID = f.CREATE_BY where 1=1 " +

            "<if test=\"assessFlag !=null \"> and f.assess_flag = #{assessFlag} </if>" +

            "<if test=\"startQuery !=null and endQuery !=null \"> and ((f.start_date &gt;= #{startQuery} " +
            " and f.start_date &lt;=#{endQuery}) or (f.end_date is not null and f.end_date &gt;= #{startQuery} " +
            " and f.end_date &lt;=#{endQuery} )) </if>" +

            "<if test=\"createUser !=null and createUser !='' \"> and user.name like concat('%',#{createUser},'%') </if>" +

            "<if test=\"createBy !='' and createBy !=null \"> and f.create_by = #{createBy} </if>" +
            "<if test=\"flag !='' and flag !=null \"> and f.flag = #{flag} </if>" +
            "<if test=\"delFlag !='' and delFlag !=null \"> and f.del_flag = #{delFlag} </if>" +
            " order by f.create_by desc,f.start_date asc  " +
            "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<Timesheet> queryTimeSheetsOrderByUser(Map param);


    /**
     * 修改timeSheet
     * @param timesheet
     * @return
     */
    @Update("<script>update f_timesheet " +
            "<trim prefix=\"SET\" suffixOverrides=\",\">" +

            " content = #{content}, " +
            " all_day = #{allDay}, " +
            " start_date = #{start} ," +
            " end_date = #{end} ," +
            " <if test=\"title !='' and title !=null \" >title = #{title} ,</if>" +
            " <if test=\"assessFlag !='' and assessFlag !=null \" >ASSESS_FLAG = #{assessFlag} ,</if>" +
            " <if test=\"topicId !='' and topicId !=null \" >topic_id = #{topicId} ,</if>" +
            " <if test=\"type !='' and type !=null \" >type = #{type} ,</if>" +
            /*" <if test=\"assessUser !='' and assessUser !=null \" >ASSESS_USER = #{assessUser} ,</if>" +
            " <if test=\"assessDate !='' and assessDate !=null \" >ASSESS_DATE = #{assessDate} ,</if>" +*/

            //commons
            " <if test=\"createBy !='' and createBy !=null \" > create_by = #{createBy} ,</if>" +
            " <if test=\"updateBy !='' and updateBy !=null \" > update_by = #{updateBy} ,</if>" +
            " <if test=\"createDate !=null \" > create_date = #{createDate} </if>" +
            " <if test=\"updateDate !=null \" > update_date = #{updateDate} ,</if>" +
            " <if test=\"flag !='' and flag !=null \" > flag = #{flag} ,</if>" +
            " <if test=\"delFlag !='' and delFlag !=null \" > del_flag = #{delFlag} ,</if>" +
            "</trim>" +
            " where business_id = #{businessId} </script>")
    int updateSelective(Timesheet timesheet);

    /**
     * 批量审核timeSheet
     * @param list
     * @return
     */
    int batchAssessTimeSheet(List<Map> list);
    /*批量插入 sheetBlog*/
    int insertBatchSheetBlog(List<Map> sheetBlog);

    /*清空 sheetBlog*/
    @Delete("delete from r_sheet_blog where SHEET_ID = #{businessId}")
    int deleteSheetBlog(String businessId);
}
