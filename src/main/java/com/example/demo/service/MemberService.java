package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
/* 
읽기 전용이라고 말해줘서 성능 최적화 가능
jpa는 transaction 내에서 데이터 변경이 이루어져야 함
클래스 레벨에서 쓸 수도 있고 메소드마다 표기해도 됨
*/
@RequiredArgsConstructor
/*
 * lombok에서 지원하는 생성자 주입 어노테이션으로
 * final이나 @NotNull 붙은 필드의 생성자를 자동생성해줌
 * 기존의 의존성주입 타입인 Constructor, Setter, Field의 번거로운 작성법을
 * 해결하고 테스트 코드 작성 용이와 객체 변이 방지의 장점을 가진다.
 */

public class MemberService {	
	
	private final MemberRepository memberRepository;
	
//	@Autowired //생성자 인젝션
//	public MemberService(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}
	
	@Transactional
	//readOnly = false
	public Long join(Member member) {
		//중복 회원 검증
		validateDupicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDupicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미존재하는회원");
		}
	}
	
	//회원 전체 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}

	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}
