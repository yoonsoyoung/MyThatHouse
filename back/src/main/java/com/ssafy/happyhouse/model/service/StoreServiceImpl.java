package com.ssafy.happyhouse.model.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.ssafy.happyhouse.model.dao.StoreDao;
import com.ssafy.happyhouse.model.dto.StoreInfo;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	StoreDao storeDao;
	
	

	@Override
	public List<Entry<String,Integer>> addStore(String guCode, String dongCode) {
		List<Entry<String,Integer>> list = new ArrayList<>();
		Map<String, Integer> hm = new HashMap<>();
		
//		ClassPathResource resource = new ClassPathResource("csv/StoreInfo_Seoul.txt");
		File csv = null;
		try {
			csv = ResourceUtils.getFile("classpath:csv/StoreInfo_Seoul.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		File csv = new File(resource.get);

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csv));
			Charset.forName("UTF-8");
			String line = "";
			
			int cnt = 0;
			while ((line = br.readLine()) != null) {
				String[] token;
				if(cnt++==0) continue;
				
				token = line.split("\\|");
				StoreInfo si = new StoreInfo();
				
				if(token[14].equals(guCode) && token[18].equals(dongCode)) {
					hm.put(token[6], hm.getOrDefault(token[6], 0)+1);
				}
				
				
//				si.setId(Integer.parseInt(token[0]));
//				si.setStoreName(token[1]);
//				si.setLocalName(token[2]);
//				si.setCode1(token[3]);
//				si.setCodeName1(token[4]);
//				si.setCode2(token[5]);
//				si.setCodeName2(token[6]);
//				si.setCode3(token[7]);
//				si.setCodeName3(token[8]);
//				si.setCode4(token[9]);
//				si.setCodeName4(token[10]);
//				si.setCityCode(token[11]);
//				si.setCity(token[12]);
//				si.setGuCode(token[13]);
//				si.setGu(token[14]);
//				si.setDongCode(token[15]);
//				si.setDong(token[16]);
//				si.setDongCode2(token[17]);
//				si.setDong2(token[18]);
//				si.setJibunAddress(token[24]);
//				si.setBuildName(token[30]);
//				si.setAddress(token[31]);
//				si.setOldPostCode(token[32]);
//				si.setPostCode(token[33]);
//				si.setLng(token[37]);
//				si.setLat(token[38]);
//				
//				list.add(si);

			}
			// Map.Entry 리스트 작성
			List<Entry<String, Integer>> list_entries = new ArrayList<Entry<String, Integer>>(hm.entrySet());

			// 비교함수 Comparator를 사용하여 오름차순으로 정렬
			Collections.sort(list_entries, new Comparator<Entry<String, Integer>>() {
				// compare로 값을 비교
				public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2) {
					// 오름 차순 정렬
					return obj2.getValue().compareTo(obj1.getValue());
				}
			});

//			System.out.println("오름 차순 정렬");
//			// 결과 출력
//			for(Entry<String, Integer> entry : list_entries) {
//				System.out.println(entry.getKey() + " : " + entry.getValue());
//			}
			
			for(int i=0; i<10; i++) {
				list.add(list_entries.get(i));
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}

		return list;
	}

}
