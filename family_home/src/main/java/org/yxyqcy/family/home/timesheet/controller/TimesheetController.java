package org.yxyqcy.family.home.timesheet.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yxyqcy.family.common.config.Global;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.controller.PaginationController;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.util.FamilyLogger;
import org.yxyqcy.family.home.timesheet.entity.Timesheet;
import org.yxyqcy.family.home.timesheet.service.TimesheetService;
import org.yxyqcy.family.home.timesheet.util.TimeSheetUtil;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by lcy on 17/2/6.
 */
@Controller
@RequestMapping("/timesheet")
public class TimesheetController extends PaginationController<Timesheet> {

    @Resource
    private TimesheetService timesheetServiceImpl;

    /**
     * 查询timesheet
     * @param param
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:timesheet:view")
    @RequestMapping("queryTimesheetList")
    public AjaxResponse queryTimesheetsList(Timesheet param, AjaxResponse ajaxResponse){
        //当前登录人
        param.setCreateBy(UserUtils.getUser().getBusinessId());
        List<Timesheet> result = timesheetServiceImpl.queryTimeSheets(param);
        ajaxResponse.setSuccessMessage("查询成功",result);
        return  ajaxResponse;
    }

    /**
     * 管理者查看 日程timesheet
     * @param param
     * @param ajaxResponse
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:timesheet:view")
    @RequestMapping("queryManagerTimesheetList")
    public AjaxResponse queryManagerTimesheetList(Timesheet param, AjaxResponse ajaxResponse){
        Subject sub = SecurityUtils.getSubject();
        if(!sub.isPermitted("home:timesheet:assess"))
            param.setCreateBy(UserUtils.getUser().getBusinessId());
        List<Timesheet> result = timesheetServiceImpl.queryTimeSheets(param);
        TimeSheetUtil.transferColorByUser(result);
        ajaxResponse.setSuccessMessage("查询成功",result);
        return  ajaxResponse;
    }
    /**
     * 分页查询 timesheet
     * @param param
     * @return
     */
    @ResponseBody
    @RequiresPermissions("home:timesheet:view")
    @RequestMapping("queryTimesheets")
    public GridModel queryTimesheets(Timesheet param){
        Subject sub = SecurityUtils.getSubject();
        //没有审批权限,直接看自己的
        if(!sub.isPermitted("home:timesheet:assess"))
            param.setCreateBy(UserUtils.getUser().getBusinessId());
        List<Timesheet> result = timesheetServiceImpl.queryTimeSheetsByPagination(getPaginationUtility(),param);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }




    /**
     * 添加timesheet
     * @param timeSheet
     * @param
     * @return
     */
    @RequiresPermissions("home:timesheet:add")
    @RequestMapping("timesheetAdd")
    @ResponseBody
    public AjaxResponse timesheetAdd(@Valid Timesheet timeSheet, BindingResult br, AjaxResponse ajaxResponse){
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse, MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = timesheetServiceImpl.persistTimeSheet(timeSheet);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,timeSheet);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return  ajaxResponse;
    }

    /**
     * 修改timesheet
     * @param timesheet
     * @param
     * @return
     */
    @RequiresPermissions("home:timesheet:update")
    @RequestMapping("timesheetUpdate")
    @ResponseBody
    public AjaxResponse timesheetUpdate(@Valid Timesheet timesheet, BindingResult br, AjaxResponse ajaxResponse){
        if(super.hasErrors(br)){
            super.setBindError(br,ajaxResponse,MessageConstant.MESSAGE_ALERT_INFO_INVALID);
            return ajaxResponse;
        }
        PersistModel persistModel = timesheetServiceImpl.mergeTimesheet(timesheet);
        if(persistModel.isSuccessBySinglePersist()) {
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,timesheet);
        }
        else {
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        }
        return  ajaxResponse;
    }

    /**
     * 批量/单 审核 timeSheet
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("home:timesheet:assess")
    @RequestMapping("timesheetAssess")
    @ResponseBody
    public AjaxResponse timesheetAssess(Timesheet timesheet,AjaxResponse ajaxResponse){
        timesheet.setCreateBy(UserUtils.getUser().getBusinessId());
        //批量审核
        PersistModel persistModel = timesheetServiceImpl.assessTimeSheet(timesheet);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,timesheet);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return  ajaxResponse;
    }
    /**
     * delete timesheet
     * @param id
     * @return
     */
    @RequiresPermissions("home:timesheet:delete")
    @RequestMapping("timesheetDelete/{id}")
    @ResponseBody
    public AjaxResponse timesheetDelete(@PathVariable("id") String id, AjaxResponse ajaxResponse){
        PersistModel persistModel = timesheetServiceImpl.removeTimesheet(id);
        if(persistModel.isSuccessBySinglePersist())
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,null);
        else
            ajaxResponse.setErrorMessage(MessageConstant.MESSAGE_ALERT_ERROR,null);
        return ajaxResponse;
    }

    /**
     * 导出   工时 excel
     * @param timesheet
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("home:timesheet:export")
    @RequestMapping("timesheetExport")
    public String timesheetExport(Timesheet timesheet,AjaxResponse ajaxResponse){
        //批量审核
        String fileName = "工时周报.xls";
        try {
            if("1".equals(Global.getConfig("server_linux")))
                fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HSSFWorkbook wb = timesheetServiceImpl.exportTimesheet(timesheet);
        //导出xlsx格式
        //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setContentType("application/force-download;charset=utf-8");// 设置强制下载不打开
        //response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        OutputStream outputStream = null;
        //输出
        try {
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            //outputStream.write(out.toByteArray());
        } catch (IOException e) {
            FamilyLogger.error(e.getMessage());
        }finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }
}
