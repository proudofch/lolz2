package com.bit.lolz.dto;

public class ReplyDto {
	private int replynum;
	private String id;
	private int boardnum;
	private String replycont;
	private String replydate;
	
	public ReplyDto() {
		
	}
	
	public ReplyDto(int replynum, String id, int boardnum, String replycont, String replydate) {
		this.replynum = replynum;
		this.id = id;
		this.boardnum = boardnum;
		this.replycont = replycont;
		this.replydate = replydate;
	}
	
	public int getReplynum() {
		return replynum;
	}
	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public String getReplycont() {
		return replycont;
	}
	public void setReplycont(String replycont) {
		this.replycont = replycont;
	}
	public String getReplydate() {
		return replydate;
	}
	public void setReplydate(String replydate) {
		this.replydate = replydate;
	}
	
	
	
}
