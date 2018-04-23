package org.yxyqcy.family.home.blog.service;

import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.home.blog.entity.BlogLabel;

import java.util.List;

/**
 * Created by lcy on 17/2/15.
 */
public interface BlogLabelService {
    /**
     * 查询博客标签
     * @param blogLabel   查询条件
     */
    public List<BlogLabel> queryBlogLabels(BlogLabel blogLabel);

    /**
     * 插入博客标签
     * @param blogLabel  博客
     */
    PersistModel persistBlogLabel(BlogLabel blogLabel);

    /**
     * remove 博客标签
     * @param businessId
     * @return
     */
    PersistModel removeBlogLabel(String businessId);

    /**
     * merge 博客标签
     * @param label
     * @return
     */
    PersistModel mergeBlogLabel(BlogLabel label);

}
