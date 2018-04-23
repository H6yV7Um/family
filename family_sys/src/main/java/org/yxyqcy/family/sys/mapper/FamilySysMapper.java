package org.yxyqcy.family.sys.mapper;

import tk.mybatis.mapper.common.Mapper;

/**
 * Created by lcy on 17/11/16.
 *
 * 通用mapper
 */
public interface FamilySysMapper<T> extends Mapper<T>,LogicalDeleteMapper<T> {
}
