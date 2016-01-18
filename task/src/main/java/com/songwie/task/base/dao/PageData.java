/**
 *
 */
package com.songwie.task.base.dao;

import java.util.List;

/**
 * 分页数据。组合了 实体列表和总行数。
 * @author sw
 */
public class PageData<T>
{
	public PageData()
	{
	}

	public PageData(List<T> data, int total)
	{
		this.data = data;
		this.total = total;
	}

	private List<T> data;
	private int total;
	public List<T> getData()
	{
		return data;
	}
	public void setData(List<T> data)
	{
		this.data = data;
	}
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
}
