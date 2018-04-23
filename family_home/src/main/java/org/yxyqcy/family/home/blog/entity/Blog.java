package org.yxyqcy.family.home.blog.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/24
 * Time: 上午9:53
 * description: 博客
 */
@Entity
@Table(name = "f_blog")
public class Blog  extends IdEntity<Blog> {

    private static final long serialVersionUID = 1247529619327168610L;

    public Blog() {
    }
    private float star;// 评价0-5 star

    private Date seqDate;// 排序时间

    private String blogMd;// md 内容
    private String blogHtml;// md html

    private String blogLabelId;

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public Date getSeqDate() {
        return seqDate;
    }

    public void setSeqDate(Date seqDate) {
        this.seqDate = seqDate;
    }

    public String getBlogMd() {
        return blogMd;
    }

    public void setBlogMd(String blogMd) {
        this.blogMd = blogMd;
    }

    public String getBlogHtml() {
        return blogHtml;
    }

    public void setBlogHtml(String blogHtml) {
        this.blogHtml = blogHtml;
    }

    public String getBlogLabelId() {
        return blogLabelId;
    }

    public void setBlogLabelId(String blogLabelId) {
        this.blogLabelId = blogLabelId;
    }

    public String getBkeys() {
        return bkeys;
    }

    public void setBkeys(String bkeys) {
        this.bkeys = bkeys;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBdesc() {
        return bdesc;
    }

    public void setBdesc(String bdesc) {
        this.bdesc = bdesc;
    }

    private String bkeys; // 类型 0 gaming

    @NotEmpty(message="标题不能为空")
    private String title; // title

    private String bdesc; // 预存内容

    public String getBdescHtml() {
        return bdescHtml;
    }

    public void setBdescHtml(String bdescHtml) {
        this.bdescHtml = bdescHtml;
    }

    @Transient
    private String bdescHtml; // html内容

    public String getBdescmd() {
        return bdescmd;
    }

    public void setBdescmd(String bdescmd) {
        this.bdescmd = bdescmd;
    }

    @Transient
    private String bdescmd; // md内容

    private Integer readTimes; //阅读次数

    private String type;    // 0.md 1.mind

    public static final String MD_FILE = "0";

    public static final String MIND_FILE = "1";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String isTop;   //1.推荐

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public Integer getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(Integer readTimes) {
        this.readTimes = readTimes;
    }

    //推荐or不推荐
    public static final String isTJ = "1";
    public static final String isNotTJ = "0";

    /* 关联的 sheetId */
    @Transient
    private String sheetId;

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    // use by BlogClassify
    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    private String classify;

    private String isPublic = this.isPub;

    @Transient
    private String publicFlag;

    public String getPublicFlag() {
        return publicFlag;
    }

    public void setPublicFlag(String publicFlag) {
        this.publicFlag = publicFlag;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    //公开or不公开
    public static final String isPub = "1";
    public static final String isNotPub = "0";

    @Transient
    private String checked;

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getBlogOwn() {
        return blogOwn;
    }

    public void setBlogOwn(String blogOwn) {
        this.blogOwn = blogOwn;
    }

    /**
     * 1是全部  0 是 own 查询条件

      */
    @Transient
    private String blogOwn;
    /**
     * 1是own
     * 0是other
     */
    @Transient
    private String own = "1";

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }


    public String getBlogUserName() {
        return blogUserName;
    }

    public void setBlogUserName(String blogUserName) {
        this.blogUserName = blogUserName;
    }

    public String getBlogUseRealName() {
        return blogUseRealName;
    }

    public void setBlogUseRealName(String blogUseRealName) {
        this.blogUseRealName = blogUseRealName;
    }

    @Transient
    private String blogUserName;

    @Transient
    private String blogUseRealName;

    /*rtopic id*/
    @Transient
    private String rtopic;

    public String getRtopic() {
        return rtopic;
    }

    public void setRtopic(String rtopic) {
        this.rtopic = rtopic;
    }
}
