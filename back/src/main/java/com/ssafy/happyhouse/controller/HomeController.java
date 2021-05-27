//package com.ssafy.happyhouse.controller;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.ssafy.happyhouse.model.dto.BaseAddress;
//import com.ssafy.happyhouse.model.dto.BoardDto;
//import com.ssafy.happyhouse.model.dto.HouseDeal;
//import com.ssafy.happyhouse.model.dto.Member;
//import com.ssafy.happyhouse.model.service.BoardService;
//import com.ssafy.happyhouse.model.service.HouseService;
//import com.ssafy.happyhouse.model.service.MemberService;
//
///**
// * Handles requests for the application home page.
// */
//@Controller
//public class HomeController {
//	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//	@Autowired
//	private HouseService houseService;
//	@Autowired
//	private MemberService memberService;
//	@Autowired
//	private BoardService boardService;
//	/**
//	 * Simply selects the home view to render by returning its name.
//	 */
//	@GetMapping("/")
//	public String init(Model model) throws SQLException {
//		List<String> cityList = null;
//		cityList = houseService.getCityList();
//		model.addAttribute("cityList", cityList);
//		System.out.println("인덱스페이지");
//		return "index";
//	}
//	
//	@GetMapping("/contact")
//	public String moveRegister() {
//		return "contact";
//	}
//	
//	@PostMapping("/services")
//	public String moveService(@RequestParam Map<String,String> map, Model model) throws SQLException {
////		System.out.println(map.get("gugun"));
//		System.out.println(map.toString());
//		
//		BaseAddress b = houseService.searchDong(map);
//		List<HouseDeal> list = houseService.searchArea(map);
//		
//		System.out.println(b.getDong());
////		System.out.println(list.get(0).getAptNAme());
//		
//		model.addAttribute("baseAddress", b);
//		model.addAttribute("houseDealList", list);
//		return "service/services";
//	}
//	
//	@GetMapping("/memberInfo")
//	public String movePage(HttpSession session, Model model) {
//		
//		Member member = (Member) session.getAttribute("current");
//		member = memberService.getMember(member.getId());
//		model.addAttribute("memberInfo", member);
//		return "/memberInfo";
//	}
//	
//	@GetMapping("/list")
//	public String list(Model model) {
//		List<BoardDto> board = boardService.getBoardList();
//		model.addAttribute("board", board);
//		return "list";
//	}
//	
//	
//}
