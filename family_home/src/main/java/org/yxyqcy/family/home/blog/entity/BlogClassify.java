package org.yxyqcy.family.home.blog.entity;

/**
 * Created by lcy on 17/11/10.
 */
public enum BlogClassify {


    COMPREHENSIVE("0","综合"),  //综合
    MOBILE_DEVELOP("1","移动开发"), //移动开发
    ARCHITECTURE("2","架构"),   //架构
    CLOUD_COMPUTING("3","云计算/大数据"),//云计算/大数据
    INTERNET("4","互联网"),       //互联网
    OPERATION("5","运维"),      //运维
    DATABASE("6","数据库"),       //数据库
    FRONTEND("7","前端"),       //前端
    PROGRAMME("8","编程语言"),      //编程语言
    RESEARCH("9","研发管理");       //研发管理

    private String type;

    private String name;

    private String count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    BlogClassify(String type,String name) {
        this.type = type;
        this.name = name;
    }

    BlogClassify() {
    }
}
