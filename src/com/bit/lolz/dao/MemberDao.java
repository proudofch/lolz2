package com.bit.lolz.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.bit.lolz.dto.MemberDto;
import com.bit.lolz.utils.ConnectionHelper;
import com.bit.lolz.utils.DB_Close;
import javafx.scene.chart.Chart;
import com.bit.lolz.dto.MemberDto;

public class MemberDao {
	static DataSource ds;
		//멤버 객체 반환
	  public MemberDto getMember(String id) { //로그인시 사용
	         
	         Connection conn = ConnectionHelper.getConnection("oracle");        
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         MemberDto member = null;
	         String sql="select id, pwd,summonerId from member where id = ?";
	         	
	        	 try {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            rs = pstmt.executeQuery();
	            
	            if(rs.next()) {
	               
	            	member = new MemberDto();
	               
	            	
	                
		            member.setId(rs.getString("id"));
		            member.setPwd(rs.getString("pwd"));
		            
	                member.setSummonerId(rs.getString("summonerId"));
	             
	             } else {
	        	 System.out.println("아이디가 없는 경우 등..");
	        	 member = new MemberDto();
	             member.setId("null");
	         }
	         } catch (Exception e) {
	            System.out.println(e.getMessage());
	         } finally {
	            try {
	               DB_Close.close(rs);
	               DB_Close.close(pstmt);
	               conn.close(); //반환하기               
	            } catch (Exception e2) {
	               e2.printStackTrace();
	            }
	         }
	         
	         return member;
	   }
	  
	//멤버 추가
		public int insertMember(String id, String pwd, String email, String bd, String summonerId,String summonerScore ) {
			Connection conn = null;
			int resultrow = 0;
			PreparedStatement pstmt = null;
			
			try {
					conn= ConnectionHelper.getConnection("oracle");//추가
					
					String sql = "insert into Member(id,pwd,email,bd,summonerId,summonerscore) values(?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setString(2, pwd);
					pstmt.setString(3, email);
					pstmt.setString(4, bd);
					pstmt.setString(5, summonerId);
					pstmt.setString(6, summonerScore);
					
					
					
					
					resultrow = pstmt.executeUpdate();
					
			}catch(Exception e) {
				System.out.println("Insert : " + e.getMessage());
		
			}finally {
				DB_Close.close(pstmt);
				try {
					conn.close(); //반환하기
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return resultrow;
		}
		
	
	public MemberDto getMemberListByMemberId(String id) {  //id로 멤버찾기
		Connection conn = null;	
		PreparedStatement pstmt = null;
		String sql = "select id, pwd, email, bd, summonerId from Member where id = ?";
		ResultSet rs = null;
		
		MemberDto Member = null;
		
		try {		
			conn = ConnectionHelper.getConnection("oracle"); //객체 얻기	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				Member = new MemberDto();
				Member.setId(rs.getString("id"));
				Member.setPwd(rs.getString("pwd"));
				Member.setEmail(rs.getString("email"));
				Member.setBd(rs.getString("bd"));
				Member.setSummonerId(rs.getString("summonerId"));
	
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DB_Close.close(rs);
				DB_Close.close(pstmt);
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return Member;
	}
	
	public List<MemberDto> getMemberListById(String id) { //이름으로 사원 찾기
		Connection conn = ConnectionHelper.getConnection("oracle");		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id, pwd, email, bd, summonerId from Member where id like ?";
		ArrayList<MemberDto> Memberlist = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+id+"%"); //여기에 % 붙이고 jquery func keyup으로 바꾸면 한글자씩 해도 검색 될 듯
			rs = pstmt.executeQuery();
			Memberlist = new ArrayList<>();
			
			while(rs.next()) {
				
				MemberDto m = new MemberDto();
				
				m.setId(rs.getString("id"));
				m.setPwd(rs.getString("pwd"));
				m.setEmail(rs.getString("email"));
				m.setBd(rs.getString("bd"));
				m.setSummonerId(rs.getString("summonerId"));
				
				Memberlist.add(m);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return Memberlist;
		
	}
	
	
	public ArrayList<MemberDto> getMemberList() throws SQLException { //전체 회원 목록
		Connection conn = ConnectionHelper.getConnection("oracle"); 
		PreparedStatement pstmt = null;
		String sql="select id, pwd, email, bd, summonerId from Member";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<MemberDto> Memberlist = new ArrayList<>();
		
		try {
			
			while(rs.next()) {
				MemberDto m = new MemberDto();
				m.setId(rs.getString("id"));
				m.setPwd(rs.getString("pwd"));
				m.setEmail(rs.getString("email"));
				m.setBd(rs.getString("bd"));
				m.setSummonerId(rs.getString("summonerId"));
				Memberlist.add(m);
		}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DB_Close.close(rs);
			DB_Close.close(pstmt);
			conn.close(); //반환하기
			
		}
		
		return Memberlist;
	}
	
	
	public int updateMember(MemberDto m) { // 멤버 정보 수정하기
		
		Connection conn = null;
		int resultrow = 0;
		PreparedStatement pstmt = null;
		String sql = "update member set pwd=?, email=?, summonerId=?, summonerScore=? where id=?";
		
				
		try {
			
			conn = ConnectionHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, m.getPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getSummonerId());
			pstmt.setString(4, m.getSummonerScore());
			pstmt.setString(5, m.getId());
	  
			resultrow = pstmt.executeUpdate();
			
		} catch (Exception e2) {
			e2.printStackTrace();
			
		} finally {
			
			try {
				DB_Close.close(pstmt);
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}	
		return resultrow;
	}
	
	public int deleteMember(String id) { //멤버 삭제하기
		Connection conn =null;//추가
		int resultrow=0;
		PreparedStatement pstmt = null;
		try {
				conn= ConnectionHelper.getConnection("oracle");//추가
				String sql = "delete from Member where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				resultrow = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DB_Close.close(pstmt);
			try {
				conn.close(); //반환하기
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultrow;
	}
	
	//가입 시 ID 중복 확인
	public String isCheckByMemberId(String id) {
		Connection conn =null;//추가
		String isMemberNo= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
				conn= ConnectionHelper.getConnection("oracle");//추가
				String sql = "select id from Member where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
						isMemberNo = "false";
						
					//}while(rs.next());
				}else {
						isMemberNo = "true";
				}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			DB_Close.close(rs);
			DB_Close.close(pstmt);
			try {
				conn.close();//반환하기
			} catch (SQLException e) {
				
				e.printStackTrace();
			} 
		}
		return isMemberNo;
	}
	
	
}
