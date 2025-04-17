package com.example.demo.pojo;

public class CrossTabReportEntry {
	
	private String name;
	private String month;
	private Integer amount;
	
	
	public CrossTabReportEntry(String name, String month, Integer amount) {
		super();
		this.name = name;
		this.month = month;
		this.amount = amount;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
	
	

}
