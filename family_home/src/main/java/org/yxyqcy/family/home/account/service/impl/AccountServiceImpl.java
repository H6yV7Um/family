package org.yxyqcy.family.home.account.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.home.account.dao.AccountRepository;
import org.yxyqcy.family.home.account.entity.Account;
import org.yxyqcy.family.home.account.service.AccountService;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/12/19
 * Time: 下午9:06
 * description:
 */
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl extends BaseService implements AccountService {

    private static final long serialVersionUID = -627072256255964317L;
    @Resource
    private AccountRepository accountRepository;

    @Override
    public List<Account> queryAccountsByPagination(PageUtil pageUtil, Account account) {
        Map param = new HashMap<String,Object>();
        param.put("createBy",account.getCreateBy());
        param.put("flag",account.getFlag());
        param.put("delFlag",account.getDelFlag());
        param.put("type",account.getType());
        param.put("title",account.getTitle());
        return accountRepository.queryAccounts(param);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel persistAccount(Account account) {
        account.setCommonBusinessId();
        UserUtils.setCurrentPersistOperation(account);
        account.setSeqDate(new Date());
        int line = accountRepository.insertSelective(account);
        return new PersistModel(line);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel mergeAccount(Account account) {
        UserUtils.setCurrentMergeOperation(account);
        int line = accountRepository.updateSelective(account);
        return new PersistModel(line);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel removeAccount(String id) {
        Account account = new Account();
        account.setBusinessId(id);
        account.setDeleted();
        UserUtils.setCurrentMergeOperation(account);
        int line = accountRepository.deleteLogic(account);
        return new PersistModel(line);
    }

    @Override
    public Account queryAccountById(String id) {
        return accountRepository.selectByPrimaryKey(id);
    }

    @Override
    public List<Map> statisticsCount(String type,String userId) {
        Map param = new HashMap<String,Object>();
        param.put("groupType",type);
        param.put("createBy",userId);
        return accountRepository.statistics(param);
    }
}
