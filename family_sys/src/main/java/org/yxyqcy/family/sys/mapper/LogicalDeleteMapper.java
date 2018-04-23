package org.yxyqcy.family.sys.mapper;

import org.apache.ibatis.annotations.UpdateProvider;
import org.yxyqcy.family.sys.mapper.impl.LogicalDeleteMapperImpl;

/**
 * Created by lcy on 17/11/16.
 */
public interface LogicalDeleteMapper<T> {
    /**
     * 逻辑删除
     * @param record
     * @return
     */
    @UpdateProvider(type = LogicalDeleteMapperImpl.class, method = "dynamicSQL")
    int logicalDelete(T record);
}
