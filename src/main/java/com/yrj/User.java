package com.yrj;

public class User {
	
	private String mobile;
	private String name;
	private String sex;
	
	
	
	public User() {
		super();
	}
	public User(String mobile, String name, String sex) {
		super();
		this.mobile = mobile;
		this.name = name;
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	
}
