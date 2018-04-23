package org.yxyqcy.family.home.rtopic.service;

import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.home.rtopic.entity.RTopic;
import org.yxyqcy.family.sys.user.entity.User;

import java.util.List;

/**
 * Created by licy on 2017-9-29 19:56:42.
 */
public interface RTopicService{
    /**
    * 根据主键唯一查找
    * @param businessId
    * @return
    */
    RTopic selectById(String businessId);

    /**
    * 根据条件分页查找
    * @param pageUtil
    * @param model
    * @return
    */
    List<RTopic> queryRTopicsByPagination(PageUtil pageUtil, RTopic model);

    /**
    * 根据条件查找全部
    * @param model
    * @return
    */
    List<RTopic> queryRTopics(RTopic model);

    /**
    * 插入
    * @param model
    * @return
    */
    PersistModel persistRtopic(RTopic model);

    /**
     * 修改 rtopic
     * @param model
     * @return
     */
    PersistModel mergeRtopic(RTopic model);

    /**
    * 逻辑删除
    * @param id
    * @return
    */
    PersistModel removeRTopic(String id);

    /**
     * 查询users by topic
     * @param model
     * @return
     */
    List<User> queryUsersByRTopicsByPagination(PageUtil pageUtil, RTopic model);
}
