/**
 *
 */
package com.songwie.task.base.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 实体转换工具类
 * @author sw
 */
public class EntityUtils
{
	/**
	 * 把<code>ResultSet</code>中一行数据转换为指定实体类{@code<T>}<br>
	 * 实体中属性与<code>ResultSet</code>中字段名称相同即转换(大小写不敏感)<br>
	 * 实体中的多余属性或<code>ResultSet</code>中的多余字段，忽略
	 * @param <T>			实体类模板
	 * @param rs			ResultSet数据集
	 * @param entityName	指定实体类名称
	 * @return				返回指定实体对象{@code<T>}
	 * @throws Exception
	 */
	public static <T> T EntityFromRS(ResultSet rs, Class<T> entityName) throws Exception
	{
		T object = entityName.newInstance();

		if (null == rs)
		{
			return object;
		}

		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++)
		{
			String strFieldName = rsmd.getColumnName(i);

			Field[] fieldList = entityName.getDeclaredFields();
			for (int j = 0; j < fieldList.length; j++)
			{
				Field field = fieldList[j];

				/**
				 * 实体和ResultSet中名称相同自动匹配，忽略大小写
				 */
				if (field.getName().equalsIgnoreCase(strFieldName))
				{
					SetObjectAttr(object, field, rs.getObject(strFieldName));
				}
			}
		}

		return object;
	}

	/**
	 * 把整个<code>ResultSet</code>数据转换为指定实体类列表{@code List<T>}<br>
	 * 实体中属性与<code>ResultSet</code>中字段名称相同即转换(大小写不敏感)<br>
	 * 实体中的多余属性或<code>ResultSet</code>中的多余字段，忽略
	 * @param <T>			实体类模板
	 * @param rs			ResultSet数据集
	 * @param entityName	指定实体类名称
	 * @return				返回指定实体对象列表{@code List<T>}
	 * @throws Exception
	 */
	public static <T> List<T> ListFromRS(ResultSet rs, Class<T> entityName) throws Exception
	{
		List<T> list = new ArrayList<T>();

		if (null == rs)
		{
			return list;
		}

		while (rs.next())
		{
			list.add(EntityUtils.EntityFromRS(rs, entityName));
		}

		return list;
	}

	/**
	 * 检查对象上是否存在字段
	 * @param object		目标对象
	 * @param fieldName		字段名称
	 * @return
	 */
	public static boolean containsField(Object object, String fieldName)
	{
		Class<?> entityName = object.getClass();

		Field[] fieldList = entityName.getDeclaredFields();

		for (Field field : fieldList)
		{
			if (field.getName().trim().equals(fieldName.trim()))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * 检查对象上是否存在字段，忽略大小写
	 * @param object		目标对象
	 * @param fieldName		字段名称
	 * @return
	 */
	public static boolean containsFieldIgnoreCase(Object object, String fieldName)
	{
		Class<?> entityName = object.getClass();

		Field[] fieldList = entityName.getDeclaredFields();

		for (Field field : fieldList)
		{
			if (field.getName().trim().equalsIgnoreCase(fieldName.trim()))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * 读取对象上字段的值
	 * @param object
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static Object GetObjectAttr(Object object, String fieldName) throws Exception
	{
		Class<?> entityName = object.getClass();

		Field[] fieldList = entityName.getDeclaredFields();

		for (Field field : fieldList)
		{
			field.setAccessible(true);

			if (field.getName().trim().equals(fieldName.trim()))
			{
				return field.get(object);
			}
		}

		return null;
	}

	/**
	 * 读取对象上字段的值，忽略大小写
	 * @param object
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static Object GetObjectAttrIgnoreCase(Object object, String fieldName) throws Exception
	{
		Class<?> entityName = object.getClass();

		Field[] fieldList = entityName.getDeclaredFields();

		for (Field field : fieldList)
		{
			field.setAccessible(true);

			if (field.getName().trim().equalsIgnoreCase(fieldName.trim()))
			{
				return field.get(object);
			}
		}

		return null;
	}

	/**
	 * 把Object类型的值动态设置到目标对象属性上
	 * @param object		目标对象
	 * @param field			目标对象属性
	 * @param value			值(Object类型)
	 * @throws Exception
	 */
	public static void SetObjectAttr(Object object, Field field, Object value) throws Exception
	{
		if (null == object || null == field)
		{
			return;
		}

		field.setAccessible(true);

		if (field.getType().equals(String.class) && null != value)
		{
			field.set(object, value.toString());
		}
		else if (field.getType().equals(String.class) && null == value)
		{
			field.set(object, "");
		}
		else if (field.getType().equals(int.class) && null != value)
		{
			field.set(object, Integer.valueOf(value.toString()));
		}
		else if (field.getType().equals(Integer.class) && null != value)
		{
			field.set(object, Integer.valueOf(value.toString()));
		}
		else if (field.getType().equals(double.class) && null != value)
		{
			field.set(object, Double.valueOf(value.toString()));
		}
		else if (field.getType().equals(Double.class) && null != value)
		{
			field.set(object, Double.valueOf(value.toString()));
		}
		else
		{
			field.set(object, null);
		}
	}

	/**
	 * Object数组包装为EntityMap<br>
	 * 顺序匹配<br>
	 * key为传入字段列表，value为Object数组<br>
	 * 字段数小于Object数组，超出的Object值忽略<br>
	 * 字段数大于Object数组，超出的字段忽略
	 * @param data		JPA返回Object数组
	 * @param fieldList	字段列表
	 * @return			包装好的EntityMap
	 */
	public static EntityMap EntityMapFromArray(Object[] data, String[] fieldList)
	{
		EntityMap map = new EntityMap();

		if (null == data || null == fieldList)
		{
			return map;
		}

		int intHighBound = (data.length <= fieldList.length) ? data.length : fieldList.length;

		for (int i = 0; i < intHighBound; i++)
		{
			map.put(fieldList[i], data[i]);
		}

		return map;
	}
	/**
	 * JPA返回的Object数组列表包装为EntityMap列表<br>
	 * 顺序匹配<br>
	 * key为传入字段列表，value为Object数组<br>
	 * 字段数小于Object数组，超出的Object值忽略<br>
	 * 字段数大于Object数组，超出的字段忽略
	 * @param data		JPA返回的Object数组列表
	 * @param fieldList	字段列表
	 * @return			包装好的EntityMap
	 */
	public static List<EntityMap> EntityMapListFromObjectArrayList(List<Object[]> data, String[] fieldList)
	{
		List<EntityMap> list = new ArrayList<EntityMap>();

		if (null == data || null == fieldList)
		{
			return list;
		}

		for (Object[] obj : data)
		{
			list.add(EntityMapFromArray(obj, fieldList));
		}

		return list;
	}

	/**
	 * 实体类<code>T</code>对象转化为通用实体包装类<code>EntityMap</code><br>
	 * 实体类属性名称转为包装类中键值，保持属性名称原始大小写
	 * @param <T>			实体类模板
	 * @param entity		实体类对象
	 * @param entityName	实体类名称
	 * @return				通用实体包装类<code>EntityMap</code>
	 * @throws Exception
	 */
	public static <T> EntityMap EntityToMap(T entity, Class<T> entityName) throws Exception
	{
		EntityMap map = new EntityMap();
		if (null == entity)
		{
			return map;
		}

		Field[] fieldList = entityName.getDeclaredFields();
		for (Field field : fieldList)
		{
			field.setAccessible(true);

			String key = field.getName();
			Object value = field.get(entity);
			map.put(key, value);
		}

		return map;
	}

	/**
	 * 实体类<code>T</code>对象列表转化为通用实体包装类<code>EntityMap</code><br>列表
	 * 实体类属性名称转为包装类中键值，保持属性名称原始大小写
	 * @param <T>			实体类模板
	 * @param list			实体类对象列表
	 * @param entityName	实体类名称
	 * @return				通用实体包装类<code>EntityMap</code>列表
	 * @throws Exception
	 */
	public static <T> List<EntityMap> EntityListToMapList(List<T> list, Class<T> entityName) throws Exception
	{
		List<EntityMap> retList = new ArrayList<EntityMap>();
		if (null == list)
		{
			return retList;
		}

		for (T obj : list)
		{
			retList.add(EntityToMap(obj, entityName));
		}

		return retList;
	}

	/**
	 * 通用实体包装类<code>EntityMap</code>转化实体类<code>T</code>对象<br>
	 * 依据实体类属性名称与包装类中键值匹配，忽略大小写
	 * @param <T>			实体类模板
	 * @param map			通用实体包装类<code>EntityMap</code>
	 * @param entityName	实体类名称
	 * @return				实体类<code>T</code>对象
	 * @throws Exception
	 */
	public static <T> T MapToEntity(EntityMap map, Class<T> entityName) throws Exception
	{
		T object = entityName.newInstance();

		if (null == map)
		{
			return object;
		}

		Field[] fieldList = entityName.getDeclaredFields();
		for (Field field : fieldList)
		{
			for (String mapKey : map.keySet())
			{
				if (field.getName().trim().equalsIgnoreCase(mapKey.trim()))
				{
					SetObjectAttr(object, field, map.get(mapKey));
				}
			}
		}

		return object;
	}

	/**
	 * 通用实体包装类<code>EntityMap</code>列表转化实体类<code>T</code>对象<br>列表
	 * 依据实体类属性名称与包装类中键值匹配，忽略大小写
	 * @param <T>			实体类模板
	 * @param list			通用实体包装类<code>EntityMap</code>列表
	 * @param entityName	实体类名称
	 * @return				实体类<code>T</code>对象列表
	 * @throws Exception
	 */
	public static <T> List<T> MapListToEntityList(List<EntityMap> list, Class<T> entityName) throws Exception
	{
		List<T> retList = new ArrayList<T>();
		if (null == list)
		{
			return retList;
		}

		for (EntityMap map : list)
		{
			retList.add(MapToEntity(map, entityName));
		}

		return retList;
	}

	/**
	 * 按照传入的key对应关系，替换EntityMap中的key<br>
	 * 匹配时忽略大小写
	 * @param entity	准备替换的EntityMap
	 * @param keyMap	key对应关系Map
	 * @return			替换后的EntityMap
	 */
	public static EntityMap KeyRender(EntityMap entity, Map<String, String> keyMap)
	{
		if (null == entity || keyMap == null)
		{
			return entity;
		}

		for (String mapKey : keyMap.keySet())
		{
			if (entity.containsKeyIgnoreCase(mapKey))
			{
				entity.put(keyMap.get(mapKey).trim(), entity.getIgnoreCase(mapKey));
				entity.removeIgnoreCase(mapKey);
			}
		}

		return entity;
	}

	/**
	 * 按照传入的key对应关系，替换EntityMap列表中所有的key<br>
	 * 匹配时忽略大小写
	 * @param list		准备替换的EntityMap列表
	 * @param keyMap	key对应关系Map
	 * @return			替换后的EntityMap列表
	 */
	public static List<EntityMap> KeyRender(List<EntityMap> list, Map<String, String> keyMap)
	{
		if (null == list || keyMap == null)
		{
			return null;
		}

		for (EntityMap entity : list)
		{
			KeyRender(entity, keyMap);
		}

		return list;
	}

	/**
	 * 把<code>ResultSet</code>中一行数据转换为通用实体类{@code<EntityMap>}<br>
	 * @param rs	ResultSet数据集
	 * @return		返回通用实体对象{@code<EntityMap>}
	 * @throws Exception
	 */
	public static EntityMap EntityMapFromRS(ResultSet rs) throws Exception
	{
		EntityMap map = new EntityMap();
		if (null == rs)
		{
			return map;
		}

		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++)
		{
			String strFieldName = rsmd.getColumnName(i);

			map.put(strFieldName, rs.getObject(strFieldName));
		}

		return map;
	}

	/**
	 * 把<code>ResultSet</code>中一行数据转换为通用实体类{@code<EntityMap>}<br>
	 * 并按照传入的key对应关系，替换原有的字段名称
	 * @param rs		ResultSet数据集
	 * @param keyMap	key对应关系Map
	 * @return			返回通用实体对象{@code<EntityMap>}
	 * @throws Exception
	 */
	public static EntityMap EntityMapFromRS(ResultSet rs, Map<String, String> keyMap) throws Exception
	{
		EntityMap map = EntityMapFromRS(rs);

		return KeyRender(map, keyMap);
	}

	/**
	 * 把<code>ResultSet</code>中一行数据转换为通用实体类{@code<EntityMap>}<br>
	 * 并按照传入的字段列表，替换原有的字段名称<br>
	 * 如果传入的字段列表不足，则使用原有的字段名称
	 * @param rs		ResultSet数据集
	 * @param fieldList	替换字段列表
	 * @return			返回通用实体对象{@code<EntityMap>}
	 * @throws Exception
	 */
	public static EntityMap EntityMapFromRS(ResultSet rs, String[] fieldList) throws Exception
	{
		EntityMap map = new EntityMap();
		if (null == rs || null == fieldList)
		{
			return map;
		}

		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 0; i < rsmd.getColumnCount(); i++)
		{
			String strFieldName = rsmd.getColumnName(i + 1);

			if (i < fieldList.length)
			{
				map.put(fieldList[i], rs.getObject(strFieldName));
			}
			else
			{
				map.put(strFieldName, rs.getObject(strFieldName));
			}
		}

		return map;
	}

	/**
	 * 把整个<code>ResultSet</code>数据转换为通用实体类列表{@code<EntityMap>}列表<br>
	 * @param rs	ResultSet数据集
	 * @return		返回通用实体对象{@code<EntityMap>}列表
	 * @throws Exception
	 */
	public static List<EntityMap> ListFromRS(ResultSet rs) throws Exception
	{
		List<EntityMap> list = new ArrayList<EntityMap>();
		if (null == rs)
		{
			return list;
		}

		while (rs.next())
		{
			list.add(EntityUtils.EntityMapFromRS(rs));
		}

		return list;
	}

	/**
	 * 把整个<code>ResultSet</code>数据转换为通用实体类列表{@code<EntityMap>}列表<br>
	 * @param rs		ResultSet数据集
	 * @param keyMap	key对应关系Map
	 * @return			返回通用实体对象{@code<EntityMap>}列表
	 * @throws Exception
	 */
	public static List<EntityMap> ListFromRS(ResultSet rs, Map<String, String> keyMap) throws Exception
	{
		List<EntityMap> list = new ArrayList<EntityMap>();
		if (null == rs)
		{
			return list;
		}

		while (rs.next())
		{
			list.add(EntityUtils.EntityMapFromRS(rs, keyMap));
		}

		return list;
	}

	/**
	 * 把整个<code>ResultSet</code>数据转换为通用实体类列表{@code<EntityMap>}列表<br>
	 * 并按照传入的字段列表，替换原有的字段名称<br>
	 * 如果传入的字段列表不足，则使用原有的字段名称
	 * @param rs		ResultSet数据集
	 * @param fieldList	替换字段列表
	 * @return			返回通用实体对象{@code<EntityMap>}列表
	 * @throws Exception
	 */
	public static List<EntityMap> ListFromRS(ResultSet rs, String[] fieldList) throws Exception
	{
		List<EntityMap> list = new ArrayList<EntityMap>();
		if (null == rs)
		{
			return list;
		}

		while (rs.next())
		{
			list.add(EntityUtils.EntityMapFromRS(rs, fieldList));
		}

		return list;
	}

	/**
	 * 把<code>ResultSet</code>中一行数据转换为通用实体类{@code<EntityMap>}<br>
	 * 一行空数据
	 * @param rs	ResultSet数据集
	 * @return		返回通用实体对象{@code<EntityMap>}
	 * @throws Exception
	 */
	public static EntityMap EntityMapFromRSNullRow(ResultSet rs) throws Exception
	{
		EntityMap map = new EntityMap();
		if (null == rs)
		{
			return map;
		}

		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++)
		{
			String strFieldName = rsmd.getColumnName(i);

			String strFieldTypeName = rsmd.getColumnTypeName(i);

			if (strFieldTypeName.equalsIgnoreCase("INTEGER")
					|| strFieldTypeName.equalsIgnoreCase("NUMBER")
					|| strFieldTypeName.equalsIgnoreCase("DOUBLE"))
			{
				map.put(strFieldName, 0);
			}
			else
			{
				map.put(strFieldName, "");
			}
		}

		return map;
	}

	/**
	 * 把整个<code>ResultSet</code>数据转换为通用实体类列表{@code<EntityMap>}列表<br>
	 * 一行空数据
	 * @param rs	ResultSet数据集
	 * @return		返回通用实体对象{@code<EntityMap>}列表
	 * @throws Exception
	 */
	public static List<EntityMap> ListFromRSNullRow(ResultSet rs) throws Exception
	{
		List<EntityMap> list = new ArrayList<EntityMap>();
		if (null == rs)
		{
			return list;
		}

		list.add(EntityUtils.EntityMapFromRSNullRow(rs));

		return list;
	}

	/**
	 * 复制属性
	 * @param source
	 * @param dest
	 * @throws Exception
	 */
	public static void CopyAttributes(Object source, Object dest) throws Exception
	{
		Field[] fieldListDesc = dest.getClass().getDeclaredFields();
		Field[] fieldListSource = source.getClass().getDeclaredFields();

		for (Field fieldDesc : fieldListDesc)
		{
			for (Field fieldSource : fieldListSource)
			{
				if (fieldDesc.getName().trim().equalsIgnoreCase(fieldSource.getName().trim()))
				{
					fieldSource.setAccessible(true);
					SetObjectAttr(dest, fieldDesc, fieldSource.get(source));
				}
			}
		}
	}
}