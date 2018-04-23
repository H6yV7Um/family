package org.yxyqcy.family.home.timesheet.model;

import org.yxyqcy.family.home.timesheet.entity.Timesheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 17/6/22.
 */
public class TimeSheetModel {

    public TimeSheetModel() {
    }

    private String createBy,createUser;

    private List<Timesheet> timesheetList = new ArrayList<Timesheet>();

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public List<Timesheet> getTimesheetList() {
        return timesheetList;
    }

    public void setTimesheetList(List<Timesheet> timesheetList) {
        this.timesheetList = timesheetList;
    }

    /**
     * 增加
     * @param timesheet
     */
    public void addTimeSheet(Timesheet timesheet) {
        this.getTimesheetList().add(timesheet);
    }
}
