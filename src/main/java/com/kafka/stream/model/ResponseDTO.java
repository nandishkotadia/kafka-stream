package com.kafka.stream.model;

public class ResponseDTO {
	private MetaData meta;
	private Data data;
	private Status status;
	public MetaData getMeta() {
		return meta;
	}
	public void setMeta(MetaData meta) {
		this.meta = meta;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
