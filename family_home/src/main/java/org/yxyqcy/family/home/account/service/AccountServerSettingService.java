package org.yxyqcy.family.home.account.service;

import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.home.account.entity.AccountServerSetting;

import java.util.List;

/**
 * Created by lcy on 17/2/13.
 */
public interface AccountServerSettingService {
    /**
     * 根据server id 查询 配置
     * @param setting  配置
     */
    public List<AccountServerSetting> queryServerSettingsByServerId(AccountServerSetting setting);

    /**
     * 插入服务器配置
     * @param account  博客
     */
    PersistModel persistAccountServerSetting(AccountServerSetting account);

    /**
     * 删除服务器配置
     * @param accountId  setting
     */
    PersistModel removeAccountServerSetting(String accountId);

    /**
     * 修改服务器配置
     * @param param
     * @return
     */
    PersistModel mergeAccountServerSetting(AccountServerSetting param);
}
