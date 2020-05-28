package com.bit.lolz.memberService;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.MemberDao;
import com.bit.lolz.dao.boarddao;
import com.bit.lolz.dto.BoardDto;
import com.bit.lolz.dto.MemberDto;

public class MemberBoardListService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");


		boarddao dao = new boarddao();
//		int totalBoardCount = dao.totalBoardCount(); //전체 게시물 건수 얻기
		
		
		//List<BoardDto> list = dao.list(currentPage, pageSize, 1);
//		List<BoardDto> memberboardlist = dao.list(1, 3, 1);
		List<BoardDto> memberboardlist = dao.memberboardlist(id);
		request.setAttribute("boardlist", memberboardlist);
//		request.setAttribute("totalboardcount", totalBoardCount);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/memberboardlist.jsp");
		
		return forward;
	
	}
}
