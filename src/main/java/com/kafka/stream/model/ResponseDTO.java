package com.kafka.stream.model;

public class ResponseDTO {
	private MetaData meta;
	private Long data;
	private Status status;
	public MetaData getMeta() {
		return meta;
	}
	public void setMeta(MetaData meta) {
		this.meta = meta;
	}
	public Long getData() {
		return data;
	}
	public void setData(Long data) {
		this.data = data;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
