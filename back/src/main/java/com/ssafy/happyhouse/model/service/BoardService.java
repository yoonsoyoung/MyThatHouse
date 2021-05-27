package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.BoardDto;

public interface BoardService {
	List<BoardDto> getBoardList();
	
	int writeBoard(BoardDto boardDto);
	
	int modifyBoard(BoardDto boardDto);
	
	int removeBoard(int boardId);
	
	BoardDto getBoard(int id);

}
