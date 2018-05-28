package com.kafka.stream.model;

public class ResultPayload extends Payload{

	private Long contractId;
	
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
}
