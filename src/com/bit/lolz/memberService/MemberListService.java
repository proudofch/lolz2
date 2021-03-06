package com.bit.lolz.memberService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.MemberDao;
import com.bit.lolz.dto.MemberDto;

public class MemberListService implements Action{
@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			MemberDao dao = new MemberDao();
			 List<MemberDto> memberlist = dao.getMemberList();
			 request.setAttribute("Memberlist", memberlist);
			 forward = new ActionForward();
			 forward.setRedirect(false);
			 forward.setPath("/WEB-INF/views/memberlist.jsp");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;
		
	}
}


