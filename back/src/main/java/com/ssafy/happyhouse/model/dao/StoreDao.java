package com.ssafy.happyhouse.model.dao;

import java.util.List;

import com.ssafy.happyhouse.model.dto.StoreInfo;

public interface StoreDao {
	//상권 정보 입력
	public int insertStore(List<StoreInfo> list);
}
