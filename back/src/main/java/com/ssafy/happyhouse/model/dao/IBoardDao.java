package com.ssafy.happyhouse.model.dao;

import java.util.List;

import com.ssafy.happyhouse.model.dto.BoardDto;

public interface IBoardDao {
	List<BoardDto> selectBoardList ();
	
	int insertBoard(BoardDto boardDto);
	
	int deleteBoard(int id);
	
	int updateBoard(BoardDto boardDto);
	
	BoardDto getBoard(int id);
}
