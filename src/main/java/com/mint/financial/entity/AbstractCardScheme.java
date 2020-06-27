package com.mint.financial.entity;

public abstract class AbstractCardScheme {
private String type ;
private String scheme ;

public AbstractCardScheme(String type, String scheme) {
	this.type = type;
	this.scheme = scheme;
}

public AbstractCardScheme() {
	super();
}

public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getScheme() {
	return scheme;
}
public void setScheme(String scheme) {
	this.scheme = scheme;
}

}
