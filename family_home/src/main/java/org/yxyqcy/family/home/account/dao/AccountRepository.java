package org.yxyqcy.family.home.account.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.home.account.entity.Account;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/12/19
 * Time: 下午9:10
 * description:  帐号 dao
 */
@Repository
public interface AccountRepository extends Mapper<Account> {

    /**
     * 查询account
     * @param map
     * @return
     */
    @Select("<script>select f.* from f_account f where 1=1 " +

            "<if test=\"type !=null and type !='' \"> and f.type = #{type} </if>" +
            "<if test=\"title !=null and title !='' \"> and f.title like concat('%',#{title},'%') </if>" +

            "<if test=\"createBy !='' and createBy !=null \"> and f.create_by = #{createBy} </if>" +
            "<if test=\"flag !='' and flag !=null \"> and f.flag = #{flag} </if>" +
            "<if test=\"delFlag !='' and delFlag !=null \"> and f.del_flag = #{delFlag} </if>" +
            "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<Account> queryAccounts(Map map);


    /**
     * 逻辑删除
     * @param account
     * @return
     */
    @Update("<script>update f_account " +
            "<trim prefix=\"SET\" suffixOverrides=\",\">" +
            " <if test=\"seqDate !=null \" >seq_date = #{seqDate} ,</if>" +
            " <if test=\"type !='' and type !=null \" >type = #{type} ,</if>" +
            " <if test=\"passwd !='' and passwd !=null \" >passwd = #{passwd} ,</if>" +
            " <if test=\"account !='' and account !=null \" >account = #{account} ,</if>" +
            " <if test=\"title !='' and title !=null \" >title = #{title} ,</if>" +
            " <if test=\"url !='' and url !=null \" >url = #{url} ,</if>" +
            " <if test=\"description !='' and description !=null \" >description = #{description} ,</if>" +


            //commons
            " <if test=\"createBy !='' and createBy !=null \" > create_by = #{createBy} ,</if>" +
            " <if test=\"updateBy !='' and updateBy !=null \" > update_by = #{updateBy} ,</if>" +
            " <if test=\"createDate !=null \" > create_date = #{createDate} ,</if>" +
            " <if test=\"updateDate !=null \" > update_date = #{updateDate} ,</if>" +
            " <if test=\"flag !='' and flag !=null \" > flag = #{flag} ,</if>" +
            " <if test=\"delFlag !='' and delFlag !=null \" > del_flag = #{delFlag} ,</if>" +
            "</trim>" +
            " where business_id = #{businessId} </script>")
    int updateSelective(Account account);

    /**
     * 逻辑删除
     * @param account
     * @return
     */
    @Update("<script>update f_account " +
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
    int deleteLogic(Account account);

    /**
     * 统计查询
     * @param param
     * @return
     */
    @Select("<script>select type,count(*) as count from f_account where DEL_FLAG = '0'" +
            "<if test=\"createBy !='' and createBy !=null \"> and create_by = #{createBy} </if>" +
            " group by ${groupType} </script>")
    @ResultType(Map.class)
    List<Map> statistics(Map param);
}
