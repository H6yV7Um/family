package org.yxyqcy.family.home.blog.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.config.Global;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.common.util.DateUtil;
import org.yxyqcy.family.common.util.FileUtil;
import org.yxyqcy.family.home.blog.dao.BlogRepository;
import org.yxyqcy.family.home.blog.entity.Blog;
import org.yxyqcy.family.home.blog.entity.BlogClassify;
import org.yxyqcy.family.home.blog.service.BlogService;
import org.yxyqcy.family.home.blog.util.BlogUtil;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/24
 * Time: 上午10:08
 * description:  博客 service 实现
 */
@Service
@Transactional(readOnly = true)
public class BlogServiceImpl  extends BaseService implements BlogService {


    private static final long serialVersionUID = -5483384834223662563L;
    @Resource
    public BlogRepository blogRepository;


    @Override
    public List<Blog> queryBlogsByPagination(PageUtil pageUtil, Blog blog) {
        Map param = new HashMap<String,Object>();
        param.put("createBy",blog.getCreateBy());
        param.put("flag",blog.getFlag());
        param.put("delFlag",blog.getDelFlag());
        param.put("isTop",blog.getIsTop());
        param.put("title",blog.getTitle());
        param.put("blogLabelId",blog.getBlogLabelId());
        param.put("sheetId",blog.getSheetId());
        //是否是public的
        param.put("isPublic",blog.getIsPublic());
        param.put("publicFlag",blog.getPublicFlag());
        param.put("classify",blog.getClassify());
        param.put("rtopic",blog.getRtopic());

        if(StringUtils.isBlank(blog.getSheetId()) && StringUtils.isBlank(blog.getRtopic()))
            return blogRepository.queryBlogs(param);
        //首页 根据 rtopic 查询
        else if (StringUtils.isBlank(blog.getSheetId()) && StringUtils.isNotBlank(blog.getRtopic()))
            return blogRepository.queryBlogsByRtopic(param);
        else
            return blogRepository.querySheetBlogs(param);
    }

    @Override
    public List<Blog> queryBlogsBySheetByPagination(PageUtil<Blog> paginationUtility, Blog blog) {
        Map param = new HashMap<String,Object>();
        param.put("flag",blog.getFlag());
        param.put("delFlag",blog.getDelFlag());
        param.put("sheetId",blog.getSheetId());
        return blogRepository.queryBlogsBySheet(param);
    }

    @Override
    public Blog queryBlogById(String id) {
        return blogRepository.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel persistBlog(Blog blog) {
        blog.setCommonBusinessId();
        UserUtils.setCurrentPersistOperation(blog);
        blog.setSeqDate(new Date());
        blog.setReadTimes(0);
        blog.setStar(0.0f);
        /*md file*/
        if(Blog.MD_FILE.equals(blog.getType())){
            this.createBlogMdFile(blog);
        }
        /*插入表*/
        int line = blogRepository.insertSelective(blog);
        return new PersistModel(line);
    }



    @Override
    public Blog browseBlogInToc(String id) {
        Blog blog = blogRepository.queryBlogById(id);
        String basePath = Global.getConfig("base_upload_location");
        if(!StringUtils.isBlank(blog.getBlogHtml()))
            blog.setBdescHtml(FileUtil.read(basePath + blog.getBlogHtml(), "UTF-8"));
        if(!StringUtils.isBlank(blog.getBlogMd()))
            blog.setBdescmd(FileUtil.read(basePath + blog.getBlogMd(), "UTF-8"));
        return blog;
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel removeBlog(String id) {
        Blog blog = new Blog();
        blog.setBusinessId(id);
        blog.setDeleted();
        int line = blogRepository.deleteLogic(blog);
        return new PersistModel(line);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel mergeBlog(Blog blog,Blog origin,boolean minderContent) {
        UserUtils.setCurrentMergeOperation(blog);
        blog.setSeqDate(new Date());
        //原始文件
        if(null == origin)
            origin = blogRepository.queryBlogById(blog.getBusinessId());
        /* exception*/
        if(null == origin || StringUtils.isBlank(origin.getType()))
            return new PersistModel(0);
        /*清空 原始文件*/
        if(null != origin.getBlogMd()) {
            File fileMd = new File(origin.getBlogMd());
            fileMd.deleteOnExit();

        }
        if(null != origin.getBlogHtml()){
            File fileHtml = new File(origin.getBlogHtml());
            fileHtml.deleteOnExit();
        }

        /*md file*/
        if(Blog.MD_FILE.equals(origin.getType())){
            this.createBlogMdFile(blog);
            blog.setType(Blog.MD_FILE);
        }
        /*minder file content*/
        if(minderContent && Blog.MIND_FILE.equals(origin.getType())){
            this.createBlogMindFile(blog);
            origin.setBdesc(blog.getBdesc());
            origin.setBlogMd(blog.getBlogMd());
            blog = origin;
        }
        //修改
        int line = blogRepository.updateSelective(blog);
        return new PersistModel(line);
    }

    /**
     * 生成 blog md html file
     * @param blog
     */
    private void createBlogMdFile(Blog blog){
        String basePath = Global.getConfig("base_upload_location");
        String mdPath = "/blogs" + File.separator + UserUtils.getUser().getBusinessId() + File.separator
                + DateUtil.getSimepleDate("yyyy-MM-dd",blog.getCreateDate()) + File.separator;

        File mdFileDirectory = null;
        mdFileDirectory = new File(basePath+mdPath);
        //创建目录
        if(!mdFileDirectory.exists())
            mdFileDirectory.mkdirs();
            /*生成md 规则  baseblogs/userID/createDate/md/*/
        FileUtil.write(basePath + mdPath + blog.getBusinessId() + ".md",blog.getBdesc(),"UTF-8");
            /*生成带目录的html 规则  base/family_files/blogs/userID/createDate/html/*/
        FileUtil.write(basePath + mdPath + blog.getBusinessId() + ".html",blog.getBdescHtml(),"UTF-8");
            /*插入表*/
        blog.setBlogMd(mdPath + blog.getBusinessId() + ".md");
        blog.setBlogHtml(mdPath + blog.getBusinessId() + ".html");
    }
    /**
     * 生成 blog mind file
     * @param blog
     */
    private void createBlogMindFile(Blog blog){
        String basePath = Global.getConfig("base_upload_location");
        String mdPath = "/blogs" + File.separator + UserUtils.getUser().getBusinessId() + File.separator
                + DateUtil.getSimepleDate("yyyy-MM-dd",blog.getCreateDate()) + File.separator;

        File mdFileDirectory = null;
        mdFileDirectory = new File(basePath+mdPath);
        //创建目录
        if(!mdFileDirectory.exists())
            mdFileDirectory.mkdirs();
            /*生成md 规则  baseblogs/userID/createDate/md/*/
        FileUtil.write(basePath + mdPath + blog.getBusinessId() + ".mind",blog.getBdesc(),"UTF-8");
            /*插入表*/
        blog.setBlogMd(mdPath + blog.getBusinessId() + ".mind");
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel topBlog(Blog blog) {
        UserUtils.setCurrentMergeOperation(blog);
        blog.setIsPublic(null);//防止修改public
        int line = blogRepository.updateBlogState(blog);
        return new PersistModel(line);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel publicBlog(Blog blog) {
        UserUtils.setCurrentMergeOperation(blog);
        if(Blog.isNotPub.equals(blog.getIsPublic()))
            blog.setIsTop(Blog.isNotTJ);//改成private 自动取消推荐
        else
            blog.setIsTop(null);//防止修改top
        int line = blogRepository.updateBlogState(blog);
        return new PersistModel(line);
    }

    @Override
    public List<Map> staticBlogCountByClassify() {
        List<BlogClassify> classifyList = BlogUtil.getAllBlogClassify();
        List<Map> list = blogRepository.queryStaticBlogCountByClassify();
        for (Map map : list) {
            for (BlogClassify classify: classifyList) {
                //匹配
                if(map.get("classify").equals(classify.getType())) {
                    map.put("name",classify.getName());
                    break;
                }
            }
        }
        return list;
    }

}
