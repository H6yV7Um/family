package org.yxyqcy.family.common.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.CommonPageGridModel;
import org.yxyqcy.family.common.model.GridModel;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/16
 * Time: 上午10:52
 * description: 分页初始化
 */

public class PaginationController<T> extends ResponseController {

    protected static final String PAGINATIONKEY = "pagination";

    public PaginationController() {
    }

    public PageUtil<T> getPaginationUtility() {
        PageUtil paginationUtility = (PageUtil)request.getAttribute(PAGINATIONKEY);
        if(paginationUtility == null) {
            //throw new NullPointerException("未传递分页参数（page&rows），却在方法中使用paginationUtility对象。");
            return null;
        } else {
            return paginationUtility;
        }
    }

    @ModelAttribute
    private void setPagnationAttribute(HttpServletRequest request, Integer rows, Integer page, String sort) {
        /*10-3号改 = new PageUtil(); 可实现分页or不分页 共用*/
        PageUtil _paginationUtility  = new PageUtil();
        if(rows != null && page != null && sort != null) {
            _paginationUtility.setPageNum(page.intValue());
            _paginationUtility.setPageSize(rows.intValue());
            _paginationUtility.setOrderBy(sort);
        }

        else if(rows != null && page != null) {
            _paginationUtility.setPageNum(page.intValue());
            _paginationUtility.setPageSize(rows.intValue());
        }
        /*10-3号改 = new PageUtil(); 不分页 又 sort
         */
        else if(null != sort){
            _paginationUtility.setOrderBy(sort);
        }
        request.setAttribute(PAGINATIONKEY, _paginationUtility);
    }

    /**
     * get gridMoel
     * @return
     */
    public GridModel getGridModelResponse(){
        PageUtil pagination = getPaginationUtility();
        if(null != getPaginationUtility())
            return pagination.getGridModel();
        else{
            return new CommonPageGridModel();
        }
    }
}
