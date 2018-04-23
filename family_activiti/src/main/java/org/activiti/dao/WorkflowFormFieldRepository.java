package org.activiti.dao;

import org.activiti.entity.WorkflowFormField;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created with family.
 * author: cy
 * Date: 17/06/14
 * Time: 下午9:10
 * description:  帐号 dao
 */
@Repository
public interface WorkflowFormFieldRepository extends Mapper<WorkflowFormField> {

}
