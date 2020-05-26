package com.bit.lolz.memberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.MemberDao;

import com.bit.lolz.dto.MemberDto;

public class LoginOkService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	  
	      ActionForward forward = null;
	      
	      try {
	         MemberDao  dao = new  MemberDao ();

	         String id = request.getParameter("id");
	         String pwd = request.getParameter("pwd");
	         
	         MemberDto member = dao.getMember(id);
	      
	         String url = ""; 
	         String msg ="";
	         if(pwd.equals(member.getPwd())) {
	        	HttpSession session= request.getSession();        		
	        	session.setAttribute("id", id); 
	        	msg="로그인 되었습니다.";
	        	url="Index.Lolz";
	        	System.out.println("e");
	         }else if(member.getId()=="null"){
	        	 msg="아이디가 없습니다.";
	        	 url ="Login.Lolz";
	         }
	         else if(!pwd.equals(member.getPwd())) {
		        msg="비밀번호가 틀렸습니다.";
		        url = "Login.Lolz";
		        }
	         request.setAttribute("board_msg", msg);
	         request.setAttribute("board_url", url);
	         
	          forward = new ActionForward();
	          forward.setRedirect(false);
	         // forward.setPath("index.jsp");
	  		forward.setPath("/WEB-INF/views/redirect.jsp");
	      }catch (Exception e) {
	         System.out.println(e.getMessage());
	      }
	      return forward;
	   }
}
