package org.yxyqcy.family.sys.dictionary.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.common.util.IdGen;
import org.yxyqcy.family.sys.dictionary.dao.DictionaryRepository;
import org.yxyqcy.family.sys.dictionary.entity.Dictionary;
import org.yxyqcy.family.sys.dictionary.service.DictionaryService;
import org.yxyqcy.family.sys.util.UserUtils;

import java.util.List;

/**
 * @author Nannan
 * 数据字典  接口实现
 */
@Service
@Transactional(readOnly = true)
public class DictionaryServiceImpl extends BaseService implements DictionaryService {

	private static final long serialVersionUID = -434191748510758634L;
	@Autowired
	public DictionaryRepository dictionaryRepository;

	@Override
	public List<Dictionary> queryDictionaryByPagination(PageUtil pageUtil, Dictionary dictionary) {
		return dictionaryRepository.queryDictionaries(dictionary);
	}
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
	@Override
	public PersistModel persistDictionary(Dictionary param) {
		param.setCommonBusinessId();
		UserUtils.setCurrentPersistOperation(param);
        int line = dictionaryRepository.insertSelective(param);
        return new PersistModel(line);
	}

	@Override
	public Dictionary queryDictionaryById(String id) {
		return dictionaryRepository.selectByPrimaryKey(id);
	}

	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
	@Override
	public int updateDictionary(Dictionary param) {
		UserUtils.setCurrentMergeOperation(param);
		param.setDeleted();
		return dictionaryRepository.updateByPrimaryKeySelective(param);
	}

	@Override
	public List<Dictionary> selectAllType() {
		
		return dictionaryRepository.selectAllType();
	}

	public List<Dictionary> selectByType(String type) {
		
		return dictionaryRepository.selectAllByType(type);
	}


	
	
}
