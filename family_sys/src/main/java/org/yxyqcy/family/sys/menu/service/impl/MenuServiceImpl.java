package org.yxyqcy.family.sys.menu.service.impl;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.model.*;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.sys.menu.dao.MenuRepository;
import org.yxyqcy.family.sys.menu.entity.Menu;
import org.yxyqcy.family.sys.menu.service.MenuService;
import org.yxyqcy.family.sys.util.UserUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 16/7/30.
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
public class MenuServiceImpl extends BaseService implements MenuService {

    private static final long serialVersionUID = 1489358516758954490L;
    @Autowired
    private MenuRepository menuRepository;

    /**
     * 查询菜单树
     */
    @Override
    public List<TreeViewModel> queryMenuTree() {
        TreeViewModel treeRoot = null;
        List<Menu> MenuList = menuRepository.queryRootMenuTree();
        //返回集
        List<TreeViewModel> treeList = new ArrayList<TreeViewModel>();
        for(int i = 0; i < MenuList.size();i++){
            treeRoot = conversionMenu(MenuList.get(i));
            //查询子部门
            queryChildMenu(treeRoot);
            treeList.add(treeRoot);
        }
        return treeList;
    }


    /**
     * 添加菜单
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel persistMenu(Menu menu) {
        menu.setCommonBusinessId();
        //增加操作
        UserUtils.setCurrentPersistOperation(menu);
        int line = menuRepository.insertSelective(menu);
        return new PersistModel(line);
    }

    /**
     * 合并菜单
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel mergeMenu(Menu menu) {
        //修改操作
        UserUtils.setCurrentMergeOperation(menu);
        /*if("".equals(menu.getParentId()))
            menu.setParentId(null);*/
        int line = menuRepository.updateAppointedColumnByPrimaryKey(menu);
        return new PersistModel(line);
    }

    /**
     * 移除菜单
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel removeMenu(String id) {
        //int line = menuRepository.deleteByPrimaryKey(id);
        //逻辑删除
    	
    	int line= menuRepository.deleteRoleMenuByMenuId(id);
    	
        List<Menu> list = new ArrayList<Menu>();
        Menu menu = new Menu();
        menu.setBusinessId(id);
        UserUtils.setCurrentPersistOperation(menu);
        list.add(menu);
        //子节点
        list = this.queryChildMenuToList(id,list);
        line = menuRepository.logicRemoveMenus(list);
        return new PersistModel(line);
    }

    /**
     * 根据主键查菜单
     */
    @Override
    public Menu queryMenuById(String id) {

        return menuRepository.selectByPrimaryKey(id);
    }
    /**
     * 根据角色id，查询角色绑定的菜单
     */
    @Override
    public List<TreeViewModel> queryMenuTreeForSelectedByRP(String id) {
        TreeViewModel treeRoot = null;
        //根节点不能为checked
        List<Menu> MenuList = menuRepository.queryRootMenuTree();
        //返回集
        List<TreeViewModel> treeList = new ArrayList<TreeViewModel>();
        for(int i = 0; i < MenuList.size();i++){
            treeRoot = conversionMenu(MenuList.get(i));
            //查询子部门
            queryChildMenuByRP(id,treeRoot);
            treeList.add(treeRoot);
        }
        return treeList;
    }

    @Override
    public CommonMenuModel queryMenusListByModels(String model, String selectId) {
        List<Menu> userMenu = UserUtils.getMenuList();
        CommonMenuModel menuModel = null;
        //sys 后台
        if("sys".endsWith(model)){
            menuModel = new SysMenuModel();
            menuModel = constructModel(menuModel,userMenu,CommonMenuModel.MENU_DB_SYS_MODEL_ID,selectId);
            SysMenuModel menu = (SysMenuModel)menuModel;
            //设置 active
            this.setActiveMenu(menu,selectId,"active");
            return menuModel;
        }else if("homeBack".equals(model)){
            menuModel = new HomeMenuModel();
            menuModel = constructModel(menuModel,userMenu,CommonMenuModel.MENU_DB_HOME_MODEL_ID,selectId);
            HomeMenuModel menu = (HomeMenuModel)menuModel;
            //设置 active
            this.setActiveMenu(menu,selectId,"active");
        }

        return menuModel;
    }

    /**
     * 设置选中 active
     * @param model
     * @param activeClassName
     */
    private void setActiveMenu(CommonMenuModel model ,String selectId,String activeClassName){
        if(null == model)
            return;
        List<CommonMenuModel> modelList = model.getCommonModelList();
        if(CollectionUtils.isNotEmpty(modelList)){
            if(StringUtils.isEmpty(selectId))
                modelList.get(0).setIsActive("active");
            else{
                for(CommonMenuModel sys : modelList){
                    if(sys.getId().equals(selectId)){
                        sys.setIsActive("active");
                        break;
                    }
                }
            }

        }
    }
    /**
     * 构建模型
     * @param menuModel 菜单模型
     * @param userMenu  用户菜单
     * @param model     model ID
     * @param selectId  activiId
     */
    private CommonMenuModel constructModel(CommonMenuModel menuModel, List<Menu> userMenu, String model, String selectId) {
        if(null == userMenu || 0 == userMenu.size())
            return null;
        for(Menu menu : userMenu){
            //该模块
            if(model.equals(menu.getModelId()) && "1".equals(menu.getIsShow())){
                CommonMenuModel newModel = convertMenuModel(menuModel,menu);
                menuModel = integratedMenuModel(menuModel,newModel);
            }
        }
        return menuModel;

    }

    /**
     * 转换类型
     * @param menuModel
     * @param menu
     * @return
     */
    private CommonMenuModel convertMenuModel(CommonMenuModel menuModel,Menu menu){
        //强转
        if(menuModel instanceof SysMenuModel){
            SysMenuModel sys = new SysMenuModel();
            sys.setId(menu.getBusinessId());
            sys.setName(menu.getName());
            sys.setParentId(menu.getParentId());
            sys.setHref(menu.getHref());
            sys.setTarget(menu.getTarget());
            sys.setIcon(menu.getIcon());
            return sys;
        }else if (menuModel instanceof HomeMenuModel){
            HomeMenuModel home = new HomeMenuModel();
            home.setId(menu.getBusinessId());
            home.setName(menu.getName());
            home.setParentId(menu.getParentId());
            home.setHref(menu.getHref());
            home.setTarget(menu.getTarget());
            home.setIcon(menu.getIcon());
            return home;
        }
        return  menuModel;
    }

    /**
     * 集成
     * @param menuModel
     * @param newModel
     * @return
     */
    private CommonMenuModel integratedMenuModel(CommonMenuModel menuModel,CommonMenuModel newModel){
        //强转  sys
        if(menuModel instanceof SysMenuModel){
            SysMenuModel sys = (SysMenuModel) menuModel;
            //初始化 第一次进入
            if(null == sys.getId() || "".equals(sys.getId().trim()))
                menuModel = newModel;
            else{
                //子菜单
                if(sys.getId().equals(newModel.getModelParentId()))
                    sys.addSysMenuModel((SysMenuModel) newModel);
                else
                    sys.forInSysMenuModel(sys,(SysMenuModel) newModel);
            }
        }
        //强转  home
        else if(menuModel instanceof HomeMenuModel){
            HomeMenuModel home = (HomeMenuModel) menuModel;
            //初始化 第一次进入
            if(null == home.getId() || "".equals(home.getId().trim()))
                menuModel = newModel;
            else{
                //子菜单
                if(home.getId().equals(newModel.getModelParentId()))
                    home.addSysMenuModel((HomeMenuModel) newModel);
                /*else
                    sys.forInSysMenuModel(sys,(SysMenuModel) newModel);*/
            }
        }
        return  menuModel;
    }

    /**
     * 查询子菜单
     * @param treeRoot
     */
    private void queryChildMenu(TreeViewModel treeRoot) {
        List<Menu> MenuList = menuRepository.queryMenuTreeByParentId(
                treeRoot.getId());
        List<TreeViewModel> treeView = new ArrayList<TreeViewModel>();
        if(0 == MenuList.size())
            return;
        //赋值
        for(Menu Menu : MenuList) {
            TreeViewModel tree = conversionMenu(Menu);
            //递归
            queryChildMenu(tree);
            treeView.add(tree);
        }
        treeRoot.setNodes(treeView);
    }
    /**
     * 根据角色查询子菜单
     * @param treeRoot
     */
    private void queryChildMenuByRP(String roleId, TreeViewModel treeRoot) {
        List<Menu> MenuList = menuRepository.queryMenuTreeByParentIdRP(roleId,
                treeRoot.getId());
        List<TreeViewModel> treeView = new ArrayList<TreeViewModel>();
        if(0 == MenuList.size())
            return;
        //赋值
        for(Menu Menu : MenuList) {
            TreeViewModel tree = conversionMenu(Menu);
            //递归
            queryChildMenuByRP(roleId,tree);
            treeView.add(tree);
        }
        treeRoot.setNodes(treeView);
    }
    /**
     * 查询子菜单    复制集合
     * @param parentId   父部门id
     */
    private List<Menu> queryChildMenuToList(String parentId, List<Menu> list) {
        List<Menu> menuList = menuRepository.queryMenuTreeByParentId(
                parentId);
        if(0 == menuList.size())
            return list;

        list.addAll(menuList);
        //赋值
        for(Menu menu : menuList) {
            queryChildMenuToList(menu.getBusinessId(),list);
        }
        return list;
    }
    /**
     * 转换  Menu to  treeView
     * @param menu
     * @return
     */
    private TreeViewModel conversionMenu(Menu menu) {
        TreeViewModel treeView = new TreeViewModel();
        treeView.setId(menu.getBusinessId());
        treeView.setText(menu.getName());
        treeView.setPid(menu.getParentId());
        treeView.setParentId(menu.getParentId());
        treeView.setSelectable(true);
        treeView.setTags(new String[]{menu.getCode()});
        //配置选中
        if(null != menu.getChecked() && !"".equals(menu.getChecked())){
            TreeViewModel.TreeViewStateModel  treeViewStateModel
                    = new TreeViewModel().new TreeViewStateModel();
            if("1".equals(menu.getChecked()))
                treeViewStateModel.setChecked(true);
            else if ("0".equals(menu.getChecked()))
                treeViewStateModel.setChecked(false);
            treeView.setState(treeViewStateModel);
        }
        return treeView;
    }


}
