package com.kafka.stream.model;

public class RequestDTO {
	
	public String businessSegment;
	public String platform;
	public String startServiceDate;
	public String product;
	public String providerId;
	public String subgroup;
	public String getBusinessSegment() {
		return businessSegment;
	}
	public void setBusinessSegment(String businessSegment) {
		this.businessSegment = businessSegment;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getStartServiceDate() {
		return startServiceDate;
	}
	public void setStartServiceDate(String startServiceDate) {
		this.startServiceDate = startServiceDate;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getSubgroup() {
		return subgroup;
	}
	public void setSubgroup(String subgroup) {
		this.subgroup = subgroup;
	}
	
}
