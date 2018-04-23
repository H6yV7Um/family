package org.yxyqcy.family.home.fbook.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
* Created with yxyqcy on 2017-11-13 23:12:26.
*/
@Entity
@Table(name = "f_book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FBook extends IdEntity<FBook> {

    private static final long serialVersionUID = 3061474853748913747L;


    private String code;
    private String name;
    private String descri;
    private String bookUrl;
    private String bookCover;
    private Integer pages;
    private String classify;    //0 综合 1 移动开发 2 架构 3 云计算/大数据 4 互联网 5 运维 6 数据库 7 前端 8 编程语言 9 研发管理
    private String bookCase;    //0 初级 1 中级 2 高级 3 资深级 4 专家级


    private String isPublic = this.isPub;


    //公开or不公开
    public static final String isPub = "1";
    public static final String isNotPub = "0";


    private String isTop = null;

    //推荐or不推荐
    public static final String isTJ = "1";
    public static final String isNotTJ = "0";

    /**
     * 1是全部  0 是 own 查询条件

     */
    @Transient
    private String bookOwn;


    @Transient
    private String publicFlag;


    @Transient
    private String bookUserName;

    @Transient
    private String bookUseRealName;

    /**
     * 1是own
     * 0是other
     */
    @Transient
    private String own ;

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }

}

