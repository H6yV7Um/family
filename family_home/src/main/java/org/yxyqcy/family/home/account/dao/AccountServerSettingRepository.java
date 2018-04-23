package org.yxyqcy.family.home.account.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.home.account.entity.AccountServerSetting;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/2/13.
 */
@Repository
public interface AccountServerSettingRepository extends Mapper<AccountServerSetting> {
    /**
     * 根据serverId 查询 setting
     * @param param
     * @return
     */
    @Select("select * from f_account_serset where DEL_FLAG = '0' and server_id = #{serverId} order by seq_date ")
    @ResultMap(value = "BaseResultMap" )
    List<AccountServerSetting> queryServerSettingsByServerId(Map param);

    /**
     * 修改服务器配置
     * @param param
     * @return
     */
    @Update("<script>update f_account_serset " +
            "<trim prefix=\"SET\" suffixOverrides=\",\">" +
            " title = #{title} ," +
            " account = #{account} ," +
            " description = #{description} ," +
            " passwd = #{passwd} ," +
            " port = #{port} ," +
            " seq_date = #{seqDate} ," +

            //update commons
            " <if test=\"updateBy !='' and updateBy !=null \" > update_by = #{updateBy} ,</if>" +
            " <if test=\"updateDate !=null \" > update_date = #{updateDate} ,</if>" +
            " <if test=\"flag !='' and flag !=null \" > flag = #{flag} ,</if>" +
            " <if test=\"delFlag !='' and delFlag !=null \" > del_flag = #{delFlag} ,</if>" +
            "</trim>" +
            " where business_id = #{businessId} </script>")
    int updateSelective(AccountServerSetting param);
}
