package org.yxyqcy.family.common.message;

import java.io.Serializable;

/**
 * Created with family_common
 * User: cy
 * Date: 16/5/20
 * Time: 下午1:43
 * description:
 */
public interface ResponseMessage extends Serializable {
    String getMapperKey();
}
