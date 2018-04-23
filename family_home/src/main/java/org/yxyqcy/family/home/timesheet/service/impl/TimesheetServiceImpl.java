package org.yxyqcy.family.home.timesheet.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.constant.DelStatus;
import org.yxyqcy.family.common.constant.FlagStatus;
import org.yxyqcy.family.common.model.CommonAssessModel;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.common.util.DateUtil;
import org.yxyqcy.family.common.util.IdGen;
import org.yxyqcy.family.home.timesheet.dao.TimesheetRepository;
import org.yxyqcy.family.home.timesheet.entity.Timesheet;
import org.yxyqcy.family.home.timesheet.model.TimeSheetModel;
import org.yxyqcy.family.home.timesheet.service.TimesheetService;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lcy on 17/2/6.
 */
/**
 *
 *
 *      1.取消事务,并设置只读 @Transactional readOnly = true,propagation = Propagation.NOT_SUPPORTED
 *  可以进行没有事务的CUD 操作
 *      2.开启事务(默认开启),并设置只读 @Transactional(readOnly = true)
 *  不可以进行CUD 操作
 *      3.在方法上 @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
 *  覆盖readOnly 可以进行有事务的CUD操作
 */
@Service
@Transactional(readOnly = true)
public class TimesheetServiceImpl extends BaseService implements TimesheetService {

    private static final long serialVersionUID = -2688427508409678464L;

    @Resource
    private TimesheetRepository timeSheetRepository;

    @Override
    public List<Timesheet> queryTimeSheets(Timesheet timeSheet) {
        Map param = new HashMap<String,Object>();
        param.put("createBy",timeSheet.getCreateBy());
        param.put("flag",timeSheet.getFlag());
        param.put("delFlag",timeSheet.getDelFlag());
        param.put("start",timeSheet.getStart());
        param.put("end",timeSheet.getEnd());
        param.put("type",timeSheet.getType());
        return timeSheetRepository.queryTimeSheets(param);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel persistTimeSheet(Timesheet timeSheet) {
        timeSheet.setCommonBusinessId();
        UserUtils.setCurrentPersistOperation(timeSheet);
        //缺省待审核
        timeSheet.setAssessFlag(CommonAssessModel.ASSESS_WAIT.getAssessFlag());
        int line = timeSheetRepository.insertSelective(timeSheet);
        this.insertSheetBlog(timeSheet,false);
        return new PersistModel(line);
    }


    @Override
    public Timesheet queryTimeSheetById(String id) {
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel mergeTimesheet(Timesheet timeSheet) {
        UserUtils.setCurrentMergeOperation(timeSheet);
        //自动 待审核
        timeSheet.setAssessFlag(CommonAssessModel.ASSESS_WAIT.getAssessFlag());
        Timesheet sheet = timeSheetRepository.selectByPrimaryKey(timeSheet.getBusinessId());
        //通过的不能修改
        if(null == sheet || null == sheet.getAssessFlag() || sheet.getAssessFlag().equals(CommonAssessModel.ASSESS_PASS.getAssessFlag()))
            return new PersistModel(0);

        int line = timeSheetRepository.updateSelective(timeSheet);
        this.insertSheetBlog(timeSheet,true);
        return new PersistModel(line);
    }

    /**
     * 插入 sheet blog
     * @param timeSheet
     * @param clear
     */
    private void insertSheetBlog(Timesheet timeSheet,boolean clear){
        if(clear)
            timeSheetRepository.deleteSheetBlog(timeSheet.getBusinessId());
        /*blog */
        if(!StringUtils.isBlank(timeSheet.getSheetBlogs())){
            String[] blogs = timeSheet.getSheetBlogs().split(",");
            List<Map> sheetBlog = new ArrayList<Map>();
            Map map = null;
            for (int i = 0 ; i < blogs.length ; i++){
                map = new HashMap();
                map.put("id", IdGen.uuid());
                map.put("sheetId",timeSheet.getBusinessId());
                map.put("blogId",blogs[i]);
                sheetBlog.add(map);
            }
            timeSheetRepository.insertBatchSheetBlog(sheetBlog);
        }

    }



    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel removeTimesheet(String id) {
        //真删除
        timeSheetRepository.deleteSheetBlog(id);
        int line = timeSheetRepository.deleteByPrimaryKey(id);
        return new PersistModel(line);
    }

    @Override
    public List<Timesheet> queryTimeSheetsByPagination(PageUtil<Timesheet> paginationUtility, Timesheet timeSheet) {
        Map param = new HashMap<String,Object>();
        param.put("createBy",timeSheet.getCreateBy());
        param.put("flag",timeSheet.getFlag());
        param.put("delFlag",timeSheet.getDelFlag());
        param.put("start",timeSheet.getStart());
        param.put("end",timeSheet.getEnd());
        param.put("createUser",timeSheet.getCreateUser());
        param.put("topicId",timeSheet.getTopicId());
        param.put("type",timeSheet.getType());
        return timeSheetRepository.queryTimeSheets(param);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel assessTimeSheet(Timesheet timesheet) {
        //修改参数
        List<Map> list = new ArrayList<Map>();
        if(null == timesheet.getBusinessId())
            new PersistModel(0);
        String[] businessIds = timesheet.getBusinessId().split(",");
        for(String bus : businessIds){
            Map mapParam = new HashMap<String,Object>();
            mapParam.put("businessId",bus);
            mapParam.put("assessFlag",timesheet.getAssessFlag());
            mapParam.put("assessDate",new Date());
            mapParam.put("assessUser",timesheet.getCreateBy());
            list.add(mapParam);
        }
        //空集合
        if(0 == list.size())
            new PersistModel(0);
        int line = timeSheetRepository.batchAssessTimeSheet(list);
        return new PersistModel(line);
    }

    @Override
    public HSSFWorkbook exportTimesheet(Timesheet timeSheet) {
        Map param = new HashMap<String,Object>();
        param.put("assessFlag",CommonAssessModel.ASSESS_PASS.getAssessFlag());
        param.put("flag", FlagStatus.NORMAL.getStatus());
        param.put("delFlag", DelStatus.NORMAL.getStatus());
        param.put("startQuery",timeSheet.getStartQuery());
        param.put("endQuery",timeSheet.getEndQuery());
        param.put("createUser",timeSheet.getCreateUser());
        //根据人员,时间排序
        List<Timesheet> list = timeSheetRepository.queryTimeSheetsOrderByUser(param);
        //转换model
        List<TimeSheetModel> model = this.transfromModel(list);
        //用Workbook workbook = new XSSFWorkbook();  xlsx格式
        HSSFWorkbook wb = new HSSFWorkbook();   // xls格式
        String dataFormat = "";
        if(null != timeSheet.getStartQuery())
            dataFormat += DateUtil.getSimepleDate("yyyy-MM-dd",timeSheet.getStartQuery());
        if(null != timeSheet.getEndQuery())
            dataFormat += "  ~  " + DateUtil.getSimepleDate("yyyy-MM-dd",timeSheet.getEndQuery());
        if(0 == model.size()){
            this.createBlankTimeSheet(wb);
            return wb;
        }
        for(int i = 0 ; i < model.size() ; i++){
            this.createTimeSheet(wb,model.get(i).getTimesheetList(),model.get(i).getCreateUser(),dataFormat);
        }

        return wb;
    }

    /**
     * 创建空的 周报
     * @param wb
     */
    private void createBlankTimeSheet(HSSFWorkbook wb) {
        HSSFSheet sheet = wb.createSheet(" 周报");
        HSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellValue("项目周报");
    }

    /**
     * 根据timesheet createBy 分割
     * @param timeList
     * @return
     */
    private List<TimeSheetModel> transfromModel(List<Timesheet> timeList){

        List<TimeSheetModel> timesheets = new ArrayList<TimeSheetModel>();
        if(null == timeList || 0 == timeList.size())
            return timesheets;
        for(int i = 0;i < timeList.size(); i ++ ){
            String timeUser = timeList.get(i).getCreateBy();
            String timeUserName = timeList.get(i).getCreateUser();
            boolean flag = false;
            //循环
            for(int j = 0 ; j < timesheets.size() ;j++){
                if(null != timeUser && timeUser.equals(timesheets.get(j).getCreateBy())){
                    flag = true;
                    timesheets.get(j).addTimeSheet(timeList.get(i));
                    continue;
                }
            }

            if(!flag){
                TimeSheetModel model = new TimeSheetModel();
                model.setCreateBy(timeUser);
                model.setCreateUser(timeUserName);
                model.addTimeSheet(timeList.get(i));
                timesheets.add(model);
            }

        }
        return timesheets;
    }


    private void createTimeSheet(HSSFWorkbook wb,List<Timesheet> list,String username,String period){
        HSSFSheet sheet = wb.createSheet(username + " 周报");
        // api 段信息 Set the width (in units of 1/256th of a character width)
        sheet.setColumnWidth(0, 20 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        sheet.setColumnWidth(3, 20 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 50 * 256);

        //title
        HSSFRow title = sheet.createRow(0);
        //font
        HSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 16);//设置字体大小

        HSSFFont dataFont = wb.createFont();
        dataFont.setFontName("宋体");
        dataFont.setFontHeightInPoints((short) 14);//设置字体大小

        //style
        HSSFCellStyle simpleStyleLeft = wb.createCellStyle();
        simpleStyleLeft.setWrapText(true);
        simpleStyleLeft.setFont(font);
        simpleStyleLeft.setFillForegroundColor(IndexedColors.CORAL.getIndex());
        simpleStyleLeft.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        HSSFCellStyle simpleStyleValue = wb.createCellStyle();
        simpleStyleValue.setWrapText(true);
        simpleStyleValue.setFont(font);
        simpleStyleValue.setAlignment(HorizontalAlignment.CENTER);
        simpleStyleValue.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
        simpleStyleValue.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


        HSSFCellStyle simpleStyleCenter = wb.createCellStyle();
        simpleStyleCenter.setWrapText(true);
        simpleStyleCenter.setFont(font);
        simpleStyleCenter.setAlignment(HorizontalAlignment.CENTER);
        simpleStyleCenter.setFillForegroundColor(IndexedColors.CORAL.getIndex());
        simpleStyleCenter.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


        HSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setWrapText(true);
        dataStyle.setAlignment(HorizontalAlignment.LEFT);
        dataStyle.setFont(dataFont);
        dataStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        dataStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直
        //simpileStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        //simpileStyle.setFillBackgroundColor(HSSFColor.GREEN.index);

        title.setHeight((short)400);
        HSSFCell firstTitle = title.createCell(0);
        firstTitle.setCellValue("项目周报");
        firstTitle.setCellType(CellType.STRING);
        firstTitle.setCellStyle(simpleStyleCenter);
        CellRangeAddress craTitle = new CellRangeAddress(0, 0, 0, 5);
        sheet.addMergedRegion(craTitle);

        //project
        HSSFRow project = sheet.createRow(1);
        project.setHeight((short)400);
        HSSFCell secondProject = project.createCell(0);
        secondProject.setCellValue("项目名称");
        secondProject.setCellType(CellType.STRING);
        secondProject.setCellStyle(simpleStyleLeft);

        HSSFCell secondProjectValue = project.createCell(1);
        secondProjectValue.setCellValue("");
        secondProjectValue.setCellType(CellType.STRING);
        secondProjectValue.setCellStyle(simpleStyleValue);

        HSSFCell secondUser = project.createCell(2);
        secondUser.setCellValue("完成人");
        secondUser.setCellType(CellType.STRING);
        secondUser.setCellStyle(simpleStyleLeft);

        HSSFCell secondUserValue = project.createCell(3);
        secondUserValue.setCellValue(username);
        secondUserValue.setCellType(CellType.STRING);
        secondUserValue.setCellStyle(simpleStyleValue);


        HSSFCell secondDate = project.createCell(4);
        secondDate.setCellValue("周报日期");
        secondDate.setCellType(CellType.STRING);
        secondDate.setCellStyle(simpleStyleLeft);

        HSSFCell secondDateValue = project.createCell(5);
        secondDateValue.setCellValue(period);
        secondDateValue.setCellType(CellType.STRING);
        secondDateValue.setCellStyle(simpleStyleValue);


        //project
        HSSFRow milestone = sheet.createRow(2);
        milestone.setHeight((short)400);
        HSSFCell thirdMile = milestone.createCell(0);
        thirdMile.setCellValue("项目主要里程碑:");
        thirdMile.setCellType(CellType.STRING);
        thirdMile.setCellStyle(simpleStyleLeft);
        CellRangeAddress craMileStone = new CellRangeAddress(2, 2, 0, 5);
        sheet.addMergedRegion(craMileStone);


        milestone = sheet.createRow(3);
        HSSFCell fouthNum = milestone.createCell(0);
        fouthNum.setCellValue("编号");
        fouthNum.setCellType(CellType.STRING);
        fouthNum.setCellStyle(simpleStyleLeft);



        HSSFCell milestoneName = milestone.createCell(1);
        milestoneName.setCellValue("里程碑名称");
        milestoneName.setCellStyle(simpleStyleLeft);
        milestoneName.setCellType(CellType.STRING);

        HSSFCell fouthContent = milestone.createCell(2);
        fouthContent.setCellValue("内容");
        fouthContent.setCellType(CellType.STRING);
        fouthContent.setCellStyle(simpleStyleLeft);


        HSSFCell fouthDate = milestone.createCell(5);
        fouthDate.setCellValue("完成时间");
        fouthDate.setCellType(CellType.STRING);
        fouthDate.setCellStyle(simpleStyleLeft);

        CellRangeAddress craMileStoneDesc = new CellRangeAddress(3, 3, 2, 4);
        sheet.addMergedRegion(craMileStoneDesc);
        // data row
        HSSFRow dataRow = null;
        int dataRowIndex = 4;
        Timesheet ts = null;
        HSSFCell fifthNum = null,fifthName = null,fifthContent = null,fifthDate = null;
        for (int i =0 ; i < list.size(); i++) {
            ts = list.get(i);
            dataRow = sheet.createRow(dataRowIndex++);
            //dataRow.setHeightInPoints(20);
            dataRow.setHeight((short)1000);
            fifthNum = dataRow.createCell(0);
            fifthNum.setCellValue(i+1);
            fifthNum.setCellType(CellType.STRING);
            fifthNum.setCellStyle(dataStyle);

            fifthName = dataRow.createCell(1);
            fifthName.setCellValue(ts.getTitle());
            fifthName.setCellType(CellType.STRING);
            fifthName.setCellStyle(dataStyle);

            fifthContent = dataRow.createCell(2);
            fifthContent.setCellValue(ts.getContent());
            fifthContent.setCellType(CellType.STRING);
            fifthContent.setCellStyle(dataStyle);

            craMileStoneDesc = new CellRangeAddress(dataRowIndex-1, dataRowIndex-1, 2, 4);
            sheet.addMergedRegion(craMileStoneDesc);
            fifthDate = dataRow.createCell(5);
            fifthDate.setCellType(CellType.STRING);
            fifthDate.setCellStyle(dataStyle);

            //结束任务为null的整日任务
            if(null == ts.getEnd())
                fifthDate.setCellValue(DateUtil.getSimepleDate("yyyy-MM-dd",ts.getStart()));
            else
                fifthDate.setCellValue(DateUtil.getSimepleDate("yyyy-MM-dd",ts.getStart()) +
                        " -- " + DateUtil.getSimepleDate("yyyy-MM-dd",ts.getEnd()));
        }
        //自适应宽度
        //for (int i = 0; i < 6 ; i ++)
          //  sheet.autoSizeColumn((short)i, true); //调整第一列宽度
    }
}
