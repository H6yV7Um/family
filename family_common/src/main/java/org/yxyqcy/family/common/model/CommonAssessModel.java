package org.yxyqcy.family.common.model;

/**
 * Created by lcy on 17/6/21.
 */
public enum CommonAssessModel {

    ASSESS_WAIT("0","#27a0c9"),ASSESS_PASS("2","#4eaa4c"),ASSESS_REJECT("1","#dd514c");

    private String assessFlag;

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAssessFlag() {
        return assessFlag;
    }

    public void setAssessFlag(String assessFlag) {
        this.assessFlag = assessFlag;
    }

    CommonAssessModel(String flag,String color) {
        assessFlag = flag;this.color = color;
    }

    /**
     * 根据flag 返回 assessModel
     * @param flag
     * @return
     */
    public static CommonAssessModel queryAssessModelByFlag(String flag){

        if(null == flag)
            return null;
        if(flag.equals(ASSESS_WAIT.getAssessFlag()))
            return ASSESS_WAIT;
        else if(flag.equals(ASSESS_PASS.getAssessFlag()))
            return ASSESS_PASS;
        else if(flag.equals(ASSESS_REJECT.getAssessFlag()))
            return ASSESS_REJECT;
        return null;
    }

}
