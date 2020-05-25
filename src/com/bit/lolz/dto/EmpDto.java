package com.bit.lolz.dto;

/*
create table memo(
	id varchar2(15) not null,
	email varchar2(20) not null,
	content varchar2(100)
); 

데이터 row 1건을 담을 수 있는 클래스 (DTO)
 */
public class EmpDto {
	private int empNo;
	private String ename;
	private String job;
	private int mgr;
	private String hireDate;
	private int sal;
	private int comm;
	private int deptNo;
	private String img;
	
	public EmpDto() {}
	
	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Emp [empNo=" + empNo + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", hireDate=" + hireDate
				+ ", sal=" + sal + ", comm=" + comm + ", deptNo=" + deptNo + ", img=" + img + "]";
	}
	
	
	
	
}
