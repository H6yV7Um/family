package org.yxyqcy.family.home.timesheet.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.yxyqcy.family.common.entity.IdEntity;
import org.yxyqcy.family.common.model.CommonAssessModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by lcy on 17/2/6.
 */
@Entity
@Table(name = "f_timesheet")
public class Timesheet extends IdEntity<Timesheet> {
    private static final long serialVersionUID = 3847195745894234268L;




    public Timesheet() {
    }
    private String title;
    private String content;
    private boolean allDay;
    private String assessFlag;//0 待审核 1 已通过 2 已打回
    private String assessUser;

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    private String createBy;

    private Date assessDate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name="start_date")
    private Date start;
    @Column(name="end_date")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date end;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public String getAssessFlag() {
        return assessFlag;
    }

    public void setAssessFlag(String assessFlag) {
        this.assessFlag = assessFlag;
    }

    public String getAssessUser() {
        return assessUser;
    }

    public void setAssessUser(String assessUser) {
        this.assessUser = assessUser;
    }

    public Date getAssessDate() {
        return assessDate;
    }

    public void setAssessDate(Date assessDate) {
        this.assessDate = assessDate;
    }


    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * fullcalendar event id
     * @return
     */
    public String getId(){
        return getBusinessId();
    }

    @Transient
    private String color ;
    @Transient
    private String textColor = "#fff";

    public boolean isColorByUser() {
        return colorByUser;
    }

    public void setColorByUser(boolean colorByUser) {
        this.colorByUser = colorByUser;
    }

    /**
     * 是否根据 用户 显示
     */
    @Transient

    private boolean colorByUser = false;

    public String getColor() {
        if(this.isColorByUser())
            return color;
        CommonAssessModel model = CommonAssessModel.queryAssessModelByFlag(this.getAssessFlag());
        if(null == model)
            return color;
        return model.getColor();
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }


    @Transient
    private String createUser ;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Transient
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startQuery;

    @Transient
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endQuery;

    public Date getStartQuery() {
        return startQuery;
    }

    public void setStartQuery(Date startQuery) {
        this.startQuery = startQuery;
    }

    public Date getEndQuery() {
        return endQuery;
    }

    public void setEndQuery(Date endQuery) {
        this.endQuery = endQuery;
    }

    private String topicId;

    private String type;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Transient
    private String sheetBlogs;

    public String getSheetBlogs() {
        return sheetBlogs;
    }

    public void setSheetBlogs(String sheetBlogs) {
        this.sheetBlogs = sheetBlogs;
    }

    @Transient
    private int blogCount;

    public int getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(int blogCount) {
        this.blogCount = blogCount;
    }
}
