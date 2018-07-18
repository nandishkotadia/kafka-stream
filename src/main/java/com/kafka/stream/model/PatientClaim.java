package com.kafka.stream.model;

import java.util.List;

public class PatientClaim {
	
	private String admit_date;
	private String billtype;
	private String birth_date;
	private String condcd;
	private String dstat;
	private String facility;
	private String from_date;
	private String npi;
	private String gdr_typ_id;
	private String taxonomy;
	private String thru_date;
	private String tot_chg;
	private List<ValueCodeDTO> ValueCodeList;
	private String sex;
	private String ex_criteria = "0";
	private String paysrc = "09";
	
	public String getPaysrc() {
		return paysrc;
	}
	public void setPaysrc(String paysrc) {
		this.paysrc = paysrc;
	}
	public List<ValueCodeDTO> getValueCodeList() {
		return ValueCodeList;
	}
	public void setValueCodeList(List<ValueCodeDTO> valueCodeList) {
		ValueCodeList = valueCodeList;
	}
	public String getEx_criteria() {
		return ex_criteria;
	}
	public void setEx_criteria(String ex_criteria) {
		this.ex_criteria = ex_criteria;
	}
	public String getAdmit_date() {
		return admit_date;
	}
	public void setAdmit_date(String admit_date) {
		this.admit_date = admit_date;
	}
	public String getBilltype() {
		return billtype;
	}
	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getCondcd() {
		return condcd;
	}
	public void setCondcd(String condcd) {
		this.condcd = condcd;
	}
	public String getDstat() {
		return dstat;
	}
	public void setDstat(String dstat) {
		this.dstat = dstat;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getNpi() {
		return npi;
	}
	public void setNpi(String npi) {
		this.npi = npi;
	}
	public String getGdr_typ_id() {
		return gdr_typ_id;
	}
	public void setGdr_typ_id(String gdr_typ_id) {
		this.gdr_typ_id = gdr_typ_id;
	}
	public String getTaxonomy() {
		return taxonomy;
	}
	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
	}
	public String getThru_date() {
		return thru_date;
	}
	public void setThru_date(String thru_date) {
		this.thru_date = thru_date;
	}
	public String getTot_chg() {
		return tot_chg;
	}
	public void setTot_chg(String tot_chg) {
		this.tot_chg = tot_chg;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
