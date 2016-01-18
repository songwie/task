/**
 *
 */
package com.songwie.task.base.dao;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author sw
 */
@SuppressWarnings("serial")
public class ParaList extends ArrayList<Object>
{
	public ParaList()
	{
		super();
	};

	public ParaList(int initialCapacity)
	{
		super(initialCapacity);
	}

	public ParaList(Collection<? extends Object> c)
	{
		super(c);
	}

	public ParaList(Object[] array)
	{
		super();

		this.fromArray(array);
	}

	public void fromArray(Object[] array)
	{
		for (Object o : array)
		{
			this.add(o);
		}
	}
}
