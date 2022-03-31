package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	
	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;
	
	private String name;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "member") //읽기 전용, order 테이블에 있는 member 필드에 의해 매핑 되었다는 뜻.
	private List<Order> orders = new ArrayList<>();
	
}
/* 
회원과 주문의 관계가 OneToMany
각 클래스에서 각 객체를 갖고 있는 양방향 연관관계로
회원에서 주문을 변경할 수 있고 주문에서도 회원을 바꿀 수 있는 문제가 발생함
= 연관관계의 주인을 정해줘야 함
FK 업데이트를 한곳에서 하게 끔 FK를 갖고 있는 클래스를 주인으로 설정함
회원 쪽 정보 업데이트해도 주문 쪽 FK 정보 변경 안됨
 */

