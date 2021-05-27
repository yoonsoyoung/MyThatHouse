package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.service.HouseService;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;

import io.swagger.annotations.ApiOperation;

//http://localhost:9979/happyhouse/swagger-ui.html

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/")
public class HouseController {
	
	public static final Logger logger = LoggerFactory.getLogger(HouseController.class);
	

	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	private HouseService houseService;

	// 시/구/동을 입력받아 해당 데이터를 검색해서 가져오기
	@ApiOperation(value = "선택한 도시의 아파트 정보를 반환한다.", response = Map.class)
	@PostMapping("/search")
	public ResponseEntity<Map<String,Object>> search(@RequestBody Map<String,String> map) throws SQLException {

		String city = map.get("city");
		String gu = map.get("gu");
		String dong = map.get("dong");
		System.out.println(city+" "+gu);
		
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("baseAddress", houseService.searchDong(map));
		resultMap.put("houseDealList",  houseService.searchArea(map));
		
		if(!resultMap.isEmpty()) {
			return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
		} else
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
	}
	
	// 구 리시트 가져오기
	@ApiOperation(value = "입력받은 도시의 구,군 정보를 반환한다.", response = List.class)    
	@GetMapping("/getGu/{city}")
	public ResponseEntity<List<String>> getGu(@PathVariable String city) throws SQLException {
		List<String> guList = new ArrayList<>();
		System.out.println(city);
		guList = houseService.getGuList(city);
		System.out.println("리스트 실행완료");
		if(!guList.isEmpty()) {
			return new ResponseEntity<List<String>>(guList,HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "'구'를 입력받아 '동'정보를 반환한다.", response = List.class)    
	@GetMapping("/getDong/{gu}")
	public ResponseEntity<List<String>> getDong( @PathVariable String gu) throws SQLException {
		List<String> dongList = new ArrayList<>();
		dongList = houseService.getDongList(gu);
		if(!dongList.isEmpty()) {
			return new ResponseEntity<List<String>>(dongList,HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//세션이 랜덤한 숫자를 가져와서 번호를 클라이언트를 세션스토리지나 저장해놨다가 
	
	//pom 다운, 프로퍼티 jwt정보 추가, jwt서비스, 로그인이 되고안되고 컨트롤러 가져갈 것
	
	
	
	//houseinfo에서  구(code), 동(dong) , 아파트명(aptName) 찾기
	//아파트 no를 찾아서 bookmark테이블에넣기, 로그인중인 세션 얻어서 유저 아이디 넣기
	@PostMapping("/jjim/{userid}")
	public ResponseEntity<Map<String,Object>> bookMark(@PathVariable String userid, @RequestBody HouseDeal houseInfo, HttpServletRequest request){
		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				int res = houseService.setBookMark(houseInfo, userid);
				List<HouseDeal> list = new ArrayList<>();
				list = houseService.getJjimList(userid);
				resultMap.put("message", "success");
				resultMap.put("jjimList", list);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", "fail");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@PostMapping("/jjim/delete/{userid}")
	public ResponseEntity<Map<String,Object>> deleteBookMark(@PathVariable String userid, @RequestBody HouseDeal houseInfo, HttpServletRequest request){
		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				할 일 .
				int res = houseService.deleteBookMark(houseInfo, userid);
				List<HouseDeal> list = new ArrayList<>();
				list = houseService.getJjimList(userid);
				resultMap.put("jjimList", list);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", "fail");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	
	@GetMapping("/jjim/{userid}")
	public ResponseEntity<?> getJjimList(@PathVariable String userid) throws SQLException{
		List<HouseDeal> list = new ArrayList<>();
		list = houseService.getJjimList(userid);
		if(list != null)
			return new ResponseEntity<List<HouseDeal>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/top10")
	public ResponseEntity<?> getTop10() throws SQLException{
		List<HouseInfo> list = new ArrayList<>();
		list = houseService.getTop10();
		if(list != null)
			return new ResponseEntity<List<HouseInfo>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
}
