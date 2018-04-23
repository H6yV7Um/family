package org.yxyqcy.family.sys.log.service;


import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.sys.log.entity.Log;

import java.util.List;

/**
 * 日志管理service接口
 * @author yangxuenan
 */
public interface LogService {
	
	/**
     * 分页查询
     * @param pageUtil  分页组件
     * @param log   查询条件
     */
    public List<Log> queryLogByPagination(PageUtil pageUtil, Log log);


}
