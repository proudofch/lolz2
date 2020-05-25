package com.bit.lolz.dto;


public class MemberDto {

	private String id;
	private String pwd;
	private String email;
	private String bd;
	private String summonerId;
	
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pwd=" + pwd + ", email=" + email + ", bd=" + bd + ", summonerId=" + summonerId
				+ "]";
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	public String getSummonerId() {
		return summonerId;
	}

	public void setSummonerId(String summonerId) {
		this.summonerId = summonerId;
	}

	public MemberDto() {}
	

	
	
	
	
}
