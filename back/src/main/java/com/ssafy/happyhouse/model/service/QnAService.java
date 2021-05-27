package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.Answer;
import com.ssafy.happyhouse.model.dto.Question;

public interface QnAService {

	//질문 리스트
	List<Question> selectList() throws SQLException;
	
	//질문 쓰기
	int insertQuestion(Question question) throws SQLException;
	
	//답변 쓰기
	int insertAnswer(Answer answer) throws SQLException;
	
	//질문 읽기(답변있으면 읽기)
	Map<String, Object> selectQuestion(int no) throws SQLException;
	
	//질문 삭제
	int deleteQuestion(int no) throws SQLException;
	
	//답변 삭제
	int  deleteAnswer(int no) throws SQLException;
	
	//질문 수정
	int modifyQuestion(Question question) throws SQLException;
	
	//답변 수정
	int modifyAnswer(Answer answer) throws SQLException;
	
}
