package org.yxyqcy.family.home.fbook.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.home.fbook.entity.FBook;
import org.yxyqcy.family.sys.mapper.FamilySysMapper;

import java.util.List;
import java.util.Map;

/**
* Created with yxyqcy on 2017-11-13 23:12:26.
*/
@Repository
public interface FBookRepository extends FamilySysMapper<FBook> {

    /**
    * 根据条件查找
    * @param param
    * @return
    */
    @Select("<script>select f.*,s.LOGIN_NAME as bookUserName,s.name as bookUseRealName,'' checked from f_book f left join sys_user s on f.create_by = s.business_id  where 1=1 " +

            "<if test=\"classify !=null and classify !='' \"> and f.classify = #{classify} </if>" +


            "<if test=\"name !=null and name !='' \"> and f.name like concat('%',#{name},'%') </if>" +

            "<if test=\"createBy !='' and createBy !=null \"> and f.create_by = #{createBy} </if>" +

            "<if test=\"isTop !='' and isTop !=null \"> and f.is_top = #{isTop} </if>" +

            "<if test=\"flag !='' and flag !=null \"> and f.flag = #{flag} </if>" +

            "<if test=\"delFlag !='' and delFlag !=null \"> and f.del_flag = #{delFlag} </if>" +

            "<if test=\"isPublic !='' and isPublic !=null \"> and  ((f.create_by = #{isPublic}) or (f.create_by != #{isPublic} and f.is_public = '1'))</if>" +

            "<if test=\"publicFlag !='' and publicFlag !=null \"> and  f.is_public = #{publicFlag} </if>" +

            "<if test=\"code !='' and code !=null \"> and f.code = #{code} </if>" +

            "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<FBook> queryFBooks(Map<String, Object> param);
    /**
     * 统计
     * @return
     */
    @Select("<script>select classify,count(*) as count from f_book where DEL_FLAG = '0' and is_public = '1'" +
            " group by classify </script>")
    @ResultType(Map.class)
    List<Map> queryStaticBookCountByClassify();

    /**
     * update 状态
     * @param book
     * @return
     */
    @Update("<script>update f_book " +
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
    int updateBookState(FBook book);
}

