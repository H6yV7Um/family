package org.yxyqcy.family.sys.dictionary.service;


import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.sys.dictionary.entity.Dictionary;

import java.util.List;

/**
 * @author yangxuenan
 * 数据字典service
 */
public interface DictionaryService {
    
	 /**
     * 分页查询
     * @param pageUtil  分页组件
     * @param dictionary   查询条件
     */
    public List<Dictionary> queryDictionaryByPagination(PageUtil pageUtil, Dictionary dictionary);

    /**
     * 插入user
     * @param param  user
     */
    PersistModel persistDictionary(Dictionary param);
    

    /**
     * 根据用户id查询
     * @param id
     * @return
     */
    Dictionary queryDictionaryById(String id);
    
    /**
     * updateByPkSeletive
     * @param param
     * @return
     */
    int updateDictionary(Dictionary param);
    
    /**
     * 查出所有type
     * @return
     */
    List<Dictionary> selectAllType();
    
    /**
     * 按类型查出数据
     * @return
     */
    List<Dictionary> selectByType(String type);

}
