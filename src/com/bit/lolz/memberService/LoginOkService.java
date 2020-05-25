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
	                          
	         if(pwd.equals(member.getPwd())) {
	        	HttpSession session= request.getSession();        		
	        	session.setAttribute("id", id);    	
	         }
	          
	          forward = new ActionForward();
	          forward.setRedirect(false);
	          forward.setPath("index.jsp");
	          
	      }catch (Exception e) {
	         System.out.println(e.getMessage());
	      }
	      return forward;
	   }
}
