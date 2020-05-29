package com.bit.lolz.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.boarddao;
import com.bit.lolz.dto.BoardDto;
import com.bit.lolz.dto.ReplyDto;

import net.sf.json.JSONArray;

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
		System.out.println("replynum넘어옴: "+replynum);
		
		//댓글 삭제
		boarddao boarddao = new boarddao();
		boarddao.replyDeleteOk(replynum);
		System.out.println("replyDeleteOk 실행");
		
		
		//다시뿌려보자
    	ActionForward forward = null;
    	forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/board/FreeBoardList.jsp");
        
        if(forward != null) {
    		RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    		dis.forward(request, response);
    	} 
    	 
        
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
