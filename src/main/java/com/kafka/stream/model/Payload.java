package com.kafka.stream.model;

public class Payload {

	/*private String clm_loc_typ_cd;
	private String clm_adjd_pltfm_id;
	private String clm_fl_id;
	private String strt_srvc_dt;
	private String subgroup;
	private String mng_hlth_subpln_nm;
	private String mng_hlth_pln_nm;
	private String srvc_loc_prov_id;
	private String clm_id;
	private Long ptnt_src_sys_id;
	private String bus_seg_id;
	private String product;*/
	private String businesssegment;
	private String platform;
	private String product;
	private String subgroup;
	private String strt_srvc_dt;
	private String srvc_loc_prov_id;
	private String clm_id;
	private String pst_dt;
	private String dx;
	private String poa;
	private String code_class;
	private String charges;
	private String date;
	private String hcpcs;
	private String mod_1;
	private String mod_2;
	private String mod_3;
	private String mod_4;
	private String pos;
	private String rev;
	private String tot_units;
	private String op;
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
	private String valamt1;
	private String valamt2;
	private String valamt3;
	private String valamt4;
	private String valamt5;
	private String valamt6;
	private String valamt7;
	private String valamt8;
	private String valcode1;
	private String valcode2;
	private String valcode3;
	private String valcode4;
	private String valcode5;
	private String valcode6;
	private String valcode7;
	private String valcode8;
	private String pattype;
	private String se;
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
	public String getPst_dt() {
		return pst_dt;
	}
	public void setPst_dt(String pst_dt) {
		this.pst_dt = pst_dt;
	}
	public String getDx() {
		return dx;
	}
	public void setDx(String dx) {
		this.dx = dx;
	}
	public String getPoa() {
		return poa;
	}
	public void setPoa(String poa) {
		this.poa = poa;
	}
	public String getCode_class() {
		return code_class;
	}
	public void setCode_class(String code_class) {
		this.code_class = code_class;
	}
	public String getCharges() {
		return charges;
	}
	public void setCharges(String charges) {
		this.charges = charges;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHcpcs() {
		return hcpcs;
	}
	public void setHcpcs(String hcpcs) {
		this.hcpcs = hcpcs;
	}
	public String getMod_1() {
		return mod_1;
	}
	public void setMod_1(String mod_1) {
		this.mod_1 = mod_1;
	}
	public String getMod_2() {
		return mod_2;
	}
	public void setMod_2(String mod_2) {
		this.mod_2 = mod_2;
	}
	public String getMod_3() {
		return mod_3;
	}
	public void setMod_3(String mod_3) {
		this.mod_3 = mod_3;
	}
	public String getMod_4() {
		return mod_4;
	}
	public void setMod_4(String mod_4) {
		this.mod_4 = mod_4;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getRev() {
		return rev;
	}
	public void setRev(String rev) {
		this.rev = rev;
	}
	public String getTot_units() {
		return tot_units;
	}
	public void setTot_units(String tot_units) {
		this.tot_units = tot_units;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
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
	public String getValamt1() {
		return valamt1;
	}
	public void setValamt1(String valamt1) {
		this.valamt1 = valamt1;
	}
	public String getValamt2() {
		return valamt2;
	}
	public void setValamt2(String valamt2) {
		this.valamt2 = valamt2;
	}
	public String getValamt3() {
		return valamt3;
	}
	public void setValamt3(String valamt3) {
		this.valamt3 = valamt3;
	}
	public String getValamt4() {
		return valamt4;
	}
	public void setValamt4(String valamt4) {
		this.valamt4 = valamt4;
	}
	public String getValamt5() {
		return valamt5;
	}
	public void setValamt5(String valamt5) {
		this.valamt5 = valamt5;
	}
	public String getValamt6() {
		return valamt6;
	}
	public void setValamt6(String valamt6) {
		this.valamt6 = valamt6;
	}
	public String getValamt7() {
		return valamt7;
	}
	public void setValamt7(String valamt7) {
		this.valamt7 = valamt7;
	}
	public String getValamt8() {
		return valamt8;
	}
	public void setValamt8(String valamt8) {
		this.valamt8 = valamt8;
	}
	public String getValcode1() {
		return valcode1;
	}
	public void setValcode1(String valcode1) {
		this.valcode1 = valcode1;
	}
	public String getValcode2() {
		return valcode2;
	}
	public void setValcode2(String valcode2) {
		this.valcode2 = valcode2;
	}
	public String getValcode3() {
		return valcode3;
	}
	public void setValcode3(String valcode3) {
		this.valcode3 = valcode3;
	}
	public String getValcode4() {
		return valcode4;
	}
	public void setValcode4(String valcode4) {
		this.valcode4 = valcode4;
	}
	public String getValcode5() {
		return valcode5;
	}
	public void setValcode5(String valcode5) {
		this.valcode5 = valcode5;
	}
	public String getValcode6() {
		return valcode6;
	}
	public void setValcode6(String valcode6) {
		this.valcode6 = valcode6;
	}
	public String getValcode7() {
		return valcode7;
	}
	public void setValcode7(String valcode7) {
		this.valcode7 = valcode7;
	}
	public String getValcode8() {
		return valcode8;
	}
	public void setValcode8(String valcode8) {
		this.valcode8 = valcode8;
	}
	public String getPattype() {
		return pattype;
	}
	public void setPattype(String pattype) {
		this.pattype = pattype;
	}
	public String getSe() {
		return se;
	}
	public void setSe(String se) {
		this.se = se;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	
}
