package org.yxyqcy.family.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 17/1/22.
 */
public class HomeMenuModel implements CommonMenuModel {

    private static final long serialVersionUID = 492500109809496357L;

    private String id;
    private String parentId;
    private String isActive;  //是否选中  选中active  未选中
    private String name;
    private String href;
    private String target;
    private String icon;

    public String getId() {
        return id;
    }
    @JsonIgnore
    @Override
    public List<CommonMenuModel> getCommonModelList() {
        if(CollectionUtils.isEmpty(this.getHomeMenuModelList()))
            return null;
        List<CommonMenuModel> models = new ArrayList<CommonMenuModel>();
        for (HomeMenuModel home :  this.getHomeMenuModelList()) {
            models.add(home);
        }
        return models;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String getModelParentId() {
        return getParentId();
    }

    private List<HomeMenuModel> homeMenuModelList;

    public List<HomeMenuModel> getHomeMenuModelList() {
        return homeMenuModelList;
    }

    public void setHomeMenuModelList(List<HomeMenuModel> homeMenuModelList) {
        this.homeMenuModelList = homeMenuModelList;
    }

    /**
     * add  home model
     * @param model
     */
    public void addSysMenuModel(HomeMenuModel model){
        if(null == this.getHomeMenuModelList()){
            this.setHomeMenuModelList(new ArrayList<HomeMenuModel>());
        }
        this.getHomeMenuModelList().add(model);
    }
}
