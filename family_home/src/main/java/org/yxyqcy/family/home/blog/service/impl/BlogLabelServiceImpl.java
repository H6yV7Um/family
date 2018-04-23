package org.yxyqcy.family.home.blog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.home.blog.dao.BlogLabelRepository;
import org.yxyqcy.family.home.blog.dao.BlogRepository;
import org.yxyqcy.family.home.blog.entity.BlogClassify;
import org.yxyqcy.family.home.blog.entity.BlogLabel;
import org.yxyqcy.family.home.blog.service.BlogLabelService;
import org.yxyqcy.family.home.blog.util.BlogUtil;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/2/15.
 */
@Service
@Transactional(readOnly = true)
public class BlogLabelServiceImpl extends BaseService implements BlogLabelService {
    private static final long serialVersionUID = -7965233182314695782L;

    @Resource
    private BlogLabelRepository blogLabelRepository;

    @Resource
    private BlogRepository blogRepository;

    @Override
    public List<BlogLabel> queryBlogLabels(BlogLabel blogLabel) {

        Map param = new HashMap<String,Object>();
        param.put("createBy",blogLabel.getCreateBy());
        param.put("flag",blogLabel.getFlag());
        param.put("delFlag",blogLabel.getDelFlag());
        return blogLabelRepository.queryBlogLabels(param);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel persistBlogLabel(BlogLabel blogLabel) {
        blogLabel.setCommonBusinessId();
        UserUtils.setCurrentPersistOperation(blogLabel);
        blogLabel.setSeqDate(new Date());
        int line = blogLabelRepository.insertSelective(blogLabel);
        return new PersistModel(line);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel removeBlogLabel(String businessId) {
        Integer count = blogRepository.queryCountByLabelId(businessId);
        if(0 < count)
            return new PersistModel(0,"当前标签下,存在博客,不能删除该标签");
        int line = blogLabelRepository.deleteByPrimaryKey(businessId);
        return new PersistModel(line);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel mergeBlogLabel(BlogLabel label) {
        UserUtils.setCurrentMergeOperation(label);
        label.setSeqDate(new Date());
        int line = blogLabelRepository.mergeBlogLabel(label);
        return new PersistModel(line);
    }


}
