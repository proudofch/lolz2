package com.bit.lolz.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.boardService.FreeBoardList;
import com.bit.lolz.boardService.FreeBoardWrite;
import com.bit.lolz.boardService.FreeBoardWriteOk;

@WebServlet("*.Board")
public class FrontBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontBoardController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	
    	Action action = null;
    	ActionForward forward = null;
    	
    	if(url_Command.equals("/FreeBoardWrite.Board")) { //게시판 글쓰기 UI
        	forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/BoardWrite.jsp");

    	} else if(url_Command.equals("/FreeBoardWriteOk.Board")) { //게시판 글쓰기 로직 처리
        	action = new FreeBoardWriteOk();
        	forward = action.execute(request, response);
        
    		
    	} else if(url_Command.equals("/FreeBoardList.Board")) {
    		action = new FreeBoardList();
    		forward = action.execute(request, response);
    	}
    	
    	//else if 붙을 자리
    	//커밋용 수정
    	
    	
    	
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
