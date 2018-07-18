package com.kafka.stream.model;

public class EzgControl {

	private String code_class;
	private String pattype;
	private String opcode1 = "16";
	
	public String getCode_class() {
		return code_class;
	}
	public void setCode_class(String code_class) {
		this.code_class = code_class;
	}
	public String getPattype() {
		return pattype;
	}
	public void setPattype(String pattype) {
		this.pattype = pattype;
	}
	public String getOpcode1() {
		return opcode1;
	}
	public void setOpcode1(String opcode1) {
		this.opcode1 = opcode1;
	}
	
	
}
