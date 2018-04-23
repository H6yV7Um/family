package org.activiti.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.dao.WorkflowRepository;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.LastedBpmnJsonConverter;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.entity.WorkflowDeployment;
import org.activiti.entity.WorkflowInstance;
import org.activiti.entity.WorkflowModel;
import org.activiti.entity.WorkflowTask;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.activiti.model.QueryWorkflowModel;
import org.activiti.model.WorkflowBpmnModel;
import org.activiti.service.WorkflowService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.CommonPageGridModel;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.service.BaseService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/6/8.
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
public class WorkflowServiceImpl extends BaseService implements WorkflowService {


    private static final long serialVersionUID = -5678267139934608553L;
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    private WorkflowRepository workflowRepository;


    @Override
    public GridModel queryWorkflowModelByActPagination(PageUtil<WorkflowModel> pageUtil, QueryWorkflowModel workflow) {

        ModelQuery modelQuery = repositoryService.createModelQuery();

        List<Model> list = (List<Model>) modelQuery
                .listPage((pageUtil.getPageNum()-1)*pageUtil.getPageSize(),pageUtil.getPageSize());
        long count = modelQuery.count();
        List<WorkflowModel> flows = new ArrayList<WorkflowModel>();
        if(null == list || 0 == list.size())
            return new CommonPageGridModel(0,flows,1,1);
        for (Model model : list) {
            WorkflowModel flow = new WorkflowModel();
            flow.copyModel(model);
            flows.add(flow);
        }
        return new CommonPageGridModel(count, flows,pageUtil.getPageNum(), (count / pageUtil.getPageSize() + 1));
    }
    @Transactional
    @Override
    public PersistModel modelRemove(String id) {
        repositoryService.deleteModel(id);
        return new PersistModel(1);
    }
    @Transactional
    @Override
    public PersistModel persistModel(WorkflowModel workflowModel) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();
            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, workflowModel.getModelName());
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            workflowModel.setModelDes(StringUtils.defaultString(workflowModel.getModelDes()));
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, workflowModel.getModelDes());
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(workflowModel.getModelName());
            modelData.setKey(StringUtils.defaultString(workflowModel.getModelKey()));
            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
            workflowModel.setModelId(modelData.getId());
        }catch (Exception e){
            logger.error(e.getMessage());
            return new PersistModel(0);
        }
        return new PersistModel(1);
    }


    @Transactional
    @Override
    public Deployment deployModel(String modelId) {
        Model modelData = repositoryService.getModel(modelId);
        Deployment deployment = null;
        try {
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;

            BpmnModel model = new LastedBpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);
            String processName = modelData.getName() + ".bpmn20.xml";
            deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes)).deploy();
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
        return deployment;
    }

    @Override
    public WorkflowBpmnModel getByteModel(String modelId, String type) {
        WorkflowBpmnModel model = null;
        try {
            byte[] exportBytes = null;
            String fileName = "";
            Model modelData = repositoryService.getModel(modelId);
            LastedBpmnJsonConverter jsonConverter = new LastedBpmnJsonConverter();
            byte[] modelEditorSource = repositoryService.getModelEditorSource(modelData.getId());
            JsonNode editorNode = new ObjectMapper().readTree(modelEditorSource);
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
            // 处理异常
            if (null == bpmnModel.getMainProcess()) {
                return null;
            }
            String mainProcessId = bpmnModel.getMainProcess().getId();
            // bpmn or json
            if (type.equals("bpmn")) {
                BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
                exportBytes = xmlConverter.convertToXML(bpmnModel);
                fileName = mainProcessId + ".bpmn20.xml";
                model = new WorkflowBpmnModel(exportBytes,fileName);
            } else if (type.equals("json")) {
                exportBytes = modelEditorSource;
                fileName = mainProcessId + ".json";
                model = new WorkflowBpmnModel(exportBytes,fileName);
            } else if(type.equals("png")) {
                model = new WorkflowBpmnModel(this.getProcessPhoto(bpmnModel),mainProcessId + ".png");
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return model;
    }

    @Override
    public List<WorkflowDeployment> queryWorkflowDeploymentByPagination(PageUtil<WorkflowModel> pageUtil, QueryWorkflowModel workflow) {
        Map map = new HashMap<String,Object>();
        return workflowRepository.queryWorkflowDeployments(map);
    }

    @Transactional
    @Override
    public ProcessInstance startProcessByKey(String key,String businessKey, Map<String, Object> variable, boolean autoPassSubmit) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, businessKey, variable);
        //自动跳过节点
        if(autoPassSubmit && null != processInstance){
            Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
            taskService.complete(task.getId());
        }
        return processInstance;
    }


    @Override
    public List<WorkflowInstance> queryWorkflowInstanceByPagination(PageUtil<WorkflowModel> paginationUtility, QueryWorkflowModel workflow) {
        Map param = new HashMap<String,Object>();
        return workflowRepository.queryWorkflowInstances(param);
    }

    @Override
    public List<WorkflowInstance> queryWorkflowHisInstanceByPagination(PageUtil<WorkflowModel> paginationUtility, QueryWorkflowModel workflowModel) {
        Map param = new HashMap<String,Object>();
        return workflowRepository.queryWorkflowHisInstances(param);
    }

    @Override
    public List<WorkflowTask> queryWorkflowAlreadyTasksByPagination(PageUtil<WorkflowModel> paginationUtility, QueryWorkflowModel workflowModel) {
        Map param = new HashMap<String,Object>();
        return workflowRepository.queryWorkflowAlreadyTasks(param);
    }

    @Override
    public List<WorkflowTask> queryWorkflowWaitTasksByPagination(PageUtil<WorkflowModel> paginationUtility, QueryWorkflowModel workflowModel) {
        Map param = new HashMap<String,Object>();
        return workflowRepository.queryWorkflowWaitTasks(param);
    }

    /**
     * 获取流程图
     * @param bpmnModel
     * @return
     */
    private InputStream getProcessPhoto(BpmnModel bpmnModel){
        List<String> activeActivityIds = new ArrayList<String>(), highLightedFlows = new ArrayList<String>();

        InputStream imageStream = null;
        imageStream = new DefaultProcessDiagramGenerator()
            .generateDiagram(bpmnModel, "png", activeActivityIds,
                    highLightedFlows,
                    processEngineConfiguration.getActivityFontName(),
                    processEngineConfiguration.getLabelFontName(),
                    processEngineConfiguration.getAnnotationFontName(),
                    processEngineConfiguration.getClassLoader(),
                    1.0);
        return imageStream;
    }
}
