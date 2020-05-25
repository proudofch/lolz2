package com.bit.lolz.memberService;
import java.io.Console;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.MemberDao;

public class MemberAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String bd = request.getParameter("bd");
		String summonerId = request.getParameter("summonerId");

		String pwd = request.getParameter("pwd");
   

		
		MemberDao dao = new MemberDao(); //POINT
		int result = dao.insertMember(id,pwd,email, bd, summonerId);
		
		String msg = "";
		String url = "";
		
		if(result > 0) {
			msg = "회원가입이 완료되었습니다.";
			url = "Index.Lolz";
		} else {
			msg = "등록 실패";
			url = "/MemberAdd.Lolz";
		}
		
		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url", url);
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/redirect.jsp");
		
		return forward;
		
	}
}