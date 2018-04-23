package org.activiti.controller;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.entity.WorkflowModel;
import org.activiti.model.QueryWorkflowModel;
import org.activiti.model.WorkflowBpmnModel;
import org.activiti.service.WorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.controller.PaginationController;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.common.model.PersistModel;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;

/**
 * 流程模型控制器
 *
 * @author lcy
 */
@Controller
@RequestMapping(value = "/workflow/model")
public class WorkflowModelController extends PaginationController<WorkflowModel> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    WorkflowService workflowServiceImpl;



    /**
     * query workflow 模型
     * @param workflow
     * @return
     */
    @ResponseBody
    @RequestMapping("queryWorkflowModel")
    public GridModel queryWorkflowModel(QueryWorkflowModel workflow){
        GridModel gridModel = workflowServiceImpl.queryWorkflowModelByActPagination(getPaginationUtility(),workflow);
        return  gridModel;
    }

    /**
     * 目前未验证是否发布
     * 删除流程模型
     */
    @RequestMapping("modelDel/{id}")
    @ResponseBody
    public AjaxResponse modelDel(@PathVariable("id") String id, AjaxResponse ar){
        PersistModel persistModel = workflowServiceImpl.modelRemove(id);
        if(persistModel.isSuccessBySinglePersist())
            ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ar.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ar;
    }


    /**
     * 创建模型
     */
    @RequestMapping(value = "modelAdd")
    @ResponseBody
    public AjaxResponse modelAdd(AjaxResponse ar,WorkflowModel workflowModel) {

        PersistModel persistModel = workflowServiceImpl.persistModel(workflowModel);
        if(persistModel.isSuccessBySinglePersist())
            ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,workflowModel.getModelId());
        else
            ar.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ar;
    }

    /**
     * 根据Model部署流程
     */
    @RequestMapping(value = "modelDeploy/{modelId}")
    @ResponseBody
    public AjaxResponse modelDeploy(@PathVariable("modelId") String modelId, AjaxResponse ar) {

        Deployment deployment = workflowServiceImpl.deployModel(modelId);
        if(null != deployment)
            ar.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,deployment.getId());
        else
            ar.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ar;
    }

    /**
     * 导出model对象为指定类型
     *
     * @param modelId 模型ID
     * @param type    导出文件类型(bpmn\json)
     */
    @RequestMapping(value = "export/{modelId}/{type}")
    public String export(@PathVariable("modelId") String modelId,
                       @PathVariable("type") String type) {
        try {
            WorkflowBpmnModel byteModel = workflowServiceImpl.getByteModel(modelId,type);
            response.setContentType("application/force-download;charset=utf-8");// 设置强制下载不打开
            //无文件
            if(null == byteModel){
                return null;
            }
            response.setHeader("Content-Disposition", "attachment;fileName=" + byteModel.getFileName());
            OutputStream outputStream = response.getOutputStream();
            byte[] by = null;
            //流程图下载
            if("png".equals(type)){
                by = new byte[byteModel.getInputStream().available()];
                byteModel.getInputStream().read(by);
                outputStream.write(by);
                byteModel.getInputStream().close();
            }else{//其他
                ByteArrayInputStream in = new ByteArrayInputStream(byteModel.getContents());
                by  = new byte[in.available()];
                in.read(by);
                outputStream.write(by);
                in.close();
            }
            outputStream.close();

        } catch (Exception e) {
            logger.error("导出model的xml文件失败：modelId={}, type={}", modelId, type, e);
        }
        return null;
    }



}