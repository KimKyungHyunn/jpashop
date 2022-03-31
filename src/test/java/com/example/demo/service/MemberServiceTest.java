package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	@Test
	@Rollback(false) 
	//test에서는 트렌젝션 동작 후 롤백하여 데이터를 초기화함..데이터 삽입을 확인하고 싶으면 롤백 못하게 false로 표기 
	public void 회원가입() throws Exception{
		//given
		Member member = new Member();
		member.setName("Kim");
		
		//when
		Long savedId = memberService.join(member);
		
		//then
		assertEquals(member, memberRepository.findOne(savedId));
	}

	@Test(expected = IllegalStateException.class)
	public void 중복회원예외() throws Exception{
		//given
		Member member1 = new Member();
		member1.setName("Kim");
		
		Member member2 = new Member();
		member2.setName("Kim");
				
		//when
		memberService.join(member1);
		try {
			memberService.join(member2);//예외발생해야함
		} catch(IllegalStateException e) {
			return;
		}
					
		//then
		fail("예외발생해야한다");
	}
}
