package com.terwergreen.middle.common.dao.impl;

import java.util.List;
import java.util.Map;

import com.terwergreen.framework.core.bg.app.integration.dao.BGDAOException;
import com.terwergreen.framework.core.bg.biz.dao.BaseDAO;
import com.terwergreen.middle.common.dao.CommonDAO;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Resource;

/**
 * *****************************************************
 * <br>数据库访问接口</br>
 * <br>功能描述：提供数据库增删改查 (应用于所有模块)</br>
 *
 * @author terwergreen
 * @version v1.0
 * *****************************************************
 * @ClassName：IBaseCommonDAO
 * @date 2018-03-31
 */
@Repository
public class CommonIbatisDAO extends BaseDAO implements CommonDAO {

    @Override
    public List queryList(String sql, String str) {
        return getSqlSession().selectList(sql, str);
    }

    @Override
    public List queryList(String sql, Map paraMap) {
        return getSqlSession().selectList(sql, paraMap);
    }

    @Override
    public List queryList(String sql, Object object) {
        return getSqlSession().selectList(sql, object);
    }

    @Override
    public List queryList(String sql, String str, int start, int pageSize) {
        pageSize = pageSize > MAX_ROW ? MAX_ROW : pageSize;
        try {
            return getSqlSession().selectList(sql, str, new RowBounds(start - 1, pageSize));
        } catch (DataAccessException e) {
            super.logger.error("分页查询发生异常：", e);
        }
        return null;
    }

    @Override
    public List queryList(String sql, Map paraMap, int start, int pageSize) {
        pageSize = pageSize > MAX_ROW ? MAX_ROW : pageSize;
        try {
            return getSqlSession().selectList(sql, paraMap, new RowBounds(start - 1, pageSize));
        } catch (DataAccessException e) {
            super.logger.error("分页查询发生异常：", e);
        }
        return null;
    }

    @Override
    public List queryList(String sql, Object object, int start, int pageSize) {
        pageSize = pageSize > MAX_ROW ? MAX_ROW : pageSize;
        try {
            return getSqlSession().selectList(sql, object, new RowBounds(start - 1, pageSize));
        } catch (DataAccessException e) {
            super.logger.error("分页查询发生异常：", e);
        }
        return null;
    }

    @Override
    public Object querySingle(String sql, String str) {
        List list = getSqlSession().selectList(sql, str);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Object querySingle(String sql, Map paraMap) {
        List list = getSqlSession().selectList(sql, paraMap);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Object querySingle(String sql, Object object) {
        List list = getSqlSession().selectList(sql, object);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Object insert(String sql, Map paraMap) {
        return getSqlSession().insert(sql, paraMap);
    }

    @Override
    public Object insert(String sql, Object object) {
        return getSqlSession().insert(sql, object);
    }

    @Override
    public int delete(String sql, Map paraMap) {
        return getSqlSession().delete(sql, paraMap);
    }

    @Override
    public int delete(String sql, Object object) {
        return getSqlSession().delete(sql, object);
    }

    @Override
    public boolean checkDelete(String sql, Map paraMap) {
        int row = this.delete(sql, paraMap);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkDelete(String sql, Object object) {
        int row = this.delete(sql, object);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int update(String sql, Map paraMap) {
        return getSqlSession().update(sql, paraMap);
    }

    @Override
    public int update(String sql, Object object) {
        return getSqlSession().update(sql, object);
    }

    @Override
    public boolean checkUpdate(String sql, Map paraMap) {
        int row = this.update(sql, paraMap);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUpdate(String sql, Object object) {
        int row = this.update(sql, object);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Integer updateBatch(final String sql, final List updateList) throws BGDAOException {
        try {
            throw new NotImplementedException();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Integer insertBatch(final String sql, final List insertList) throws BGDAOException {
        try {
            throw new NotImplementedException();
        } catch (Exception e) {
            throw e;
        }
    }

}
