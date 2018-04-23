package org.yxyqcy.family.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lcy on 17/1/16.
 */
public interface CommonMenuModel extends Serializable {

    //sys model ID
    public static final String MENU_DB_SYS_MODEL_ID = "20";
    //home model ID
    public static final String MENU_DB_HOME_MODEL_ID = "21";

    /**
     * 获取  父模型id
     * @return
     */
    public String  getModelParentId();

    /**
     * 设置选中 active
     */
    public void setIsActive(String active);

    /**
     *
     * @return
     */
    public String  getId();

    /**
     * 获取子 common menu
     * @return
     */
    public List<CommonMenuModel> getCommonModelList();
}
