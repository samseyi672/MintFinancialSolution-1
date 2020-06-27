package com.mint.financial.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardScheme extends AbstractCardScheme {

private HashMap<String,String>   bank  ;
private String type ;
private String scheme ;

public CardScheme(HashMap<String, String> bank, String type, String scheme) {
	super(type,scheme) ;
	this.bank = bank;
}

public CardScheme() {
	super();
}

public HashMap<String, String> getBank() {
	return bank;
}

public void setBank(HashMap<String, String> bank) {
	this.bank = bank;
}

}
