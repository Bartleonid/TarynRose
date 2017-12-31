package com.taryn.items;

public class CreditCard {

	public String cardNumber;
	public String expiration;
	public String cvv;
	public String nameOnCard;
	public String completeName;
	public String shipAddress;
	public String state;
	public String shipCiity;
	public String phone;

	public CreditCard(String cardNumber, String expiration, String cvv, String nameOnCard, String completeName,
			String shipAddress, String state, String shipCiity, String phone) {
		this.cardNumber = cardNumber;
		this.expiration = expiration;
		this.cvv = cvv;
		this.nameOnCard = nameOnCard;
		this.completeName = completeName;
		this.shipAddress = shipAddress;
		this.state = state;
		this.shipCiity = shipCiity;
		this.phone = phone;

	}

}
