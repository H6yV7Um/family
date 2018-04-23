package org.yxyqcy.family.home.story.service.impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.config.Global;
import org.yxyqcy.family.common.constant.DelStatus;
import org.yxyqcy.family.common.constant.FlagStatus;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.common.util.DateUtil;
import org.yxyqcy.family.home.story.dao.StoryFragmentRepository;
import org.yxyqcy.family.home.story.dao.StoryRepository;
import org.yxyqcy.family.home.story.entity.Story;
import org.yxyqcy.family.home.story.entity.StoryFragment;
import org.yxyqcy.family.home.story.service.StoryService;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/3/16.
 */
@Service
@Transactional(readOnly = true)
public class StoryServiceImpl  extends BaseService implements StoryService{

    private static final long serialVersionUID = 911623274418744787L;

    @Resource
    private StoryRepository storyRepository;

    @Resource
    private StoryFragmentRepository storyFragmentRepository;


    @Override
    public List<Story> queryStoriesByPagination(PageUtil<Story> paginationUtility, Story story) {
        Map param = new HashMap<String,Object>();
        param.put("createBy",story.getCreateBy());
        param.put("flag",story.getFlag());
        param.put("delFlag",story.getDelFlag());
        param.put("title",story.getTitle());
        return storyRepository.queryStories(param);
    }

    @Override
    public List<StoryFragment> queryStoryFragmentsByPagination(PageUtil<Story> paginationUtility, StoryFragment story) {
        Map param = new HashMap<String,Object>();
        param.put("createBy",story.getCreateBy());
        param.put("flag",story.getFlag());
        param.put("delFlag",story.getDelFlag());
        param.put("storyId",story.getStoryId());
        param.put("title",story.getTitle());
        return storyFragmentRepository.queryStoryFragments(param);
    }

    @Override
    public List<StoryFragment> queryStoryFragments(String id) {
        Map param = new HashMap<String,Object>();
        param.put("createBy", UserUtils.getUser().getBusinessId());
        param.put("flag", FlagStatus.NORMAL.getStatus());
        param.put("delFlag", DelStatus.NORMAL.getStatus());
        param.put("storyId",id);
        param.put("orderBy","story_date,update_date asc");
        return storyFragmentRepository.queryStoryFragments(param);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel persistStoryFragment(StoryFragment storyFragment) {
        storyFragment.setCommonBusinessId();
        UserUtils.setCurrentPersistOperation(storyFragment);
        this.moveStoryFragmentImage(storyFragment);
        int line = storyFragmentRepository.insertSelective(storyFragment);
        return new PersistModel(line);
    }

    @Override
    public StoryFragment queryFragmentById(String id) {

        return storyFragmentRepository.selectByPrimaryKey(id);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel mergeFragment(StoryFragment storyFragment) {
        UserUtils.setCurrentMergeOperation(storyFragment);
        this.moveStoryFragmentImage(storyFragment);
        int line = storyFragmentRepository.updateFragment(storyFragment);
        return new PersistModel(line);
    }

    /**
     * 转移 fragment image
     * @param storyFragment
     */
    private void moveStoryFragmentImage(StoryFragment storyFragment){
        //配置目录
        String abPath =  Global.getConfig("base_upload_location") ;
        String stPath = "/stories" + File.separator + UserUtils.getUser().getBusinessId()
                + File.separator + storyFragment.getStoryId() + File.separator
                + DateUtil.getSimepleDate("yyyy-MM-dd",new Date()) + File.separator;
        //图片从临时目录中移出
        if(StringUtils.isNotEmpty(storyFragment.getImageFirst())){
            File file = new File(abPath + storyFragment.getImageFirst());
            File dest = new File(abPath + stPath+file.getName());
            //有源图片,无目标图 防止修改操作 不改图片
            if(file.exists() && !dest.exists()){
                try {
                    FileUtils.moveFile(file,dest);
                    storyFragment.setImageFirst(stPath+file.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        //图片2
        if(StringUtils.isNotEmpty(storyFragment.getImageSecond())){
            File fileSecond = new File(abPath + storyFragment.getImageSecond());
            File destSecond = new File(abPath + stPath+fileSecond.getName());
            //有源图片,无目标图 防止修改操作 不改图片
            if(fileSecond.exists() && !destSecond.exists()){
                try {
                    FileUtils.moveFile(fileSecond,destSecond);
                    storyFragment.setImageSecond(stPath+fileSecond.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel removeFragment(String id) {
        StoryFragment storyFragment = new StoryFragment();
        storyFragment.setDeleted();
        storyFragment.setBusinessId(id);
        UserUtils.setCurrentMergeOperation(storyFragment);
        int line = storyFragmentRepository.deleteLogic(storyFragment);
        return new PersistModel(line);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel persistStory(Story story) {
        story.setCommonBusinessId();
        UserUtils.setCurrentPersistOperation(story);
        int line = storyRepository.insertSelective(story);
        return new PersistModel(line);
    }

    @Override
    public Story queryStoryById(String id) {
        return storyRepository.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel mergeStory(Story story) {
        UserUtils.setCurrentMergeOperation(story);
        int line = storyRepository.updateStory(story);
        return new PersistModel(line);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel removeStory(String id) {
        Story story = new Story();
        story.setDeleted();
        story.setBusinessId(id);
        UserUtils.setCurrentMergeOperation(story);
        int line = storyRepository.deleteLogic(story);
        return new PersistModel(line);
    }
}
