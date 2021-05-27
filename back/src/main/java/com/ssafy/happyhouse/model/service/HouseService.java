package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.HousePageBean;

public interface HouseService {

	List<HouseDeal> searchAll(HousePageBean Bean);

	HouseDeal serach(int no);

	// 도시 선택
	List<String> getCityList() throws SQLException;

	// 구 선택
	List<String> getGuList(String city) throws SQLException;

	// 동 선택
	List<String> getDongList(String gu) throws SQLException;

	List<HouseDeal> searchArea(Map<String, String> map) throws SQLException;

	BaseAddress searchDong(Map<String, String> map) throws SQLException;

	int setBookMark(HouseDeal houseInfo, String userId) throws SQLException;

	int deleteBookMark(HouseDeal houseInfo, String userId) throws SQLException;
	
	List<HouseDeal> getJjimList(String userId) throws SQLException;
	
	//top10 
	List<HouseInfo> getTop10() throws SQLException;
}
