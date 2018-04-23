package org.yxyqcy.family.home.story.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.yxyqcy.family.common.entity.IdEntity;
import org.yxyqcy.family.common.util.DateUtil;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lcy on 17/3/16.
 */
@Entity
@Table(name = "l_story_fragment")

public class StoryFragment extends IdEntity<StoryFragment> {

    private static final long serialVersionUID = -9110264006105367220L;

    public StoryFragment() {
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String imageFirst,contentFirst,classFirst,imageSecond,contentSecond,classSecond,storyId;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8") //起作用 timezone   解决jackson 少一天
    public Date getStoryDate() {
        return storyDate;
    }

    public void setStoryDate(Date storyDate) {
        this.storyDate = storyDate;
    }

    public String getFormatterStoryDate(){
        return DateUtil.getSimepleDate("yyyy-MM-dd",getStoryDate());
    }


    private Date storyDate;

    public String getFormatterRomanticDate(){
        if(0 == getIsYear()){
            return DateUtil.getSimepleDate("M-d",getStoryDate());
        }else{
            //年
            return DateUtil.getSimepleDate("yyyy",getStoryDate());
        }
    }


    private int isYear;

    public String getImageFirst() {
        return imageFirst;
    }

    public void setImageFirst(String imageFirst) {
        this.imageFirst = imageFirst;
    }

    public String getContentFirst() {
        return contentFirst;
    }

    public void setContentFirst(String contentFirst) {
        this.contentFirst = contentFirst;
    }

    public String getClassFirst() {
        return classFirst;
    }

    public void setClassFirst(String classFirst) {
        this.classFirst = classFirst;
    }

    public String getImageSecond() {
        return imageSecond;
    }

    public void setImageSecond(String imageSecond) {
        this.imageSecond = imageSecond;
    }

    public String getContentSecond() {
        return contentSecond;
    }

    public void setContentSecond(String contentSecond) {
        this.contentSecond = contentSecond;
    }

    public String getClassSecond() {
        return classSecond;
    }

    public void setClassSecond(String classSecond) {
        this.classSecond = classSecond;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public int getIsYear() {
        return isYear;
    }

    public void setIsYear(int isYear) {
        this.isYear = isYear;
    }
}
