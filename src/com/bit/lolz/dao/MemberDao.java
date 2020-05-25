package com.bit.lolz.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
​
import com.bit.lolz.dto.MemberDto;
import com.bit.lolz.utils.ConnectionHelper;
import com.bit.lolz.utils.DB_Close;
​
import javafx.scene.chart.Chart;
​
​
public class MemberDao {
	static DataSource ds;
		
	public MemberDto getMemberListByMemberNo(String id) {  //사번으로 사원 찾기
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
​
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
	
	/*
	public List<MemberDto> getMemberListByDeptno(String deptno) { //
		DecimalFormat formatter = new DecimalFormat("###,###");
		Connection conn = ConnectionHelper.getConnection("oracle");		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select Memberno, ename, job, mgr, hiredate, sal, comm, deptno, img from Member where deptno = ?";
		ArrayList<MemberDto> Memberlist = null;
​
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptno);
			rs = pstmt.executeQuery();
			Memberlist = new ArrayList<>();
			
			while(rs.next()) {
				
				MemberDto e = new MemberDto();
				
				e.setMemberNo(rs.getInt("MemberNo"));
				e.setEname(rs.getString("ename"));
				e.setJob(rs.getString("job"));
				e.setMgr(rs.getInt("mgr"));
				e.setHireDate(rs.getString("hiredate"));
				//e.setSal(formatter.format(rs.getInt("sal")));
				e.setSal(rs.getInt("sal"));
				e.setComm(rs.getInt("comm"));
				e.setDeptNo(rs.getInt("deptno"));
				e.setImg(rs.getString("img"));
				Memberlist.add(e);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Memberlist;
		
	}
	*/
​
	public ArrayList<MemberDto> getMemberList() throws SQLException { //전체 사원 목록 조회
		Connection conn = ConnectionHelper.getConnection("oracle"); //객체 얻기
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
​
			DB_Close.close(rs);
			DB_Close.close(pstmt);
			conn.close(); //반환하기
			
		}
		
		return Memberlist;
	}
​
	//멤버 추가
	public int insertMember(String id, String pwd, String email, String bd, String summonerId) {
​
		Connection conn = null;
		int resultrow = 0;
		PreparedStatement pstmt = null;
		
		try {
				conn= ConnectionHelper.getConnection("oracle");//추가
				
				String sql = "insert into Member(id,pwd,email,bd,summonerId) values(?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
				pstmt.setString(3, email);
				pstmt.setString(4, bd);
				pstmt.setString(5, summonerId);
				
				resultrow = pstmt.executeUpdate();
				
		}catch(Exception e) {
			System.out.println("Insert : " + e.getMessage());
		}finally {
			DB_Close.close(pstmt);
			try {
				conn.close(); //받환하기
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultrow;
	}
	
	
	public int updateMember(MemberDto m) { // 멤버 정보 수정하기
		
		Connection conn = null;
		int resultrow = 0;
		PreparedStatement pstmt = null;
		String sql = "update Member set id=?, pwd=?, email=?, bd=?, summonerId=? where id=?";
				
		try {
			
			conn = ConnectionHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPwd());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getBd());
			pstmt.setString(5, m.getSummonerId());
	
			
			resultrow = pstmt.executeUpdate();
			
		} catch (Exception e2) {
​
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
​
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
				conn.close(); //받환하기
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultrow;
	}
​
	//추가함수 (ID 존재 유무 판단 함수)
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
		//System.out.println("isMemberid : " + isMemberid);
		return isMemberNo;
	}
	/*
	차트 추후 수정계획
	public List<Chart> selectAvgSalByJob() { //직종별 평균 급여
		Connection conn =null;//추가
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<Chart> avgSalList = null;
		
		try {
			System.out.println("왜안들어와?");
			conn= ConnectionHelper.getConnection("oracle");
			String sql = "select round(avg(sal),0) avgsal, job from Member group by job";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
 			avgSalList = new ArrayList<Chart>();
			while(rs.next()) {
				Chart chart = new Chart();
				chart.setJob(rs.getString("job"));
				chart.setAvgSal(rs.getInt("avgsal"));
				avgSalList.add(chart);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close(); //반환
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		System.out.println("avgSalList:" + avgSalList);
		return avgSalList;
	}
	
	public List<String> getJobList() { //직종 목록 출력하기
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<String> joblist = null;
		String sql = "select job from Member group by job";
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			joblist = new ArrayList<>();
			
			while(rs.next()) {
				joblist.add(rs.getString("job"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
		
		return joblist;
		
	}*/
	
	
	
}