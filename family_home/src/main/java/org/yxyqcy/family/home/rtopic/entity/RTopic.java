package org.yxyqcy.family.home.rtopic.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
* Created with licy on 2017-9-29 19:56:42.
*/
@Entity
@Table(name = "r_topic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RTopic extends IdEntity<RTopic> {

    private static final long serialVersionUID = 55535495110394358L;


    private String title;
    private String description;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8") //起作用 timezone   解决jackson 少一天
    private Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8") //起作用 timezone   解决jackson 少一天
    private Date endDate;
    private String type; //类型 0 前端 1 后端 2 运维

    //任务人集合
    @Transient
    private String users;

    //任务当前人
    @Transient
    private String user;


    @Transient
    private String rtopicUsersAdd;

    @Transient
    private String isBlogCount;

    /**
     * 日志数量
     */
    @Transient
    private Integer blogCount;
}

