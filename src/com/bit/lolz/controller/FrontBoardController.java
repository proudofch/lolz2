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
import com.bit.lolz.boardService.FreeBoardDeleteService;
import com.bit.lolz.boardService.FreeBoardEditOkService;
import com.bit.lolz.boardService.FreeBoardEditService;
import com.bit.lolz.boardService.FreeBoardListService;
import com.bit.lolz.boardService.FreeBoardReadService;
import com.bit.lolz.boardService.FreeBoardRewriteOkService;
import com.bit.lolz.boardService.FreeBoardRewriteService;
import com.bit.lolz.boardService.FreeBoardWriteOkService;

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
    	
    	if(url_Command.equals("/FreeBoardWrite.Board")) { //자유게시판 글쓰기 UI
        	forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/BoardWrite.jsp");
    	} else if(url_Command.equals("/FreeBoardWriteOk.Board")) { //자유게시판 글쓰기 로직 처리
        	action = new FreeBoardWriteOkService();
        	forward = action.execute(request, response);
        
    	} else if(url_Command.equals("/FreeBoardList.Board")) { //자유게시판 목록 보기
    		action = new FreeBoardListService();
    		forward = action.execute(request, response);
    		
    	} else if(url_Command.equals("/FreeBoardRead.Board")) { //자유게시판 글 상세보기
    		action = new FreeBoardReadService();
    		forward = action.execute(request, response);
    		
    	} else if(url_Command.equals("/FreeBoardEdit.Board")) { //자유게시판 글 수정 UI
    		action = new FreeBoardEditService();
    		forward = action.execute(request, response);
    		
    	} else if(url_Command.equals("/FreeBoardEditOk.Board")) { //자유게시판 글 수정 로직 처리
    		action = new FreeBoardEditOkService();
    		forward = action.execute(request, response);
    	
    	} else if(url_Command.equals("/FreeBoardDelete.Board")) { //자유게시판 글 삭제
    		action = new FreeBoardDeleteService();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/FreeBoardRewrite.Board")) { //자유게시판 답글달기
    		action = new FreeBoardRewriteService();
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("/FreeBoardRewriteOk.Board")) { //자유게시판 답글달기 로직 처리
    		action = new FreeBoardRewriteOkService();
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    	
    	

    	
    	
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
