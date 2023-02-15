package com.retailstore.Customer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int addressrId;
	int doorNo;
	int streetNo;
	String layout;
	String city;
	int pincode;
	@OneToOne(mappedBy = "billingAddress")
	@JsonIgnore
	Customer bcustomer;
	@OneToOne(mappedBy = "shippingAddress")
	@JsonIgnore
	Customer scustomer;
	public int getAddressrId() {
		return addressrId;
	}
	public void setAddressrId(int addressrId) {
		this.addressrId = addressrId;
	}
	public int getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(int doorNo) {
		this.doorNo = doorNo;
	}
	public int getStreetNo() {
		return streetNo;
	}
	public void setStreetNo(int streetNo) {
		this.streetNo = streetNo;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public Customer getBcustomer() {
		return bcustomer;
	}
	public void setBcustomer(Customer bcustomer) {
		this.bcustomer = bcustomer;
	}
	public Customer getScustomer() {
		return scustomer;
	}
	public void setScustomer(Customer scustomer) {
		this.scustomer = scustomer;
	}
	
	
}
