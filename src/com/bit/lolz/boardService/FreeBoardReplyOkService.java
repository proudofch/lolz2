package com.bit.lolz.boardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.boarddao;

public class FreeBoardReplyOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		String strboardnum = request.getParameter("boardnum");
		int boardnum_fk = Integer.parseInt(strboardnum);
		String id = request.getParameter("id");
		String comment = request.getParameter("comment");
		
		boarddao dao = new boarddao();
		int result = dao.replyOk(boardnum_fk, id, comment);
		
		if(result > 0) {
			System.out.println("reply add 성공");
		}
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/board/BoardContent.jsp");
		
		return forward;
	}

}
