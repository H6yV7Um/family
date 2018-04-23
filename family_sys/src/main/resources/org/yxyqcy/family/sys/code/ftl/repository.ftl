package ${basePackage}.dao;

import ${basePackage}.entity.${table.entityName};

import tk.mybatis.mapper.common.Mapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.Map;
import java.util.List;

/**
* Created with ${author} on ${.now}.
*/
@Repository
public interface ${table.entityName}Repository extends Mapper<${table.entityName}> {

    /**
    * 根据条件查找
    * @param param
    * @return
    */
    @Select("select * from ${table.tableName}")
    @ResultMap(value = "BaseResultMap" )
    List<${table.entityName}> query${table.entityName}s(Map<String,Object> param);
}

