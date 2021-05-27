package com.ssafy.happyhouse.model.dto;

public class HouseDeal {
	String APT_DEAL;
	String APT_RENT;
	String HOUSE_DEAL;
	String HOUSE_RENT;
	int no;
	String dong;
	public String getAPT_DEAL() {
		return APT_DEAL;
	}
	public void setAPT_DEAL(String aPT_DEAL) {
		APT_DEAL = aPT_DEAL;
	}
	public String getAPT_RENT() {
		return APT_RENT;
	}
	public void setAPT_RENT(String aPT_RENT) {
		APT_RENT = aPT_RENT;
	}
	public String getHOUSE_DEAL() {
		return HOUSE_DEAL;
	}
	public void setHOUSE_DEAL(String hOUSE_DEAL) {
		HOUSE_DEAL = hOUSE_DEAL;
	}
	public String getHOUSE_RENT() {
		return HOUSE_RENT;
	}
	public void setHOUSE_RENT(String hOUSE_RENT) {
		HOUSE_RENT = hOUSE_RENT;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getAptNAme() {
		return aptNAme;
	}
	public void setAptNAme(String aptNAme) {
		this.aptNAme = aptNAme;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}
	public int getDealDay() {
		return dealDay;
	}
	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getRentMoney() {
		return rentMoney;
	}
	public void setRentMoney(String rentMoney) {
		this.rentMoney = rentMoney;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	String aptNAme;
	int code;
	String dealAmount;
	int buildYear;
	int dealYear;
	int dealMonth;
	int dealDay;
	double area;
	int floor;
	String lat;
	String type;
	String jibun;
	String lng;
	String rentMoney;
	String img;
	
	
	private String userId;
	private String city;
	private String gugun;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGugun() {
		return gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
