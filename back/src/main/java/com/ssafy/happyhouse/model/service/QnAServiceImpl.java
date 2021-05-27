package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.QnADao;
import com.ssafy.happyhouse.model.dto.Answer;
import com.ssafy.happyhouse.model.dto.Question;

@Service
public class QnAServiceImpl implements QnAService {

	@Autowired
	private QnADao qnaDao;
	
	@Override
	public List<Question> selectList() throws SQLException {
		// TODO Auto-generated method stub
		return qnaDao.selectList();
	}

	@Override
	public int insertQuestion(Question question) throws SQLException {
		// TODO Auto-generated method stub
		return qnaDao.insertQuestion(question);
	}

	@Override
	public int insertAnswer(Answer answer) throws SQLException {
		// TODO Auto-generated method stub
		return qnaDao.insertAnswer(answer);
	}

	@Override
	public Map<String, Object> selectQuestion(int no) throws SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		Answer answer = qnaDao.selectAnswer(no);
		if(answer!=null) map.put("answer", answer);
		
		Question question = qnaDao.selectQuestion(no);
		map.put("question", question);
		return map;
	}
	
//	public Answer selectAnswer(int question_no) throws SQLException{
//		return qnaDao.selectAnswer(question_no);
//	}

	@Override
	public int deleteQuestion(int no) throws SQLException {
		// TODO Auto-generated method stub
		return qnaDao.deleteQuestion(no);
	}

	@Override
	public int deleteAnswer(int no) throws SQLException {
		// TODO Auto-generated method stub
		return qnaDao.deleteAnswer(no);
	}

	@Override
	public int modifyQuestion(Question question) throws SQLException {
		// TODO Auto-generated method stub
		return qnaDao.modifyQuestion(question);
	}

	@Override
	public int modifyAnswer(Answer answer) throws SQLException {
		// TODO Auto-generated method stub
		return qnaDao.modifyAnswer(answer);
	}

}
