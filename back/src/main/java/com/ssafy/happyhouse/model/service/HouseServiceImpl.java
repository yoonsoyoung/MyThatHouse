package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.HouseDao;
import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.HousePageBean;

@Service
public class HouseServiceImpl implements HouseService{
	
	@Autowired
	private HouseDao houseDao;
	
	public List<HouseDeal> searchArea(Map<String,String> map) throws SQLException{
		BaseAddress b = houseDao.selectBaseAddress(map);
		map.put("code", b.getDongcode()+"");
		return houseDao.selectHouseDeal(map);
	}
	
	// 도시 선택
	public List<String> getCityList() throws SQLException{
		return houseDao.selectCity();
	}
	
	//구 선택
	public List<String> getGuList(String city) throws SQLException {
		System.out.println("구리시트 서비스 실행");
		return houseDao.selectGu(city);
	}
	
	//동 선택
	public List<String> getDongList(String gu) throws SQLException {
		return houseDao.selectDong(gu);
	}
	
	
	//houseinfo 구코드, 동(한글), 아파트명, 지번으로  no찾기
	public int setBookMark(HouseDeal houseInfo, String userId) throws SQLException {
		houseInfo.setUserId(userId);
		return houseDao.insertBookMark(houseInfo);
	}
	
	public int deleteBookMark(HouseDeal houseInfo, String userId) throws SQLException{
		houseInfo.setUserId(userId);
		return houseDao.deleteBookMark(houseInfo);
	}
	
	@Override
	public List<HouseDeal> getJjimList(String userId) throws SQLException {
		return houseDao.selectBookMark(userId);
	}
	
	//top10 찾기
	@Override
	public List<HouseInfo> getTop10() throws SQLException {
		return houseDao.selectTop10();
	}
	
	
	
	
	
	@Override
	public List<HouseDeal> searchAll(HousePageBean Bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HouseDeal serach(int no) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseAddress searchDong(Map<String,String> map) throws SQLException {
		return houseDao.selectBaseAddress(map);
	}



}
