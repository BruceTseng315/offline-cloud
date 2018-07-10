package com.turingmaker.service.operation.entity;

import java.io.Serializable;

/**
 * 
 * @author tzj
 *
 */
public class Resourceinfo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6398906497318445580L;

	/**
	 * 资源id
	 */
	private  Long id;
	/**
	 * 资源名称
	 */
	private String name;
	
	/**
	 * 资源URL
	 */
	private String url;
	
	
	public Resourceinfo() {}

	public Resourceinfo(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Resourceinfo [name=" + name + ", url=" + url + "]"+super.toString();
	}
	
	
}
