package com.songwie.task.web;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="SUTDENT" )
public class Student {

	//@XmlAttribute
	private String idd;
	//@XmlElement
	private String name;
	
	@XmlJavaTypeAdapter(value=DateAdapter.class, type=Date.class)
	private Date date;
	 
	 
	@XmlElementWrapper(name="datas")
    @XmlElement(name="data")
	List<Data> datas = new ArrayList<Data>();
	
	//@XmlElementWrapper(name="list")
    //@XmlElement(name="data")
	//List<Map<String, String>> list = new ArrayList<Map<String,String>>();
	 
	 
	public String getName() {
		return name;
	}
	public String getIdd() {
		return idd;
	}
	public void setIdd(String idd) {
		this.idd = idd;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
