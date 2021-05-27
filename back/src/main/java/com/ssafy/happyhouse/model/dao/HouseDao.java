package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;

public interface HouseDao {

	void loadData();
//	List<HouseDeal> searchAll(HousePageBean bean)  throws SQLException ;
	List<HouseDeal> selectHouseDeal(Map<String,String> map) throws SQLException;
	BaseAddress selectBaseAddress(Map<String,String> map) throws SQLException;
//	HouseDeal seach(int no);
	
	List<String> selectDong(String gu) throws SQLException;
	List<String> selectGu(String city) throws SQLException;
	List<String> selectCity() throws SQLException;
	
	//찜한 하우스 no정보를 가져오기
	int selectHouseInfo(HouseInfo houseInfo) throws SQLException;
	
	//테이블에 현재 로그인 유저와 해당 no를 넣기
	int insertBookMark(HouseDeal houseInfo) throws SQLException;
	
	int deleteBookMark(HouseDeal houseInfo) throws SQLException;
	
	List<HouseDeal> selectBookMark(String userId) throws SQLException;
	
	List<HouseInfo> selectTop10() throws SQLException;
	
}
