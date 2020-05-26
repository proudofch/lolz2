package com.bit.lolz.memberService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;

public class LogoutService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		  ActionForward forward = null;
		  try {
				PrintWriter out = response.getWriter();
				HttpSession session = request.getSession();
				session.invalidate();
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("Index.Lolz");
		  } catch (IOException e) {
				e.printStackTrace();
			}
		
			return forward;
	}

}
