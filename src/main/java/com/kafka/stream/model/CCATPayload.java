package com.kafka.stream.model;

public class CCATPayload {

	private String businesssegment;
	private String platform;
	private String product;
	private String subgroup;
	private String strt_srvc_dt;
	private String srvc_loc_prov_id;
	private String clm_id;
	private Long contractId;
	public String getBusinesssegment() {
		return businesssegment;
	}
	public void setBusinesssegment(String businesssegment) {
		this.businesssegment = businesssegment;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getSubgroup() {
		return subgroup;
	}
	public void setSubgroup(String subgroup) {
		this.subgroup = subgroup;
	}
	public String getStrt_srvc_dt() {
		return strt_srvc_dt;
	}
	public void setStrt_srvc_dt(String strt_srvc_dt) {
		this.strt_srvc_dt = strt_srvc_dt;
	}
	public String getSrvc_loc_prov_id() {
		return srvc_loc_prov_id;
	}
	public void setSrvc_loc_prov_id(String srvc_loc_prov_id) {
		this.srvc_loc_prov_id = srvc_loc_prov_id;
	}
	public String getClm_id() {
		return clm_id;
	}
	public void setClm_id(String clm_id) {
		this.clm_id = clm_id;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}


}
