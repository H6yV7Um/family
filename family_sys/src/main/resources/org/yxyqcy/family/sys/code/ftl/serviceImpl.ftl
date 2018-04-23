package ${basePackage}.service.impl;

import ${basePackage}.entity.${table.entityName};
import ${basePackage}.dao.${table.entityName}Repository;
import ${basePackage}.service.${table.entityName}Service;

import com.isoft.common.model.PersistModel;
import org.springframework.stereotype.Service;
import com.isoft.common.component.PageUtil;
import com.isoft.sys.util.UserUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.isoft.sys.user.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by ${author} on ${.now}.
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
public class ${table.entityName}ServiceImpl implements ${table.entityName}Service{
    @Autowired
    private ${table.entityName}Repository ${table.entityName?uncap_first}Repository;

    @Override
    public ${table.entityName} selectOne(String businessId){
        return ${table.entityName?uncap_first}Repository.selectByPrimaryKey(businessId);
    }

    @Override
    public List<${table.entityName}> query${table.entityName}sByPagination(PageUtil pageUtil, ${table.entityName} model) {
        Map param = new HashMap<String,Object>();
        return ${table.entityName?uncap_first}Repository.query${table.entityName}s(param);
    }

    @Override
    public List<${table.entityName}> query${table.entityName}s(${table.entityName} model) {
        Map param = new HashMap<String,Object>();
        return ${table.entityName?uncap_first}Repository.query${table.entityName}s(param);
    }


    @Override
    public PersistModel persist(${table.entityName} model){
        model.setCommonBusinessId();
        UserUtils.setCurrentPersistOperation(model);
        int line = ${table.entityName?uncap_first}Repository.insertSelective(model);
        //FamilyLogger.sysInfo(UserUtils.getUser().getBusinessId(),UserUtils.getUser().getLoginName()+"新增了ID为"+user.getBusinessId()+"的用户");
        return new PersistModel(line);
    }

    @Override
    public PersistModel remove${table.entityName}(String id) {
        ${table.entityName} ${table.entityName?uncap_first} = ${table.entityName?uncap_first}Repository.selectByPrimaryKey(id);
        ${table.entityName?uncap_first}.setDelFlag("1");
        UserUtils.setCurrentMergeOperation(${table.entityName?uncap_first});
        int line = ${table.entityName?uncap_first}Repository.updateByPrimaryKeySelective(${table.entityName?uncap_first});
        return new PersistModel(line);
    }
}
