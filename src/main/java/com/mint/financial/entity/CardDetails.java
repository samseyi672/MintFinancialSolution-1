package com.mint.financial.entity;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDetails {

 @JsonProperty(value = "success")
private  boolean  success ;
 
private FormattedCardScheme payload  ;

public CardDetails() {
	super();
}
public boolean isSuccess() {
	return success;
}
public void setSuccess(boolean success) {
	this.success = success;
}
@JsonProperty(value="payload")
public FormattedCardScheme getPayLoad() {
	return payload;
}
public void setPayLoad(FormattedCardScheme payload) {
	this.payload = payload;
}
@Override
public String toString() {
	return "CardDetails [success=" + success + ", payload=" + payload + "]";
}

 }
