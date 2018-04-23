package org.yxyqcy.family.sys.log.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yxyqcy.family.common.controller.PaginationController;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.sys.log.entity.Log;
import org.yxyqcy.family.sys.log.service.LogService;

/**
 * 日志管理
 * @author yangxuenan
 *
 */
@Controller
@RequestMapping("/log")
public class LogController extends PaginationController<Log> {
	

	@Autowired
	public LogService logService;
	
	/**
     * 分页查询
     * @param log
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:user:view")
    @RequestMapping("queryLog")
    public GridModel queryLog(Log log){
    	logService.queryLogByPagination(getPaginationUtility(), log);
        GridModel gridModel = getGridModelResponse();
        return  gridModel;
    }

}
