package org.yxyqcy.family.home.rtopic.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.common.util.IdGen;
import org.yxyqcy.family.home.rtopic.dao.RTopicRepository;
import org.yxyqcy.family.home.rtopic.entity.RTopic;
import org.yxyqcy.family.home.rtopic.service.RTopicService;
import org.yxyqcy.family.sys.user.entity.User;
import org.yxyqcy.family.sys.util.UserUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by licy on 2017-9-29 19:56:42.
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
public class RTopicServiceImpl extends BaseService implements RTopicService{
    private static final long serialVersionUID = 6455150705651994126L;
    @Autowired
    private RTopicRepository rTopicRepository;

    @Override
    public RTopic selectById(String businessId){
        return rTopicRepository.selectByPrimaryKey(businessId);
    }

    @Override
    public List<RTopic> queryRTopicsByPagination(PageUtil pageUtil, RTopic model) {
        Map param = new HashMap<String,Object>();
        param.put("user",model.getUser());
        param.put("isBlogCount",model.getIsBlogCount());
        return rTopicRepository.queryRTopics(param);
    }

    @Override
    public List<User> queryUsersByRTopicsByPagination(PageUtil pageUtil, RTopic model){
        Map param = new HashMap<String,Object>();
        param.put("businessId",model.getBusinessId());
        param.put("flag",model.getFlag());
        param.put("delFlag",model.getDelFlag());
        return rTopicRepository.queryUsersByRtopic(model);
    }


    @Override
    public List<RTopic> queryRTopics(RTopic model) {
        Map param = new HashMap<String,Object>();
        return rTopicRepository.queryRTopics(param);
    }


    @Override
    public PersistModel persistRtopic(RTopic model){
        model.setCommonBusinessId();
        UserUtils.setCurrentPersistOperation(model);
        int line = rTopicRepository.insertSelective(model);
        this.insertRtopicUser(model,false);
        return new PersistModel(line);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel mergeRtopic(RTopic model){
        UserUtils.setCurrentMergeOperation(model);
        int line = rTopicRepository.updateRtopic(model);
        this.insertRtopicUser(model,true);
        return new PersistModel(line);
    }



    /**
     * 插入 rtopic user
     * @param rTopic
     * @param clear
     */
    private void insertRtopicUser(RTopic rTopic, boolean clear){
        if(clear)
            rTopicRepository.deleteRtopicUsers(rTopic.getBusinessId());
        /*blog */
        if(!StringUtils.isBlank(rTopic.getRtopicUsersAdd())){
            String[] blogs = rTopic.getRtopicUsersAdd().split(",");
            List<Map> rtopicUser = new ArrayList<Map>();
            Map map = null;
            for (int i = 0 ; i < blogs.length ; i++){
                map = new HashMap();
                map.put("id", IdGen.uuid());
                map.put("rtopicId",rTopic.getBusinessId());
                map.put("userId",blogs[i]);
                rtopicUser.add(map);
            }
            rTopicRepository.insertBatchRtopicUser(rtopicUser);
        }

    }



    @Override
    public PersistModel removeRTopic(String id) {
        RTopic rTopic = new RTopic();
        rTopic.setBusinessId(id);
        rTopic.setDeleted();
        UserUtils.setCurrentMergeOperation(rTopic);
        int line = rTopicRepository.updateByPrimaryKeySelective(rTopic);
        rTopicRepository.deleteRtopicUsers(rTopic.getBusinessId());
        return new PersistModel(line);
    }



}
