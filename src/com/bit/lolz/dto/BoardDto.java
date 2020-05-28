package com.bit.lolz.dto;

import java.sql.Date;

public class BoardDto {

	private int boardnum;
	private String id;
	private int boardtype;
	private String boardtitle;
	private String boardcontent;
	private Date boarddate;
	private int boardhit;
	private String boardfile;
	private int boardref;
	private int boardstep;
	private int boarddepth;
	private String boardnotice;
	
	public BoardDto() { }
	
	public BoardDto(int boardnum, String id, int boardtype, String boardtitle, String boardcontent,
					Date boarddate, int boardhit, String boardfile, int boardref, int boardstep, int boarddepth) {
		this.boardnum = boardnum;
		this.id = id;
		this.boardtype = boardtype;
		this.boardtitle = boardtitle;
		this.boardcontent = boardcontent;
		this.boarddate = boarddate;
		this.boardhit = boardhit;
		this.boardfile = boardfile;
		this.boardref = boardref;
		this.boardstep = boardstep;
		this.boarddepth = boarddepth;
	}
	
	public BoardDto(int boardnum, String id, int boardtype, String boardtitle, String boardcontent,
			Date boarddate, int boardhit, String boardfile, int boardref, int boardstep, int boarddepth, String boardnotice) {
		this.boardnum = boardnum;
		this.id = id;
		this.boardtype = boardtype;
		this.boardtitle = boardtitle;
		this.boardcontent = boardcontent;
		this.boarddate = boarddate;
		this.boardhit = boardhit;
		this.boardfile = boardfile;
		this.boardref = boardref;
		this.boardstep = boardstep;
		this.boarddepth = boarddepth;
		this.boardnotice = boardnotice;
	}
	
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBoardtype() {
		return boardtype;
	}
	public void setBoardtype(int boardtype) {
		this.boardtype = boardtype;
	}
	public String getBoardtitle() {
		return boardtitle;
	}
	public void setBoardtitle(String boardtitle) {
		this.boardtitle = boardtitle;
	}
	public String getBoardcontent() {
		return boardcontent;
	}
	public void setBoardcontent(String boardcontent) {
		this.boardcontent = boardcontent;
	}
	public Date getBoarddate() {
		return boarddate;
	}
	public void setBoarddate(Date boarddate) {
		this.boarddate = boarddate;
	}
	public int getBoardhit() {
		return boardhit;
	}
	public void setBoardhit(int boardhit) {
		this.boardhit = boardhit;
	}
	public String getBoardfile() {
		return boardfile;
	}
	public void setBoardfile(String boardfile) {
		this.boardfile = boardfile;
	}
	public int getBoardref() {
		return boardref;
	}
	public void setBoardref(int boardref) {
		this.boardref = boardref;
	}
	public int getBoardstep() {
		return boardstep;
	}
	public void setBoardstep(int boardstep) {
		this.boardstep = boardstep;
	}
	public int getBoarddepth() {
		return boarddepth;
	}
	public void setBoarddepth(int boarddepth) {
		this.boarddepth = boarddepth;
	}
	public String getBoardnotice() {
		return boardnotice;
	}
	public void setBoardnotice(String boardnotice) {
		this.boardnotice = boardnotice;
	}
	

}
