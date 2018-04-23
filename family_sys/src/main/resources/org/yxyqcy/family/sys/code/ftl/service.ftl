package ${basePackage}.service;

import ${basePackage}.entity.${table.entityName};

import com.isoft.common.component.PageUtil;
import com.isoft.common.model.PersistModel;
import com.isoft.sys.user.entity.User;

import java.util.List;

/**
 * Created by ${author} on ${.now}.
 */
public interface ${table.entityName}Service{
    /**
    * 根据主键唯一查找
    * @param businessId
    * @return
    */
    ${table.entityName} selectOne(String businessId);

    /**
    * 根据条件分页查找
    * @param pageUtil
    * @param model
    * @return
    */
    List<${table.entityName}> query${table.entityName}sByPagination(PageUtil pageUtil,${table.entityName} model);

    /**
    * 根据条件查找全部
    * @param model
    * @return
    */
    List<${table.entityName}> query${table.entityName}s(${table.entityName} model);

    /**
    * 插入
    * @param model
    * @return
    */
    PersistModel persist(${table.entityName} model);

    /**
    * 逻辑删除
    * @param id
    * @return
    */
    PersistModel remove${table.entityName}(String id);
}
