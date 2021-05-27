package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.Member;
import com.ssafy.happyhouse.model.service.BoardService;
// 제발..ㅜㅜ
// 나와라요
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardController {

	
	@Autowired
	private BoardService boardService;
	
	@GetMapping
	public ResponseEntity<?> list() {
		List<BoardDto> board = boardService.getBoardList();
		if(!board.isEmpty()) {
			return new ResponseEntity<List<BoardDto>>(board,HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("NO DATA",HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> detail(@PathVariable int id) {
		BoardDto board = boardService.getBoard(id);
		if(board != null) {
			return new ResponseEntity<BoardDto>(board, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("FAIL",HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping
	public ResponseEntity<String> write(@RequestBody BoardDto board, HttpSession session) {
		
//		Member member = (Member) session.getAttribute("current");		
//		String userId = member.getId();

//		board.setUserId(userId);
		int res = boardService.writeBoard(board);
		if(res>0) {
			return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("FAIL",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	@GetMapping("/{boardid}")
//	public ResponseEntity<?> writeinfo(@PathVariable int boardid) {
//		BoardDto board = boardService.getBoard(boardid);
//		if(board != null) {
//			return new ResponseEntity<BoardDto>(board, HttpStatus.OK);
//		}else {
//			return new ResponseEntity<String>("FAIL",HttpStatus.NO_CONTENT);
//		}
//	}
	
	@PutMapping
	public ResponseEntity<String> modify(@RequestBody BoardDto board, HttpSession session) {
//		Member member = (Member) session.getAttribute("current");
//		
//		String userId = member.getId();
//		board.setUserId(userId);
		int res = boardService.modifyBoard(board);
		if(res>0) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("FAIL",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("/{boardid}")
	public ResponseEntity<String> delete(@PathVariable int boardid) {
		System.out.println("글삭제 아이디:"+boardid);
		int res = boardService.removeBoard(boardid);
		if(res>0) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("FAIL",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
