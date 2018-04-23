package org.yxyqcy.family.common.message;

import java.util.Map;

/**
 * Created with family.
 * User: cy
 * Date: 16/5/20
 * Time: 下午1:45
 * description:
 */
public class ValidatedMessage extends MainStatus implements ResponseMessage {
    private static final long serialVersionUID = -8037198481805394212L;
    private Map<String, String[]> validmessage;

    public ValidatedMessage() {
    }

    public Map<String, String[]> getValidmessage() {
        return this.validmessage;
    }

    public void setValidmessage(Map<String, String[]> validmessage) {
        this.validmessage = validmessage;
    }

    public String getMapperKey() {
        return "validmessage";
    }
}
