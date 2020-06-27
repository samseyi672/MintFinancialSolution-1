package com.mint.financial.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//this  is to monitor the no  of hits 
@Document(collection="atmscheme")
public class NoOfHits {
	
@Id
private String id ;

private String size ;

private String success ;

private String limits ;

private String start ;

public String getStart() {
	return start;
}
public void setStart(String start) {
	this.start = start;
}
public String getSuccess() {
	return success;
}
public void setSuccess(String success) {
	this.success = success;
}
public String getLimits() {
	return limits;
}
public void setLimits(String limits) {
	this.limits = limits;
}
@Field(name="payload")
private PayLoad payLoad ;

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getSize() {
	return size;
}
public void setSize(String size) {
	this.size = size;
}
public PayLoad getPayload() {
	return payLoad;
}
public void setPayload(PayLoad payload) {
	this.payLoad = payload;
}

}







































































































