package org.yxyqcy.family.sys.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.sys.log.dao.LogRepository;
import org.yxyqcy.family.sys.log.entity.Log;
import org.yxyqcy.family.sys.log.service.LogService;

import java.util.List;

/**
 * 日志管理service实现
 * @author Nannan
 *
 */
@Service
@Transactional(readOnly = true)
public class LogServiceImpl extends BaseService implements LogService {
	
	@Autowired
	public LogRepository logRepository;

	@Override
	public List<Log> queryLogByPagination(PageUtil pageUtil, Log log) {
		return logRepository.queryLogByPagination(log);
	}

}
