package com.mint.financial.entity;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FinalOutput {
private String success ;
private String start ;
private String limit ;
private List<FinalPayload> payload ;

public FinalOutput(String success, String start, String limit, List<FinalPayload> payload) {
	super();
	this.success = success;
	this.start = start;
	this.limit = limit;
	this.payload = payload;
}

public FinalOutput() {
	super();
}

public List<FinalPayload> getPayload() {
	return payload;
}
public void setPayload(List<FinalPayload> payload) {
	this.payload = payload;
}
public String getSuccess() {
	return success;
}
public void setSuccess(String success) {
	this.success = success;
}
public String getStart() {
	return start;
}
public void setStart(String start) {
	this.start = start;
}
public String getLimit() {
	return limit;
}
public void setLimit(String limit) {
	this.limit = limit;
}

}
