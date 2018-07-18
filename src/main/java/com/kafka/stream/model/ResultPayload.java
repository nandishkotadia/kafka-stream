package com.kafka.stream.model;

public class ResultPayload {

	private CCATPayload ccatPayload;
	private EzgrpPayload ezgrpPayload;
	
	public CCATPayload getCcatPayload() {
		return ccatPayload;
	}
	public void setCcatPayload(CCATPayload ccatPayload) {
		this.ccatPayload = ccatPayload;
	}
	public EzgrpPayload getEzgrpPayload() {
		return ezgrpPayload;
	}
	public void setEzgrpPayload(EzgrpPayload ezgrpPayload) {
		this.ezgrpPayload = ezgrpPayload;
	}
	
}
