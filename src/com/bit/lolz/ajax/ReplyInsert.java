package com.bit.lolz.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.lolz.dao.boarddao;
import com.bit.lolz.dto.ReplyDto;

import net.sf.json.JSONArray;

@WebServlet("/ReplyInsert")
public class ReplyInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyInsert() {
        super();
    }

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String strboardnum = request.getParameter("boardnum");
		int boardnum_fk = Integer.parseInt(strboardnum);
		
		HttpSession session= request.getSession();        		
    	String id = (String)session.getAttribute("id");
		String comment = request.getParameter("comment");
		
		boarddao dao = new boarddao();
		dao.replyOk(boardnum_fk, id, comment);
		
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
