package com.terwergreen.bugucms.base.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;

/**
 * @Author Terwer
 * @Date 2018/7/2 15:07
 * @Version 1.0
 * @Description 注入sqlSessionFactory
 **/
public class BaseDAO extends SqlSessionDaoSupport {

    protected final Log logger = LogFactory.getLog(getClass());

    @Resource
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
}
