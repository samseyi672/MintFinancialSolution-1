package com.mint.financial.entity;

public class FormattedCardScheme extends AbstractCardScheme {
	private String bank  ;
	private String type ;
	private String scheme ;
	
	public FormattedCardScheme(String type, String scheme, String bank) {
		super(type, scheme);
		this.bank = bank;
	}
	public FormattedCardScheme() {
		super();
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	
}
