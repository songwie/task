package com.songwie.task.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.stereotype.Component;

/**
 * 数据访问通用类（适合JPA调用）
 * @author sw
 */

@Component
public class CommonDAO 
{

	@PersistenceContext
	transient EntityManager entityManager;
	
    
	public EntityManager getEntityManager() {
		return entityManager;
	}

	protected Connection getConnection()
    {
		SessionImplementor session = this.getEntityManager().unwrap(SessionImplementor.class);
        return session.connection();
    };


    /**
     * 执行查询，并按照传入实体类{@code<T>}构造实体列表{@code List<T>}
     * @param <T>         实体类模板
     * @param strSql        查询SQL语句
     * @param entityName    实体类
     * @return              返回实体列表{@code List<T>}
     */
    public <T> List<T> getEntityList(String strSql, Class<T> entityName)
    {
        return this.getEntityList(strSql, new ParaList(), entityName);
    }

    /**
     * 使用参数<code>paras</code>执行查询，并按照传入实体类{@code<T>}构造实体列表{@code List<T>}
     * @param <T>         实体类模板
     * @param strSql        查询SQL语句
     * @param paras         查询参数
     * @param entityName    实体类
     * @return              返回实体列表{@code List<T>}
     */
    public <T> List<T> getEntityList(String strSql, List<Object> paras, Class<T> entityName)
    {
        try
        {
            Connection conn = this.getConnection();

            PreparedStatement ps = null;
            ResultSet rs = null;
            try
            {
                ps = conn.prepareStatement(strSql);

                this.setParasToStatement(ps, paras);

                rs = ps.executeQuery();

                List<T> list = EntityUtils.ListFromRS(rs, entityName);

                return list;
            }
            finally
            {
                if (ps != null)
                {
                    ps.close();
                    ps = null;
                }
                if (rs != null)
                {
                    rs.close();
                    rs = null;
                }
            }
        }
        catch(Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 使用参数<code>paras</code>执行查询，并按照传入实体类{@code<T>}构造实体列表{@code List<T>}
     * @param <T>         实体类模板
     * @param strSql        查询SQL语句
     * @param paras         查询参数
     * @param entityName    实体类
     * @return              返回实体列表{@code List<T>}
     */
    public <T> List<T> getEntityList(String strSql, Object[] paras, Class<T> entityName)
    {
        return this.getEntityList(strSql, new ParaList(paras), entityName);
    }

    /**
     * 设置参数
     * @param ps
     * @param paras
     * @throws Exception
     */
    protected void setParasToStatement(PreparedStatement ps, List<Object> paras) throws Exception
    {
        if (paras == null)
        {
            return;
        }

        if (paras.size() <=   0)
        {
            return;
        }

        for (int i =   0; i < paras.size(); i++)
        {
            Object para = paras.get(i);

            if (para.getClass().equals(String.class))
            {
                ps.setString(i +   1, para.toString());
            }
            else if (para.getClass().equals(int.class))
            {
                ps.setInt(i +   1, (Integer.parseInt(para.toString())));
            }
            else if (para.getClass().equals(Integer.class))
            {
                ps.setInt(i +   1, ((Integer) para).intValue());
            }
            else if (para.getClass().equals(Long.class))
            {
                ps.setLong(i +   1, ((Long) para).longValue());
            }
            else if (para.getClass().equals(long.class))
            {
                ps.setLong(i +   1, (Long.parseLong(para.toString())));
            }
            else if (para.getClass().equals(double.class))
            {
                ps.setDouble(i +   1, (Double.parseDouble(para.toString())));
            }
            else if (para.getClass().equals(Double.class))
            {
                ps.setDouble(i +   1, ((Double) para).doubleValue());
            }
        }
    }

    protected void setListParasToQuery(Query query, List<Object> paras)
    {
        for (int i =   0; i < paras.size(); i++)
        {
            query.setParameter(i +   1, paras.get(i));
        }
    }
    protected void setMapParasToQuery(Query query, Map<String, Object> paras)
    {
        for (String key : paras.keySet())
        {
            query.setParameter(key, paras.get(key));
        }
    }

    /**
     * 查询总行数
     * @param strSql        查询SQL语句
     * @return              总行数
     */
    public int getTotal(String strSql)
    {
        return this.getTotal(strSql, new ParaList());
    }
    /**
     * 使用参数<code>paras</code>执行查询总行数
     * @param strSql        查询SQL语句
     * @param paras         查询参数
     * @return              总行数
     */
    public int getTotal(String strSql, List<Object> paras)
    {
        strSql = "select count(1) from (" + strSql + ") t";

        return this.querySingleInteger(strSql, paras);
    }
    /**
     * 使用参数<code>paras</code>执行查询总行数
     * @param strSql        查询SQL语句
     * @param paras         查询参数
     * @return              总行数
     */
    public int getTotal(String strSql, Object[] paras)
    {
        return this.querySingleInteger(strSql, new ParaList(paras));
    }
    /**
     * 使用参数<code>paras</code>执行查询总行数
     * @param strSql        查询SQL语句
     * @param paras         查询参数
     * @return              总行数
     */
    public int getTotal(String strSql, Map<String, Object> paras)
    {
        strSql = "select count(1) from (" + strSql + ") t";

        return this.querySingleInteger(strSql, paras);
    }

    /**
     * 执行分页查询，构造实体列表{@code List<T>}
     * @param <T>         实体类模板
     * @param strSql        查询SQL语句
     * @param entityName    实体类
     * @param start         开始行
     * @param limit         页面行数
     * @return              返回实体列表{@code List<T>}
     */
    public <T> PageData<T> getEntityListPage(String strSql, Class<T> entityName, int start, int limit)
    {
        return this.getEntityListPage(strSql, new ParaList(), entityName, start, limit);
    }

    /**
     * 使用参数<code>paras</code>执行分页查询，构造实体列表{@code List<T>}
     * @param <T>         实体类模板
     * @param strSql        查询SQL语句
     * @param paras         查询参数
     * @param entityName    实体类
     * @param start         开始行
     * @param limit         页面行数
     * @return              返回实体列表{@code List<T>}
     */
    public <T> PageData<T> getEntityListPage(String strSql, List<Object> paras, Class<T> entityName, int start, int limit)
    {
        try
        {
            Connection conn = this.getConnection();

            PreparedStatement ps = null;
            ResultSet rs = null;
            try
            {
                String strSQLQuery =  strSql + "limit "+start +" ," + limit;
                ps = conn.prepareStatement(strSQLQuery);

                this.setParasToStatement(ps, paras);

                rs = ps.executeQuery();

                List<T> list = EntityUtils.ListFromRS(rs, entityName);

                int total = this.getTotal(strSql, paras);

                return new PageData<T>(list, total);
            }
            finally
            {
                if (ps != null)
                {
                    ps.close();
                    ps = null;
                }
                if (rs != null)
                {
                    rs.close();
                    rs = null;
                }
            }
        }
        catch(Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }
    /**
     * 使用参数<code>paras</code>执行分页查询，构造实体列表{@code List<T>}
     * @param <T>         实体类模板
     * @param strSql        查询SQL语句
     * @param paras         查询参数
     * @param entityName    实体类
     * @param start         开始行
     * @param limit         页面行数
     * @return              返回实体列表{@code List<T>}
     */
    public <T> PageData<T> getEntityListPage(String strSql, Object[] paras, Class<T> entityName, int start, int limit)
    {
        return this.getEntityListPage(strSql, new ParaList(paras), entityName, start, limit);
    }

    /**
     * 获取单个实体
     * @param <T>         实体类模板
     * @param strSql        查询SQL语句
     * @param entityName    实体类
     * @return              返回实体类{@code<T>}
     */
    public <T> T getEntity(String strSql, Class<T> entityName)
    {
        return this.getEntity(strSql, new ParaList(), entityName);
    }

    /**
     * 获取单个实体
     * @param <T>         实体类模板
     * @param strSql        查询SQL语句
     * @param paras         查询参数
     * @param entityName    实体类
     * @return              返回实体类{@code<T>}
     */
    public <T> T getEntity(String strSql, List<Object> paras, Class<T> entityName)
    {
        List<T> list = this.getEntityList(strSql, paras, entityName);

        if (list.size() ==   1)
        {
            return list.get(  0);
        }
        else if (list.size() >   1)
        {
            throw new RuntimeException("查询结果多余一行");
        }
        else
        {
            return null;
        }
    }

    /**
     * 获取单个实体
     * @param <T>         实体类模板
     * @param strSql        查询SQL语句
     * @param paras         查询参数
     * @param entityName    实体类
     * @return              返回实体类{@code<T>}
     */
    public <T> T getEntity(String strSql, Object[] paras, Class<T> entityName)
    {
        return this.getEntity(strSql, new ParaList(paras), entityName);
    }
    /**
     * 执行查询构造实体列表{@code List<T>}
     * @param strSql        查询SQL语句
     * @return              返回实体列表{@code List<T>}
     */
    public <T> List<EntityMap> getList(String strSql)
    {
		List<EntityMap> resultList = new ArrayList<EntityMap>();
    	try
        {
            Connection conn = this.getConnection();

            PreparedStatement ps = null;
            ResultSet rs = null;
            try
            {
                ps = conn.prepareStatement(strSql);
                rs = ps.executeQuery();
                ResultSetMetaData data = rs.getMetaData();
                while (rs.next()){
                	EntityMap row = new EntityMap();
                    // 循环当前SQL语句查询的列
                    for (int i = 1; i <= data.getColumnCount(); i++) {
                        // 获取当前列名
                        String columnName = data.getColumnName(i);
                        // 获取当前列值
                        String columnValue = rs.getString(i);
                        // 对空进行处理
                        if (columnValue == null)
                            columnValue = "";

                        row.put(columnName.toLowerCase(), columnValue);
                    }
                    resultList.add(row);
                    rs.next();
                }
            }
            finally
            {
                if (ps != null)
                {
                    ps.close();
                    ps = null;
                }
                if (rs != null)
                {
                    rs.close();
                    rs = null;
                }
            }
        }
        catch(Exception ex)
        {
            throw new RuntimeException(ex);
        }
    	return resultList;
    }

    /**
     * 执行查询，并把结果构造为通用实体包装类
     * 字段根据<code>paras</code>参数按顺序替换
     * @param strSql    查询SQL语句
     * @param fieldList 替换字段列表
     * @return          返回的通用实体包装类
     */
    public List<EntityMap> getList(String strSql, String[] fieldList)
    {
        return this.getList(strSql, fieldList, new ParaList());
    }
    /**
     * 使用List参数<code>paras</code>执行查询，并把结果构造为通用实体包装类
     * 字段根据<code>paras</code>参数按顺序替换
     * @param strSql    查询SQL语句
     * @param fieldList 替换字段列表
     * @param paras     查询参数
     * @return          返回的通用实体包装类
     */
    public List<EntityMap> getList(String strSql, String[] fieldList, List<Object> paras)
    {
        Query query = this.getEntityManager().createNativeQuery(strSql);
        this.setListParasToQuery(query, paras);
        List<Object[]> list = query.getResultList();

        return EntityUtils.EntityMapListFromObjectArrayList(list, fieldList);
    }
    /**
     * 使用字符串数组参数<code>paras</code>执行查询，并把结果构造为通用实体包装类
     * 字段根据<code>paras</code>参数按顺序替换
     * @param strSql    查询SQL语句
     * @param fieldList 替换字段列表
     * @param paras     查询参数
     * @return          返回的通用实体包装类
     */
    public List<EntityMap> getList(String strSql, String[] fieldList, Object[] paras)
    {
        return this.getList(strSql, fieldList, new ParaList(paras));
    }
    /**
     * 使用List参数<code>paras</code>执行查询，并把结果构造为通用实体包装类
     * 字段根据<code>paras</code>参数按字段名替换
     * @param strSql    查询SQL语句
     * @param fieldList 替换字段列表
     * @param paras     查询参数
     * @return          返回的通用实体包装类
     */
    public List<EntityMap> getList(String strSql, String[] fieldList, Map<String, Object> paras)
    {
        Query query = this.getEntityManager().createNativeQuery(strSql);
        this.setMapParasToQuery(query, paras);
        List<Object[]> list = query.getResultList();

        return EntityUtils.EntityMapListFromObjectArrayList(list, fieldList);
    }

    /**
     * 执行分页查询，并把结果构造为通用实体包装类
     * 字段根据<code>paras</code>参数按顺序替换
     * @param strSql    查询SQL语句
     * @param fieldList 替换字段列表
     * @param start     开始行
     * @param limit     页面行数
     * @return          返回的通用实体包装类
     */
    public PageData<EntityMap> getListPage(String strSql, String[] fieldList, int start, int limit)
    {
        return this.getListPage(strSql, fieldList, new ParaList(), start, limit);
    }

    /**
     * 使用List参数<code>paras</code>执行分页查询，并把结果构造为通用实体包装类\
     * 字段根据<code>paras</code>参数按顺序替换
     * @param strSql    查询SQL语句
     * @param fieldList 替换字段列表
     * @param paras     查询参数
     * @param start     开始行
     * @param limit     页面行数
     * @return          返回的通用实体包装类
     */
    public PageData<EntityMap> getListPage(String strSql, String[] fieldList, List<Object> paras, int start, int limit)
    {
        Query query = this.getEntityManager().createNativeQuery(strSql);
        this.setListParasToQuery(query, paras);
        query.setFirstResult(start);
        query.setMaxResults(limit);
        List<Object[]> list = query.getResultList();

        List<EntityMap> list2 = EntityUtils.EntityMapListFromObjectArrayList(list, fieldList);

        int total = this.getTotal(strSql, paras);

        return new PageData<EntityMap>(list2, total);
    }
    /**
     * 使用字符串数组参数<code>paras</code>执行分页查询，并把结果构造为通用实体包装类
     * 字段根据<code>paras</code>参数按顺序替换
     * @param strSql    查询SQL语句
     * @param fieldList 替换字段列表
     * @param paras     查询参数
     * @param start     开始行
     * @param limit     页面行数
     * @return          返回的通用实体包装类
     */
    public PageData<EntityMap> getListPage(String strSql, String[] fieldList, Object[] paras, int start, int limit)
    {
        return this.getListPage(strSql, fieldList, new ParaList(paras), start, limit);
    }
    /**
     * 使用List参数<code>paras</code>执行分页查询，并把结果构造为通用实体包装类\
     * 字段根据<code>paras</code>参数按字段名替换
     * @param strSql    查询SQL语句
     * @param fieldList 替换字段列表
     * @param paras     查询参数
     * @param start     开始行
     * @param limit     页面行数
     * @return          返回的通用实体包装类
     */
    public PageData<EntityMap> getListPage(String strSql, String[] fieldList, Map<String, Object> paras, int start, int limit)
    {
        Query query = this.getEntityManager().createNativeQuery(strSql);
        this.setMapParasToQuery(query, paras);
        query.setFirstResult(start);
        query.setMaxResults(limit);
        List<Object[]> list = query.getResultList();

        List<EntityMap> list2 = EntityUtils.EntityMapListFromObjectArrayList(list, fieldList);

        int total = this.getTotal(strSql, paras);

        return new PageData<EntityMap>(list2, total);
    }

    /**
     * 查询单个数值，返回字符串<code>java.lang.String</code>
     * @param strSql    查询SQL
     * @return          返回结果<code>java.lang.String</code>
     */
    public String querySingleString(String strSql)
    {
        return this.querySingleString(strSql, new ParaList());
    }
    /**
     * 查询单个数值，返回字符串<code>java.lang.String</code>
     * @param strSql    查询SQL
     * @param paras     参数<code>paras extend List</code>
     * @return          返回结果<code>java.lang.String</code>
     */
    public String querySingleString(String strSql, List<Object> paras)
    {
        Object obj = this.querySingleObject(strSql, paras);

        return obj.toString();
    }
    /**
     * 查询单个数值，返回字符串<code>java.lang.String</code>
     * @param strSql    查询SQL
     * @param paras     参数<code>paras extend List</code>
     * @return          返回结果<code>java.lang.String</code>
     */
    public String querySingleString(String strSql, Object[] paras)
    {
        return this.querySingleString(strSql, new ParaList(paras));
    }
    /**
     * 查询单个数值，返回字符串<code>java.lang.String</code>
     * @param strSql    查询SQL
     * @param paras     参数<code>ParaMap extend HashMap</code>
     * @return          返回结果<code>java.lang.String</code>
     */
    public String querySingleString(String strSql, Map<String, Object> paras)
    {
        Object obj = this.querySingleObject(strSql, paras);

        return obj.toString();
    }

    public int querySingleInteger(String strSql)
    {
        return this.querySingleInteger(strSql, new ParaList());
    }
    public int querySingleInteger(String strSql, List<Object> paras)
    {
        return Integer.parseInt(this.querySingleString(strSql, paras));
    }
    public int querySingleInteger(String strSql, Object[] paras)
    {
        return Integer.parseInt(this.querySingleString(strSql, paras));
    }
    public int querySingleInteger(String strSql, Map<String, Object> paras)
    {
        return Integer.parseInt(this.querySingleString(strSql, paras));
    }

    public double querySingleDouble(String strSql)
    {
        return this.querySingleDouble(strSql, new ParaList());
    }
    public double querySingleDouble(String strSql, List<Object> paras)
    {
        return Double.parseDouble(this.querySingleString(strSql, paras));
    }
    public double querySingleDouble(String strSql, Object[] paras)
    {
        return Double.parseDouble(this.querySingleString(strSql, paras));
    }
    public double querySingleDouble(String strSql, Map<String, Object> paras)
    {
        return Double.parseDouble(this.querySingleString(strSql, paras));
    }

    /**
     * 查询单个数值，返回对象<code>java.lang.Object</code>
     * @param strSql    查询SQL
     * @return          返回结果<code>java.lang.Object</code>
     */
    public Object querySingleObject(String strSql)
    {
        return this.querySingleObject(strSql, new ParaList());
    }
    /**
     * 查询单个数值，返回对象<code>java.lang.Object</code>
     * @param strSql    查询SQL
     * @param paras     参数<code>paras extend List</code>
     * @return          返回结果<code>java.lang.Object</code>
     */
    public Object querySingleObject(String strSql, List<Object> paras)
    {
        Query query = this.getEntityManager().createNativeQuery(strSql);

        this.setListParasToQuery(query, paras);

        return query.getSingleResult();
    }
    /**
     * 查询单个数值，返回对象<code>java.lang.Object</code>
     * @param strSql    查询SQL
     * @param paras     参数<code>paras extend Map</code>
     * @return          返回结果<code>java.lang.Object</code>
     */
    public Object querySingleObject(String strSql, Object[] paras)
    {
        return this.querySingleObject(strSql, new ParaList(paras));
    }
    /**
     * 查询单个数值，返回对象<code>java.lang.Object</code>
     * @param strSql    查询SQL
     * @param paras     参数<code>ParaMap extend HashMap</code>
     * @return          返回结果<code>java.lang.Object</code>
     */
    public Object querySingleObject(String strSql, Map<String, Object> paras)
    {
        Query query = this.getEntityManager().createNativeQuery(strSql);

        this.setMapParasToQuery(query, paras);

        return query.getSingleResult();
    }

    public int execute(String strSql)
    {
        Query query = this.getEntityManager().createNativeQuery(strSql);

        return query.executeUpdate();
    }

    public int execute(String strSql, List<Object> paras)
    {
        Query query = this.getEntityManager().createNativeQuery(strSql);

        this.setListParasToQuery(query, paras);

        return query.executeUpdate();
    }

    public int execute(String strSql, Object[] paras)
    {
        return this.execute(strSql, new ParaList(paras));
    }

    public int execute(String strSql, Map<String, Object> paras)
    {
        Query query = this.getEntityManager().createNativeQuery(strSql);

        this.setMapParasToQuery(query, paras);

        return query.executeUpdate();
    }
}
