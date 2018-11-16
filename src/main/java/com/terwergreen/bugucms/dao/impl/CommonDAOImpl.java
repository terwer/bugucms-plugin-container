package com.terwergreen.bugucms.dao.impl;

import com.terwergreen.bugucms.dao.CommonDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class CommonDAOImpl implements CommonDAO {
    private final Log logger = LogFactory.getLog(getClass());
    private static final int MAX_ROW = 9999;

    @Resource
    private SqlSession sqlSession;

    @Override
    public List queryList(String sql) {
        return sqlSession.selectList(sql);
    }

    @Override
    public List queryListByString(String sql, String str) {
        return sqlSession.selectList(sql, str);
    }

    @Override
    public List queryListByMap(String sql, Map paraMap) {
        return sqlSession.selectList(sql, paraMap);
    }

    @Override
    public List queryListByObject(String sql, Object object) {
        return sqlSession.selectList(sql, object);
    }

    @Override
    public List queryPageList(String sql, Map paraMap, int start, int pageSize) {
        pageSize = pageSize > MAX_ROW ? MAX_ROW : pageSize;
        try {
            return sqlSession.selectList(sql, paraMap, new RowBounds(start - 1, pageSize));
        } catch (DataAccessException e) {
            logger.error("分页查询发生异常：", e);
        }
        return null;
    }

    @Override
    public List queryPageListByString(String sql, String str, int start, int pageSize) {
        pageSize = pageSize > MAX_ROW ? MAX_ROW : pageSize;
        try {
            return sqlSession.selectList(sql, str, new RowBounds(start - 1, pageSize));
        } catch (DataAccessException e) {
            logger.error("分页查询发生异常：", e);
        }
        return null;
    }

    @Override
    public List queryPageListByMap(String sql, Map paraMap, int start, int pageSize) {
        pageSize = pageSize > MAX_ROW ? MAX_ROW : pageSize;
        try {
            return sqlSession.selectList(sql, paraMap, new RowBounds(start - 1, pageSize));
        } catch (DataAccessException e) {
            logger.error("分页查询发生异常：", e);
        }
        return null;
    }

    @Override
    public List queryPageListByObject(String sql, Object object, int start, int pageSize) {
        pageSize = pageSize > MAX_ROW ? MAX_ROW : pageSize;
        try {
            return sqlSession.selectList(sql, object, new RowBounds(start - 1, pageSize));
        } catch (DataAccessException e) {
            logger.error("分页查询发生异常：", e);
        }
        return null;
    }

    @Override
    public Object querySingleByString(String sql) {
        List list = sqlSession.selectList(sql);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Object querySingleByMap(String sql, Map paraMap) {
        List list = sqlSession.selectList(sql, paraMap);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Object querySingleByString(String sql, String str) {
        List list = sqlSession.selectList(sql, str);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Object querySingleByObject(String sql, Object object) {
        List list = sqlSession.selectList(sql, object);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Object insert(String sql, Map paraMap) {
        return sqlSession.insert(sql, paraMap);
    }

    @Override
    public Object insertByObject(String sql, Object object) {
        return sqlSession.insert(sql, object);
    }

    @Override
    public int delete(String sql, Map paraMap) {
        return sqlSession.delete(sql, paraMap);
    }

    @Override
    public int deleteByObject(String sql, Object object) {
        return sqlSession.delete(sql, object);
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
    public boolean checkDeleteByObject(String sql, Object object) {
        int row = this.deleteByObject(sql, object);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int update(String sql, Map paraMap) {
        return sqlSession.update(sql, paraMap);
    }

    @Override
    public int updateByObject(String sql, Object object) {
        return sqlSession.update(sql, object);
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
    public boolean checkUpdateByObject(String sql, Object object) {
        int row = this.updateByObject(sql, object);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Integer insertBatch(String sql, List insertList) {
        throw new NotImplementedException();
    }

    @Override
    public Integer updateBatch(String sql, List updateList) {
        throw new NotImplementedException();
    }
}
