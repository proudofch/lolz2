package com.bit.lolz.boardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.boarddao;

public class KnowHowBoardDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		String strboardnum = request.getParameter("boardnum");
		int boardnum = Integer.parseInt(strboardnum);
		
		boarddao dao = new boarddao();
		int result = dao.deleteBoardContent(boardnum);
		
		String msg = "";
		String url = "";
		
		if(result > 0) {
			msg = "게시글이 삭제되었습니다.";
			url = "KnowHowBoardList.Board";
		} else {
			msg = "삭제 실패";
			url = "KnowHowBoardList.Board"; 
		}
		
		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url", url);
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/redirect.jsp");
		
		return forward;
	}

}
