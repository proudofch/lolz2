package com.bit.lolz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.bit.lolz.dto.BoardDto;

public class boarddao {

	static DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//초기자 - DB 연결
	static {
		
		InitialContext ctx;
		
		try {
			ctx = new InitialContext();
			Context envCtx = (Context)ctx.lookup("java:comp/env");
			ds = (DataSource)envCtx.lookup("/jdbc/oracle");
			System.out.println("DataSource 객체 생성 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//원본 글 쓰기
	public int writeOk(BoardDto boarddata) {
		
		int resultRow = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into board"
					   + "(boardnum, id, boardtype, boardtitle, boardcontent, boarddate, boardhit, boardfile, boardref)"
					   + "values(boardseq.nextval, ?, ?, ?, ?, sysdate, 0, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boarddata.getId());
			pstmt.setInt(2, boarddata.getBoardType());
			pstmt.setString(3, boarddata.getBoardTitle());
			pstmt.setString(4, boarddata.getBoardContent());
			pstmt.setString(5, boarddata.getBoardFile());

			int maxBoardRef = getMaxBoardRef(conn);
			int boardRef = maxBoardRef + 1;
			pstmt.setInt(6, boardRef);

			resultRow = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		
		return resultRow;
	}
	
	//답글 정렬에 참고하세요(네이버처럼 정렬하기)
	//select * from multiboard order by ref desc, step desc
	//update multiboard set step=step+1 where refer=? and step >= ?
	//원래 update 칠 때 step+1 해주고 정렬하는 게 뭔가 문제가 있나봄(생각해봅시다)
	
	
	
	
	//최대 boardRef 구하기
	public int getMaxBoardRef(Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int maxBoardRef = 0;

		try {
			String sql = "select nvl(max(boardref), 0) from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				maxBoardRef = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(pstmt != null) {
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace();}
			}
		}
		
		return maxBoardRef;
	}
	
	//최대 boardStep 구하기
	
	//게시물 총 건수
	public int totalBoardCount() {
		
		int totalcount = 0;

		try {
			conn = ds.getConnection();
			String sql = "select count(*) cnt from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalcount = rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(pstmt != null) { try {pstmt.close();} catch (SQLException e) {e.printStackTrace();} }
			if(rs != null) { try {rs.close();} catch (SQLException e) {e.printStackTrace();} }
			if(conn != null) { try {conn.close();} catch (SQLException e) {e.printStackTrace();} }
		}
		
		return totalcount;
	}
	

	//게시물 목록 //정렬 손보기(지금은 답글 중 최신 글이 위로 붙는 상태) //boardtype???
	public List<BoardDto> list(int cp, int ps) {
		
		List<BoardDto> list = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select * from"
					   + "( select rownum rn, boardnum, id, boardtype, boardtitle, boardcontent, boarddate, boardhit, boardfile,"
					   + "boardref, boardstep, boarddepth"
					   + "from ( select * from board order by boardref desc, boardstep asc)"
					   + ") where rn between ? and ?";
			pstmt = conn.prepareStatement(sql);
			
			int start = cp * ps - (ps - 1);
			int end = cp * ps;
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<BoardDto>();
			
			if(rs.next()) {
				do {
					int boardNum = rs.getInt("boardnum");
					String id = rs.getString("id");
					
					
					
				} while(rs.next());
			}
			
			
		} catch (Exception e) {
		}
		
		
		return list;
	}
	
}
