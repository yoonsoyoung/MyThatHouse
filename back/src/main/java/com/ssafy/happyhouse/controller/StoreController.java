package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.StoreService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	StoreService storeService;
	
	@GetMapping("/{gugun}/{dong}")
	public List<Entry<String,Integer>> in(@PathVariable String gugun, @PathVariable String dong) {
		System.out.println("상권 검색");
		System.out.println(gugun+" "+dong);
		List<Entry<String,Integer>> top10 = storeService.addStore(gugun, dong);
		for(Entry<String, Integer> entry : top10) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		return top10;
	}
}
