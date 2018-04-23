package org.yxyqcy.family.common.model;

/**
 * Created by lcy on 17/1/16.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台菜单模型
 */
public class SysMenuModel implements CommonMenuModel{


    private static final long serialVersionUID = 6069722005250998708L;
    private String id;
    private String parentId;
    private String isActive;  //是否选中  选中active  未选中
    private String name;
    private String href;
    private String target;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private String icon;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public SysMenuModel() {
    }


    private List<SysMenuModel> backgroundMenuModelList = new ArrayList<SysMenuModel>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SysMenuModel> getBackgroundMenuModelList() {
        return backgroundMenuModelList;
    }

    public void setBackgroundMenuModelList(List<SysMenuModel> backgroundMenuModelList) {
        this.backgroundMenuModelList = backgroundMenuModelList;
    }

    @Override
    public String getModelParentId() {
        return getParentId();
    }

    /**
     * add  sys model
     * @param model
     */
    public void addSysMenuModel(SysMenuModel model){
        if(null == this.getBackgroundMenuModelList()){
            this.setBackgroundMenuModelList(new ArrayList<SysMenuModel>());
        }
        this.getBackgroundMenuModelList().add(model);
    }

    /**
     * 循环遍历  找到父节点
     * @param newModel
     */
    public void forInSysMenuModel(SysMenuModel sys,SysMenuModel newModel) {
        for (SysMenuModel model:sys.getBackgroundMenuModelList()) {
            if(model.getId().equals(newModel.getParentId())){
                model.addSysMenuModel(newModel);
                break;
            }else{
                if(null != model.getBackgroundMenuModelList() && 0 < model.getBackgroundMenuModelList().size())
                    forInSysMenuModel(model,newModel);
            }
        }
    }



    @JsonIgnore
    @Override
    public List<CommonMenuModel> getCommonModelList() {
        if(CollectionUtils.isEmpty(this.getBackgroundMenuModelList()))
            return null;
        List<CommonMenuModel> models = new ArrayList<CommonMenuModel>();
        for (SysMenuModel home :  this.getBackgroundMenuModelList()) {
            models.add(home);
        }
        return models;
    }


}
