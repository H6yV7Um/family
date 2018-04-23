package org.yxyqcy.family.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/2
 * Time: 上午10:09
 * description:  基础  service
 */
public abstract class BaseService implements Serializable{

    private static final long serialVersionUID = -3526613932075436338L;
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());


}
