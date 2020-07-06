package com.java.member.model;

import java.util.Date;

public class MemberDto {
	private String id;
	private String password;
	private String name;
	private String jumin1;
	private String jumin2;
	private String email;
	private String zipcode;
	private String address;
	private String job;
	private String mailing;
	private String interest;
	
	// 사용자가 입력하지 않고 DB에서 로직상 필요한 데이터
	private Date registerDate;  // Date, sysdate 오라클에서 처리
	private int num;			// .nextval 오라클에서 처리
	private String memberLevel;	// 회원등급 : 3개 (BA, AA, VIP) 자바에서 처리
	
	public MemberDto() {};
	
	public MemberDto(String id, String password, String name, String jumin1, String jumin2, String email,
			String zipcode, String address, String job, String mailing, String interest, Date registerDate, int num,
			String memberLevel) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.jumin1 = jumin1;
		this.jumin2 = jumin2;
		this.email = email;
		this.zipcode = zipcode;
		this.address = address;
		this.job = job;
		this.mailing = mailing;
		this.interest = interest;
		this.registerDate = registerDate;
		this.num = num;
		this.memberLevel = memberLevel;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJumin1() {
		return jumin1;
	}
	public void setJumin1(String jumin1) {
		this.jumin1 = jumin1;
	}
	public String getJumin2() {
		return jumin2;
	}
	public void setJumin2(String jumin2) {
		this.jumin2 = jumin2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getMailing() {
		return mailing;
	}
	public void setMailing(String mailing) {
		this.mailing = mailing;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	
	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", password=" + password + ", name=" + name + ", jumin1=" + jumin1 + ", jumin2="
				+ jumin2 + ", email=" + email + ", zipcode=" + zipcode + ", address=" + address + ", job=" + job
				+ ", mailing=" + mailing + ", interest=" + interest + ", registerDate=" + registerDate + ", num=" + num
				+ ", memberLevel=" + memberLevel + "]";
	}
	
}
