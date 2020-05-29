package com.bit.lolz.ajax;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.boarddao;

@WebServlet("/ReplyDelete")
public class ReplyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyDelete() {
        super();
    }

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int replynum = Integer.parseInt(request.getParameter("replynum"));
		
		//댓글 삭제
		boarddao boarddao = new boarddao();
		boarddao.replyDeleteOk(replynum);
		System.out.println("replyDeleteOk 실행");
		
		
		//다시뿌려보자
    	ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/board/FreeBoardList.jsp");
        System.out.println("replyDeleteOk 실행");
        
		if(forward != null) { 
		RequestDispatcher dis =
		request.getRequestDispatcher(forward.getPath());
		dis.forward(request,response);
		System.out.println("replyDeleteOk 실행");
		}
		 
		
        
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
		
	}

}
