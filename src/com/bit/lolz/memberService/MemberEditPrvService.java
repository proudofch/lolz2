package com.bit.lolz.memberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.MemberDao;
import com.bit.lolz.dto.MemberDto;

public class MemberEditPrvService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		String memberparam = request.getParameter("id");

		try {
			
			MemberDao dao = new MemberDao();
			MemberDto dto = dao.getMemberListByMemberId(memberparam);
//질문!누구 아는 사람 서영한테 알려주세요. dto에 윗줄이 반환한member객체 갖다주면, 어케 인식하나요?
			 request.setAttribute("memberdata", dto);
			 forward = new ActionForward();
			 forward.setRedirect(false); //forward
			 forward.setPath("/WEB-INF/views/memberedit.jsp");
			 
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;
		
	}
}
