package com.example.demo.pojo;

public class SalesPojo {
	
	private String region;
	private String product;
	private Double sales;
	
	public SalesPojo(String region, String product, Double sales) {
		super();
		this.region = region;
		this.product = product;
		this.sales = sales;
	}
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Double getSales() {
		return sales;
	}
	public void setSales(Double sales) {
		this.sales = sales;
	}
	
	
	

}
