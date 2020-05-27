package com.bit.lolz.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
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
	public int writeOk(BoardDto boarddata, int boardtype) {
												
		int resultRow = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into board"
					   + "(boardnum, id, boardtype, boardtitle, boardcontent, boarddate, boardhit, boardfile, boardref)"
					   + "values(boardseq.nextval, ?, ?, ?, ?, sysdate, 0, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boarddata.getId());
			pstmt.setInt(2, boardtype);
			pstmt.setString(3, boarddata.getBoardtitle());
			pstmt.setString(4, boarddata.getBoardcontent());
			pstmt.setString(5, boarddata.getBoardfile());

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
	

	//답글 정렬에 참고하세요(네이버처럼 정렬하기)
		//select * from multiboard order by ref desc, step desc
		//update multiboard set step=step+1 where refer=? and step >= ?
		//원래 update 칠 때 step+1 해주고 정렬하는 게 뭔가 문제가 있나봄(생각해봅시다)
		
	
	//게시물 목록 //정렬 손보기(지금은 답글 중 최신 글이 위로 붙는 상태)
	public List<BoardDto> list(int cp, int ps, int type) {
		
		List<BoardDto> list = null;
		
		try {
			conn = ds.getConnection();

			String sql = "SELECT * FROM"
					   + "( SELECT ROWNUM rn, boardnum, id, boardtype, boardtitle, boardcontent, boarddate, boardhit, boardfile,"
					   + " boardref, boardstep, boarddepth"
					   + " FROM ( SELECT * FROM board where boardtype = ? ORDER BY boardref DESC, boardstep ASC )"
					   + ") WHERE rn BETWEEN ? AND ?";
			
			pstmt = conn.prepareStatement(sql);
			
			//페이징 처리 방법이 이것만 있는 건 아님(start, end를 왜 구하는지를 이해한다면 다른 방식으로도 풀 수 있음)
			int start = cp * ps - (ps - 1);
			int end = cp * ps;
			
			pstmt.setInt(1, type);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<BoardDto>();
			
			if(rs.next()) {
				
				do {
					
					int boardnum = rs.getInt("BOARDNUM");
					String id = rs.getString("ID");
					int boardtype = rs.getInt("BOARDTYPE");
					String boardtitle = rs.getString("BOARDTITLE");
					String boardcontent = rs.getString("BOARDCONTENT");
					Date boarddate = rs.getDate("BOARDDATE");
					int boardhit = rs.getInt("BOARDHIT");
					String boardfile = rs.getString("BOARDFILE");
					int boardref = rs.getInt("BOARDREF");
					int boardstep = rs.getInt("BOARDSTEP");
					int boarddepth = rs.getInt("BOARDDEPTH");
					
					BoardDto dto = new BoardDto(boardnum, id, boardtype, boardtitle, boardcontent,
							boarddate, boardhit, boardfile, boardref, boardstep, boarddepth);
					
					list.add(dto);
					
				} while(rs.next());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SQL 오류");
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	//게시글 읽기
	public BoardDto boardRead(int boardnum) {
		
		BoardDto dto = null;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT BOARDNUM, ID, BOARDTYPE, BOARDTITLE, BOARDCONTENT, BOARDDATE, BOARDHIT, NVL(BOARDFILE, 'NONE') BOARDFILE, BOARDREF, BOARDSTEP, BOARDDEPTH"
					   + " FROM BOARD WHERE BOARDNUM = ?"; //FROM 앞에 띄어쓰기 없으면 오류 발생 (***QUERY 작성 시 띄어쓰기 주의***)
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int bnum = rs.getInt("BOARDNUM");
				String id = rs.getString("ID");
				int boardtype = rs.getInt("BOARDTYPE");
				String boardtitle = rs.getString("BOARDTITLE");
				String boardcontent = rs.getString("BOARDCONTENT");
				Date boarddate = rs.getDate("BOARDDATE");
				int boardhit = rs.getInt("BOARDHIT");
				String boardfile = rs.getString("BOARDFILE");
				int boardref = rs.getInt("BOARDREF");
				int boardstep = rs.getInt("BOARDSTEP");
				int boarddepth = rs.getInt("BOARDDEPTH");
				
				dto = new BoardDto(bnum, id, boardtype, boardtitle, boardcontent,
						boarddate, boardhit, boardfile, boardref, boardstep, boarddepth);
			}
			
		} catch (Exception e) {
			System.out.println("boardRead() 문제 발생: "+e.getMessage());
			
		} finally {

			try {
				/* rs.close(); */
				pstmt.close();
				conn.close();
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	
	//게시글 조회수 증가
	public boolean getBoardHit(int boardnum) {

		boolean flag = false; 
		
		try {
			conn = ds.getConnection();
			String sql = "UPDATE BOARD SET BOARDHIT = BOARDHIT + 1 WHERE BOARDNUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			
			int resultrow = pstmt.executeUpdate();
			
			if(resultrow > 0) {
				flag = true;
			} else {
				flag = false;
			}
			
		} catch (Exception e) {
			System.out.println("getBoardHit() 문제 발생: "+e.getMessage());
			
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		
		return flag;
		
	}
	
	
	//게시글 수정 - 수정할 게시글 내용 불러오기
	public BoardDto getEditContent(int boardnum) {
		return this.boardRead(boardnum);
	}
	
	
	//게시글 수정 OK
	public int editOk(BoardDto boarddata) {
		
		int resultRow = 0;
		
		//title, content, file 수정할 수 있음
		try {
			conn = ds.getConnection();
			
			String select_sql = "SELECT BOARDNUM FROM BOARD WHERE BOARDNUM = ?";
			String update_sql = "UPDATE BOARD SET BOARDTITLE = ?, BOARDCONTENT = ?, BOARDFILE = ? WHERE BOARDNUM = ?";
			
			int boardnum = boarddata.getBoardnum();
			
			pstmt = conn.prepareStatement(select_sql);
			pstmt.setInt(1, boardnum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pstmt = conn.prepareStatement(update_sql);
				pstmt.setString(1, boarddata.getBoardtitle());
				pstmt.setString(2, boarddata.getBoardcontent());
				pstmt.setString(3, boarddata.getBoardfile());
				pstmt.setInt(4, boardnum); //이중으로 할 필요 없을 거 같은데...
				
				resultRow = pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("editOk() 문제 발생");
			
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return resultRow;
	}
	
}
