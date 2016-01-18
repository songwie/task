/**
 *
 */
package com.songwie.task.base.dao;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 通用实体包装类
 * 字段保留put的顺序
 * @author sw
 */
@SuppressWarnings("serial")
public class EntityMap extends LinkedHashMap<String, Object>
{
	public EntityMap()
	{
		super();
	};

	public EntityMap(int initialCapacity)
	{
		super(initialCapacity);
	};

	public EntityMap(Map<? extends String, ? extends Object> m)
	{
		super(m);
	};

	public boolean containsKeyIgnoreCase(String key)
	{
		for (String vkey : this.keySet())
		{
			if (vkey.trim().equalsIgnoreCase(key.trim()))
			{
				return true;
			}
		}

		return false;
	};

	public Object getIgnoreCase(String key)
	{
		for (String vkey : this.keySet())
		{
			if (vkey.trim().equalsIgnoreCase(key.trim()))
			{
				return this.get(vkey);
			}
		}

		return null;
	}

	public Object removeIgnoreCase(String key)
	{
		for (String vkey : this.keySet())
		{
			if (vkey.trim().equalsIgnoreCase(key.trim()))
			{
				return this.remove(vkey);
			}
		}

		return null;
	}

	public String getString(String key)
	{
		if (! this.containsKeyIgnoreCase(key))
		{
			return "";
		}

		Object obj = this.getIgnoreCase(key);

		if (obj == null)
		{
			return "";
		}
		else
		{
			return obj.toString();
		}
	}
}
