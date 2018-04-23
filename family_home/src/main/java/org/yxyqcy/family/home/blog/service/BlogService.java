package org.yxyqcy.family.home.blog.service;


import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.home.blog.entity.Blog;

import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/24
 * Time: 上午9:42
 * description:  blog 服务
 */
public interface BlogService {

    /**
     * 分页查询博客
     * @param pageUtil  分页组件
     * @param blog   查询条件
     */
    public List<Blog> queryBlogsByPagination(PageUtil pageUtil, Blog blog);

    /**
     * 插入博客
     * @param blog  博客
     */
    PersistModel persistBlog(Blog blog);


    /**
     * 浏览博客toc
     * @param id
     * @return
     */
    Blog browseBlogInToc(String id);

    /**
     * 删除博客
     * @param id
     * @return
     */
    PersistModel removeBlog(String id);

    /**
     * 修改博客
     * @param param
     * @param  minderContent 是否修改 minder 内容
     * @return
     */
    PersistModel mergeBlog(Blog param,Blog origin,boolean minderContent);

    /**
     * 推荐博客
     * @param blog
     * @return
     */
    PersistModel topBlog(Blog blog);

    /**
     * 公开博客
     * @param blog
     * @return
     */
    PersistModel publicBlog(Blog blog);

    /**
     * 查询 sheet 对应的 blog
     * @param paginationUtility
     * @param param
     * @return
     */
    public List<Blog>  queryBlogsBySheetByPagination(PageUtil<Blog> paginationUtility, Blog param);

    /**
     * 根据 pk 查 blog
     * @param id
     * @return
     */
    Blog queryBlogById(String id);

    /**
     * 统计 blogClassify count
     * @return
     */
    List<Map> staticBlogCountByClassify() ;
}
