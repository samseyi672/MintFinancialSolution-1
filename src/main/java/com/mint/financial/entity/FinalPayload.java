package com.mint.financial.entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinalPayload {
private String card ;
private String count ;

//
//public FinalPayload(String card, String count) {
//	super();
//	this.card = card;
//	this.count = count;
//}
public FinalPayload() {
	super();
}

public String getCard() {
	return card;
}
public void setCard(String card) {
	this.card = card;
}
public String getCount() {
	return count;
}
public void setCount(String count) {
	this.count = count;
}

}




































