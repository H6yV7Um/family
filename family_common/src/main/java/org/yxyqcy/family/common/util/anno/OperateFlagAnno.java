package org.yxyqcy.family.common.util.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by lcy on 17/11/16.
 */

@Target({FIELD})
@Retention(RUNTIME)
public @interface OperateFlagAnno {

    /**
     * jdbcType
     * @return
     */
    public String jdbcTypeName() default "";
}
