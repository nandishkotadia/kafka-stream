package com.kafka.stream.model;

import java.util.Date;

public class Payload {

	private Long sbscr_indv_id;
	private String sbscr_src_sys_id;
	private Date sbscr_eff_dtl_dt;
	private String enrlee_indv_id;
	private Long enrlee_src_sys_id;
	private Date enrlee_eff_dtl_dt;
	private Date mbrshp_eff_dt;
	private String cust_acct_id;
	private Long cust_acct_src_sys_id;
	private Date cust_acct_eff_dtl_dt;
	private String cust_pchs_id;
	private Date cust_pchs_eff_dtl_dt;
	private Date mbrshp_eff_dtl_dt;
	private Long cob_ins_typ_id;
	private Date cob_ins_eff_dt;
	private String cob_ins_carr_nbr;
	private Long cob_cov_typ_id;
	private Date eff_dtl_dt;
	private String creat_user_id;
	private Date creat_tmstmp;
	private String updt_user_id;
	private Date updt_tmstmp;
	private Long aud_id;
	private Date canc_dtl_dt;
	private String medcr_entl_typ_id;
	private Long payr_resp_seq_id;
	private Long cob_rsn_typ_id;
	private String cob_ins_carr_nm;
	private String cob_ins_carr_tax_id;
	private String cob_ins_pol_nbr;
	private String cob_ins_pol_nm;
	private Long custd_typ_id;
	private String cob_ins_tel_nbr;
	private Date cob_ins_verf_dt;
	private Date cob_ins_trm_dt;
	private String cob_ins_ee_id;
	private String cob_ins_ee_2_id;
	private String cob_ins_ee_3_id;
	private String hcfa_nat_pln_id;
	private String cob_note_txt;
	private Integer cob_ins_pst_adr_surrg_key;
	private String adr_ln_1_txt;
	private String adr_ln_2_txt;
	private String adr_ln_3_txt;
	private String adr_ln_4_txt;
	private String adr_ln_5_txt;
	private Integer adr_vld_id;
	private String adr_guid_ver;
	private String res_dlvr_ind;
	private String adr_hse_nbr_wo_frac;
	private String adr_hse_nbr_frac;
	private String adr_str_prfx_dir;
	private String adr_str_nm;
	private String adr_str_sufx_abbr;
	private String adr_str_sufx_dir;
	private String adr_sec_unit_desg;
	private String adr_sec_unit_qual;
	private String adr_po_box_nbr;
	private String adr_rte_desg;
	private String adr_box_nbr;
	private String adr_urbn_nm;
	private String adr_tn_nm;
	private String adr_dlvr_pt_cd;
	private String adr_dlvr_pt_chk_dgt;
	private String adr_carr_rte_cd;
	private String lat_deg;
	private String lng_deg;
	private String pst_cd;
	private String pst_prfx_cd;
	private String pst_sufx_cd;
	private String pst_desc;
	private Long tm_zone_id;
	private String tm_zone_nm;
	private String utc_ofst_tm;
	private Long iso_cntry_id;
	private Long iso_cntry_subdiv_id;
	private Long fips_st_id;
	private Long fips_cnty_id;
	private Long st_prvc_id;
	private String del_in;
	
	public Long getSbscr_indv_id() {
		return sbscr_indv_id;
	}
	public void setSbscr_indv_id(Long sbscr_indv_id) {
		this.sbscr_indv_id = sbscr_indv_id;
	}
	public String getSbscr_src_sys_id() {
		return sbscr_src_sys_id;
	}
	public void setSbscr_src_sys_id(String sbscr_src_sys_id) {
		this.sbscr_src_sys_id = sbscr_src_sys_id;
	}
	public Date getSbscr_eff_dtl_dt() {
		return sbscr_eff_dtl_dt;
	}
	public void setSbscr_eff_dtl_dt(Date sbscr_eff_dtl_dt) {
		this.sbscr_eff_dtl_dt = sbscr_eff_dtl_dt;
	}
	public String getEnrlee_indv_id() {
		return enrlee_indv_id;
	}
	public void setEnrlee_indv_id(String enrlee_indv_id) {
		this.enrlee_indv_id = enrlee_indv_id;
	}
	public Long getEnrlee_src_sys_id() {
		return enrlee_src_sys_id;
	}
	public void setEnrlee_src_sys_id(Long enrlee_src_sys_id) {
		this.enrlee_src_sys_id = enrlee_src_sys_id;
	}
	public Date getEnrlee_eff_dtl_dt() {
		return enrlee_eff_dtl_dt;
	}
	public void setEnrlee_eff_dtl_dt(Date enrlee_eff_dtl_dt) {
		this.enrlee_eff_dtl_dt = enrlee_eff_dtl_dt;
	}
	public Date getMbrshp_eff_dt() {
		return mbrshp_eff_dt;
	}
	public void setMbrshp_eff_dt(Date mbrshp_eff_dt) {
		this.mbrshp_eff_dt = mbrshp_eff_dt;
	}
	public String getCust_acct_id() {
		return cust_acct_id;
	}
	public void setCust_acct_id(String cust_acct_id) {
		this.cust_acct_id = cust_acct_id;
	}
	public Long getCust_acct_src_sys_id() {
		return cust_acct_src_sys_id;
	}
	public void setCust_acct_src_sys_id(Long cust_acct_src_sys_id) {
		this.cust_acct_src_sys_id = cust_acct_src_sys_id;
	}
	public Date getCust_acct_eff_dtl_dt() {
		return cust_acct_eff_dtl_dt;
	}
	public void setCust_acct_eff_dtl_dt(Date cust_acct_eff_dtl_dt) {
		this.cust_acct_eff_dtl_dt = cust_acct_eff_dtl_dt;
	}
	public String getCust_pchs_id() {
		return cust_pchs_id;
	}
	public void setCust_pchs_id(String cust_pchs_id) {
		this.cust_pchs_id = cust_pchs_id;
	}
	public Date getCust_pchs_eff_dtl_dt() {
		return cust_pchs_eff_dtl_dt;
	}
	public void setCust_pchs_eff_dtl_dt(Date cust_pchs_eff_dtl_dt) {
		this.cust_pchs_eff_dtl_dt = cust_pchs_eff_dtl_dt;
	}
	public Date getMbrshp_eff_dtl_dt() {
		return mbrshp_eff_dtl_dt;
	}
	public void setMbrshp_eff_dtl_dt(Date mbrshp_eff_dtl_dt) {
		this.mbrshp_eff_dtl_dt = mbrshp_eff_dtl_dt;
	}
	public Long getCob_ins_typ_id() {
		return cob_ins_typ_id;
	}
	public void setCob_ins_typ_id(Long cob_ins_typ_id) {
		this.cob_ins_typ_id = cob_ins_typ_id;
	}
	public Date getCob_ins_eff_dt() {
		return cob_ins_eff_dt;
	}
	public void setCob_ins_eff_dt(Date cob_ins_eff_dt) {
		this.cob_ins_eff_dt = cob_ins_eff_dt;
	}
	public String getCob_ins_carr_nbr() {
		return cob_ins_carr_nbr;
	}
	public void setCob_ins_carr_nbr(String cob_ins_carr_nbr) {
		this.cob_ins_carr_nbr = cob_ins_carr_nbr;
	}
	public Long getCob_cov_typ_id() {
		return cob_cov_typ_id;
	}
	public void setCob_cov_typ_id(Long cob_cov_typ_id) {
		this.cob_cov_typ_id = cob_cov_typ_id;
	}
	public Date getEff_dtl_dt() {
		return eff_dtl_dt;
	}
	public void setEff_dtl_dt(Date eff_dtl_dt) {
		this.eff_dtl_dt = eff_dtl_dt;
	}
	public String getCreat_user_id() {
		return creat_user_id;
	}
	public void setCreat_user_id(String creat_user_id) {
		this.creat_user_id = creat_user_id;
	}
	public Date getCreat_tmstmp() {
		return creat_tmstmp;
	}
	public void setCreat_tmstmp(Date creat_tmstmp) {
		this.creat_tmstmp = creat_tmstmp;
	}
	public String getUpdt_user_id() {
		return updt_user_id;
	}
	public void setUpdt_user_id(String updt_user_id) {
		this.updt_user_id = updt_user_id;
	}
	public Date getUpdt_tmstmp() {
		return updt_tmstmp;
	}
	public void setUpdt_tmstmp(Date updt_tmstmp) {
		this.updt_tmstmp = updt_tmstmp;
	}
	public Long getAud_id() {
		return aud_id;
	}
	public void setAud_id(Long aud_id) {
		this.aud_id = aud_id;
	}
	public Date getCanc_dtl_dt() {
		return canc_dtl_dt;
	}
	public void setCanc_dtl_dt(Date canc_dtl_dt) {
		this.canc_dtl_dt = canc_dtl_dt;
	}
	public String getMedcr_entl_typ_id() {
		return medcr_entl_typ_id;
	}
	public void setMedcr_entl_typ_id(String medcr_entl_typ_id) {
		this.medcr_entl_typ_id = medcr_entl_typ_id;
	}
	public Long getPayr_resp_seq_id() {
		return payr_resp_seq_id;
	}
	public void setPayr_resp_seq_id(Long payr_resp_seq_id) {
		this.payr_resp_seq_id = payr_resp_seq_id;
	}
	public Long getCob_rsn_typ_id() {
		return cob_rsn_typ_id;
	}
	public void setCob_rsn_typ_id(Long cob_rsn_typ_id) {
		this.cob_rsn_typ_id = cob_rsn_typ_id;
	}
	public String getCob_ins_carr_nm() {
		return cob_ins_carr_nm;
	}
	public void setCob_ins_carr_nm(String cob_ins_carr_nm) {
		this.cob_ins_carr_nm = cob_ins_carr_nm;
	}
	public String getCob_ins_carr_tax_id() {
		return cob_ins_carr_tax_id;
	}
	public void setCob_ins_carr_tax_id(String cob_ins_carr_tax_id) {
		this.cob_ins_carr_tax_id = cob_ins_carr_tax_id;
	}
	public String getCob_ins_pol_nbr() {
		return cob_ins_pol_nbr;
	}
	public void setCob_ins_pol_nbr(String cob_ins_pol_nbr) {
		this.cob_ins_pol_nbr = cob_ins_pol_nbr;
	}
	public String getCob_ins_pol_nm() {
		return cob_ins_pol_nm;
	}
	public void setCob_ins_pol_nm(String cob_ins_pol_nm) {
		this.cob_ins_pol_nm = cob_ins_pol_nm;
	}
	public Long getCustd_typ_id() {
		return custd_typ_id;
	}
	public void setCustd_typ_id(Long custd_typ_id) {
		this.custd_typ_id = custd_typ_id;
	}
	public String getCob_ins_tel_nbr() {
		return cob_ins_tel_nbr;
	}
	public void setCob_ins_tel_nbr(String cob_ins_tel_nbr) {
		this.cob_ins_tel_nbr = cob_ins_tel_nbr;
	}
	public Date getCob_ins_verf_dt() {
		return cob_ins_verf_dt;
	}
	public void setCob_ins_verf_dt(Date cob_ins_verf_dt) {
		this.cob_ins_verf_dt = cob_ins_verf_dt;
	}
	public Date getCob_ins_trm_dt() {
		return cob_ins_trm_dt;
	}
	public void setCob_ins_trm_dt(Date cob_ins_trm_dt) {
		this.cob_ins_trm_dt = cob_ins_trm_dt;
	}
	public String getCob_ins_ee_id() {
		return cob_ins_ee_id;
	}
	public void setCob_ins_ee_id(String cob_ins_ee_id) {
		this.cob_ins_ee_id = cob_ins_ee_id;
	}
	public String getCob_ins_ee_2_id() {
		return cob_ins_ee_2_id;
	}
	public void setCob_ins_ee_2_id(String cob_ins_ee_2_id) {
		this.cob_ins_ee_2_id = cob_ins_ee_2_id;
	}
	public String getCob_ins_ee_3_id() {
		return cob_ins_ee_3_id;
	}
	public void setCob_ins_ee_3_id(String cob_ins_ee_3_id) {
		this.cob_ins_ee_3_id = cob_ins_ee_3_id;
	}
	public String getHcfa_nat_pln_id() {
		return hcfa_nat_pln_id;
	}
	public void setHcfa_nat_pln_id(String hcfa_nat_pln_id) {
		this.hcfa_nat_pln_id = hcfa_nat_pln_id;
	}
	public String getCob_note_txt() {
		return cob_note_txt;
	}
	public void setCob_note_txt(String cob_note_txt) {
		this.cob_note_txt = cob_note_txt;
	}
	public Integer getCob_ins_pst_adr_surrg_key() {
		return cob_ins_pst_adr_surrg_key;
	}
	public void setCob_ins_pst_adr_surrg_key(Integer cob_ins_pst_adr_surrg_key) {
		this.cob_ins_pst_adr_surrg_key = cob_ins_pst_adr_surrg_key;
	}
	public String getAdr_ln_1_txt() {
		return adr_ln_1_txt;
	}
	public void setAdr_ln_1_txt(String adr_ln_1_txt) {
		this.adr_ln_1_txt = adr_ln_1_txt;
	}
	public String getAdr_ln_2_txt() {
		return adr_ln_2_txt;
	}
	public void setAdr_ln_2_txt(String adr_ln_2_txt) {
		this.adr_ln_2_txt = adr_ln_2_txt;
	}
	public String getAdr_ln_3_txt() {
		return adr_ln_3_txt;
	}
	public void setAdr_ln_3_txt(String adr_ln_3_txt) {
		this.adr_ln_3_txt = adr_ln_3_txt;
	}
	public String getAdr_ln_4_txt() {
		return adr_ln_4_txt;
	}
	public void setAdr_ln_4_txt(String adr_ln_4_txt) {
		this.adr_ln_4_txt = adr_ln_4_txt;
	}
	public String getAdr_ln_5_txt() {
		return adr_ln_5_txt;
	}
	public void setAdr_ln_5_txt(String adr_ln_5_txt) {
		this.adr_ln_5_txt = adr_ln_5_txt;
	}
	public Integer getAdr_vld_id() {
		return adr_vld_id;
	}
	public void setAdr_vld_id(Integer adr_vld_id) {
		this.adr_vld_id = adr_vld_id;
	}
	public String getAdr_guid_ver() {
		return adr_guid_ver;
	}
	public void setAdr_guid_ver(String adr_guid_ver) {
		this.adr_guid_ver = adr_guid_ver;
	}
	public String getRes_dlvr_ind() {
		return res_dlvr_ind;
	}
	public void setRes_dlvr_ind(String res_dlvr_ind) {
		this.res_dlvr_ind = res_dlvr_ind;
	}
	public String getAdr_hse_nbr_wo_frac() {
		return adr_hse_nbr_wo_frac;
	}
	public void setAdr_hse_nbr_wo_frac(String adr_hse_nbr_wo_frac) {
		this.adr_hse_nbr_wo_frac = adr_hse_nbr_wo_frac;
	}
	public String getAdr_hse_nbr_frac() {
		return adr_hse_nbr_frac;
	}
	public void setAdr_hse_nbr_frac(String adr_hse_nbr_frac) {
		this.adr_hse_nbr_frac = adr_hse_nbr_frac;
	}
	public String getAdr_str_prfx_dir() {
		return adr_str_prfx_dir;
	}
	public void setAdr_str_prfx_dir(String adr_str_prfx_dir) {
		this.adr_str_prfx_dir = adr_str_prfx_dir;
	}
	public String getAdr_str_nm() {
		return adr_str_nm;
	}
	public void setAdr_str_nm(String adr_str_nm) {
		this.adr_str_nm = adr_str_nm;
	}
	public String getAdr_str_sufx_abbr() {
		return adr_str_sufx_abbr;
	}
	public void setAdr_str_sufx_abbr(String adr_str_sufx_abbr) {
		this.adr_str_sufx_abbr = adr_str_sufx_abbr;
	}
	public String getAdr_str_sufx_dir() {
		return adr_str_sufx_dir;
	}
	public void setAdr_str_sufx_dir(String adr_str_sufx_dir) {
		this.adr_str_sufx_dir = adr_str_sufx_dir;
	}
	public String getAdr_sec_unit_desg() {
		return adr_sec_unit_desg;
	}
	public void setAdr_sec_unit_desg(String adr_sec_unit_desg) {
		this.adr_sec_unit_desg = adr_sec_unit_desg;
	}
	public String getAdr_sec_unit_qual() {
		return adr_sec_unit_qual;
	}
	public void setAdr_sec_unit_qual(String adr_sec_unit_qual) {
		this.adr_sec_unit_qual = adr_sec_unit_qual;
	}
	public String getAdr_po_box_nbr() {
		return adr_po_box_nbr;
	}
	public void setAdr_po_box_nbr(String adr_po_box_nbr) {
		this.adr_po_box_nbr = adr_po_box_nbr;
	}
	public String getAdr_rte_desg() {
		return adr_rte_desg;
	}
	public void setAdr_rte_desg(String adr_rte_desg) {
		this.adr_rte_desg = adr_rte_desg;
	}
	public String getAdr_box_nbr() {
		return adr_box_nbr;
	}
	public void setAdr_box_nbr(String adr_box_nbr) {
		this.adr_box_nbr = adr_box_nbr;
	}
	public String getAdr_urbn_nm() {
		return adr_urbn_nm;
	}
	public void setAdr_urbn_nm(String adr_urbn_nm) {
		this.adr_urbn_nm = adr_urbn_nm;
	}
	public String getAdr_tn_nm() {
		return adr_tn_nm;
	}
	public void setAdr_tn_nm(String adr_tn_nm) {
		this.adr_tn_nm = adr_tn_nm;
	}
	public String getAdr_dlvr_pt_cd() {
		return adr_dlvr_pt_cd;
	}
	public void setAdr_dlvr_pt_cd(String adr_dlvr_pt_cd) {
		this.adr_dlvr_pt_cd = adr_dlvr_pt_cd;
	}
	public String getAdr_dlvr_pt_chk_dgt() {
		return adr_dlvr_pt_chk_dgt;
	}
	public void setAdr_dlvr_pt_chk_dgt(String adr_dlvr_pt_chk_dgt) {
		this.adr_dlvr_pt_chk_dgt = adr_dlvr_pt_chk_dgt;
	}
	public String getAdr_carr_rte_cd() {
		return adr_carr_rte_cd;
	}
	public void setAdr_carr_rte_cd(String adr_carr_rte_cd) {
		this.adr_carr_rte_cd = adr_carr_rte_cd;
	}
	public String getLat_deg() {
		return lat_deg;
	}
	public void setLat_deg(String lat_deg) {
		this.lat_deg = lat_deg;
	}
	public String getLng_deg() {
		return lng_deg;
	}
	public void setLng_deg(String lng_deg) {
		this.lng_deg = lng_deg;
	}
	public String getPst_cd() {
		return pst_cd;
	}
	public void setPst_cd(String pst_cd) {
		this.pst_cd = pst_cd;
	}
	public String getPst_prfx_cd() {
		return pst_prfx_cd;
	}
	public void setPst_prfx_cd(String pst_prfx_cd) {
		this.pst_prfx_cd = pst_prfx_cd;
	}
	public String getPst_sufx_cd() {
		return pst_sufx_cd;
	}
	public void setPst_sufx_cd(String pst_sufx_cd) {
		this.pst_sufx_cd = pst_sufx_cd;
	}
	public String getPst_desc() {
		return pst_desc;
	}
	public void setPst_desc(String pst_desc) {
		this.pst_desc = pst_desc;
	}
	public Long getTm_zone_id() {
		return tm_zone_id;
	}
	public void setTm_zone_id(Long tm_zone_id) {
		this.tm_zone_id = tm_zone_id;
	}
	public String getTm_zone_nm() {
		return tm_zone_nm;
	}
	public void setTm_zone_nm(String tm_zone_nm) {
		this.tm_zone_nm = tm_zone_nm;
	}
	public String getUtc_ofst_tm() {
		return utc_ofst_tm;
	}
	public void setUtc_ofst_tm(String utc_ofst_tm) {
		this.utc_ofst_tm = utc_ofst_tm;
	}
	public Long getIso_cntry_id() {
		return iso_cntry_id;
	}
	public void setIso_cntry_id(Long iso_cntry_id) {
		this.iso_cntry_id = iso_cntry_id;
	}
	public Long getIso_cntry_subdiv_id() {
		return iso_cntry_subdiv_id;
	}
	public void setIso_cntry_subdiv_id(Long iso_cntry_subdiv_id) {
		this.iso_cntry_subdiv_id = iso_cntry_subdiv_id;
	}
	public Long getFips_st_id() {
		return fips_st_id;
	}
	public void setFips_st_id(Long fips_st_id) {
		this.fips_st_id = fips_st_id;
	}
	public Long getFips_cnty_id() {
		return fips_cnty_id;
	}
	public void setFips_cnty_id(Long fips_cnty_id) {
		this.fips_cnty_id = fips_cnty_id;
	}
	public Long getSt_prvc_id() {
		return st_prvc_id;
	}
	public void setSt_prvc_id(Long st_prvc_id) {
		this.st_prvc_id = st_prvc_id;
	}
	public String getDel_in() {
		return del_in;
	}
	public void setDel_in(String del_in) {
		this.del_in = del_in;
	}
	
	
}
