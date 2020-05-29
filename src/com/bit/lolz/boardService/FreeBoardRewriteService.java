package com.bit.lolz.boardService;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.boarddao;
import com.bit.lolz.dto.BoardDto;


public class FreeBoardRewriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

//	
		String strboardnum = request.getParameter("boardnum");//그저 이게 필요할뿐
		if(strboardnum == null || strboardnum.trim().equals("")) {
			try {
				response.sendRedirect("FreeBoardList.Board");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
//		boarddao dao = new boarddao();
//		BoardDto boarddata=dao.boarddata;
		
		System.out.println(strboardnum);
		ActionForward forward = new ActionForward();
		request.setAttribute("boardnum", strboardnum);
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/board/BoardRewrite.jsp");
		
		return forward;
	}

}
