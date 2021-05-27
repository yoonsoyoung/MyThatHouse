package com.ssafy.happyhouse.model.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.IBoardDao;
import com.ssafy.happyhouse.model.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private IBoardDao boardDao;

	@Override
	public List<BoardDto> getBoardList() {
		return boardDao.selectBoardList();
	}

	@Override
	public int writeBoard(BoardDto boardDto) {
		return boardDao.insertBoard(boardDto);
	}

	@Override
	public int modifyBoard(BoardDto boardDto) {
		
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		String today = null;
		today = formatter.format(cal.getTime());
		Timestamp ts = Timestamp.valueOf(today);

		boardDto.setWriteDate(ts);
		
		return boardDao.updateBoard(boardDto);
	}

	@Override
	public int removeBoard(int boardId) {
		return boardDao.deleteBoard(boardId);
	}

	@Override
	public BoardDto getBoard(int id) {
		// TODO Auto-generated method stub
		return boardDao.getBoard(id);
	}
	

}
