package org.yxyqcy.family.home.story.service;

import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.home.story.entity.Story;
import org.yxyqcy.family.home.story.entity.StoryFragment;

import java.util.List;

/**
 * Created by lcy on 17/3/16.
 */
public interface StoryService {
    /**
     * 分页查询 story
     * @param paginationUtility
     * @param param
     * @return
     */
    List<Story> queryStoriesByPagination(PageUtil<Story> paginationUtility, Story param);

    /**
     * 分页查询 story 判断
     * @param paginationUtility
     * @param param
     * @return
     */
    List<StoryFragment> queryStoryFragmentsByPagination(PageUtil<Story> paginationUtility, StoryFragment param);

    /**
     * 不分页查询 story
     * @param id
     * @return
     */
    List<StoryFragment> queryStoryFragments(String id);

    /**
     * persist 故事片段
     * @param param
     * @return
     */
    PersistModel persistStoryFragment(StoryFragment param);

    /**
     * 根据id查询 fragment
     * @param id
     * @return
     */
    StoryFragment queryFragmentById(String id);

    /**
     * merge 故事片段
     * @param account
     * @return
     */
    PersistModel mergeFragment(StoryFragment account);

    /**
     * 逻辑删除 片段
     * @param id
     * @return
     */
    PersistModel removeFragment(String id);
    /**
     * story model
     */

    /**
     * persist story
     * @param param
     * @return
     */
    PersistModel persistStory(Story param);

    /**
     * query story
     * @param id
     * @return
     */
    Story queryStoryById(String id);

    /**
     * merge story
     * @param story
     * @return
     */
    PersistModel mergeStory(Story story);

    /**
     * logic remove story
     * @param id
     * @return
     */
    PersistModel removeStory(String id);
}
