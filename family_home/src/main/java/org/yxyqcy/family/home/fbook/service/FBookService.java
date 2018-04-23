package org.yxyqcy.family.home.fbook.service;

import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.home.fbook.entity.FBook;

import java.util.List;
import java.util.Map;

/**
 * Created by yxyqcy on 2017-11-13 23:12:26.
 */
public interface FBookService{
    /**
    * 根据主键唯一查找
    * @param businessId
    * @return
    */
    FBook selectOne(String businessId);

    /**
    * 根据条件分页查找
    * @param pageUtil
    * @param model
    * @return
    */
    List<FBook> queryFBooksByPagination(PageUtil pageUtil, FBook model);

    /**
    * 根据条件查找全部
    * @param model
    * @return
    */
    List<FBook> queryFBooks(FBook model);

    /**
    * 插入
    * @param model
    * @return
    */
    PersistModel persistFbook(FBook model);

    /**
    * 逻辑删除
    * @param id
    * @return
    */
    PersistModel removeFBook(String id);

    /**
     * 浏览book
     * @param id
     * @return
     */
    List<String> browseBookById(String id);


    /**
     * 根据id 查询 book
     * @param id
     * @return
     */
    FBook queryFBookById(String id);

    /**
     * 统计
     * @return
     */
    List<Map> staticBookCountByClassify();

    /**
     * 置顶
     * @param book
     * @return
     */
    PersistModel topBlog(FBook book);

    /**
     * 公开
     * @param book
     * @return
     */
    PersistModel publicBook(FBook book);

    /**
     * 更改book selective
     * @param fBook
     * @return
     */
    PersistModel updateBookStateSelective(FBook fBook);
}
