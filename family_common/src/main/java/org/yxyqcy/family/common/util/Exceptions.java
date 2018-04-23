package org.yxyqcy.family.common.util;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/2
 * Time: 上午9:50
 * description:异常的工具类.
 */
public class Exceptions {



    /**
      * 将CheckedException转换为UncheckedException.
      */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }
}
