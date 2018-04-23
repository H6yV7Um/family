package org.yxyqcy.family.home.blog.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by lcy on 17/2/14.
 */
@Entity
@Table(name = "f_blog_label")
public class BlogLabel extends IdEntity<BlogLabel> {
    private static final long serialVersionUID = -4877380996286836724L;

    public BlogLabel() {
    }

    @NotEmpty(message="标题不能为空")
    private String title; // title
    @Transient
    private Integer countBlog;

    private Date seqDate;// 排序时间

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCountBlog() {
        return countBlog;
    }

    public void setCountBlog(Integer countBlog) {
        this.countBlog = countBlog;
    }

    public Date getSeqDate() {
        return seqDate;
    }

    public void setSeqDate(Date seqDate) {
        this.seqDate = seqDate;
    }
}
