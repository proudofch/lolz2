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
		
		List<BoardDto> memberboardlist = dao.memberboardlist(id);
		request.setAttribute("boardlist", memberboardlist);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/memberboardlist.jsp");
		
		return forward;
	
	}
}
