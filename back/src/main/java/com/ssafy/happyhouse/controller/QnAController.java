package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.Answer;
import com.ssafy.happyhouse.model.dto.Question;
import com.ssafy.happyhouse.model.service.QnAService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qna")
public class QnAController {
	
	@Autowired
	private QnAService qnaService;
	
	@GetMapping
	public ResponseEntity<?> getList() throws SQLException{
		List<Question> list = qnaService.selectList();
		if(list != null)
			return new ResponseEntity<List<Question>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	//질문 쓰기
	@PostMapping("/question")
	public ResponseEntity<?> writeQuestion(@RequestBody Question question) throws SQLException{
		int res = qnaService.insertQuestion(question);
		if(res>0)
			return new ResponseEntity<String>("success", HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	//답변 쓰기
	@PostMapping("/answer")
	public ResponseEntity<?> writeAnswer(@RequestBody Answer answer) throws SQLException{
		int res = qnaService.insertAnswer(answer);
		if(res>0)
			return new ResponseEntity<String>("success", HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	//읽기
	@GetMapping("/{no}")
	public ResponseEntity<?> getQuestion(@PathVariable int no) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		map = qnaService.selectQuestion(no);
		if(map != null) 
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	//질문 삭제
	@DeleteMapping("/question/{no}")
	public ResponseEntity<?> deleteQuestion(@PathVariable int no) throws SQLException{
		int res = qnaService.deleteQuestion(no);
		if(res>0)
			return new ResponseEntity<String>("success", HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	//답변 삭제
	@DeleteMapping("/answer/{no}")
	public ResponseEntity<?> deleteAnswer(@PathVariable int no) throws SQLException{
		int res = qnaService.deleteAnswer(no);
		if(res>0)
			return new ResponseEntity<String>("success", HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	//질문 수정
	@PutMapping("/question")
	public ResponseEntity<?> modifyQuestion(@RequestBody Question question) throws SQLException{
		int res = qnaService.modifyQuestion(question);
		if(res>0)
			return new ResponseEntity<String>("success", HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	//답변 수정
	@PutMapping("/answer")
	public ResponseEntity<?> modifyAnswer(@RequestBody Answer answer) throws SQLException{
		System.out.println(answer.getQuestion_no()+" "+answer.getContent());
		int res = qnaService.modifyAnswer(answer);
		if(res>0)
			return new ResponseEntity<String>("success", HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}

}
