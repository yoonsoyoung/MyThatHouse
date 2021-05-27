package com.ssafy.happyhouse.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.MemberDao;
import com.ssafy.happyhouse.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao memberdao;
	@Override
	public int registerMember(Member member) {
		return memberdao.registerMember(member);
	}

	@Override
	public Member login(Member member) {
		if(member.getId() == null || member.getPwd() == null)
			return null;
		return memberdao.login(member);
	}

	@Override
	public Member getMember(String id) {
		// TODO Auto-generated method stub
		return memberdao.getMember(id);
	}

	@Override
	public int modifyMember(Member member) {
		// TODO Auto-generated method stub
		return memberdao.modifyMember(member);
	}

	@Override
	public int deleteMember(String id) {
		// TODO Auto-generated method stub
		return memberdao.deleteMember(id);
	}
	
	@Override
	public Member userInfo(String userid) throws Exception {
		return memberdao.userInfo(userid);
	}

}
