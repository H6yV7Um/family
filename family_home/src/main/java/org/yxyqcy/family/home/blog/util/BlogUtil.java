package org.yxyqcy.family.home.blog.util;

import org.yxyqcy.family.home.blog.entity.Blog;
import org.yxyqcy.family.home.blog.entity.BlogClassify;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 17/11/10.
 */
public class BlogUtil implements Serializable{


    private static final long serialVersionUID = 2378886324586247397L;

    /**
     * 设置所属日志
     * @param blogList
     * @param businessId
     */
    public static void setOwnsBlogs(List<Blog> blogList, String businessId) {
        if(null == blogList || 0 == blogList.size())
            return;

        for (Blog blog: blogList) {
            if(!businessId.equals(blog.getCreateBy()))
                blog.setOwn("0");
        }

    }

    /**
     * 获取所有classify enum
     * @return
     */
    public static List<BlogClassify> getAllBlogClassify(){
        List<BlogClassify> list = new ArrayList<BlogClassify>();
        list.add(BlogClassify.COMPREHENSIVE);
        list.add(BlogClassify.MOBILE_DEVELOP);
        list.add(BlogClassify.ARCHITECTURE);
        list.add(BlogClassify.CLOUD_COMPUTING);
        list.add(BlogClassify.INTERNET);
        list.add(BlogClassify.OPERATION);
        list.add(BlogClassify.DATABASE);
        list.add(BlogClassify.FRONTEND);
        list.add(BlogClassify.PROGRAMME);
        list.add(BlogClassify.RESEARCH);
        return list;
    }
}
