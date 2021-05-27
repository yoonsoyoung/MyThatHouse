package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map.Entry;

public interface StoreService {
	public List<Entry<String,Integer>> addStore(String guCode, String dongCode);
}
