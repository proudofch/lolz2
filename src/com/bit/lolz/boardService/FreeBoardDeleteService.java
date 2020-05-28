package com.bit.lolz.boardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.boarddao;

public class FreeBoardDeleteService implements Action {

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
			url = "FreeBoardList.Board";
		} else {
			msg = "삭제 실패";
			url = "FreeBoardList.Board"; //목록?? 상세보기로 다시?? 
		}
		
		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url", url);
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/redirect.jsp");
		
		return forward;
	}

}
