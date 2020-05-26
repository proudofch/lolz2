package com.bit.lolz.memberService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.MemberDao;


public class MemberDeleteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");

    	MemberDao dao = new MemberDao();
		int result = dao.deleteMember(id);
				
		 	String msg="";
		 	String url="";
		    if(result > 0){
		    	msg = "그동안 이용해주셔서 감사합니다.";
		    	url = "Index.Lolz";
		    }else{
		    	msg = "탈퇴 실패!";
		    	url = "prvMemberEdit.Lolz?id="+id;
		    }
		    
		    request.setAttribute("board_msg",msg);
		    request.setAttribute("board_url", url);
		    
		
		    ActionForward forward = new ActionForward();
		    forward.setRedirect(false);
		    forward.setPath("/WEB-INF/views/redirect.jsp");
		    
		return forward;
	}

}













