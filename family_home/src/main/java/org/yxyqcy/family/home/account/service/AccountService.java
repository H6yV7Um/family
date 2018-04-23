package org.yxyqcy.family.home.account.service;

import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.home.account.entity.Account;

import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/12/19
 * Time: 下午9:03
 * description: account service
 */
public interface AccountService {
    /**
     * 分页查询帐号
     * @param pageUtil  分页组件
     * @param account   查询条件
     */
    public List<Account> queryAccountsByPagination(PageUtil pageUtil, Account account);

    /**
     * 插入帐号
     * @param account  博客
     */
    PersistModel persistAccount(Account account);

    /**
     * 修改帐号
     * @param account
     * @return
     */
    PersistModel mergeAccount(Account account);

    /**
     * 删除帐号
     * @param id
     * @return
     */
    PersistModel removeAccount(String id);

    /**
     * 根据id查询帐号
     * @param id
     * @return
     */
    Account queryAccountById(String id);

    /**
     * 统计数量
     * @param type group by
     * @return
     */
    List<Map> statisticsCount(String type,String userId);
}
