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
import com.bit.lolz.dto.ReplyDto;
import com.sun.org.apache.regexp.internal.RE;

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
					   + "(boardnum, id, boardtype, boardtitle, boardcontent, boarddate, boardhit, boardfile, boardref, boardnotice)"
					   + "values(boardseq.nextval, ?, ?, ?, ?, sysdate, 0, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boarddata.getId());
			pstmt.setInt(2, boardtype);
			pstmt.setString(3, boarddata.getBoardtitle());
			pstmt.setString(4, boarddata.getBoardcontent());
			pstmt.setString(5, boarddata.getBoardfile());
			pstmt.setString(7, boarddata.getBoardnotice());

			int maxBoardRef = getMaxBoardRef(conn);
			int boardRef = maxBoardRef + 1;
			pstmt.setInt(6, boardRef);

			resultRow = pstmt.executeUpdate();
			System.out.println("resut"+resultRow);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		
		return resultRow;
	}
	
	
	//답글 쓰기
	public int rewriteOk(BoardDto boarddata, int boardtype) {
		
		int resultRow = 0;
		
		try {
			conn = ds.getConnection();
			
			int boardnum = boarddata.getBoardnum(); //추가
			System.out.println("dao단의 boardnum: "+boardnum);
			String id = boarddata.getId();
			int type = boarddata.getBoardtype();
			String Boardtitle = boarddata.getBoardtitle();
			String Boardcontent = boarddata.getBoardcontent();
			String Boardfile = boarddata.getBoardfile();
			String Boardnotice = boarddata.getBoardnotice();

			String refer_depth_step_sql="select boardref , boarddepth , boardstep from board where boardnum=?";
	
String step_update_sql = "select nvl(min(boardstep), 0) step from board where boardref=? and boardstep > ? and boardref <= ?";

			String rewrite_sql = "insert into board"
					+ "(boardnum, id, boardtype, boardtitle, boardcontent, boarddate, boardhit, boardfile, boardref, boardnotice,BOARDDEPTH,BOARDSTEP)"
					+ "values(boardseq.nextval, ?, ?, ?, ?, sysdate, 0, ?, ?, ?,?,?)";
			pstmt = conn.prepareStatement(refer_depth_step_sql);
			pstmt.setInt(1, boardnum);
			rs = pstmt.executeQuery();
	

			
			//내꺼
		if(rs.next()){
			int boardstep = rs.getInt("boardstep");
			int boardrefer = rs.getInt("boardref");
			int boarddepth = rs.getInt("boarddepth");
			
			pstmt = conn.prepareStatement(step_update_sql);
		    pstmt.setInt(1, boardstep);
			pstmt.setInt(2, boardrefer);
			pstmt.setInt(3, boarddepth);
			rs=pstmt.executeQuery();
			  if(rs.next()) {
	               boardstep = rs.getInt("step");
	               if(boardstep == 0) {
	                  String maxStep = "select max(boardstep)+1 maxStep from board where boardref=?";
	                  pstmt = conn.prepareStatement(maxStep);
	                  pstmt.setInt(1, boardrefer); //원본글 ref
	                  rs = pstmt.executeQuery();
	                  if(rs.next()) {
	                	  boardstep = rs.getInt("maxStep");
	                  }
	               } else {
	                  String update_step = "update board set boardstep=boardstep+1 where boardref=? and boardstep >= ? ";
	                  pstmt = conn.prepareStatement(update_step);
	                  pstmt.setInt(1, boardrefer); //원본글 ref
	                  pstmt.setInt(2, boardstep);
	                  pstmt.executeUpdate();
	               }
	            }
	            
				
			pstmt = conn.prepareStatement(rewrite_sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, boardtype);//게시판 바꿀꺼면 이 숫자를 바꾸면 돼요.
			pstmt.setString(3, Boardtitle);
			pstmt.setString(4, Boardcontent);
			pstmt.setString(5, Boardfile);
			pstmt.setString(7, Boardnotice);
			
			pstmt.setInt(8, boarddepth+1);
			pstmt.setInt(9, boardstep+1);
		
			pstmt.setInt(6, boardrefer);

			resultRow = pstmt.executeUpdate();
		}
			
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
	
	
	//게시물 총 건수
	public int totalBoardCount(int boardtype) {
		
		int totalcount = 0;

		try {
			conn = ds.getConnection();
			String sql = "select count(*) cnt from board where boardtype = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardtype);
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
	
	
	//게시물 목록 //정렬 손보기(지금은 답글 중 최신 글이 위로 붙는 상태)
	public List<BoardDto> list(int cp, int ps, int type) {
		
		List<BoardDto> list = null;
		
		try {
			conn = ds.getConnection();

			String sql = "SELECT * FROM"
					   + "( SELECT ROWNUM rn, boardnum, id, boardtype, boardtitle, boardcontent, boarddate, boardhit, boardfile,"
					   + " boardref, boardstep, boarddepth, boardnotice"
					   + " FROM ( SELECT * FROM board where boardtype = ? ORDER BY boardnotice DESC, boardref DESC, boardstep ASC )"
					   + ") WHERE rn BETWEEN ? AND ?";
			
			pstmt = conn.prepareStatement(sql);
			
			//페이징 처리 방법이 이것만 있는 건 아님(start, end를 왜 구하는지를 이해한다면 다른 방식으로도 풀 수 있음)
			int start = cp * ps - (ps - 1);
			int end = cp * ps;
			
			pstmt.setInt(1, type);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			System.out.println("시작긑"+start+end);
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
					String boardnotice = rs.getString("BOARDNOTICE");
					
					BoardDto dto = new BoardDto(boardnum, id, boardtype, boardtitle, boardcontent,
							boarddate, boardhit, boardfile, boardref, boardstep, boarddepth, boardnotice);
					
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
			String sql = "SELECT BOARDNUM, ID, BOARDTYPE, BOARDTITLE, BOARDCONTENT, BOARDDATE, BOARDHIT, NVL(BOARDFILE, 'NONE') BOARDFILE, BOARDREF, BOARDSTEP, BOARDDEPTH, BOARDNOTICE"
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
				String boardnotice = rs.getString("BOARDNOTICE");
				
				dto = new BoardDto(bnum, id, boardtype, boardtitle, boardcontent,
						boarddate, boardhit, boardfile, boardref, boardstep, boarddepth, boardnotice);
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
	//세션값 같으면 안 올라가게 하면 좋을 듯...
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
	public BoardDto getRewriteContent(int boardnum) {
		return this.boardRead(boardnum);
	}
	
	//게시글 수정 OK
	public int editOk(BoardDto boarddata) {
		
		int resultRow = 0;
		
		//title, content, file 수정할 수 있음
		try {
			conn = ds.getConnection();
			
			String select_sql = "SELECT BOARDNUM FROM BOARD WHERE BOARDNUM = ?";
			String update_sql = "UPDATE BOARD SET BOARDTITLE = ?, BOARDCONTENT = ?, BOARDFILE = ?, BOARDNOTICE = ? WHERE BOARDNUM = ?";
			
			int boardnum = boarddata.getBoardnum();
			
			pstmt = conn.prepareStatement(select_sql);
			pstmt.setInt(1, boardnum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pstmt = conn.prepareStatement(update_sql);
				pstmt.setString(1, boarddata.getBoardtitle());
				pstmt.setString(2, boarddata.getBoardcontent());
				pstmt.setString(3, boarddata.getBoardfile());
				pstmt.setString(4, boarddata.getBoardnotice());
				pstmt.setInt(5, boardnum);
				
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
	
	
	//게시글 삭제
	public int deleteBoardContent(int boardnum) {
		
		int resultRow = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "DELETE FROM BOARD WHERE BOARDNUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			
			resultRow = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("delete board content에 문제 발생");
			
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return resultRow;
	}
	
	
	//댓글 달기
	public int replyOk(int boardnum_fk, String id, String comment) {
		
		int resultRow = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "INSERT INTO REPLY(REPLYNUM, ID, BOARDNUM, REPLYCONT, REPLYDATE)"
					   + " VALUES(REPLYSEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, boardnum_fk);
			pstmt.setString(3, comment);
			
			resultRow = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("replyok 문제 발생!");
			
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return resultRow;
	}
	
	
	//댓글 지우기
	public int replyDeleteOk(int replynum) {
			
			int resultRow = 0;
			
			try {
				
				conn = ds.getConnection();
				
				String sql = "delete from reply where replynum = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, replynum);
				
				resultRow = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("replyDeleteok 문제 발생!");
				
			} finally {
				if(pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
				if(conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			
			return resultRow;
	}
	
	//댓글 목록 가져오기
	public List<ReplyDto> getReplyList(int boardnum_fk) {
		
		ArrayList<ReplyDto> replylist = null;
		ReplyDto replydto = null;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM REPLY WHERE BOARDNUM = ? ORDER BY REPLYNUM ASC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardnum_fk);
			
			rs = pstmt.executeQuery();
			
			replylist = new ArrayList<>();
			
			if(rs.next()) {
				do {
					int replynum = rs.getInt("REPLYNUM");
					String id = rs.getString("ID");
					int boardnum = rs.getInt("BOARDNUM");
					String replycont = rs.getString("REPLYCONT");
					String replydate = rs.getString("REPLYDATE");
					
					replydto = new ReplyDto(replynum, id, boardnum, replycont, replydate);
					replylist.add(replydto);
				
				} while (rs.next());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("댓글 목록 가져오기에 문제 발생");
			
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return replylist;
	}
			
	
	//자기가 쓴 글 목록보기
	public List<BoardDto> memberboardlist(String id) {
		
		List<BoardDto> memberboardlist = null;
		
		try {
			conn = ds.getConnection();

			String sql = "SELECT * FROM ( SELECT * FROM board ORDER BY boardref DESC, boardstep ASC ) WHERE ID=?";
			//자유게시판인지,공략게시판인지는 상관없이 출력
			
			pstmt = conn.prepareStatement(sql);
			
		
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			memberboardlist = new ArrayList<BoardDto>();
			
			if(rs.next()) {
				
				do {
					int boardnum = rs.getInt("BOARDNUM");
					id = rs.getString("ID");
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
					
					memberboardlist.add(dto);
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
		return memberboardlist;
	}
}



