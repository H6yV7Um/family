package org.activiti.service.impl;

import org.activiti.dao.WorkflowFormFieldRepository;
import org.activiti.dao.WorkflowFormRepository;
import org.activiti.entity.WorkflowForm;
import org.activiti.model.QueryWorkflowModel;
import org.activiti.service.WorkflowFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.service.BaseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/6/14.
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
public class WorkflowFormServiceImpl extends BaseService implements WorkflowFormService {
    private static final long serialVersionUID = 1487267795880680406L;

    @Autowired
    private WorkflowFormRepository workflowFormRepository;

    @Autowired
    private WorkflowFormFieldRepository workflowFormFieldRepository;


    @Override
    public List<WorkflowForm> queryWorkflowFormByPagination(PageUtil<WorkflowForm> paginationUtility, QueryWorkflowModel workflow) {
        Map param = new HashMap<String,Object>();
        param.put("flag",workflow.getFlag());
        param.put("delFlag",workflow.getDelFlag());
        return workflowFormRepository.queryForms(param);

    }
}
