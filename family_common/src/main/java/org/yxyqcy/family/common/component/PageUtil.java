package org.yxyqcy.family.common.component;


import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.yxyqcy.family.common.model.CommonPageGridModel;
import org.yxyqcy.family.common.model.GridModel;
import org.yxyqcy.family.common.model.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @descript page component
 * @author cy
 * Created by cy on 16/5/20.
 */
@Component
public class PageUtil<T> extends PageInfo<T> implements Serializable{


    private static final long serialVersionUID = 1790431977860519908L;
    private GridModel gridModel;

    public GridModel getGridModel() {
        return gridModel;
    }

    public void setGridModel(GridModel gridModel) {
        this.gridModel = gridModel;
    }

    public PageUtil() {

    }


    private void setPageInfo(PageUtil<T> paginationUtility, PageInfo<T> pageInfo) {
        if(null == pageInfo || paginationUtility == null)
            return;
        paginationUtility.setEndRow(pageInfo.getEndRow());
        paginationUtility.setFirstPage(pageInfo.getFirstPage());
        paginationUtility.setHasNextPage(pageInfo.isHasNextPage());
        paginationUtility.setHasPreviousPage(pageInfo.isHasPreviousPage());
        paginationUtility.setIsFirstPage(pageInfo.isIsFirstPage());
        paginationUtility.setIsLastPage(pageInfo.isIsLastPage());
        paginationUtility.setLastPage(pageInfo.getLastPage());
        paginationUtility.setNavigatepageNums(pageInfo.getNavigatepageNums());
        paginationUtility.setNavigatePages(pageInfo.getNavigatePages());
        paginationUtility.setNextPage(pageInfo.getNextPage());
        paginationUtility.setOrderBy(pageInfo.getOrderBy());
        paginationUtility.setPageNum(pageInfo.getPageNum());
        paginationUtility.setPages(pageInfo.getPages());
        paginationUtility.setPageSize(pageInfo.getPageSize());
        paginationUtility.setPrePage(pageInfo.getPrePage());
        paginationUtility.setSize(pageInfo.getSize());
        paginationUtility.setStartRow(pageInfo.getStartRow());
        paginationUtility.setList(pageInfo.getList());
        paginationUtility.setTotal(pageInfo.getTotal());
        paginationUtility.setGridModel(new CommonPageGridModel(paginationUtility));
    }

    /**
     * 环绕aop   service.*ByPagination    分页拦截
     * @param proceedingJoinPoint
     * @return
     */
    public List<T> preparedPageHeplerAndloadingPageInfoSetResults(ProceedingJoinPoint proceedingJoinPoint) {
        PageUtil paginationUtility = (PageUtil)proceedingJoinPoint.getArgs()[0];
        /*需要分页*/
        if(null != paginationUtility && 0 != paginationUtility.getPageNum() && 0 != paginationUtility.getPageSize()){
            /*需要排序*/
            if(!StringUtils.isBlank(paginationUtility.getOrderBy()))
                PageHelper.startPage(paginationUtility.getPageNum(),paginationUtility.getPageSize(),paginationUtility.getOrderBy());
            else
                PageHelper.startPage(paginationUtility.getPageNum(),paginationUtility.getPageSize());
        }
        else if(null != paginationUtility && !StringUtils.isBlank(paginationUtility.getOrderBy()))
            PageHelper.orderBy(paginationUtility.getOrderBy());
        try {
            List e = (List)proceedingJoinPoint.proceed();
            PageInfo pageInfo = new PageInfo(e);
            this.setPageInfo(paginationUtility, pageInfo);
            return e;
        } catch (Throwable var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public List<T> getList() {
        return super.getList();
    }
}
