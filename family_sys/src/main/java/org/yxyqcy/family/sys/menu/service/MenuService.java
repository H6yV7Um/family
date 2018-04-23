package org.yxyqcy.family.sys.menu.service;



import org.yxyqcy.family.common.model.CommonMenuModel;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.model.TreeViewModel;
import org.yxyqcy.family.sys.menu.entity.Menu;

import java.util.List;

/**
 * Created by lcy on 16/7/30.
 */
public interface MenuService {
    /**
     * 查询 Menu 根据树
     * @return
     */
    public List<TreeViewModel> queryMenuTree();

    /**
     * 保存部门
     * @param Menu
     * @return
     */
    PersistModel persistMenu(Menu Menu);

    /**
     * 修改部门
     * @param Menu
     * @return
     */
    PersistModel mergeMenu(Menu Menu);

    /**
     * 删除部门
     * @param id
     * @return
     */
    PersistModel removeMenu(String id);

    /**
     * 根据id 查询 menu
     * @param id
     * @return
     */
    Menu queryMenuById(String id);

    /**
     * 获取全部菜单(角色已拥有的进行选中)
     * @param id    role id
     * @return
     */
    List<TreeViewModel> queryMenuTreeForSelectedByRP(String id);

    /**
     * 查询登录用户 菜单
     * @param model
     * @param selectId
     * @return
     */
    CommonMenuModel queryMenusListByModels(String model, String selectId);
}
