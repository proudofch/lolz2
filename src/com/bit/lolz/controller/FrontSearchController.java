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

@WebServlet("*.Search")
public class FrontSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontSearchController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	
    	Action action = null;
    	ActionForward forward = null;
    	
    	if(url_Command.equals("/Search.Search")) { //게시판 글쓰기 UI
        	forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/search/searching.jsp");

    	}else if(url_Command.equals("/lolchamp.Search")) { //게시판 글쓰기 UI
        	forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/search/lolchamp.json");

    	} 
    		
//    	else if(url_Command.equals("/FreeBoardWriteOk.Board")) { //게시판 글쓰기 로직 처리
//        	action = new FreeBoardWriteOk();
//        	forward = action.execute(request, response);} 
//    	 	
    	
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