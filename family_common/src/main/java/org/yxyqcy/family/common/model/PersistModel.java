package org.yxyqcy.family.common.model;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/22
 * Time: 上午9:13
 * description:
 */
public class PersistModel {

    public PersistModel(int influence) {
        this.influence = influence;
    }

    private int influence;

    private String influenceReason = "";

    public String getInfluenceReason() {
        return influenceReason;
    }



    public PersistModel(int influence, String influenceReason) {
        this.influence = influence;
        this.influenceReason = influenceReason;
    }

    /**
     * 单一插入是否成功
     * @return  是否成功
     */
    public boolean isSuccessBySinglePersist(){
        if(1 <= influence)
            return true;
        return false;
    }
}
