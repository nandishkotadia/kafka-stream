package com.kafka.stream.model;

import java.util.List;

public class Line {

	
	private String date;
	private String hcpcs;
	private String rev;
	private String tot_units;
	private String charges;
	private List<String> mod;
	private String pos;
	
	public List<String> getMod() {
		return mod;
	}
	public void setMod(List<String> mod) {
		this.mod = mod;
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
	
	
}
