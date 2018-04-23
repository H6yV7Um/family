package ${basePackage}.controller;

import ${basePackage}.entity.${table.entityName};
import ${basePackage}.service.${table.entityName}Service;

import com.isoft.sys.user.entity.User;
import com.isoft.common.model.PersistModel;
import com.isoft.sys.util.UserUtils;
import com.isoft.common.constant.MessageConstant;
import com.isoft.common.message.AjaxResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import com.isoft.common.controller.PaginationController;

import java.util.List;

/**
* Created by ${author} on ${.now}.
*/
@Controller
@RequestMapping("/${table.entityName}")
public class ${table.entityName}Controller extends PaginationController<${table.entityName}>{
    @Autowired
    private ${table.entityName}Service ${table.entityName?uncap_first}Service;

    /**
    * 插入${table.entityName}
    * @param ${table.entityName?uncap_first}
    * @param ar
    * @return
    */
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResponse add(${table.entityName} ${table.entityName?uncap_first},AjaxResponse ar) {
        PersistModel result = ${table.entityName?uncap_first}Service.persist(${table.entityName?uncap_first});
        ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,result);
        return ar;
    }

    /**
    * 逻辑删除${table.entityName}
    * @param id
    * @param ar
    * @return
    */
    @RequestMapping("/logicRemove")
    @ResponseBody
    public AjaxResponse logicRemove(String id,AjaxResponse ar) {
        PersistModel result = ${table.entityName?uncap_first}Service.remove${table.entityName}(id);
        ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,result);
        return ar;
    }

    /**
    * 根据主键唯一查找
    * @param businessId
    * @param ar
    * @return
    */
    @RequestMapping("/one")
    @ResponseBody
    public AjaxResponse queryOne(String businessId,AjaxResponse ar) {
        ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,${table.entityName?uncap_first}Service.selectOne(businessId));
        return ar;
    }


    /**
    * 根据条件分页查询
    * @param param
    * @param ar
    * @return
    */
    @RequestMapping("/query${table.entityName}sByPagination")
    @ResponseBody
    public AjaxResponse query${table.entityName}sByPagination(${table.entityName} param,AjaxResponse ar) {
        param.setCreateBy(UserUtils.getUser().getBusinessId());
        ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,${table.entityName?uncap_first}Service.query${table.entityName}sByPagination(getPaginationUtility(),param));
        return ar;
    }

    /**
    * 根据条件查询
    * @param param
    * @param ar
    * @return
    */
    @RequestMapping("/query${table.entityName}s")
    @ResponseBody
    public AjaxResponse query${table.entityName}s(${table.entityName} param,AjaxResponse ar) {
        param.setCreateBy(UserUtils.getUser().getBusinessId());
        ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,${table.entityName?uncap_first}Service.query${table.entityName}s(param));
        return ar;
    }
}
