package org.yxyqcy.family.home.timesheet.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.home.timesheet.entity.Timesheet;

import java.util.List;

/**
 * Created by lcy on 17/2/6.
 */
public interface TimesheetService {
    /**
     * 查询timesheet
     * @param timeSheet   查询条件
     */
    public List<Timesheet> queryTimeSheets(Timesheet timeSheet);

    /**
     * 插入 timeSheet
     * @param param  movie
     */
    PersistModel persistTimeSheet(Timesheet param);
    /**
     * 根据id查询 timeSheet
     * @param id
     * @return
     */
    Timesheet queryTimeSheetById(String id);

    /**
     * 修改timesheet
     * @param timesheet
     * @return
     */
    PersistModel mergeTimesheet(Timesheet timesheet);

    /**
     * 删除timeSheet
     * @param id
     * @return
     */
    PersistModel removeTimesheet(String id);

    /**
     * 分页查询 timeSheet
     * @param paginationUtility
     * @param param
     * @return
     */
    List<Timesheet> queryTimeSheetsByPagination(PageUtil<Timesheet> paginationUtility, Timesheet param);

    /**
     * 批量审核 timeSheet
     * @param timesheet
     * @return
     */
    PersistModel assessTimeSheet(Timesheet timesheet);

    HSSFWorkbook exportTimesheet(Timesheet timesheet);
}
