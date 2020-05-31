package com.bit.lolz.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.boarddao;
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
		
		String replynum = request.getParameter("replynum");
		int replynum_int = Integer.parseInt(replynum);
		
		boarddao boarddao = new boarddao();
		boarddao.replyDeleteOk(replynum_int);
        
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
		
	}

}
