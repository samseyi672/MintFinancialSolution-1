package com.mint.financial.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public class PayLoad {
	
 private String type ;
 
 private String bank ;
 
 @Field(name="cardnumber")
 private String cardNumber  ;
 
 @Field(name="cardcount")
 private String cardCount  ;

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getBank() {
	return bank;
}

public void setBank(String bank) {
	this.bank = bank;
}

public String getCardNumber() {
	return cardNumber;
}

public void setCardNumber(String cardNumber) {
	this.cardNumber = cardNumber;
}

public String getCardCount() {
	return cardCount;
}

public void setCardCount(String cardCount) {
	this.cardCount = cardCount;
}
 
}