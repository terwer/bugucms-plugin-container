package com.terwergreen.bugucms.core.dao;

import com.terwergreen.core.dao.BGDAOException;

import java.util.List;
import java.util.Map;

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
@SuppressWarnings({ "rawtypes" })
public interface CommonDAO {
    /**
     * 查询最大记录数
     */
    int MAX_ROW = 9999;

    /**
     * 查询列表
     *
     * @param sql
     * @param str
     * @return List
     */
    List queryListByString(String sql, String str);

    /**
     * 查询列表
     *
     * @param sql
     * @param paraMap
     * @return List
     */
    List queryList(String sql, Map paraMap);

    /**
     * 查询列表
     *
     * @param sql
     * @param object
     * @return List
     */
    List queryListByObject(String sql, Object object);

    /**
     * 分页查询
     *
     * @param sql
     * @param str      查询条件
     * @param start    起始条目
     * @param pageSize 每页显示条目
     * @return
     */
    List queryPageListByString(String sql, String str, int start, int pageSize);

    /**
     * 分页查询
     *
     * @param sql
     * @param paraMap  查询条件
     * @param start    起始条目
     * @param pageSize 每页显示条目
     * @return
     */
    List queryPageList(String sql, Map paraMap, int start, int pageSize);

    /**
     * 分页查询
     *
     * @param sql
     * @param object   查询条件
     * @param start    起始条目
     * @param pageSize 每页显示条目
     * @return
     */
    List queryPageListByObject(String sql, Object object, int start, int pageSize);

    /**
     * 查询单个信息
     *
     * @param sql
     * @param str
     * @return Object
     */
    Object querySingleByString(String sql, String str);

    /**
     * 查询单个信息
     *
     * @param sql
     * @param paraMap
     * @return Object
     */
    Object querySingle(String sql, Map paraMap);

    /**
     * 查询单个信息
     *
     * @param sql
     * @param object
     * @return Object
     */
    Object querySingleByObject(String sql, Object object);

    /**
     * 新增
     *
     * @param sql
     * @param paraMap
     * @return Object
     */
    Object insert(String sql, Map paraMap);

    /**
     * 新增
     *
     * @param sql
     * @param object
     * @return Object
     */
    Object insertByObject(String sql, Object object);

    /**
     * 删除
     *
     * @param sql
     * @param paraMap
     * @return int
     */
    int delete(String sql, Map paraMap);

    /**
     * 删除
     *
     * @param sql
     * @param object
     * @return int
     */
    int deleteByObject(String sql, Object object);

    /**
     * 检核删除是否成功
     *
     * @param sql
     * @param paraMap
     * @return true:成功  false:失败
     */
    boolean checkDelete(String sql, Map paraMap);

    /**
     * 检核删除是否成功
     *
     * @param sql
     * @param object
     * @return true:成功  false:失败
     */
    boolean checkDeleteByObject(String sql, Object object);

    /**
     * 更新
     *
     * @param sql
     * @param paraMap
     * @return int
     */
    int update(String sql, Map paraMap);

    /**
     * 更新
     *
     * @param sql
     * @param object
     * @return int
     */
    int updateByObject(String sql, Object object);

    /**
     * 检核更新是否成功
     *
     * @param sql
     * @param paraMap
     * @return true:成功  false:失败
     */
    boolean checkUpdate(String sql, Map paraMap);

    /**
     * 检核更新是否成功
     *
     * @param sql
     * @param object
     * @return true:成功  false:失败
     */
    boolean checkUpdateByObject(String sql, Object object);

    /**
     * 批量更新
     *
     * @param sql
     * @param updateList
     * @return
     * @throws BGDAOException
     */
    Integer updateBatch(final String sql, final List updateList) throws BGDAOException;

    /**
     * 批量新增
     *
     * @param sql
     * @param insertList
     * @return
     * @throws BGDAOException
     */
    Integer insertBatch(final String sql, final List insertList) throws BGDAOException;
}

