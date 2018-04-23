package org.yxyqcy.family.sys.log.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.sys.log.entity.Log;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * 日志管理dao接口
 * @author yangxuenan
 *
 */
@Repository
public interface LogRepository extends Mapper<Log>{

	/**
     * 日志管理分页查询
     */
	 @Select("<script>select sys_log.*,sys_user.NAME as username  from sys_log INNER JOIN sys_user ON sys_log.CREATE_BY = sys_user.business_id where "
	    		+ "<if test=\"message !=null \">sys_log.message like concat('%',#{message},'%') </if> "
	    		+ "<if test=\"level !=null \">and sys_log.level like concat('%',#{level},'%') </if> "
	    		+ "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<Log> queryLogByPagination(Log log);
}
