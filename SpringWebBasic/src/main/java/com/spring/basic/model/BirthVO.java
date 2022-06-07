package com.spring.basic.model;

public class BirthVO {
	
	private String year;
	private String month;
	private String day;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
//		System.out.println(month.length());
		if (month.length() < 2) {
			this.month = "0"+month;
//			System.out.println(this.month);
		}else {
			this.month = month;
		}
		
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		if (day.length() < 2) {
			this.day = "0"+day;
		}else {
			this.day = day;
		}
	}

}
