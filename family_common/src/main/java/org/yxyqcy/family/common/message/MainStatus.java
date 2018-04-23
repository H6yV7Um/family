package org.yxyqcy.family.common.message;

/**
 * Created with family.
 * User: cy
 * Date: 16/5/20
 * Time: 下午1:47
 * description:
 */
public class MainStatus implements ResponseMessage {
    private static final long serialVersionUID = -7441079017861505376L;
    private String status;

    public MainStatus() {
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMapperKey() {
        return "mainstatus";
    }
}