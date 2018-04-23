package org.yxyqcy.family.home.account.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.home.account.dao.AccountServerSettingRepository;
import org.yxyqcy.family.home.account.entity.AccountServerSetting;
import org.yxyqcy.family.home.account.service.AccountServerSettingService;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/2/13.
 */
@Service
@Transactional(readOnly = true)
public class AccountServerSettingServiceImpl extends BaseService implements AccountServerSettingService{
    private static final long serialVersionUID = -5352386368894627471L;

    @Resource
    private AccountServerSettingRepository accountServerSettingRepository;

    @Override
    public List<AccountServerSetting> queryServerSettingsByServerId(AccountServerSetting setting) {
        Map param = new HashMap<String,Object>();
        param.put("serverId",setting.getServerId());
        return accountServerSettingRepository.queryServerSettingsByServerId(param);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel persistAccountServerSetting(AccountServerSetting account) {
        account.setCommonBusinessId();
        UserUtils.setCurrentPersistOperation(account);
        account.setSeqDate(new Date());
        int line = accountServerSettingRepository.insertSelective(account);
        return new PersistModel(line);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel removeAccountServerSetting(String id) {
        //真删除
        int line = accountServerSettingRepository.deleteByPrimaryKey(id);
        return new PersistModel(line);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel mergeAccountServerSetting(AccountServerSetting param) {

        UserUtils.setCurrentMergeOperation(param);
        param.setSeqDate(new Date());
        int line = accountServerSettingRepository.updateSelective(param);
        return new PersistModel(line);
    }
}
