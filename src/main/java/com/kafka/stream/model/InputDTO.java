package com.kafka.stream.model;

import java.util.List;

public class InputDTO {
	private EzgControl EzgControl;
	private PatientClaim PatientClaim;
	private List<Dx> Dx;
	private List<Line> Line;
	private String Op;
	
	public String getOp() {
		return Op;
	}
	public void setOp(String op) {
		Op = op;
	}
	public EzgControl getEzgControl() {
		return EzgControl;
	}
	public void setEzgControl(EzgControl ezgControl) {
		EzgControl = ezgControl;
	}
	public PatientClaim getPatientClaim() {
		return PatientClaim;
	}
	public void setPatientClaim(PatientClaim patientClaim) {
		PatientClaim = patientClaim;
	}
	public List<Dx> getDx() {
		return Dx;
	}
	public void setDx(List<Dx> dx) {
		Dx = dx;
	}
	public List<Line> getLine() {
		return Line;
	}
	public void setLine(List<Line> line) {
		Line = line;
	}
}
