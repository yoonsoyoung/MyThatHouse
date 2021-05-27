package com.ssafy.happyhouse.model.dao;

import com.ssafy.happyhouse.model.dto.Member;

public interface MemberDao {

	// 회원가입
	int registerMember(Member member);	
//	로그인
	Member login(Member member);
	
//	회원정보 정보 얻기
	Member getMember(String id);
	
//	회원정보 수정
	int modifyMember(Member member);
	
//	회원탈퇴
	int deleteMember(String id);
	
	Member userInfo(String id);
}
