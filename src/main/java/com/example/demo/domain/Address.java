package com.example.demo.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

//값 타입은 데이터가 변경되면 안된다. 생성할 때만 값 쓰고 이후 데이터 변경 못하게 getter만 만들어 놓기.
@Embeddable //내장 가능
@Getter
public class Address {
	
	private String city;
	private String street;
	private String zipCode;
	
	public Address(String city, String street, String zipCode) {
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}
}
